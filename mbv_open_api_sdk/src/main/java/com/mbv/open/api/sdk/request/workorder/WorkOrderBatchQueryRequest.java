package com.mbv.open.api.sdk.request.workorder;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.workorder.WorkOrderBatchQueryResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class WorkOrderBatchQueryRequest extends BaseRequest<WorkOrderBatchQueryResponse> {

 
	private String startDate;                 
    
 
	private String endDate;                 
    
 
	private String workorderState;                 
    
 
	private String workorderType;                 
    
 
	private String pageNo;                 
    
 
	private String pageSize;                 
    
 
	private String sortType;                 
    

 

  	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}                
    
 

  	
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}                
    
 

  	
	public String getWorkorderState() {
		return workorderState;
	}

	public void setWorkorderState(String workorderState) {
		this.workorderState = workorderState;
	}                
    
 

  	
	public String getWorkorderType() {
		return workorderType;
	}

	public void setWorkorderType(String workorderType) {
		this.workorderType = workorderType;
	}                
    
 

  	
	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}                
    
 

  	
	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}                
    
 

  	
	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "workorder/mb.workorder.batch.get";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("start_date", this.getStartDate());
	    map.put("end_date", this.getEndDate());
	    map.put("workorder_state", this.getWorkorderState());
	    map.put("workorder_type", this.getWorkorderType());
	    map.put("page_no", this.getPageNo());
	    map.put("page_size", this.getPageSize());
	    map.put("sort_type", this.getSortType());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<WorkOrderBatchQueryResponse> getResponseClass() {
		return WorkOrderBatchQueryResponse.class;
	}

}	