package com.ericsson.oss.services.cenm.broproxy.rest;

public class HttpRestPostRequest extends HttpRestRequest{
	
	private String jsonInput;

	public HttpRestPostRequest(HttpRestRequestBuilder builder,final String jsonInput) {
		super(builder);
		this.setJsonInput(jsonInput);		
	}

	public String getJsonInput() {
		return jsonInput;
	}

	public void setJsonInput(String jsonInput) {
		this.jsonInput = jsonInput;
	}
	
	@Override
    public String toString() {
		
		return super.toString() + "[jsonInput=" + jsonInput +"]" ;
		
	}

}
