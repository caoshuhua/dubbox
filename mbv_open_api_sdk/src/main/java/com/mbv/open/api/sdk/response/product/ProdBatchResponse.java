package com.mbv.open.api.sdk.response.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbv.open.api.sdk.bean.product.Product;
import java.util.List;
import com.mbv.open.api.sdk.response.BaseResponse;

public class ProdBatchResponse extends BaseResponse {

    @JsonProperty("products")
	private List<Product> products;                 
    

 

  	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}                
    

}