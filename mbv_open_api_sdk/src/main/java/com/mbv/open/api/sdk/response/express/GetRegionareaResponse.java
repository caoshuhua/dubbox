package com.mbv.open.api.sdk.response.express;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbv.open.api.sdk.bean.express.Region;
import java.util.List;
import com.mbv.open.api.sdk.response.BaseResponse;

public class GetRegionareaResponse extends BaseResponse {

    @JsonProperty("regions")
	private List<Region> regions;                 
    

 

  	
	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}                
    

}