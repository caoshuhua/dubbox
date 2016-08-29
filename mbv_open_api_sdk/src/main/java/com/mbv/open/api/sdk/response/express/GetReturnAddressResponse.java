package com.mbv.open.api.sdk.response.express;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;
import com.mbv.open.api.sdk.response.BaseResponse;

public class GetReturnAddressResponse extends BaseResponse {

    @JsonProperty("vendor_id")
	private String vendorId;                 
    
    @JsonProperty("contact")
	private String contact;                 
    
    @JsonProperty("mobile")
	private String mobile;                 
    
    @JsonProperty("telephone")
	private String telephone;                 
    
    @JsonProperty("province")
	private String province;                 
    
    @JsonProperty("city")
	private String city;                 
    
    @JsonProperty("district")
	private String district;                 
    
    @JsonProperty("address")
	private String address;                 
    
    @JsonProperty("zip")
	private String zip;                 
    

 

  	
	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}                
    
 

  	
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}                
    
 

  	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}                
    
 

  	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
    
 

  	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}                
    
 

  	
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}                
    

}