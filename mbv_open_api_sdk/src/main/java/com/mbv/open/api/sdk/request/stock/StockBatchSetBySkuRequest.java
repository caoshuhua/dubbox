package com.mbv.open.api.sdk.request.stock;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.mbv.open.api.sdk.util.JsonUtil;
import com.mbv.open.api.sdk.request.BaseRequest;
import com.mbv.open.api.sdk.bean.stock.SkuStock;
import java.util.List;
import com.mbv.open.api.sdk.response.stock.StockSetBySkuResponse;

/**
 * 
 * @author mb.sdk.tool
 *
 */
public class StockBatchSetBySkuRequest extends BaseRequest<StockSetBySkuResponse> {

 
	private List<SkuStock> skuStocks;                 
    

 

  	
	public List<SkuStock> getSkuStocks() {
		return skuStocks;
	}

	public void setSkuStocks(List<SkuStock> skuStocks) {
		this.skuStocks = skuStocks;
	}                
    
	
	@Override
	public String getApiMethod() {
		return "stock/mb.stock.sku.batch.set";
	}


	@Override
	public String getAppParams() throws IOException {
		Map<String, Object> map = new TreeMap<String,Object>();
	    map.put("sku_stocks", this.getSkuStocks());
	    return JsonUtil.toJson(map);
	}

	@Override
	public Class<StockSetBySkuResponse> getResponseClass() {
		return StockSetBySkuResponse.class;
	}

}	