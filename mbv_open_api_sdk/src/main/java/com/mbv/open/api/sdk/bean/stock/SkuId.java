package com.mbv.open.api.sdk.bean.stock;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class SkuId{

 
	
	@JsonProperty("sku_id")
	private String skuId;                 
    

 
	
	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}                
    

}