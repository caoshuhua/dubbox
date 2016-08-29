package com.mbv.open.api.sdk.bean.stock;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class SkuStock{

 
	
	@JsonProperty("sku_id")
	private String skuId;                 
    
 
	
	@JsonProperty("vendor_sku_id")
	private String vendorSkuId;                 
    
 
	
	@JsonProperty("vendor_international_id")
	private String vendorInternationalId;                 
    
 
	
	@JsonProperty("available_stock")
	private String availableStock;                 
    
 
	
	@JsonProperty("actual_stock")
	private String actualStock;                 
    
 
	
	@JsonProperty("committed_stock")
	private String committedStock;                 
    
 
	
	@JsonProperty("locked_stock")
	private String lockedStock;                 
    

 
	
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
    
 
	
	public String getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(String availableStock) {
		this.availableStock = availableStock;
	}                
    
 
	
	public String getActualStock() {
		return actualStock;
	}

	public void setActualStock(String actualStock) {
		this.actualStock = actualStock;
	}                
    
 
	
	public String getCommittedStock() {
		return committedStock;
	}

	public void setCommittedStock(String committedStock) {
		this.committedStock = committedStock;
	}                
    
 
	
	public String getLockedStock() {
		return lockedStock;
	}

	public void setLockedStock(String lockedStock) {
		this.lockedStock = lockedStock;
	}                
    

}