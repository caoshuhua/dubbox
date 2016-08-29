package com.mbv.open.api.sdk.response.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbv.open.api.sdk.bean.product.Product;
import com.mbv.open.api.sdk.response.BaseResponse;

public class ProdOneResponse extends BaseResponse {

    @JsonProperty("product")
	private Product product;                 
    

 

  	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}                
    

}