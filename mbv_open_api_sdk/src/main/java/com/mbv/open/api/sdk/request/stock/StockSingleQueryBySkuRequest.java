package com.mbv.open.api.sdk.request.stock;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.stock.StockSingleQueryBySkuResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class StockSingleQueryBySkuRequest extends BaseRequest<StockSingleQueryBySkuResponse> {

 
	private String skuId;                 
    

 

  	
	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "stock/mb.stock.sku.single.get";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("sku_id", this.getSkuId());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<StockSingleQueryBySkuResponse> getResponseClass() {
		return StockSingleQueryBySkuResponse.class;
	}

}	