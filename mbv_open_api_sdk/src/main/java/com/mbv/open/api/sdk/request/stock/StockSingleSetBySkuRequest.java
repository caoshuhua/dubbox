package com.mbv.open.api.sdk.request.stock;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import java.lang.String;
import com.mbv.open.api.sdk.response.stock.StockSetBySkuResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class StockSingleSetBySkuRequest extends BaseRequest<StockSetBySkuResponse> {

 
	private String skuId;                 
    
 
	private String actualStock;                 
    

 

  	
	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}                
    
 

  	
	public String getActualStock() {
		return actualStock;
	}

	public void setActualStock(String actualStock) {
		this.actualStock = actualStock;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "stock/mb.stock.sku.single.set";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("sku_id", this.getSkuId());
	    map.put("actual_stock", this.getActualStock());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<StockSetBySkuResponse> getResponseClass() {
		return StockSetBySkuResponse.class;
	}

}	