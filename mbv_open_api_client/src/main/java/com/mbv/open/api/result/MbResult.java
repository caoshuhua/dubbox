package com.mbv.open.api.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MbResult extends MbResponseResult{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3075028777247505668L;

	@JsonProperty("result_name")
    private String resultName;
	
	@JsonProperty("result_value")
    private String resultValue;

    public MbResult() {
    }

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

}
