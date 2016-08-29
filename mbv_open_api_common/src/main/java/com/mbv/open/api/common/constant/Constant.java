package com.mbv.open.api.common.constant;


public class Constant {
	
	public static int unAuthCode=401;
	
	public static String missAppKeyResponse="{\"resp_code\":1,\"resp_info\":\"missing appkey!\"}";
	
	public static String missTokenResponse="{\"resp_code\":2,\"resp_info\":\"missing token!\"}";

	public static String noSellerResponse="{\"resp_code\":3,\"resp_info\":\"this seller don't exist!\"}";
	
	public static String ipUnAuthResponse="{\"resp_code\":4,\"resp_info\":\"this ip unauthed!\"}";
	
	public static String overRequestLimitResponse="{\"resp_code\":5,\"resp_info\":\"over request limit!\"}";
	
	public static String statusErrorResponse="{\"resp_code\":6,\"resp_info\":\"account status error!\"}";
	
	public static int errorCode=500;
	
	public static int successCode=200;
	
	public static String errorResponse="{\"resp_code\":500,\"resp_info\":\"server error!\"}";
	
	public static String sellerCode = "V2015";
	
	public static String userCode = "abc";
	
	public final static String CACHE_BUSINESS_CODE = "open-api";
	public final static String CACHE_TYPE = "seller_security";
	
	public static String updateUserName="open-api";
	
	
}
