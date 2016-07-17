package com.uwaytech.eCoinMoney.dao;

import com.uwaytech.eCoinMoney.domain.ECoinMoney;
import com.uwaytech.eCoinMoney.domain.ECoinMoneyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ECoinMoneyMapper {
    int countByExample(ECoinMoneyExample example);

    int deleteByExample(ECoinMoneyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ECoinMoney record);

    int insertSelective(ECoinMoney record);

    List<ECoinMoney> selectByExample(ECoinMoneyExample example);

    ECoinMoney selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ECoinMoney record, @Param("example") ECoinMoneyExample example);

    int updateByExample(@Param("record") ECoinMoney record, @Param("example") ECoinMoneyExample example);

    int updateByPrimaryKeySelective(ECoinMoney record);

    int updateByPrimaryKey(ECoinMoney record);
}