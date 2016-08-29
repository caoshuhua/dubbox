package com.mbv.open.api.sdk.exception;

/**
 * sdk异常
 * @author Leon
 *
 */
public class SdkException
  extends Exception
{
  private static final long serialVersionUID = -7035498848577048685L;
  private String errCode;
  private String errMsg;
  
  public SdkException() {}
  
  public SdkException(String message, Throwable cause)
  {
    super(message, cause);
  }
  
  public SdkException(String message)
  {
    super(message);
  }
  
  public SdkException(Throwable cause)
  {
    super(cause);
  }
  
	public SdkException(String errCode, String errMsg) {
		super(errCode + ": " + errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
  
  public String getErrCode()
  {
    return this.errCode;
  }
  
  public String getErrMsg()
  {
    return this.errMsg;
  }
}

