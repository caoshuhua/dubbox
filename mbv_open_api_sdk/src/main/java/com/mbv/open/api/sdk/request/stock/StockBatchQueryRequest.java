package com.mbv.open.api.sdk.request.stock;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.stock.StockBatchQueryResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class StockBatchQueryRequest extends BaseRequest<StockBatchQueryResponse> {

 
	private String pageNo;                 
    
 
	private String pageSize;                 
    

 

  	
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
		return "stock/mb.stock.verdorstock.get";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("page_no", this.getPageNo());
	    map.put("page_size", this.getPageSize());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<StockBatchQueryResponse> getResponseClass() {
		return StockBatchQueryResponse.class;
	}

}	