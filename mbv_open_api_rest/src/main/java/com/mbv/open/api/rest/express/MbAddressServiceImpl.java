package com.mbv.open.api.rest.express;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.mbv.open.api.bean.UserContext;
import com.mbv.open.api.client.express.MbAddressService;
import com.mbv.open.api.common.constant.SandBoxConstant;
import com.mbv.open.api.common.util.JsonUtil;
import com.mbv.open.api.internal.util.Api;
import com.mbv.open.api.internal.util.Constant;
import com.mbv.open.api.internal.util.MbvApiException;
import com.mbv.open.api.request.address.AddDeliveryAddressRequest;
import com.mbv.open.api.request.address.AddReturnAddressRequest;
import com.mbv.open.api.request.address.DeleteDeliveryAddressRequest;
import com.mbv.open.api.request.address.DeleteReturnAddressRequest;
import com.mbv.open.api.request.address.GetDeliveryAddressRequest;
import com.mbv.open.api.request.address.GetExpressCompanyRequest;
import com.mbv.open.api.request.address.GetRegionareaRequest;
import com.mbv.open.api.request.address.GetReturnAddressRequest;
import com.mbv.open.api.request.address.UpdateDeliveryAddressRequest;
import com.mbv.open.api.request.address.UpdateReturnAddressRequest;
import com.mbv.open.api.response.address.AddDeliveryAddressResponse;
import com.mbv.open.api.response.address.AddReturnAddressResponse;
import com.mbv.open.api.response.address.DeleteDeliveryAddressResponse;
import com.mbv.open.api.response.address.DeleteReturnAddressResponse;
import com.mbv.open.api.response.address.GetDeliveryAddressResponse;
import com.mbv.open.api.response.address.GetExpressCompanyResponse;
import com.mbv.open.api.response.address.GetRegionareaResponse;
import com.mbv.open.api.response.address.GetReturnAddressResponse;
import com.mbv.open.api.response.address.UpdateDeliveryAddressResponse;
import com.mbv.open.api.response.address.UpdateReturnAddressResponse;
import com.mbv.open.api.rest.BaseServiceImpl;
import com.mbv.open.api.service.AddressApiService;

@Api(name = "物流",index=4,desc = "物流查询")
@Path("express")
public class MbAddressServiceImpl extends BaseServiceImpl implements MbAddressService {
	
	@Autowired
	private AddressApiService addressApiService;
	
	@GET
    @POST
    @Path("mb.logistics.deliveryaddress.get")
	@Api(name = "查询发货地址",desc = "查询发货地址",index=4,resultJson=SandBoxConstant.MB_LOGISTICS_DELIVERYADDRESS_GET)
	@Produces({ContentType.APPLICATION_JSON_UTF_8})
	@com.mbv.open.api.annotation.Parameter(GetDeliveryAddressRequest.class)
	public GetDeliveryAddressResponse getSendAddressInfo(@Context SecurityContext securityContext, 
			@QueryParam("params")String getDeliveryAddressRequest) throws MbvApiException {
		GetDeliveryAddressResponse response = new GetDeliveryAddressResponse();
		try{
			UserContext context = getUserContext(securityContext);
			GetDeliveryAddressRequest request = new GetDeliveryAddressRequest();
			request.setUnitCode(context.getSellerCode());
			request.setUserCode(context.getUserCode());
			response = addressApiService.getSendAddressInfo(request);
			return response;
		} catch(MbvApiException me){
			response.setRespCode(me.getRespCode());
			response.setRespInfo(me.getRespInfo());
			return response;
		}
	}
	
	@GET
    @POST
    @Path("mb.logistics.returnaddress.get")
	@Api(name = "查询退货地址",desc = "查询退货地址",index=8,resultJson=SandBoxConstant.MB_LOGISTICS_RETURNADDRESS_GET)
	@Produces({ContentType.APPLICATION_JSON_UTF_8})
	@com.mbv.open.api.annotation.Parameter(GetReturnAddressRequest.class)
	public GetReturnAddressResponse getReturnAddressInfo(@Context SecurityContext securityContext, 
			@QueryParam("params")String getReturnAddressRequest) throws MbvApiException {
		UserContext context = getUserContext(securityContext);
		GetReturnAddressResponse response = new GetReturnAddressResponse();
		try{
			GetReturnAddressRequest request = new GetReturnAddressRequest();
			request.setUnitCode(context.getSellerCode());
			request.setUserCode(context.getUserCode());
			response = addressApiService.getReturnAddressInfo(request);
			return response;
		} catch(MbvApiException me){
			response.setRespCode(me.getRespCode());
			response.setRespInfo(me.getRespInfo());
			return response;
		}
		
	}
	
	@GET
    @POST
    @Path("mb.logistics.deliveryaddress.add")
	@Api(name = "新增发货地址",desc = "新增发货地址",index=1,resultJson=SandBoxConstant.MB_LOGISTICS_DELIVERYADDRESS_ADD)
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@com.mbv.open.api.annotation.Parameter(AddDeliveryAddressRequest.class)
	public AddDeliveryAddressResponse addSendAddress(@Context SecurityContext securityContext, 
			@QueryParam("params") String addDeliveryAddressRequest) throws MbvApiException {
		UserContext context = getUserContext(securityContext);
		AddDeliveryAddressResponse response = new AddDeliveryAddressResponse();
		try{
			AddDeliveryAddressRequest request = JsonUtil.toObject(addDeliveryAddressRequest, AddDeliveryAddressRequest.class);
			if(request == null){
				response.setRespCode(Constant.MBV_API_REQUEST_PARAM_NOT_VALID_CODE);
				response.setRespInfo(Constant.MBV_API_REQUEST_PARAM_NOT_VALID_MSG);
				return response;
			}
			request.setUnitCode(context.getSellerCode());
			request.setUserCode(context.getUserCode());
			response = addressApiService.addSendAddress(request);
			return response;
		} catch(MbvApiException me){
			response.setRespCode(me.getRespCode());
			response.setRespInfo(me.getRespInfo());
			return response;
		}
	}
	
	@GET
    @POST
    @Path("mb.logistics.returnaddress.add")
	@Api(name = "新增退货地址",desc = "新增退货地址",index=5,resultJson=SandBoxConstant.MB_LOGISTICS_RETURNADDRESS_ADD)
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@com.mbv.open.api.annotation.Parameter(AddReturnAddressRequest.class)
	public AddReturnAddressResponse addReturnAddress(@Context SecurityContext securityContext,
			@QueryParam("params")String addReturnAddressRequest) throws MbvApiException {
		UserContext context = getUserContext(securityContext);
		AddReturnAddressResponse response = new AddReturnAddressResponse();
		try{
			AddReturnAddressRequest request = JsonUtil.toObject(addReturnAddressRequest, AddReturnAddressRequest.class);
			if(request == null){
				response.setRespCode(Constant.MBV_API_REQUEST_PARAM_NOT_VALID_CODE);
				response.setRespInfo(Constant.MBV_API_REQUEST_PARAM_NOT_VALID_MSG);
				return response;
			}
			request.setUnitCode(context.getSellerCode());
			request.setUserCode(context.getUserCode());
			response = addressApiService.addReturnAddress(request);
			return response;
		} catch(MbvApiException me){
			response.setRespCode(me.getRespCode());
			response.setRespInfo(me.getRespInfo());
			return response;
		}
		
	}
	
	@GET
    @POST
    @Path("mb.logistics.deliveryaddress.update")
	@Api(name = "编辑发货地址",desc = "编辑发货地址",index=2,resultJson=SandBoxConstant.MB_LOGISTICS_DELIVERYADDRESS_UPDATE)
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@com.mbv.open.api.annotation.Parameter(UpdateDeliveryAddressRequest.class)
	public UpdateDeliveryAddressResponse updateSendAddress(@Context SecurityContext securityContext, 
			@QueryParam("params") String updateDeliveryAddressRequest) throws MbvApiException {
		UserContext context = getUserContext(securityContext);
		UpdateDeliveryAddressResponse response = new UpdateDeliveryAddressResponse();
		try{
			UpdateDeliveryAddressRequest request = JsonUtil.toObject(updateDeliveryAddressRequest, UpdateDeliveryAddressRequest.class);
			if(request == null){
				response.setRespCode(Constant.MBV_API_REQUEST_PARAM_NOT_VALID_CODE);
				response.setRespInfo(Constant.MBV_API_REQUEST_PARAM_NOT_VALID_MSG);
				return response;
			}
			request.setUnitCode(context.getSellerCode());
			request.setUserCode(context.getUserCode());
			response = addressApiService.updateSendAddress(request);
			return response;
		} catch(MbvApiException me){
			response.setRespCode(me.getRespCode());
			response.setRespInfo(me.getRespInfo());
			return response;
		}
		
	}


	@GET
    @POST
    @Path("mb.logistics.returnaddress.update")
	@Api(name = "编辑退货地址",desc = "编辑退货地址",index=6,resultJson=SandBoxConstant.MB_LOGISTICS_RETURNADDRESS_UPDATE)
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@com.mbv.open.api.annotation.Parameter(UpdateReturnAddressRequest.class)
	public UpdateReturnAddressResponse updateReturnAddress(@Context SecurityContext securityContext, 
			@QueryParam("params") String updateReturnAddressRequest) throws MbvApiException {
		UserContext context = getUserContext(securityContext);
		UpdateReturnAddressResponse response = new UpdateReturnAddressResponse();
		try{
			UpdateReturnAddressRequest request = JsonUtil.toObject(updateReturnAddressRequest, UpdateReturnAddressRequest.class);
			if(request == null){
				response.setRespCode(Constant.MBV_API_REQUEST_PARAM_NOT_VALID_CODE);
				response.setRespInfo(Constant.MBV_API_REQUEST_PARAM_NOT_VALID_MSG);
				return response;
			}
			request.setUnitCode(context.getSellerCode());
			request.setUserCode(context.getUserCode());
			response = addressApiService.updateReturnAddress(request);
			return response;
		} catch(MbvApiException me){
			response.setRespCode(me.getRespCode());
			response.setRespInfo(me.getRespInfo());
			return response;
		}
	}
	
	@GET
    @POST
    @Path("mb.logistics.deliveryaddress.delete")
	@Api(name = "删除发货地址",desc = "删除发货地址",index=3,resultJson=SandBoxConstant.MB_LOGISTICS_DELIVERYADDRESS_DELETE)
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@com.mbv.open.api.annotation.Parameter(DeleteDeliveryAddressRequest.class)
	public DeleteDeliveryAddressResponse deleteSendAddress(@Context SecurityContext securityContext, 
			@QueryParam("params") String deleteDeliveryAddressRequest) throws MbvApiException {
		UserContext context = getUserContext(securityContext);
		DeleteDeliveryAddressResponse response = new DeleteDeliveryAddressResponse();
		try{
			DeleteDeliveryAddressRequest request = JsonUtil.toObject(deleteDeliveryAddressRequest, DeleteDeliveryAddressRequest.class);
			if(request == null){
				response.setRespCode(Constant.MBV_API_REQUEST_PARAM_NOT_VALID_CODE);
				response.setRespInfo(Constant.MBV_API_REQUEST_PARAM_NOT_VALID_MSG);
				return response;
			}
			request.setUnitCode(context.getSellerCode());
			request.setUserCode(context.getUserCode());
			response = addressApiService.deleteSendAddress(request);
			return response;
		} catch(MbvApiException me){
			response.setRespCode(me.getRespCode());
			response.setRespInfo(me.getRespInfo());
			return response;
		}
	}

	@GET
    @POST
    @Path("mb.logistics.returnaddress.delete")
	@Api(name = "删除退货地址",desc = "删除退货地址",index=7,resultJson=SandBoxConstant.MB_LOGISTICS_RETURNADDRESS_DELETE)
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@com.mbv.open.api.annotation.Parameter(DeleteReturnAddressRequest.class)
	public DeleteReturnAddressResponse deleteReturnAddress(@Context SecurityContext securityContext, 
			@QueryParam("params") String deleteReturnAddressRequest) throws MbvApiException {
		UserContext context = getUserContext(securityContext);
		DeleteReturnAddressResponse response = new DeleteReturnAddressResponse();
		try{
			DeleteReturnAddressRequest request = JsonUtil.toObject(deleteReturnAddressRequest, DeleteReturnAddressRequest.class);
			if(request == null){
				response.setRespCode(Constant.MBV_API_REQUEST_PARAM_NOT_VALID_CODE);
				response.setRespInfo(Constant.MBV_API_REQUEST_PARAM_NOT_VALID_MSG);
				return response;
			}
			request.setUnitCode(context.getSellerCode());
			request.setUserCode(context.getUserCode());
			response = addressApiService.deleteReturnAddress(request);
			return response;
		} catch(MbvApiException me){
			response.setRespCode(me.getRespCode());
			response.setRespInfo(me.getRespInfo());
			return response;
		}
	}
	
	@GET
    @POST
    @Path("mb.logistics.expresscompany.get")
	@Api(name = "商家获取快递公司",desc = "商家获取快递公司",index=9,resultJson=SandBoxConstant.MB_LOGISTICS_EXPRESSCOMPANY_GET)
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@com.mbv.open.api.annotation.Parameter(GetExpressCompanyRequest.class)
	public GetExpressCompanyResponse getExpressCompany(@Context SecurityContext securityContext,
			@QueryParam("params") String getExpressCompanyRequest) throws MbvApiException {
		UserContext context = getUserContext(securityContext);
		GetExpressCompanyResponse response = new GetExpressCompanyResponse();
		try{
			GetExpressCompanyRequest request = new GetExpressCompanyRequest();
			request.setUnitCode(context.getSellerCode());
			request.setUserCode(context.getUserCode());
			response = addressApiService.getExpressCompany(request);
			return response;
		} catch(MbvApiException me){
			response.setRespCode(me.getRespCode());
			response.setRespInfo(me.getRespInfo());
			return response;
		}
	}
	
	@GET
    @POST
    @Path("mb.logistics.regionarea.get")
	@Api(name = "商家获取省市区县",desc = "商家获取省市区县",index=10,resultJson=SandBoxConstant.MB_LOGISTICS_REGIONAREA_GET)
    @Produces({ContentType.APPLICATION_JSON_UTF_8})
	@com.mbv.open.api.annotation.Parameter(GetRegionareaRequest.class)
	public GetRegionareaResponse getRegionArea(@Context SecurityContext securityContext, 
			@QueryParam("params") String getRegionareaRequest) throws MbvApiException {
		UserContext context = getUserContext(securityContext);
		GetRegionareaResponse response = new GetRegionareaResponse();
		try{
			GetRegionareaRequest request = JsonUtil.toObject(getRegionareaRequest, GetRegionareaRequest.class);
			request.setUnitCode(context.getSellerCode());
			request.setUserCode(context.getUserCode());
			response = addressApiService.getRegionArea(request);
			return response;
		} catch(MbvApiException me){
			response.setRespCode(me.getRespCode());
			response.setRespInfo(me.getRespInfo());
			return response;
		}
	}
}