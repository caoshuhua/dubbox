package com.mbv.open.api.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.mbv.open.api.common.constant.Constant;
import com.mbv.open.api.internal.util.MbvApiException;

@Provider
public class ExceptionMapperSupport implements ExceptionMapper<Exception> {
	private static final Logger LOGGER = Logger
			.getLogger(ExceptionMapperSupport.class);

	/**
	 * 异常处理
	 * 
	 * @param exception
	 * @return 异常处理后的Response对象
	 */
	@Override
	public Response toResponse(Exception exception){
		String response;
		int code;
		if(exception instanceof MbvApiException){
			MbvApiException mbvApiException=(MbvApiException)exception;
			response="{\"resp_code\":\""+mbvApiException.getRespCode()+"\",\"resp_info\":\""+mbvApiException.getRespInfo()+"\"}";
		    code=Constant.successCode;
		}else{
			response=Constant.errorResponse;
			LOGGER.error(exception.getMessage(), exception);
			code=Constant.errorCode;
		}
		return Response.ok(response, MediaType.APPLICATION_JSON).status(code)
				.build();
	}
}