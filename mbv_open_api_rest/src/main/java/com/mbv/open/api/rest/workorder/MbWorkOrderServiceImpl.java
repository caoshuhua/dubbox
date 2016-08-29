package com.mbv.open.api.rest.workorder;

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
import com.mbv.open.api.client.workorder.MbWorkOrderService;
import com.mbv.open.api.common.constant.SandBoxConstant;
import com.mbv.open.api.common.util.JsonUtil;
import com.mbv.open.api.internal.util.Api;
import com.mbv.open.api.internal.util.MbvApiException;
import com.mbv.open.api.request.wn.WorkOrderAddRequest;
import com.mbv.open.api.request.wn.WorkOrderBatchQueryRequest;
import com.mbv.open.api.request.wn.WorkOrderCancelRequest;
import com.mbv.open.api.request.wn.WorkOrderConfirmRequest;
import com.mbv.open.api.request.wn.WorkOrderSingleQueryRequest;
import com.mbv.open.api.response.wn.WorkOrderAddResponse;
import com.mbv.open.api.response.wn.WorkOrderBatchQueryResponse;
import com.mbv.open.api.response.wn.WorkOrderCancelResponse;
import com.mbv.open.api.response.wn.WorkOrderConfirmResponse;
import com.mbv.open.api.response.wn.WorkOrderSingleQueryResponse;
import com.mbv.open.api.rest.BaseServiceImpl;
import com.mbv.open.api.service.WorkOrderApiService;

@Api(name="工单",index=6,desc="缺货工单接口")
@Path("workorder")
public class MbWorkOrderServiceImpl extends BaseServiceImpl implements
		MbWorkOrderService {

	private Logger logger = LoggerFactory
			.getLogger(MbWorkOrderServiceImpl.class);

	@Autowired
	WorkOrderApiService workOrderApiService;

	@GET
	@POST
	@Path("mb.workorder.batch.get")
	@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
	@Parameter(WorkOrderBatchQueryRequest.class)
	@Api(name="批量工单查询",desc="批量工单查询",index=1,resultJson=SandBoxConstant.MB_WORKORDER_BATCH_GET)
	public WorkOrderBatchQueryResponse batchQueryWorkOrder(
			@Context SecurityContext securityContext,
			@QueryParam("params") String workOrderBatchQueryRequest)
			throws MbvApiException {
		// TODO Auto-generated method stub

		// String sellerCode=getSellerCode(securityContext);

		UserContext userContext = getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		String userCode = userContext.getUserCode();

		logger.info("MbWorkOrderServiceImpl.batchQueryWorkOrder->params:"
				+ workOrderBatchQueryRequest + ",sellerCode:" + sellerCode
				+ ",userCode:" + userCode);
		WorkOrderBatchQueryRequest bean = JsonUtil.toObject(
				workOrderBatchQueryRequest, WorkOrderBatchQueryRequest.class);
		bean.setUnitCode(sellerCode);
		
		WorkOrderBatchQueryResponse response = workOrderApiService
				.batchQueryWorkOrders(bean);

		return response;

	}

	@GET
	@POST
	@Path("mb.workorder.single.get")
	@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
	@Parameter(WorkOrderSingleQueryRequest.class)
	@Api(name="单个工单查询",desc="单个工单查询",index=2,resultJson=SandBoxConstant.MB_WORKORDER_SINGLE_GET)
	public WorkOrderSingleQueryResponse singleQueryWorkOrder(
			@Context SecurityContext securityContext,
			@QueryParam("params") String workOrderSingleQueryRequest)
			throws MbvApiException {
		// TODO Auto-generated method stub

		// String sellerCode=getSellerCode(securityContext);

		UserContext userContext = getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		String userCode = userContext.getUserCode();

		logger.info("MbWorkOrderServiceImpl.singleQueryWorkOrder->params:"
				+ workOrderSingleQueryRequest + ",sellerCode:" + sellerCode
				+ ",userCode:" + userCode);
		WorkOrderSingleQueryRequest bean = JsonUtil.toObject(
				workOrderSingleQueryRequest, WorkOrderSingleQueryRequest.class);
		bean.setUnitCode(sellerCode);
	
		WorkOrderSingleQueryResponse response = workOrderApiService
				.singleQueryWorkOrder(bean);

		return response;
	}

	@GET
	@POST
	@Path("mb.workorder.add")
	@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
	@Parameter(WorkOrderAddRequest.class)
	@Api(name="新增工单",desc="新增工单",index=3,resultJson=SandBoxConstant.MB_WORKORDER_ADD)
	public WorkOrderAddResponse addWorkOrder(
			@Context SecurityContext securityContext,
			@QueryParam("params") String workOrderAddRequest)
			throws MbvApiException {
		// TODO Auto-generated method stub

		// String sellerCode=getSellerCode(securityContext);

		UserContext userContext = getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		String userCode = userContext.getUserCode();

		logger.info("MbWorkOrderServiceImpl.addWorkOrder->params:"
				+ workOrderAddRequest + ",sellerCode:" + sellerCode
				+ ",userCode:" + userCode);
		WorkOrderAddRequest bean = JsonUtil.toObject(workOrderAddRequest,
				WorkOrderAddRequest.class);
		bean.setUnitCode(sellerCode);
		bean.setUserCode(userCode);

		WorkOrderAddResponse response = workOrderApiService.addWorkOrder(bean);


		return response;
	}

	@GET
	@POST
	@Path("mb.workorder.confirm")
	@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
	@Parameter(WorkOrderConfirmRequest.class)
	@Api(name="确认工单",desc="确认工单",index=4,resultJson=SandBoxConstant.MB_WORKORDER_CONFIRM)
	public WorkOrderConfirmResponse confirmWorkOrder(
			@Context SecurityContext securityContext,
			@QueryParam("params") String workOrderConfirmRequest)
			throws MbvApiException {
		// TODO Auto-generated method stub
		// String sellerCode=getSellerCode(securityContext);

		UserContext userContext = getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		String userCode = userContext.getUserCode();

		logger.info("MbWorkOrderServiceImpl.confirmWorkOrder->params:"
				+ workOrderConfirmRequest + ",sellerCode:" + sellerCode
				+ ",userCode:" + userCode);
		WorkOrderConfirmRequest bean = JsonUtil.toObject(workOrderConfirmRequest,
				WorkOrderConfirmRequest.class);
		bean.setUnitCode(sellerCode);
		bean.setUserCode(userCode);

		WorkOrderConfirmResponse response = workOrderApiService
				.confirmWorkOrder(bean);

		return response;
	}

	@GET
	@POST
	@Path("mb.workorder.cancel")
	@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
	@Parameter(WorkOrderCancelRequest.class)
	@Api(name="取消工单",desc="取消工单",index=5,resultJson=SandBoxConstant.MB_WORKORDER_CANCEL)
	public WorkOrderCancelResponse cancelWorkOrder(
			@Context SecurityContext securityContext,
			@QueryParam("params") String workOrderCancelRequest)
			throws MbvApiException {
		// TODO Auto-generated method stub
		// String sellerCode=getSellerCode(securityContext);

		UserContext userContext = getUserContext(securityContext);
		String sellerCode = userContext.getSellerCode();
		String userCode = userContext.getUserCode();

		logger.info("MbWorkOrderServiceImpl.cancelWorkOrder->params:"
				+ workOrderCancelRequest + ",sellerCode:" + sellerCode
				+ ",userCode:" + userCode);
		WorkOrderCancelRequest bean = JsonUtil.toObject(
				workOrderCancelRequest, WorkOrderCancelRequest.class);
		bean.setUnitCode(sellerCode);

		WorkOrderCancelResponse response = workOrderApiService
				.deleteWorkOrder(bean);

		return response;
	}

}
