package com.mbv.open.api.sdk.bean.order;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.Long;
import java.lang.String;
import com.mbv.open.api.sdk.bean.order.ItemInfo;
import java.util.List;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class OrderInfo{

 
	
	@JsonProperty("id")
	private Long id;                 
    
 
	
	@JsonProperty("order_id")
	private String orderId;                 
    
 
	
	@JsonProperty("order_create_date")
	private String orderCreateDate;                 
    
 
	
	@JsonProperty("order_state")
	private String orderState;                 
    
 
	
	@JsonProperty("vendor_id")
	private String vendorId;                 
    
 
	
	@JsonProperty("warehouse_id")
	private String warehouseId;                 
    
 
	
	@JsonProperty("total_quantity")
	private int totalQuantity;                 
    
 
	
	@JsonProperty("total_value")
	private double totalValue;                 
    
 
	
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
    
 
	
	@JsonProperty("mb_order_id")
	private String mbOrderId;                 
    
 
	
	@JsonProperty("vendor_remark")
	private String vendorRemark;                 
    
 
	
	@JsonProperty("order_remark")
	private String orderRemark;                 
    
 
	
	@JsonProperty("channel_code")
	private String channelCode;                 
    
 
	
	@JsonProperty("channel_name")
	private String channelName;                 
    
 
	
	@JsonProperty("pay_time")
	private String payTime;                 
    
 
	
	@JsonProperty("is_oos")
	private String isOos;                 
    
 
	
	@JsonProperty("items")
	private List<ItemInfo> items;                 
    

 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}                
    
 
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}                
    
 
	
	public String getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(String orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}                
    
 
	
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}                
    
 
	
	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}                
    
 
	
	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}                
    
 
	
	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}                
    
 
	
	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
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
    
 
	
	public String getMbOrderId() {
		return mbOrderId;
	}

	public void setMbOrderId(String mbOrderId) {
		this.mbOrderId = mbOrderId;
	}                
    
 
	
	public String getVendorRemark() {
		return vendorRemark;
	}

	public void setVendorRemark(String vendorRemark) {
		this.vendorRemark = vendorRemark;
	}                
    
 
	
	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}                
    
 
	
	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}                
    
 
	
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}                
    
 
	
	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}                
    
 
	
	public String getIsOos() {
		return isOos;
	}

	public void setIsOos(String isOos) {
		this.isOos = isOos;
	}                
    
 
	
	public List<ItemInfo> getItems() {
		return items;
	}

	public void setItems(List<ItemInfo> items) {
		this.items = items;
	}                
    

}