package com.mbv.open.api.sdk.request.express;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.express.GetRegionareaResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class GetRegionareaRequest extends BaseRequest<GetRegionareaResponse> {

 
	private String regionId;                 
    
 
	private String regionLevel;                 
    

 

  	
	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}                
    
 

  	
	public String getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(String regionLevel) {
		this.regionLevel = regionLevel;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "express/mb.logistics.regionarea.get";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("region_id", this.getRegionId());
	    map.put("region_level", this.getRegionLevel());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<GetRegionareaResponse> getResponseClass() {
		return GetRegionareaResponse.class;
	}

}	