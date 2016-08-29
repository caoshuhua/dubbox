package com.mbv.open.api.sdk.bean.workorder;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class OosItem{

 
	
	@JsonProperty("sku_id")
	private String skuId;                 
    
 
	
	@JsonProperty("vendor_sku_id")
	private String vendorSkuId;                 
    
 
	
	@JsonProperty("vendor_international_id")
	private String vendorInternationalId;                 
    
 
	
	@JsonProperty("quantity")
	private String quantity;                 
    

 
	
	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}                
    
 
	
	public String getVendorSkuId() {
		return vendorSkuId;
	}

	public void setVendorSkuId(String vendorSkuId) {
		this.vendorSkuId = vendorSkuId;
	}                
    
 
	
	public String getVendorInternationalId() {
		return vendorInternationalId;
	}

	public void setVendorInternationalId(String vendorInternationalId) {
		this.vendorInternationalId = vendorInternationalId;
	}                
    
 
	
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}                
    

}