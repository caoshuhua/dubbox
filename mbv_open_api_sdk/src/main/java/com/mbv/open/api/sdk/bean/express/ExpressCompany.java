package com.mbv.open.api.sdk.bean.express;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class ExpressCompany{

 
	
	@JsonProperty("express_company_id")
	private String expressCompanyId;                 
    
 
	
	@JsonProperty("express_company_name")
	private String expressCompanyName;                 
    

 
	
	public String getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(String expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}                
    
 
	
	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}                
    

}