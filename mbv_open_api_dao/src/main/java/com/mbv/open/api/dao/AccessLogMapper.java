package com.mbv.open.api.dao;

import com.mbv.open.api.entity.AccessLog;

public interface AccessLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccessLog record);

    int insertSelective(AccessLog record);

    AccessLog selectByPrimaryKey(Integer id,String table);

    int updateByPrimaryKeySelective(AccessLog record);

    int updateByPrimaryKey(AccessLog record);
}