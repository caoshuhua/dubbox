package com.mbv.open.api.sdk.bean.order;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class ItemInfo{

 
	
	@JsonProperty("order_id")
	private String orderId;                 
    
 
	
	@JsonProperty("sku_id")
	private String skuId;                 
    
 
	
	@JsonProperty("vendor_sku_id")
	private String vendorSkuId;                 
    
 
	
	@JsonProperty("vendor_international_id")
	private String vendorInternationalId;                 
    
 
	
	@JsonProperty("quantity")
	private int quantity;                 
    
 
	
	@JsonProperty("unit_price")
	private double unitPrice;                 
    
 
	
	@JsonProperty("remark")
	private String remark;                 
    

 
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}                
    
 
	
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
    
 
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}                
    
 
	
	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}                
    
 
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}                
    

}