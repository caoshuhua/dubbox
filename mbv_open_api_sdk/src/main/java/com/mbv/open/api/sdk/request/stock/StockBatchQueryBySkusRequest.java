package com.mbv.open.api.sdk.request.stock;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import com.mbv.open.api.sdk.bean.stock.SkuId;
import java.util.List;
import com.mbv.open.api.sdk.response.stock.StockBatchQueryBySkusResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class StockBatchQueryBySkusRequest extends BaseRequest<StockBatchQueryBySkusResponse> {

 
	private List<SkuId> skuIds;                 
    

 

  	
	public List<SkuId> getSkuIds() {
		return skuIds;
	}

	public void setSkuIds(List<SkuId> skuIds) {
		this.skuIds = skuIds;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "stock/mb.stock.sku.batch.get";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("sku_ids", this.getSkuIds());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<StockBatchQueryBySkusResponse> getResponseClass() {
		return StockBatchQueryBySkusResponse.class;
	}

}	