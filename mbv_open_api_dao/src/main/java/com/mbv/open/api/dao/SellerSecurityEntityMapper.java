package com.mbv.open.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mbv.open.api.entity.SellerSecurityEntity;

public interface SellerSecurityEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SellerSecurityEntity record);

    int insertSelective(SellerSecurityEntity record);

    SellerSecurityEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SellerSecurityEntity record);

    int updateByPrimaryKey(SellerSecurityEntity record);
    
    List<SellerSecurityEntity> selectByAppKey(String key);
    
    int updateIsLimitById(@Param("id") Integer id,@Param("isLimit") Integer isLimit);
    
    int updateIsLimit(@Param("isLimit") Integer isLimit);
    
}