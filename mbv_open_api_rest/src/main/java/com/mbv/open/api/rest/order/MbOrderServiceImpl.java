package com.mbv.open.api.rest.order;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.mbv.open.api.annotation.Parameter;
import com.mbv.open.api.bean.UserContext;
import com.mbv.open.api.client.order.MbOrderService;
import com.mbv.open.api.common.constant.SandBoxConstant;
import com.mbv.open.api.common.util.JsonUtil;
import com.mbv.open.api.internal.util.Api;
import com.mbv.open.api.internal.util.MbvApiException;
import com.mbv.open.api.request.order.GetBatchOrderRequest;
import com.mbv.open.api.request.order.GetOrderRemarkRequest;
import com.mbv.open.api.request.order.GetSingleOrderRequest;
import com.mbv.open.api.request.order.GetVendorRemarkRequest;
import com.mbv.open.api.request.order.OutstorageOrderRequest;
import com.mbv.open.api.request.order.UpdateExpressCodeRequest;
import com.mbv.open.api.request.order.UpdateOrderRemarkRequest;
import com.mbv.open.api.request.order.UpdateVendorRemarkRequest;
import com.mbv.open.api.response.order.GetBatchOrderResponse;
import com.mbv.open.api.response.order.GetOrderRemarkResponse;
import com.mbv.open.api.response.order.GetSingleOrderResponse;
import com.mbv.open.api.response.order.GetVendorRemarkResponse;
import com.mbv.open.api.response.order.OutstorageOrderResponse;
import com.mbv.open.api.response.order.UpdateExpressCodeResponse;
import com.mbv.open.api.response.order.UpdateOrderRemarkResponse;
import com.mbv.open.api.response.order.UpdateVendorRemarkResponse;
import com.mbv.open.api.rest.BaseServiceImpl;
import com.mbv.open.api.service.OrderApiService;

@Api(name = "订单",index=2,desc = "订单管理")
@Path("order")
public class MbOrderServiceImpl extends BaseServiceImpl implements MbOrderService {
	
	@Autowired
	OrderApiService orderApiService;	
	
	
	@GET
    @POST
    @Path("mb.order.batch.get")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(GetBatchOrderRequest.class)
	@Api(name = "批量订单检索",desc = "批量订单检索",index=1,resultJson=SandBoxConstant.MB_ORDER_BATCH_GET)
	public GetBatchOrderResponse getBatchOrder(@Context SecurityContext  securityContext,@QueryParam("params") String getBatchOrderRequest) throws MbvApiException {
		UserContext userContext=getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		
		GetBatchOrderRequest bean = JsonUtil.toObject(getBatchOrderRequest, GetBatchOrderRequest.class);
		bean.setUnitCode(sellerCode);
		GetBatchOrderResponse response = orderApiService.getBatchOrder(bean);
	    return response;
	}
	
	
	@GET
    @POST
    @Path("mb.order.single.get")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(GetSingleOrderRequest.class)
	@Api(name = "单个订单检索",desc = "单个订单检索",index=2,resultJson=SandBoxConstant.MB_ORDER_SINGLE_GET)
	public GetSingleOrderResponse getSingleOrder(@Context SecurityContext  securityContext,@QueryParam("params") String getSingleOrderRequest) throws MbvApiException {
		UserContext userContext=getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		
		GetSingleOrderRequest bean = JsonUtil.toObject(getSingleOrderRequest, GetSingleOrderRequest.class);
		bean.setUnitCode(sellerCode);
		GetSingleOrderResponse response = orderApiService.getSingleOrder(bean);
	    return response;
	}
	
	
	@GET
    @POST
    @Path("mb.order.vendorremark.get")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(GetVendorRemarkRequest.class)
	@Api(name = "查询商家备注",desc = "查询商家备注",index=5,resultJson=SandBoxConstant.MB_ORDER_VENDORREMARK_GET)
	public GetVendorRemarkResponse getVendorRemark(@Context SecurityContext  securityContext,@QueryParam("params") String getVendorRemarkRequest) throws MbvApiException {
		UserContext userContext=getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		
		GetVendorRemarkRequest bean = JsonUtil.toObject(getVendorRemarkRequest, GetVendorRemarkRequest.class);
		bean.setUnitCode(sellerCode);
		GetVendorRemarkResponse response = orderApiService.getVendorRemark(bean);
	    return response;
	}
	
	
	@GET
    @POST
    @Path("mb.order.vendorremark.update")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(UpdateVendorRemarkRequest.class)
	@Api(name = "修改商家备注",desc = "修改商家备注",index=6,resultJson=SandBoxConstant.MB_ORDER_VENDORREMARK_UPDATE)
	public UpdateVendorRemarkResponse updateVendorRemark(@Context SecurityContext  securityContext,@QueryParam("params") String updateVendorRemarkRequest) throws MbvApiException {
		UserContext userContext=getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		
		UpdateVendorRemarkRequest bean = JsonUtil.toObject(updateVendorRemarkRequest, UpdateVendorRemarkRequest.class);
		bean.setUnitCode(sellerCode);
		UpdateVendorRemarkResponse response = orderApiService.updateVendorRemark(bean);
	    return response;
	}
	
	
	@GET
    @POST
    @Path("mb.order.remark.get")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(GetOrderRemarkRequest.class)
	@Api(name = "查询订单备注",desc = "查询订单备注",index=7,resultJson=SandBoxConstant.MB_ORDER_REMARK_GET)
	public GetOrderRemarkResponse getOrderRemark(@Context SecurityContext  securityContext,@QueryParam("params") String getOrderRemarkRequest) throws MbvApiException {
		UserContext userContext=getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		
		GetOrderRemarkRequest bean = JsonUtil.toObject(getOrderRemarkRequest, GetOrderRemarkRequest.class);
		bean.setUnitCode(sellerCode);
		GetOrderRemarkResponse response = orderApiService.getOrderRemark(bean);
	    return response;
	}
	
		
	@GET
    @POST
    @Path("mb.order.remark.update")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(UpdateOrderRemarkRequest.class)
	@Api(name = "追加订单备注",desc = "追加订单备注",index=8,resultJson=SandBoxConstant.MB_ORDER_REMARK_UPDATE)
	public UpdateOrderRemarkResponse updateOrderRemark(@Context SecurityContext  securityContext,@QueryParam("params") String updateOrderRemarkRequest) throws MbvApiException {
		UserContext userContext=getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		
		UpdateOrderRemarkRequest bean = JsonUtil.toObject(updateOrderRemarkRequest, UpdateOrderRemarkRequest.class);
		bean.setUnitCode(sellerCode);
		UpdateOrderRemarkResponse response = orderApiService.updateOrderRemark(bean);
	    return response;
	}
	
	
	@GET
    @POST
    @Path("mb.order.expressdeliveryorder.update")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(UpdateExpressCodeRequest.class)
	@Api(name = "修改运单号",desc = "修改运单号",index=4,resultJson=SandBoxConstant.MB_ORDER_EXPRESSDELIVERYORDER_UPDATE)
	public UpdateExpressCodeResponse updateExpressCode(@Context SecurityContext  securityContext,@QueryParam("params") String updateExpressCodeRequest) throws MbvApiException {
		UserContext userContext=getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		
		UpdateExpressCodeRequest bean = JsonUtil.toObject(updateExpressCodeRequest, UpdateExpressCodeRequest.class);
		bean.setUnitCode(sellerCode);
		UpdateExpressCodeResponse response = orderApiService.updateExpressCode(bean);
	    return response;
	}
	
	
	@GET
    @POST
    @Path("mb.order.outstorage")
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@Parameter(OutstorageOrderRequest.class)
	@Api(name = "订单出库",desc = "订单出库",index=3,resultJson=SandBoxConstant.MB_ORDER_OUTSTORAGE)
	public OutstorageOrderResponse outstorageOrder(@Context SecurityContext  securityContext,@QueryParam("params") String outstorageOrderRequest) throws MbvApiException {
		UserContext userContext=getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		
		OutstorageOrderRequest bean = JsonUtil.toObject(outstorageOrderRequest, OutstorageOrderRequest.class);
		bean.setUnitCode(sellerCode);
		bean.setUserCode(userContext.getUserCode());
		OutstorageOrderResponse response = orderApiService.outstorageOrder(bean);
	    return response;
	}
	

}
