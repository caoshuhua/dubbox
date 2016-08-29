package com.mbv.open.api.dao;

import org.apache.ibatis.annotations.Param;

import com.mbv.open.api.entity.SellerPv;

public interface SellerPvMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SellerPv record);

    int insertSelective(SellerPv record);
    
    int insertTodayData(SellerPv record);

    SellerPv selectByPrimaryKey(Integer id);
    
    SellerPv selectByAppKeyAndDate(@Param("appKey")String appKey,@Param("date")Integer date);

    int updateByPrimaryKeySelective(SellerPv record);

    int updateByPrimaryKey(SellerPv record);
    
    int updateByAppKeyAndDate(SellerPv record);
    
}