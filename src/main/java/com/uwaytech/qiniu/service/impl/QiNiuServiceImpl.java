package com.uwaytech.qiniu.service.impl;

import com.github.pagehelper.PageHelper;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import com.uwaytech.common.exception.ParamErrorException;
import com.uwaytech.cool.common.enums.FileTypeEnum;
import com.uwaytech.cool.common.enums.QiNiuStatusEnum;
import com.uwaytech.cool.common.enums.StatusEnum;
import com.uwaytech.material.dao.MaterialMapper;
import com.uwaytech.material.domain.Material;
import com.uwaytech.material.domain.MaterialExample;
import com.uwaytech.qiniu.bo.QiNiuFileInfo;
import com.uwaytech.qiniu.bo.QiNiuResourceBody;
import com.uwaytech.qiniu.bo.QiNiuResourceItem;
import com.uwaytech.qiniu.service.QiNiuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by moyi on 2016-06-06.
 */
@Service
public class QiNiuServiceImpl implements QiNiuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiNiuServiceImpl.class);
    //七牛token有效时间,单位秒，是否加入配置文件
    private final static long EXPIRES = 3600 * 60;
    private Client client = new Client();
    /**
     *
     */
    @Value("${access.key}")
    private String accessKey;

    @Value("${secret.key}")
    private String secretkey;

    @Value("${bucket.name}")
    private String bucketName;

    @Value("${open.bucket.name}")
    private String openBucketName;

    @Value("${pipe.line}")
    private String pipeline;

    @Value("${callback.url}")
    private String callBackUrl;

    @Value("${preview.line}")
    private String previewLine;

    @Value("${video.regex}")
    private String videoMimeTypeRegex;

    @Value("${document.regex}")
    private String documentMimeTypeRegex;

    @Value("${image.regex}")
    private String imageMimeTypeRegex;

    @Value("${base.url}")
    private String baseUrl;

    private Auth auth = null;
    private OperationManager operationManager = null;

    @Resource
    private MaterialMapper materialMapper;

    @Override
    public Auth getAuth() {
        if (auth == null) {
            auth = Auth.create(accessKey, secretkey);
        }
        return auth;
    }

    public String getUpToken() {
        return getAuth().uploadToken(bucketName, null, EXPIRES, null);
    }

    public String getOpenBucketToken(){
        return getAuth().uploadToken(openBucketName,null,EXPIRES,null);
    }

    @Override
    public String downloadToken(String key) {
        String downloadUrl = baseUrl+"/"+key;
        return getAuth().privateDownloadUrl(downloadUrl);
    }

    private OperationManager getOperationManager() {
        if (operationManager == null) {
            operationManager = new OperationManager(getAuth());
        }
        return operationManager;
    }

    @Override
    public void videoPfops(String key, String transformName, String fops) {
        try {
            if (StringUtils.isEmpty(key)) {
                throw new ParamErrorException("名字名不能为空");
            }
            if (transformName == null) {
                transformName = toMp4Postfix(key);
            }

            StringMap params = new StringMap().putWhen("force", 1, true).putNotEmpty("pipeline", pipeline);
            if (!StringUtils.isEmpty(callBackUrl)) {
                params.putNotEmpty("notifyURL", callBackUrl);
            }
            String urlBase64 = UrlSafeBase64.encodeToString(bucketName + ":" + transformName);
            String pfops = fops + "|saveas/" + urlBase64;
            String persistId = getOperationManager().pfop(bucketName, key, pfops, params);
            //保存素材处理状态
            Material material = new Material();
            material.setPersistId(persistId);
            material.setQiniuStatus(QiNiuStatusEnum.PROCESSING.getStatus());
            MaterialExample example = new MaterialExample();
            example.createCriteria().andQiniuKeyEqualTo(key);
            materialMapper.updateByExampleSelective(material, example);
        } catch (QiniuException e) {
            LOGGER.error("video transform failed!");
        }
    }

    @Override
    public void videoPfops(String key, String transformName) {
        videoPfops(key, transformName, null);
    }

    @Override
    public void videoPfops(String key) {
        videoPfops(key, null, null);
    }

    public void documentPfops(String key, String transformName, String fops) {

        try {
            if (StringUtils.isEmpty(key)) {
                throw new ParamErrorException("名字名不能为空");
            }
            if (fops == null) {
                fops = previewLine + "/v2";
            }
            if (transformName == null) {
                transformName = toPdfPostfix(key);
            }
            StringMap params = new StringMap();
            if (!StringUtils.isEmpty(callBackUrl)) {
                params.putNotEmpty("notifyURL", callBackUrl);
            }
            String urlBase64 = UrlSafeBase64.encodeToString(bucketName + ":" + transformName);
            String pfops = fops + "|saveas/" + urlBase64;
            String persistId = getOperationManager().pfop(bucketName, key, pfops, params);

            //更新素材状态
            Material material = new Material();
            material.setPersistId(persistId);
            material.setQiniuStatus(QiNiuStatusEnum.PROCESSING.getStatus());
            MaterialExample example = new MaterialExample();
            example.createCriteria().andQiniuKeyEqualTo(key);
            materialMapper.updateByExampleSelective(material, example);
        } catch (QiniuException e) {
            LOGGER.error("document transform failed! Message :" + e.getMessage());
        }
    }

    @Override
    public void documentPfops(String key, String transformName) {
        documentPfops(key, transformName, null);
    }

    @Override
    public void documentPfops(String key) {
        documentPfops(key, null, null);
    }

    @Override
    public void documentPfopsJpg(String key, int indexPage, String fops) {
        try {
            if (StringUtils.isEmpty(key)) {
                throw new ParamErrorException("名字名不能为空");
            }
            if (fops == null) {
                fops = previewLine + "/v2/format=jpg/page_number=" + indexPage;
            }
        } catch ( Exception e ) {
            LOGGER.error("document transform failed! Message :" + e.getMessage());
        }
    }

    @Override
    public void execute() {
        //优先处理从七牛获取文件信息，如果处理结束在继续
        queryFileInfoFormQiNiu();

        //处理文件转换
        transform();

        //获取处理文件转换结果
        queryTransform();
    }

    /**
     * 查询素材表中转换结果
     */
    private boolean queryTransform() {
        PageHelper.startPage(0, 100);
        MaterialExample example = new MaterialExample();
        example.createCriteria().andQiniuStatusEqualTo(QiNiuStatusEnum.PROCESSING.getStatus());
        List<Material> list = materialMapper.selectByExample(example);
        for (Material material : list) {
            getPfopsResponse(material.getPersistId());
        }
        return false;
    }

    //查询需要转换的素材列表
    private boolean transform() {
        PageHelper.startPage(0, 100);
        MaterialExample example = new MaterialExample();
        example.createCriteria().andQiniuStatusEqualTo(QiNiuStatusEnum.UNTREATED.getStatus()).andStatusEqualTo(StatusEnum.Valid.getValue());
        List<Material> list = materialMapper.selectByExample(example);
        for (Material material : list) {
            FileTypeEnum type = mimeTypeFilter(material.getMediaType());
            switch (type) {
                case VIDEO_TYPE:
                    break;
                case IMAGE_TYPE:
                    break;
                case DOCUMENT_TYPE: //文档预览处理
                    documentPfops(material.getQiniuKey());
                    break;
                default:
                    LOGGER.info("暂不支持此类文件类型文件转换~", material.getQiniuKey());
                    break;
            }
        }
        return false;
    }

    // 查询需要获取素材信息的素材列表
    private boolean queryFileInfoFormQiNiu() {
        PageHelper.startPage(0, 100);
        MaterialExample example = new MaterialExample();
        example.createCriteria().andQiniuStatusIsNull().andStatusEqualTo(StatusEnum.Valid.getValue());
        List<Material> list = materialMapper.selectByExample(example);
        for (Material material : list) {
            //等于数据不全的数据直接放弃
            if (StringUtils.isEmpty(material.getQiniuKey())) {
                MaterialExample materialExample = new MaterialExample();
                materialExample.createCriteria().andIdEqualTo(material.getId());
                Material material1 = new Material();
                material1.setQiniuStatus(QiNiuStatusEnum.USELESS.getStatus());
                materialMapper.updateByExampleSelective(material1, materialExample);
            }
            getFileInfo(material.getQiniuKey());
        }
        return false;
    }

    private FileTypeEnum mimeTypeFilter(String mimeType) {
        if (mimeType.matches(videoMimeTypeRegex)) {
            return FileTypeEnum.VIDEO_TYPE;
        } else if (mimeType.matches(documentMimeTypeRegex)) {
            return FileTypeEnum.DOCUMENT_TYPE;
        } else if (mimeType.matches(imageMimeTypeRegex)) {
            return FileTypeEnum.IMAGE_TYPE;
        } else {
            return FileTypeEnum.DOCUMENT_TYPE;
        }
    }

    @Override
    public QiNiuResourceBody getPfopsResponse(String id) {
        if (id == null) {
            return null;
        }
        try {
            Response response = client.get("http://api.qiniu.com/status/get/prefop?id=" + id);
            QiNiuResourceBody body = response.jsonToObject(QiNiuResourceBody.class);
            //返回code 为0表示成功, items 里面是具体处理的结果
            if (body.getCode() == 0 && body.getItems().length > 0) {
                QiNiuResourceItem item = body.getItems()[0];
                if (item.getCode() == 0) {
                    Material material = new Material();
                    material.setQiniuStatus(QiNiuStatusEnum.FINISH.getStatus());
                    material.setNormUrl(item.getKey());
                    MaterialExample example = new MaterialExample();
                    example.createCriteria().andPersistIdEqualTo(id);
                    materialMapper.updateByExampleSelective(material, example);
                }
            }
            return body;
        } catch (QiniuException e) {
            LOGGER.error("HTTP GET : http://api.qiniu.com/status/get/prefop?id=<persistentId>  failed! Message:{}", e.getMessage());
        }
        return null;
    }

    @Override
    public void getFileInfo(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        try {
            String urlBase64 = UrlSafeBase64.encodeToString(bucketName + ":" + key);
            String url = "http://rs.qiniu.com/stat/" + urlBase64;
            StringMap map = getAuth().authorization(url);
            Response response = client.get(url, map);
            QiNiuFileInfo fileInfo = response.jsonToObject(QiNiuFileInfo.class);
            if (fileInfo.getError() == null) {
                Material material = new Material();
                material.setQiniuStatus(QiNiuStatusEnum.UNTREATED.getStatus());
                material.setSize(fileInfo.getFsize() + "");
                material.setMediaType(fileInfo.getMimeType());
                updateMaterial(key, material);
                LOGGER.info("获取文件信息并保存成功：", key);
            } else {
                LOGGER.error("获取七牛文件信息失败 : ", fileInfo.toString());
            }
        } catch (QiniuException e) {
            //异常数据不在处理
            LOGGER.error("获取七牛文件信息异常 : ", e);
            Material material = new Material();
            material.setQiniuStatus(QiNiuStatusEnum.USELESS.getStatus());
            updateMaterial(key, material);
        }
    }

    private void updateMaterial(String key, Material material) {
        MaterialExample example = new MaterialExample();
        example.createCriteria().andQiniuKeyEqualTo(key);
        materialMapper.updateByExampleSelective(material, example);
    }

    private String toPdfPostfix(String key) {
        return key.substring(0, key.indexOf(".")) + "_pdf.pdf";
    }

    private String toMp4Postfix(String key) {
        return key.substring(0, key.indexOf(".")) + "_mp4.mp4";
    }

}
