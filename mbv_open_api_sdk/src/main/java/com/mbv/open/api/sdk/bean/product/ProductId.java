package com.mbv.open.api.sdk.bean.product;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class ProductId{

 
	
	@JsonProperty("product_id")
	private String productId;                 
    

 
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}                
    

}