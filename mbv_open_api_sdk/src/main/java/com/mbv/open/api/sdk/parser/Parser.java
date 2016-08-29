package com.mbv.open.api.sdk.parser;

import com.mbv.open.api.sdk.exception.SdkException;
import com.mbv.open.api.sdk.response.BaseResponse;


public abstract interface Parser
{
  public abstract <T extends BaseResponse>T parse(String paramString, Class<T> paramClass)
    throws SdkException;
}
