package com.mbv.open.api.sdk.response.express;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbv.open.api.sdk.bean.express.ExpressCompany;
import java.util.List;
import com.mbv.open.api.sdk.response.BaseResponse;

public class GetExpressCompanyResponse extends BaseResponse {

    @JsonProperty("express_companies")
	private List<ExpressCompany> expressCompanies;                 
    

 

  	
	public List<ExpressCompany> getExpressCompanies() {
		return expressCompanies;
	}

	public void setExpressCompanies(List<ExpressCompany> expressCompanies) {
		this.expressCompanies = expressCompanies;
	}                
    

}