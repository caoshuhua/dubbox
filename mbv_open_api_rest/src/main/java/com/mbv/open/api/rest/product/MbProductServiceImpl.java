package com.mbv.open.api.rest.product;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.mbv.open.api.annotation.Parameter;
import com.mbv.open.api.bean.UserContext;
import com.mbv.open.api.client.product.MbProductService;
import com.mbv.open.api.common.constant.SandBoxConstant;
import com.mbv.open.api.common.util.JsonUtil;
import com.mbv.open.api.internal.util.Api;
import com.mbv.open.api.internal.util.MbvApiException;
import com.mbv.open.api.request.product.ProdBatchRequest;
import com.mbv.open.api.request.product.ProdOneRequest;
import com.mbv.open.api.request.product.ProdSellerRequest;
import com.mbv.open.api.response.product.ProdBatchResponse;
import com.mbv.open.api.response.product.ProdOneResponse;
import com.mbv.open.api.response.product.ProdSellerResponse;
import com.mbv.open.api.rest.BaseServiceImpl;
import com.mbv.open.api.service.ProductApiService;
@Api(name="商品",index=1,desc="商品接口")
@Path("product")
public class MbProductServiceImpl extends BaseServiceImpl implements MbProductService {
	
	private  Logger logger = LoggerFactory.getLogger(MbProductServiceImpl.class);
	
	@Autowired
	ProductApiService productApiService;
	
	@GET
    @POST
    @Path("mb.product.verdorproduct.get")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(ProdSellerRequest.class)
	@Api(name="商家商品信息查询",desc="商家商品信息查询",index=1,resultJson=SandBoxConstant.MB_PRODUCT_VERDORPRODUCT_GET)
	public ProdSellerResponse queryProductBySeller(@Context SecurityContext  securityContext,@QueryParam("params") String prodSellerRequest) throws MbvApiException {
		UserContext userContext=getUserContext(securityContext);
		logger.info("MbProductServiceImpl.queryProductBySeller->params:"+prodSellerRequest+",sellerCode:"+userContext.getSellerCode());
		ProdSellerRequest bean = JsonUtil.toObject(prodSellerRequest, ProdSellerRequest.class);
		bean.setUnitCode(userContext.getSellerCode());
		bean.setUserCode(userContext.getUserCode());
		ProdSellerResponse response = productApiService.queryProductBySeller(bean);
	    return response;
	}

	@GET
    @POST
    @Path("mb.product.batch.get")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(ProdBatchRequest.class)
	@Api(name="批量商品信息查询",desc="批量商品信息查询",index=2,resultJson=SandBoxConstant.MB_PRODUCT_BATCH_GET)
	public ProdBatchResponse queryBatchProducts(@Context SecurityContext  securityContext,@QueryParam("params") String prodBatchRequest) throws MbvApiException {
		UserContext userContext=getUserContext(securityContext);
		logger.info("MbProductServiceImpl.queryBatchProducts->params:"+prodBatchRequest+",sellerCode:"+userContext.getSellerCode());
		ProdBatchRequest bean = JsonUtil.toObject(prodBatchRequest, ProdBatchRequest.class);

		bean.setUnitCode(userContext.getSellerCode());
		bean.setUserCode(userContext.getUserCode());

		ProdBatchResponse response = productApiService.queryBatchProducts(bean);

	    return response; 
	    
	}

	@GET
    @POST
    @Path("mb.product.single.get")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(ProdOneRequest.class)
	@Api(name="单个商品信息查询",desc="单个商品信息查询",index=3,resultJson=SandBoxConstant.MB_PRODUCT_SINGLE_GET)
	public ProdOneResponse queryProductInfo(@Context SecurityContext  securityContext,@QueryParam("params") String prodOneRequest) throws MbvApiException {
		UserContext userContext=getUserContext(securityContext);
		logger.info("MbProductServiceImpl.queryProductInfo->params:"+prodOneRequest+",sellerCode:"+userContext.getSellerCode());
		ProdOneRequest bean = JsonUtil.toObject(prodOneRequest, ProdOneRequest.class);
		bean.setUnitCode(userContext.getSellerCode());
		bean.setUserCode(userContext.getUserCode());
		ProdOneResponse response = productApiService.queryProductInfo(bean);
	    return response;
	}
	

}
