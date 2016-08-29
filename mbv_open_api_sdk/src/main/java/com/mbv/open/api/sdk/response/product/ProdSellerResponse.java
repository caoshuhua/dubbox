package com.mbv.open.api.sdk.response.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbv.open.api.sdk.bean.product.Product;
import java.util.List;
import java.lang.Integer;
import java.lang.String;
import com.mbv.open.api.sdk.response.BaseResponse;

public class ProdSellerResponse extends BaseResponse {

    @JsonProperty("products")
	private List<Product> products;                 
    
    @JsonProperty("total_results")
	private Integer totalResults;                 
    
    @JsonProperty("vendor_id")
	private String vendorId;                 
    

 

  	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}                
    
 

  	
	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}                
    
 

  	
	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}                
    

}