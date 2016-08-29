package com.mbv.open.api.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbv.open.api.common.constant.Constant;
import com.mbv.open.api.common.util.DateUtils;
import com.mbv.open.api.dao.AccessLogMapper;
import com.mbv.open.api.entity.AccessLog;
import com.mbv.open.api.service.AccessLogService;

@Service
public class AccessLogServiceImpl implements AccessLogService {
	
	@Autowired
	private AccessLogMapper accessLogMapper;
	
	@Override
	public void log(AccessLog accessLog){
		Date date=accessLog.getRequestTime();
		accessLog.setCreateDate(date);
		accessLog.setLastModifiedDate(date);
		accessLog.setCreateUser(Constant.updateUserName);
		accessLog.setLastModifiedUser(Constant.updateUserName);
		accessLog.setTable(DateUtils.getDate(date));
        accessLogMapper.insert(accessLog);
	}

}
