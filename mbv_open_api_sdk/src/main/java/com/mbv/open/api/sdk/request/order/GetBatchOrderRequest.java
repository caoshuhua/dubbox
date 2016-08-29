package com.mbv.open.api.sdk.request.order;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.order.GetBatchOrderResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class GetBatchOrderRequest extends BaseRequest<GetBatchOrderResponse> {

 
	private String startDate;                 
    
 
	private String endDate;                 
    
 
	private String orderState;                 
    
 
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
    
 

  	
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
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
		return "order/mb.order.batch.get";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("start_date", this.getStartDate());
	    map.put("end_date", this.getEndDate());
	    map.put("order_state", this.getOrderState());
	    map.put("page_no", this.getPageNo());
	    map.put("page_size", this.getPageSize());
	    map.put("sort_type", this.getSortType());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<GetBatchOrderResponse> getResponseClass() {
		return GetBatchOrderResponse.class;
	}

}	