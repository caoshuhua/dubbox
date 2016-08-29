package com.mbv.open.api.sdk;

import com.mbv.open.api.sdk.exception.SdkException;
import com.mbv.open.api.sdk.request.BaseRequest;
import com.mbv.open.api.sdk.response.BaseResponse;

public abstract interface MbClient
{
  public abstract <T extends BaseResponse> T execute(BaseRequest<T> request)
    throws SdkException;
} 
