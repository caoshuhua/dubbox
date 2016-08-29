package com.mbv.open.api.client.express;

import javax.ws.rs.core.SecurityContext;

import com.mbv.open.api.internal.util.MbvApiException;
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

public interface MbAddressService {

	/**
	 * ��ȡ������ַ
	 * @param securityContext
	 * @param params
	 * @return
	 * @throws MbvApiException
	 */
	public GetDeliveryAddressResponse getSendAddressInfo(SecurityContext securityContext,String getDeliveryAddressRequest) throws MbvApiException;
	
	
	/**
	 * ��ȡ�˻���ַ
	 * @param securityContext
	 * @param params
	 * @return
	 * @throws MbvApiException
	 */
	public GetReturnAddressResponse getReturnAddressInfo(SecurityContext securityContext,String getReturnAddressRequest) throws MbvApiException;
	
	/**
	 * ��ӷ�����ַ
	 * @param securityContext
	 * @param params
	 * @return
	 * @throws MbvApiException
	 */
	public AddDeliveryAddressResponse addSendAddress(SecurityContext securityContext,String addDeliveryAddressRequest) throws MbvApiException;
	
	
	/**
	 * ����˻���ַ
	 * @param securityContext
	 * @param params
	 * @return
	 * @throws MbvApiException
	 */
	public AddReturnAddressResponse addReturnAddress(SecurityContext  securityContext,String addReturnAddressRequest) throws MbvApiException;
	
	/**
	 * ���·�����ַ
	 * @param securityContext
	 * @param params
	 * @return
	 * @throws MbvApiException
	 */
	public UpdateDeliveryAddressResponse updateSendAddress(SecurityContext securityContext,String updateDeliveryAddressRequest) throws MbvApiException;
	
	
	/**
	 * �����˻���ַ
	 * @param securityContext
	 * @param params
	 * @return
	 * @throws MbvApiException
	 */
	public UpdateReturnAddressResponse updateReturnAddress(SecurityContext securityContext,String updateReturnAddressRequest) throws MbvApiException;
	
	/**
	 * ɾ�����ַ
	 * @param securityContext
	 * @param params
	 * @return
	 * @throws MbvApiException
	 */
	public DeleteDeliveryAddressResponse deleteSendAddress(SecurityContext securityContext,String deleteDeliveryAddressRequest) throws MbvApiException;
	
	
	/**
	 * ɾ���˻���ַ
	 * @param securityContext
	 * @param params
	 * @return
	 * @throws MbvApiException
	 */
	public DeleteReturnAddressResponse deleteReturnAddress(SecurityContext securityContext,String deleteReturnAddressRequest) throws MbvApiException;
	
	/**
	 * ��ȡ��ݹ�˾
	 * @param securityContext
	 * @param params
	 * @return
	 * @throws MbvApiException
	 */
	public GetExpressCompanyResponse getExpressCompany(SecurityContext securityContext,String getExpressCompanyRequest) throws MbvApiException;
	
	/**
	 * ��ȡʡ����
	 * @param securityContext
	 * @param params
	 * @return
	 * @throws MbvApiException
	 */
	public GetRegionareaResponse getRegionArea(SecurityContext securityContext,String getRegionareaRequest) throws MbvApiException;
}
