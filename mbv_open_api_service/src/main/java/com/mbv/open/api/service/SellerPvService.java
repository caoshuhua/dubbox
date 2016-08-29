package com.mbv.open.api.service;

import java.util.Date;



public interface SellerPvService {

	public Boolean updatePv(String appKey,String userCode,Integer limitRequest,Date now);
	
	public void initDataByDate(Integer date);

}