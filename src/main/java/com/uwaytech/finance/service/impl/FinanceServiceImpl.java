package com.uwaytech.finance.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.common.exception.UnknownException;
import com.uwaytech.common.json.PagingResult;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.cool.common.enums.*;
import com.uwaytech.cusCategory.dao.CusCategoryMapper;
import com.uwaytech.cusCategory.domain.CusCategory;
import com.uwaytech.cusCategory.domain.CusCategoryExample;
import com.uwaytech.downloadLog.dao.DownloadLogMapper;
import com.uwaytech.downloadLog.dao.ExtendDownloadLogMapper;
import com.uwaytech.downloadLog.domain.DownloadLog;
import com.uwaytech.downloadLog.domain.DownloadLogExample;
import com.uwaytech.downloadLog.domain.DownloadLogParam;
import com.uwaytech.finance.ctrl.dto.FinanceConsume;
import com.uwaytech.finance.ctrl.dto.SchoolFinance;
import com.uwaytech.finance.ctrl.dto.SchoolFinanceDto;
import com.uwaytech.finance.ctrl.dto.SupplierFinance;
import com.uwaytech.finance.domain.Finance;
import com.uwaytech.finance.dao.FinanceMapper;
import com.uwaytech.finance.domain.FinanceExample;
import com.uwaytech.finance.domain.FinanceResult;
import com.uwaytech.finance.domain.FinanceSupplier;
import com.uwaytech.finance.service.FinanceService;
import com.uwaytech.financeLog.dao.FinanceLogMapper;
import com.uwaytech.financeLog.domain.FinanceLog;
import com.uwaytech.httpclient.UserCenterSynProxy;
import com.uwaytech.httpclient.bo.SchoolInfo;
import com.uwaytech.id.service.IdGeneratorService;
import com.uwaytech.schoolResource.dao.ExtendResourceMapper;
import com.uwaytech.schoolResource.dao.SchoolResourceMapper;
import com.uwaytech.schoolResource.domain.ResourceMaterial;
import com.uwaytech.schoolResource.domain.SupplierResourceDetail;
import com.uwaytech.schoolResource.domain.SchoolResource;
import com.uwaytech.supplierResource.dao.SupplierResourceMapper;
import com.uwaytech.supplierResource.domain.SupplierResource;
import com.uwaytech.supplierResource.domain.SupplierResourceExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * FinanceServiceImpl
 *
 * @author lyfang
 * @date 2016/6/6
 */
@Service
public class FinanceServiceImpl implements FinanceService {

	@Resource
	private FinanceMapper financeMapper;

	@Resource
	private IdGeneratorService idGeneratorService;

	@Resource
	private SupplierResourceMapper supplierResourceMapper;

	@Resource
	private FinanceLogMapper financeLogMapper;

	@Resource
	private DownloadLogMapper downloadLogMapper;

	@Resource
	private ExtendDownloadLogMapper extendDownloadLogMapper;

	@Resource
	private SchoolResourceMapper schoolResourceMapper;

	@Resource
	private CusCategoryMapper cusCategoryMapper;

	@Resource
	private UserCenterSynProxy userCenterSynProxy;

	@Resource
	private ExtendResourceMapper extendResourceMapper;

	/**
	 * 查询学校账号列表
	 *  @param name    学校名称
	 * @param pageNum     当前页
	 * @param pageSize    每页条数
	 * @param accessToken 用户验证token
	 */
	@Override
	public SchoolFinanceDto querySchoolList(String name, Integer pageNum, Integer pageSize, String accessToken) {
		PagingResult<SchoolInfo> page = userCenterSynProxy.getPageSchoolIds(name, pageNum, pageSize);
		SchoolFinanceDto financeDto = new SchoolFinanceDto();
		if (null != page) {
			//总条数
			Long total = page.getTotal();
			financeDto.setTotal(total);
			//解析学校列表
			financeDto.setRows(getSchoolList(page.getRows()));
			financeDto.setPageSize(pageSize);
			financeDto.setPageNum(pageNum);
		}
		return financeDto;
	}

	/**
	 * 资源下载扣E币，完成交易
	 *
	 * @param resourceId 资源ID
	 * @param schoolId   学校ID
	 * @param userId     用户ID
	 * @param userName   用户名
	 * @param userDept   用户所在部门
	 * @return
	 */
	@Override
	public Integer payResourceDownloadECoin(Long resourceId, Integer schoolId,
	                                        Long userId, String userName, String userDept) {

		//判断资源是否免费
		SupplierResource resource = supplierResourceMapper.selectByPrimaryKey(resourceId);
		if (null != resource) {
			if (null == resource.geteCoin() || resource.geteCoin().longValue() == 0) {
				//该资源免费可以直接下载
				return 1;
			}
		} else {
			throw new UnknownException("资源不存在！");
		}

		//先判断该资源是否已经被当前学校下载过
		DownloadLogExample downloadLogEample = new DownloadLogExample();
		downloadLogEample.createCriteria().andGroupingEqualTo(new Long(schoolId))
				.andResouseIdEqualTo(resourceId);
		List<DownloadLog> downloadLogs = downloadLogMapper.selectByExample(downloadLogEample);
		if (!downloadLogs.isEmpty()) {
			//该资源已经被当前学校下载过，可以直接下载
			return 1;
		}

		//该资源没有被当前学校下载过，判断当前学校是否有充值金额
		FinanceExample financeExample = new FinanceExample();
		financeExample.createCriteria().andGroupingEqualTo(new Long(schoolId));
		List<Finance> finances = financeMapper.selectByExample(financeExample);
		Finance finance = null;
		if (!finances.isEmpty()) {
			//当前学校已经有账号
			finance = finances.get(0);
		} else {
			//当前学校还没有充值
			throw new UnknownException("金额不足，请先充值！");
		}

		//判断当前账号金额是否充足用来下载本资源
		Long eCoin = resource.geteCoin();
		Long eBalance = finance.geteBalance();
		if (null != eBalance && eBalance.longValue() < eCoin.longValue()) {
			//当前学校余额不足下载该资源
			throw new UnknownException("金额不足，请先充值！");
		} else {

			//扣除学校e币，添加学校消费记录
			Long financeLogId = generateSchoolRecord(finance, eCoin, userId, schoolId);

			//添加下载记录
			addDownloadRecord(schoolId, financeLogId, userId, resourceId, userName, userDept);

			//操作供应商账号
			Long supplierId = resource.getSupplierId();
			generateSupplierRecord(supplierId, finance, eCoin, userId);

			//将下载资源插入到学校资源表
			addSchoolResource(resource, schoolId);
		}
		return 1;
	}

	/**
	 * //将下载资源插入到学校资源表
	 *
	 * @param supplierResource 资源实体
	 * @param schoolId         学校ID
	 */
	private void addSchoolResource(SupplierResource supplierResource, Integer schoolId) {
		//查询学校是否已经存在精品资源分类
		CusCategoryExample example = new CusCategoryExample();
		example.createCriteria().andGroupingEqualTo(schoolId.longValue()).
				andTypeEqualTo(CusCategoryType.BUY_TYPE.getValue());
		List<CusCategory> categories = cusCategoryMapper.selectByExample(example);
		CusCategory cusCategory = new CusCategory();
		//学校已经存在精品资源分类
		if (categories.size() > 0) {
			cusCategory = categories.get(0);
		} else {
			//学校不存在精品资源库分类，新建精品资源分类
			cusCategory.setId(idGeneratorService.generatorId(schoolId));
			cusCategory.setName(Constant.ESSENCE);
			cusCategory.setLevel(Constant.LEVEL_ONO);
			cusCategory.setCreateTime(new Date());
			cusCategory.setGrouping(schoolId.longValue());
			cusCategory.setParentId(Constant.PARENTID_ZERO);
			cusCategory.setSort(Constant.AUTO.longValue());
			cusCategory.setType(CusCategoryType.BUY_TYPE.getValue());
			cusCategoryMapper.insert(cusCategory);
		}
		SchoolResource schoolResource = SupplierResourceDetail.toSchoolResource(supplierResource);
		ResourceMaterial resourceMaterial = extendResourceMapper.selectMaterialIdByResourceId(supplierResource.getId());
		schoolResource.setSchoolId(schoolId.longValue());
		long id = idGeneratorService.generatorId(schoolId);
		schoolResource.setId(id);
		//资源正常状态
		schoolResource.setStatus(Integer.valueOf(StatusEnum.Valid.getValue()));
		schoolResource.setCreateTime(new Date());
		schoolResource.setType(Constant.SCHOOL_BUY);
		schoolResource.setCategoryId(cusCategory.getId());
		//添加资源到学校资源库
		schoolResourceMapper.insertSelective(schoolResource);
		//添加资源与素材关联关系
		resourceMaterial.setResourceId(id);
		extendResourceMapper.insertResourceMaterial(resourceMaterial);
	}

	/**
	 * 供应商账号e币更新，添加供应商收入记录
	 *
	 * @param supplierId 供应商ID
	 * @param finance    供应商账号
	 * @param eCoin      收入e币
	 * @param userId     操作用户ID
	 */
	private void generateSupplierRecord(Long supplierId, Finance finance, Long eCoin, Long userId) {
		FinanceExample financeExample = new FinanceExample();
		//查询供应商账号
		financeExample.createCriteria().andGroupingEqualTo(new Long(supplierId));
		List<Finance> finances = financeMapper.selectByExample(financeExample);
		if (!finances.isEmpty()) {
			//更新供应商账号E币
			finance = finances.get(0);
			finance.seteBalance(finance.geteBalance().longValue() + eCoin.longValue());
			financeMapper.updateByPrimaryKeySelective(finance);

			//添加供应商收入记录
			FinanceLog financeLog = new FinanceLog();
			financeLog.setCreateTime(new Date());
			financeLog.setFinanceId(finance.getId());
			financeLog.setDealDate(new Date());
			financeLog.setType(FinanceLogTypeEnum.INCOME.getId());
			financeLog.seteCoin(eCoin);
			financeLog.setPayType(PayTypeEnum.OTHER.getId());
			financeLog.setOrderCode(new Date().getTime() + "");
			financeLog.setBankNumber(new Date().getTime() + "");
			financeLog.setCreateUser(userId);
			financeLogMapper.insert(financeLog);
		} else {
			throw new UnknownException("供应商账号异常！");
		}
	}

	/**
	 * 添加资源下载记录
	 *
	 * @param schoolId     学校ID
	 * @param financeLogId 资源消费记录ID
	 * @param userId       下载用户ID
	 * @param resourceId   资源ID
	 * @param userName     下载用户
	 * @param userDept     下载用户所在部门
	 */
	private void addDownloadRecord(Integer schoolId, Long financeLogId, Long userId, Long resourceId,
	                               String userName, String userDept) {
		DownloadLog downloadLog = new DownloadLog();
		long downloadLogId = idGeneratorService.generatorId(schoolId);
		downloadLog.setId(downloadLogId);
		downloadLog.setCreateTime(new Date());
		downloadLog.setFinanceLogId(financeLogId);
		downloadLog.setGrouping(schoolId.longValue());
		downloadLog.setUserId(userId);
		downloadLog.setResouseId(resourceId);
		downloadLog.setUserName(userName);
		downloadLog.setUserDept(userDept);
		downloadLogMapper.insertSelective(downloadLog);
	}

	/**
	 * 扣除学校e币，添加学校消费记录
	 *
	 * @param finance  学校账号对象
	 * @param eCoin    要扣除的e币
	 * @param userId   操作用户ID
	 * @param schoolId 学校ID
	 */
	private Long generateSchoolRecord(Finance finance, Long eCoin, Long userId, Integer schoolId) {
		//扣除下载资源所需E币
		finance.seteBalance(finance.geteBalance() - eCoin.longValue());
		financeMapper.updateByPrimaryKeySelective(finance);

		//添加学校消费记录
		FinanceLog financeLog = new FinanceLog();
		financeLog.setCreateTime(new Date());
		financeLog.setFinanceId(finance.getId());
		financeLog.setDealDate(new Date());
		financeLog.setType(FinanceLogTypeEnum.CONSUME.getId());
		financeLog.seteCoin(eCoin);
		financeLog.setPayType(PayTypeEnum.OTHER.getId());
		financeLog.setOrderCode(new Date().getTime() + "");
		financeLog.setBankNumber(new Date().getTime() + "");
		financeLog.setCreateUser(userId);
		financeLogMapper.insert(financeLog);
		return financeLog.getId();
	}

	/**
	 * 查询学校账号信息
	 *
	 * @param schoolId 学校ID
	 * @return
	 */
	@Override
	public FinanceResult queryFinanceInfos(Integer schoolId) {
		//查询学校账号余额
		FinanceResult result = new FinanceResult();
		FinanceExample financeExample = new FinanceExample();
		financeExample.createCriteria().andGroupingEqualTo(new Long(schoolId));
		List<Finance> finances = financeMapper.selectByExample(financeExample);
		Finance finance = null;
		if (null != finances && finances.size() > 0) {
			Long eBalance = finances.get(0).geteBalance();
			result.seteBalance(eBalance);
		}

		//查询学校下载消费E币
		DownloadLogParam download = new DownloadLogParam();
		download.setSchoolId(schoolId.longValue());
		Long eCoins = extendDownloadLogMapper.queryDownloadEcoins(download);
		result.seteAmounts(eCoins);

		//查询学校下载量
		Long downloadNum = extendDownloadLogMapper.queryDownloadNum(download);
		result.setDownloadNum(downloadNum);
		return result;
	}

	/**
	 * 查询学校消费统计，按月份统计
	 *
	 * @param schoolId 学校ID
	 * @param pageNum  当前页
	 * @param pageSize 每页条数
	 * @return
	 */
	@Override
	public Page<FinanceConsume> queryFinanceConsumes(Integer schoolId, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<FinanceConsume> page = (Page<FinanceConsume>) extendDownloadLogMapper.queryDownloadMonthList(schoolId);
		return page;
	}

	/**
	 * 查询供应商账号信息
	 *
	 * @param supplierId 供应商ID
	 * @return
	 */
	@Override
	public FinanceSupplier querySupplierFinanceInfos(Long supplierId) {
		//查询供应商账号余额
		FinanceSupplier result = new FinanceSupplier();
		FinanceExample financeExample = new FinanceExample();
		financeExample.createCriteria().andGroupingEqualTo(new Long(supplierId));
		List<Finance> finances = financeMapper.selectByExample(financeExample);
		Finance finance = null;
		if (!finances.isEmpty()) {
			Long eBalance = finances.get(0).geteBalance();
			result.seteBalance(eBalance);
		}

		//查询供应商资源下载次数
		DownloadLogParam download = new DownloadLogParam();
		Long downloadNum = extendDownloadLogMapper.queryDownloadCounts(supplierId);
		result.setDownloadNum(downloadNum);

		//查询供应商资源总数
		SupplierResourceExample exapmle = new SupplierResourceExample();
		exapmle.createCriteria().andSupplierIdEqualTo(supplierId).
				andCheckStatusEqualTo(new Integer(CheckStatusEnum.PASS.getValue()));
		Integer counts = supplierResourceMapper.countByExample(exapmle);
		result.setCounts(counts.longValue());
		return result;
	}

	/**
	 * 查询供应商下载明细
	 *
	 * @param supplierId   供应商ID
	 * @param pageNum      当前页
	 * @param pageSize     每页条数
	 * @param resourceName 资源名称
	 * @return 返回供应商资源下载明细列表
	 */
	@Override
	public Page<SupplierFinance> querySupplierDownloads(Long supplierId, Integer pageNum, Integer pageSize,
	                                                    String resourceName, Date startTime, Date endTime) {
		DownloadLogParam param = new DownloadLogParam();
		param.setSupplierId(supplierId);
		param.setKeyword(resourceName);
		param.setStartTime(startTime);
		param.setEndTime(endTime);
		PageHelper.startPage(pageNum, pageSize);
		Page<SupplierFinance> page = (Page<SupplierFinance>) extendDownloadLogMapper.findSupplierResDownload(param);
		return page;
	}

	@Override
	public Long querySupplierDownloadEcoins(Long supplierId) {
		DownloadLogParam param = new DownloadLogParam();
		param.setSupplierId(supplierId);
		Long ecoins = extendDownloadLogMapper.querySupplierDownloadEcoins(param);
		return ecoins;
	}

	/**
	 * 解析json数据封装到实体对象
	 *
	 * @param schools uc返回学校列表对象
	 * @return 返回学校账号列表
	 */
	private List<SchoolFinance> getSchoolList(List<SchoolInfo> schools) {
		List<SchoolFinance> list = new ArrayList<SchoolFinance>();
		if (null != schools && schools.size() > 0) {
			for (SchoolInfo school : schools) {
				SchoolFinance schoolFinance = new SchoolFinance();
				schoolFinance.setSchoolId(Long.parseLong(school.getSchoolId()));
				schoolFinance.setName(school.getName());
				//查询学校账号信息
				FinanceExample example = new FinanceExample();
				example.createCriteria().andGroupingEqualTo(schoolFinance.getSchoolId());
				List<Finance> finances = financeMapper.selectByExample(example);
				if (!finances.isEmpty()) {
					Finance finance = finances.get(0);
					schoolFinance.seteAmounts(finance.geteAmounts());
					schoolFinance.seteBalance(finance.geteBalance());
					schoolFinance.setRmbAmounts(finance.getRmbAmounts());
					if (null != finance.geteBalance() && null != finance.geteAmounts()) {
						schoolFinance.setAvailEcoin(finance.geteAmounts() - finance.geteBalance());
					}
				}
				list.add(schoolFinance);
			}
		}
		return list;
	}
}
