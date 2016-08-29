package com.mbv.open.api.sdk.bean.product;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class Sku{

 
	
	@JsonProperty("product_id")
	private String productId;                 
    
 
	
	@JsonProperty("sku_id")
	private String skuId;                 
    
 
	
	@JsonProperty("vendor_sku_id")
	private String vendorSkuId;                 
    
 
	
	@JsonProperty("barcode_id")
	private String barcodeId;                 
    
 
	
	@JsonProperty("sale_attr1_key")
	private String saleAttr1Key;                 
    
 
	
	@JsonProperty("sale_attr1_value")
	private String saleAttr1Value;                 
    
 
	
	@JsonProperty("sale_attr2_key")
	private String saleAttr2Key;                 
    
 
	
	@JsonProperty("sale_attr2_value")
	private String saleAttr2Value;                 
    
 
	
	@JsonProperty("status")
	private String status;                 
    

 
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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
    
 
	
	public String getBarcodeId() {
		return barcodeId;
	}

	public void setBarcodeId(String barcodeId) {
		this.barcodeId = barcodeId;
	}                
    
 
	
	public String getSaleAttr1Key() {
		return saleAttr1Key;
	}

	public void setSaleAttr1Key(String saleAttr1Key) {
		this.saleAttr1Key = saleAttr1Key;
	}                
    
 
	
	public String getSaleAttr1Value() {
		return saleAttr1Value;
	}

	public void setSaleAttr1Value(String saleAttr1Value) {
		this.saleAttr1Value = saleAttr1Value;
	}                
    
 
	
	public String getSaleAttr2Key() {
		return saleAttr2Key;
	}

	public void setSaleAttr2Key(String saleAttr2Key) {
		this.saleAttr2Key = saleAttr2Key;
	}                
    
 
	
	public String getSaleAttr2Value() {
		return saleAttr2Value;
	}

	public void setSaleAttr2Value(String saleAttr2Value) {
		this.saleAttr2Value = saleAttr2Value;
	}                
    
 
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}                
    

}