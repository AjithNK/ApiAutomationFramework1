package core;

import java.util.HashMap;

public class APIRequest {
	
	public String requestType;
	public String apiPath;
	public HashMap<String,String> headers;
	
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getApiPath() {
		return apiPath;
	}
	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}
	
	
	
	public HashMap<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}
	
	
	public APIRequest(String requestType, String apiPath) {
		super();
		this.requestType = requestType;
		this.apiPath = apiPath;
	}
	
	public APIRequest(String requestType, String apiPath, HashMap<String, String> headers) {
		super();
		this.requestType = requestType;
		this.apiPath = apiPath;
		this.headers = headers;
	}
	
	
	

}
