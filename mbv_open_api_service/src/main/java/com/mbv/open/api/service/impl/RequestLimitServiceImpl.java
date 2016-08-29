package com.mbv.open.api.service.impl;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbv.open.api.common.util.DateUtils;
import com.mbv.open.api.service.RequestLimitService;
import com.mbv.open.api.service.SellerPvService;
import com.mbv.open.api.service.SellerSecurityService;

@Service("requestLimitService")
public class RequestLimitServiceImpl implements RequestLimitService{
	
	@Autowired
	private SellerPvService sellerPvService;
	
	@Autowired
	private SellerSecurityService sellerSecurityService;
	
	public void initTodayData(){
		Integer date=Integer.parseInt(DateUtils.getDate(new Date()));
		sellerPvService.initDataByDate(date);
		
		sellerSecurityService.updateIsLimit(0);
	}

}
