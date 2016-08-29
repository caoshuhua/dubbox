package com.mbv.open.api.sdk.bean.express;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.String;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class Region{

 
	
	@JsonProperty("region_id")
	private String regionId;                 
    
 
	
	@JsonProperty("region_name")
	private String regionName;                 
    
 
	
	@JsonProperty("region_level")
	private String regionLevel;                 
    
 
	
	@JsonProperty("parent_id")
	private String parentId;                 
    

 
	
	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}                
    
 
	
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}                
    
 
	
	public String getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(String regionLevel) {
		this.regionLevel = regionLevel;
	}                
    
 
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}                
    

}