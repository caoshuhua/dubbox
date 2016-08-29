package com.mbv.open.api.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MbResponseResult implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6459855528728378616L;

	/**
	 * 执行成功标识， true-成功  false-失败
	 */
	@JsonProperty("resp_code")
	private String respCode;
	
	/**
	 * 提示信息   ， 执行成功时为空
	 */
	@JsonProperty("resp_info")
	private String respInfo;

	public MbResponseResult() {
	}

	
	
}
