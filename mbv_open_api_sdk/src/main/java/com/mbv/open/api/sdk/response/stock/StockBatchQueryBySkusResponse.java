package com.mbv.open.api.sdk.response.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbv.open.api.sdk.bean.stock.SkuStock;
import java.util.List;
import com.mbv.open.api.sdk.response.BaseResponse;

public class StockBatchQueryBySkusResponse extends BaseResponse {

    @JsonProperty("sku_stocks")
	private List<SkuStock> skuStocks;                 
    

 

  	
	public List<SkuStock> getSkuStocks() {
		return skuStocks;
	}

	public void setSkuStocks(List<SkuStock> skuStocks) {
		this.skuStocks = skuStocks;
	}                
    

}