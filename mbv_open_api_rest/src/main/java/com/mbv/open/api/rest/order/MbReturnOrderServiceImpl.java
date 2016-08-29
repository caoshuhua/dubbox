package com.mbv.open.api.rest.order;

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
import com.mbv.open.api.client.order.MbReturnOrderService;
import com.mbv.open.api.common.constant.SandBoxConstant;
import com.mbv.open.api.common.util.JsonUtil;
import com.mbv.open.api.internal.util.Api;
import com.mbv.open.api.internal.util.MbvApiException;
import com.mbv.open.api.request.returnorder.ReturnOrderBatchQueryRequest;
import com.mbv.open.api.request.returnorder.ReturnOrderCodeRequest;
import com.mbv.open.api.request.returnorder.ReturnOrderConfirmReceivedRequest;
import com.mbv.open.api.response.returnorder.ReturnOrderBatchQueryResponse;
import com.mbv.open.api.response.returnorder.ReturnOrderConfirmReceivedResponse;
import com.mbv.open.api.response.returnorder.ReturnOrderSingleQueryResponse;
import com.mbv.open.api.rest.BaseServiceImpl;
import com.mbv.open.api.service.ReturnOrderApiService;

@Api(name="退单",index=5,desc="退单接口")
@Path("returnorder")
public class MbReturnOrderServiceImpl extends BaseServiceImpl implements MbReturnOrderService {

	private  Logger logger = LoggerFactory.getLogger(MbReturnOrderServiceImpl.class);
	
	@Autowired
	ReturnOrderApiService returnOrderApiService;
	
	@GET
    @POST
    @Path("mb.returnorder.batch.get")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(ReturnOrderBatchQueryRequest.class)
	@Api(name="批量退单查询",desc="批量退单查询",index=1,resultJson=SandBoxConstant.MB_RETURNORDER_BATCH_GET)
	public ReturnOrderBatchQueryResponse batchQueryReturnOrders(
			@Context SecurityContext  securityContext, @QueryParam("params") String returnOrderBatchQueryRequest)
			throws MbvApiException {
		// TODO Auto-generated method stub

		//String sellerCode=getSellerCode(securityContext);
		
		UserContext userContext=getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		String userCode = userContext.getUserCode();
		
		logger.info("MbReturnOrderServiceImpl.batchQueryReturnOrders->params:"+returnOrderBatchQueryRequest+",sellerCode:"+sellerCode
				+",userCode:"+userCode);
		ReturnOrderBatchQueryRequest bean = JsonUtil.toObject(returnOrderBatchQueryRequest, ReturnOrderBatchQueryRequest.class);
		bean.setUnitCode(sellerCode);
		ReturnOrderBatchQueryResponse response = returnOrderApiService.batchQueryReturnOrders(bean);
	    return response;
	    
	}

	@GET
    @POST
    @Path("mb.returnorder.single.get")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(ReturnOrderCodeRequest.class)
	@Api(name="单个退单查询",desc="单个退单查询",index=2,resultJson=SandBoxConstant.MB_RETURNORDER_SINGLE_GET)
	public ReturnOrderSingleQueryResponse singleQueryReturnOrders(
			@Context SecurityContext  securityContext, @QueryParam("params") String returnOrderCodeRequest)
			throws MbvApiException {
		// TODO Auto-generated method stub
		
		//String sellerCode=getSellerCode(securityContext);
		
		UserContext userContext=getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		String userCode=userContext.getUserCode();
		
		logger.info("MbReturnOrderServiceImpl.singleQueryReturnOrders->params:"+returnOrderCodeRequest+",sellerCode:"+sellerCode
				+",userCode:"+userCode);
		ReturnOrderCodeRequest bean = JsonUtil.toObject(returnOrderCodeRequest, ReturnOrderCodeRequest.class);
		bean.setUnitCode(sellerCode);
		ReturnOrderSingleQueryResponse response = returnOrderApiService.singleQueryReturnOrder(bean);
	    return response;
	}

	@GET
    @POST
    @Path("mb.returnorder.receive.comfirm")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(ReturnOrderConfirmReceivedRequest.class)
	@Api(name="退单确认收货",desc="退单确认收货",index=3,resultJson=SandBoxConstant.MB_RETURNORDER_RECEIVE_COMFIRM)
	public ReturnOrderConfirmReceivedResponse confirmReturnOrderReceived(
			@Context SecurityContext  securityContext, @QueryParam("params") String returnOrderConfirmReceivedRequest)
			throws MbvApiException {
		// TODO Auto-generated method stub

		//String sellerCode=getSellerCode(securityContext);
		
		UserContext userContext=getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		String userCode=userContext.getUserCode();
		
		logger.info("MbReturnOrderServiceImpl.confirmReturnOrderReceived->params:"+returnOrderConfirmReceivedRequest+",sellerCode:"+sellerCode
				+",userCode:"+userCode);
		ReturnOrderConfirmReceivedRequest bean = JsonUtil.toObject(returnOrderConfirmReceivedRequest, ReturnOrderConfirmReceivedRequest.class);
		bean.setUnitCode(sellerCode);
		bean.setUserCode(userCode);
		ReturnOrderConfirmReceivedResponse response = returnOrderApiService.confirmReturnOrderReceived(bean);
	    return response;
	}

	
	
}
