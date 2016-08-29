package com.mbv.open.api.sdk.response.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbv.open.api.sdk.bean.stock.SkuStock;
import com.mbv.open.api.sdk.response.BaseResponse;

public class StockSingleQueryBySkuResponse extends BaseResponse {

    @JsonProperty("sku_stock")
	private SkuStock skuStock;                 
    

 

  	
	public SkuStock getSkuStock() {
		return skuStock;
	}

	public void setSkuStock(SkuStock skuStock) {
		this.skuStock = skuStock;
	}                
    

}