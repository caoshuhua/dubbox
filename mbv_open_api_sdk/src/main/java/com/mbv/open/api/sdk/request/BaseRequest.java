package com.mbv.open.api.sdk.request;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mbv.open.api.sdk.response.BaseResponse;

/**
 * 所有sdk请求的基类
 * @author Leon
 *
 * @param <T>
 */
public abstract class BaseRequest<T extends BaseResponse> {

	  private final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  protected String timestamp = this.sdf.format(new Date());
	  protected String version = "2.0";
	  protected String venderId;
	  protected String method;
	  protected String sign;
	  
	  public Map<String, String> getSysParams()
	  {
	    Map<String, String> sysParams = new HashMap<>();
	    sysParams.put("method", this.method);
	    sysParams.put("timestamp", this.timestamp);
	    sysParams.put("v", this.version);
	    
	    return sysParams;
	  }
	  
	  protected String getVenderId()
	  {
	    return this.venderId;
	  }
	  
	  protected void setVenderId(String venderId)
	  {
	    this.venderId = venderId;
	  }
	  
	  protected String getMethod()
	  {
	    return this.method;
	  }
	  
	  protected void setMethod(String method)
	  {
	    this.method = method;
	  }
	  
	  public String getTimestamp()
	  {
	    return this.timestamp;
	  }
	  
	  public void setTimestamp(String timestamp)
	  {
	    this.timestamp = timestamp;
	  }
	  
	  protected String getVersion()
	  {
	    return this.version;
	  }
	  
	  protected void setVersion(String version)
	  {
	    this.version = version;
	  }
	  
	  protected String getSign()
	  {
	    return this.sign;
	  }
	  
	  protected void setSign(String sign)
	  {
	    this.sign = sign;
	  }
	  
	  public abstract String getApiMethod();
	  
	  public abstract String getAppParams()
	    throws IOException;
	  
	  public abstract Class<T> getResponseClass();
	
}
