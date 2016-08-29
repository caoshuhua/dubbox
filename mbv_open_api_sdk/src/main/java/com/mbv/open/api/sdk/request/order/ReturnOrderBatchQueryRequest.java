package com.mbv.open.api.sdk.request.order;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.order.ReturnOrderBatchQueryResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class ReturnOrderBatchQueryRequest extends BaseRequest<ReturnOrderBatchQueryResponse> {

 
	private String startDate;                 
    
 
	private String endDate;                 
    
 
	private String returnorderState;                 
    
 
	private String pageNo;                 
    
 
	private String pageSize;                 
    

 

  	
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
    
 

  	
	public String getReturnorderState() {
		return returnorderState;
	}

	public void setReturnorderState(String returnorderState) {
		this.returnorderState = returnorderState;
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
    
	
	@Override
	public String getApiMethod() {
		return "returnorder/mb.returnorder.batch.get";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("start_date", this.getStartDate());
	    map.put("end_date", this.getEndDate());
	    map.put("returnorder_state", this.getReturnorderState());
	    map.put("page_no", this.getPageNo());
	    map.put("page_size", this.getPageSize());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<ReturnOrderBatchQueryResponse> getResponseClass() {
		return ReturnOrderBatchQueryResponse.class;
	}

}	