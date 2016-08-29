package com.mbv.open.api.sdk.bean.order;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;
import com.mbv.open.api.sdk.bean.order.ItemInfo;
import java.util.List;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class OrderRequest{

 
	
	@JsonProperty("order_id")
	private String orderId;                 
    
 
	
	@JsonProperty("order_state")
	private String orderState;                 
    
 
	
	@JsonProperty("consignee")
	private String consignee;                 
    
 
	
	@JsonProperty("moblie")
	private String moblie;                 
    
 
	
	@JsonProperty("country")
	private String country;                 
    
 
	
	@JsonProperty("province")
	private String province;                 
    
 
	
	@JsonProperty("city")
	private String city;                 
    
 
	
	@JsonProperty("district")
	private String district;                 
    
 
	
	@JsonProperty("deliv_address")
	private String delivAddress;                 
    
 
	
	@JsonProperty("deliv_pstd")
	private String delivPstd;                 
    
 
	
	@JsonProperty("tracking_number")
	private String trackingNumber;                 
    
 
	
	@JsonProperty("express_company_id")
	private String expressCompanyId;                 
    
 
	
	@JsonProperty("items")
	private List<ItemInfo> items;                 
    

 
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}                
    
 
	
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}                
    
 
	
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}                
    
 
	
	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}                
    
 
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}                
    
 
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}                
    
 
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}                
    
 
	
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}                
    
 
	
	public String getDelivAddress() {
		return delivAddress;
	}

	public void setDelivAddress(String delivAddress) {
		this.delivAddress = delivAddress;
	}                
    
 
	
	public String getDelivPstd() {
		return delivPstd;
	}

	public void setDelivPstd(String delivPstd) {
		this.delivPstd = delivPstd;
	}                
    
 
	
	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}                
    
 
	
	public String getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(String expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}                
    
 
	
	public List<ItemInfo> getItems() {
		return items;
	}

	public void setItems(List<ItemInfo> items) {
		this.items = items;
	}                
    

}