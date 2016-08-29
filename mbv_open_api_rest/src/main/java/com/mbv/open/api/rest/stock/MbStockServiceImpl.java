package com.mbv.open.api.rest.stock;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.mbv.open.api.annotation.Parameter;
import com.mbv.open.api.bean.UserContext;
import com.mbv.open.api.client.stock.MbStockService;
import com.mbv.open.api.common.constant.SandBoxConstant;
import com.mbv.open.api.common.util.JsonUtil;
import com.mbv.open.api.internal.util.Api;
import com.mbv.open.api.internal.util.MbvApiException;
import com.mbv.open.api.request.stock.StockBatchQueryBySkusRequest;
import com.mbv.open.api.request.stock.StockBatchQueryRequest;
import com.mbv.open.api.request.stock.StockBatchSetBySkuRequest;
import com.mbv.open.api.request.stock.StockSingleQueryBySkuRequest;
import com.mbv.open.api.request.stock.StockSingleSetBySkuRequest;
import com.mbv.open.api.response.stock.StockBatchQueryBySkusResponse;
import com.mbv.open.api.response.stock.StockBatchQueryResponse;
import com.mbv.open.api.response.stock.StockSetBySkuResponse;
import com.mbv.open.api.response.stock.StockSingleQueryBySkuResponse;
import com.mbv.open.api.rest.BaseServiceImpl;
import com.mbv.open.api.service.StockApiService;

@Api(name="库存",index=3,desc="商家库存接口")
@Path("stock")
public class MbStockServiceImpl extends BaseServiceImpl implements MbStockService {
	
	private  Logger logger = LoggerFactory.getLogger(MbStockServiceImpl.class);
	
	@Autowired
	StockApiService stockApiService;
	
	@GET
    @POST
    @Path("mb.stock.verdorstock.get")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(StockBatchQueryRequest.class)
	@Api(name="商家库存查询",desc="商家库存查询",index=1,resultJson=SandBoxConstant.MB_STOCK_VERDORSTOCK_GET)
	public StockBatchQueryResponse queryBatchStockInfo(@Context SecurityContext  securityContext,@QueryParam("params") String stockBatchQueryRequest) throws MbvApiException {
		UserContext userContext=getUserContext(securityContext);
		logger.info("MbStockServiceImpl.queryBatchStockInfo->params:"+stockBatchQueryRequest+",sellerCode:"+userContext.getSellerCode());
		StockBatchQueryRequest bean = JsonUtil.toObject(stockBatchQueryRequest, StockBatchQueryRequest.class);
		bean.setUnitCode(userContext.getSellerCode());
		bean.setUserCode(userContext.getUserCode());
		StockBatchQueryResponse response = stockApiService.queryBatchStockInfo(bean);
	    return response;
	}
	
	@GET
    @POST
    @Path("mb.stock.sku.batch.get")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(StockBatchQueryBySkusRequest.class)
	@Api(name="批量SKU库存查询",desc="批量SKU库存查询",index=2,resultJson=SandBoxConstant.MB_STOCK_SKU_BATCH_GET)
	public StockBatchQueryBySkusResponse queryBatchStockInfoBySkus(@Context SecurityContext  securityContext,@QueryParam("params") String stockBatchQueryBySkusRequest) throws MbvApiException{
		UserContext userContext=getUserContext(securityContext);
		logger.info("MbStockServiceImpl.queryBatchStockInfoBySkus->params:"+stockBatchQueryBySkusRequest+",sellerCode:"+userContext.getSellerCode());
		logger.info("params:"+stockBatchQueryBySkusRequest);
		StockBatchQueryBySkusRequest bean = (StockBatchQueryBySkusRequest) JsonUtil.toObject(stockBatchQueryBySkusRequest, StockBatchQueryBySkusRequest.class);
		bean.setUnitCode(userContext.getSellerCode());
		bean.setUserCode(userContext.getUserCode());
		StockBatchQueryBySkusResponse response = stockApiService.queryBatchStockInfoBySkus(bean);
	    return response;
	}
	
	@GET
    @POST
    @Path("mb.stock.sku.single.get")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(StockSingleQueryBySkuRequest.class)
	@Api(name="单个SKU库存查询",desc="单个SKU库存查询",index=3,resultJson=SandBoxConstant.MB_STOCK_SKU_SINGLE_GET)
	public StockSingleQueryBySkuResponse querySingleStockBySku(@Context SecurityContext  securityContext,@QueryParam("params") String stockSingleQueryBySkuRequest) throws MbvApiException{
		UserContext userContext=getUserContext(securityContext);
		logger.info("MbStockServiceImpl.querySingleStockBySku->params:"+stockSingleQueryBySkuRequest+",sellerCode:"+userContext.getSellerCode());
		StockSingleQueryBySkuRequest bean = JsonUtil.toObject(stockSingleQueryBySkuRequest, StockSingleQueryBySkuRequest.class);
		bean.setUnitCode(userContext.getSellerCode());
		bean.setUserCode(userContext.getUserCode());
		StockSingleQueryBySkuResponse response = stockApiService.querySingleStockBySku(bean);
	    return response;
	}
	
	@GET
    @POST
    @Path("mb.stock.sku.batch.set")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(StockBatchSetBySkuRequest.class)
	@Api(name="批量SKU库存设置",desc="批量SKU库存设置",index=4,resultJson=SandBoxConstant.MB_STOCK_SKU_BATCH_SET)
	public StockSetBySkuResponse updateBatchStockBySkus(@Context SecurityContext  securityContext,@QueryParam("params") String stockBatchSetBySkuRequest) throws MbvApiException{
		UserContext userContext=getUserContext(securityContext);
		logger.info("MbStockServiceImpl.updateBatchStockBySkus->params:"+stockBatchSetBySkuRequest+",sellerCode:"+userContext.getSellerCode());
		
		logger.info("params:"+stockBatchSetBySkuRequest);
		StockBatchSetBySkuRequest bean = JsonUtil.toObject(stockBatchSetBySkuRequest, StockBatchSetBySkuRequest.class);
		bean.setUnitCode(userContext.getSellerCode());
		bean.setUserCode(userContext.getUserCode());
		StockSetBySkuResponse response = stockApiService.updateBatchStockBySkus(bean);
	    return response;
	}
	
	@GET
    @POST
    @Path("mb.stock.sku.single.set")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(StockSingleSetBySkuRequest.class)
	@Api(name="单个SKU库存设置",desc="单个SKU库存设置",index=5,resultJson=SandBoxConstant.MB_STOCK_SKU_SINGLE_SET)
	public StockSetBySkuResponse updateSingleStockBySku(@Context SecurityContext  securityContext,@QueryParam("params") String stockSingleSetBySkuRequest) throws MbvApiException{
		UserContext userContext=getUserContext(securityContext);
		logger.info("MbStockServiceImpl.updateSingleStockBySku->params:"+stockSingleSetBySkuRequest+",sellerCode:"+userContext.getSellerCode());
		StockSingleSetBySkuRequest bean = JsonUtil.toObject(stockSingleSetBySkuRequest, StockSingleSetBySkuRequest.class);
		bean.setUnitCode(userContext.getSellerCode());
		bean.setUserCode(userContext.getUserCode());
		StockSetBySkuResponse response = stockApiService.updateSingleStockBySku(bean);
	    return response;
	}

}
