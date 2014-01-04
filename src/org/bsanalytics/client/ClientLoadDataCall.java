package org.bsanalytics.client;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

public class ClientLoadDataCall {
	
	String response_str=null;
	
	ClientObject cObj = new ClientObject();
	RestClient client_wink= cObj.getClientObject();
	
	public String sendLoadRequest(){
				
		Resource resource = client_wink.resource("http://localhost:8080/LoginFaces/rest/hi/asif");
		String response = resource.accept("text/plain").get(String.class);
		
		response_str = response.toString();
		System.out.println(response_str);
		return response.toString();
	}

}
