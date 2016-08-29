package com.mbv.open.api.sdk.bean.order;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class ReturnSku{

 
	
	@JsonProperty("sku_id")
	private String skuId;                 
    
 
	
	@JsonProperty("vendor_sku_id")
	private String vendorSkuId;                 
    
 
	
	@JsonProperty("vendor_international_id")
	private String vendorInternationalId;                 
    
 
	
	@JsonProperty("product_name")
	private String productName;                 
    
 
	
	@JsonProperty("value")
	private String value;                 
    
 
	
	@JsonProperty("quantity")
	private String quantity;                 
    
 
	
	@JsonProperty("sale_attr1_value")
	private String saleAttr1Value;                 
    
 
	
	@JsonProperty("sale_attr2_value")
	private String saleAttr2Value;                 
    
 
	
	@JsonProperty("is_received")
	private String isReceived;                 
    

 
	
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
    
 
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}                
    
 
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}                
    
 
	
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}                
    
 
	
	public String getSaleAttr1Value() {
		return saleAttr1Value;
	}

	public void setSaleAttr1Value(String saleAttr1Value) {
		this.saleAttr1Value = saleAttr1Value;
	}                
    
 
	
	public String getSaleAttr2Value() {
		return saleAttr2Value;
	}

	public void setSaleAttr2Value(String saleAttr2Value) {
		this.saleAttr2Value = saleAttr2Value;
	}                
    
 
	
	public String getIsReceived() {
		return isReceived;
	}

	public void setIsReceived(String isReceived) {
		this.isReceived = isReceived;
	}                
    

}