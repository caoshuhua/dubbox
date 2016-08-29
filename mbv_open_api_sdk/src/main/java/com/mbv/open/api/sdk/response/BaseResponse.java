package com.mbv.open.api.sdk.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse{

	  private String url;
	  @JsonProperty("resp_code")
		private String respCode;
		
		@JsonProperty("resp_info")
		private String respInfo;

		public String getRespCode() {
			return respCode;
		}

		public void setRespCode(String respCode) {
			this.respCode = respCode;
		}

		public String getRespInfo() {
			return respInfo;
		}

		public void setRespInfo(String respInfo) {
			this.respInfo = respInfo;
		}
	  
	  public String getUrl()
	  {
	    return this.url;
	  }
	  
	  public void setUrl(String url)
	  {
	    this.url = url;
	  }

}
