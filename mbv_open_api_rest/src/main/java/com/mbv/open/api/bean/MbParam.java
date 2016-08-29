package com.mbv.open.api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
 import com.mbv.open.api.domain.stock.SkuId;

public class MbParam {
	
	@JsonProperty("param_value")
    private String paramValue;
	
	@JsonProperty("sku_id")
	private SkuId skuId;

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}
