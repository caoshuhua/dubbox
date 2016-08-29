package com.mbv.open.api.sdk.request.express;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.express.AddReturnAddressResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class AddReturnAddressRequest extends BaseRequest<AddReturnAddressResponse> {

 
	private String contact;                 
    
 
	private String mobile;                 
    
 
	private String telephone;                 
    
 
	private String province;                 
    
 
	private String city;                 
    
 
	private String district;                 
    
 
	private String address;                 
    
 
	private String zip;                 
    

 

  	
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
    
	
	@Override
	public String getApiMethod() {
		return "express/mb.logistics.returnaddress.add";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("contact", this.getContact());
	    map.put("mobile", this.getMobile());
	    map.put("telephone", this.getTelephone());
	    map.put("province", this.getProvince());
	    map.put("city", this.getCity());
	    map.put("district", this.getDistrict());
	    map.put("address", this.getAddress());
	    map.put("zip", this.getZip());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<AddReturnAddressResponse> getResponseClass() {
		return AddReturnAddressResponse.class;
	}

}	