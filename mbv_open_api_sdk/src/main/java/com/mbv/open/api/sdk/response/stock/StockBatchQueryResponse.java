package com.mbv.open.api.sdk.response.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;
import com.mbv.open.api.sdk.bean.stock.SkuStock;
import java.util.List;
import com.mbv.open.api.sdk.response.BaseResponse;

public class StockBatchQueryResponse extends BaseResponse {

    @JsonProperty("total_results")
	private int totalResults;                 
    
    @JsonProperty("vendor_id")
	private String vendorId;                 
    
    @JsonProperty("warehouse_id")
	private String warehouseId;                 
    
    @JsonProperty("sku_stocks")
	private List<SkuStock> skuStocks;                 
    

 

  	
	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}                
    
 

  	
	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}                
    
 

  	
	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}                
    
 

  	
	public List<SkuStock> getSkuStocks() {
		return skuStocks;
	}

	public void setSkuStocks(List<SkuStock> skuStocks) {
		this.skuStocks = skuStocks;
	}                
    

}