package com.mbv.open.api.sdk.bean.product;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;
import com.mbv.open.api.sdk.bean.product.Sku;
import java.util.List;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class Product{

 
	
	@JsonProperty("product_id")
	private String productId;                 
    
 
	
	@JsonProperty("vendor_product_id")
	private String vendorProductId;                 
    
 
	
	@JsonProperty("product_name")
	private String productName;                 
    
 
	
	@JsonProperty("category_id")
	private String categoryId;                 
    
 
	
	@JsonProperty("category_name")
	private String categoryName;                 
    
 
	
	@JsonProperty("brand_id")
	private String brandId;                 
    
 
	
	@JsonProperty("brand_name")
	private String brandName;                 
    
 
	
	@JsonProperty("product_brief")
	private String productBrief;                 
    
 
	
	@JsonProperty("product_remark")
	private String productRemark;                 
    
 
	
	@JsonProperty("sell_point")
	private String sellPoint;                 
    
 
	
	@JsonProperty("market_price")
	private String marketPrice;                 
    
 
	
	@JsonProperty("status")
	private String status;                 
    
 
	
	@JsonProperty("skus")
	private List<Sku> skus;                 
    

 
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}                
    
 
	
	public String getVendorProductId() {
		return vendorProductId;
	}

	public void setVendorProductId(String vendorProductId) {
		this.vendorProductId = vendorProductId;
	}                
    
 
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}                
    
 
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}                
    
 
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}                
    
 
	
	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}                
    
 
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}                
    
 
	
	public String getProductBrief() {
		return productBrief;
	}

	public void setProductBrief(String productBrief) {
		this.productBrief = productBrief;
	}                
    
 
	
	public String getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}                
    
 
	
	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}                
    
 
	
	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}                
    
 
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}                
    
 
	
	public List<Sku> getSkus() {
		return skus;
	}

	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}                
    

}