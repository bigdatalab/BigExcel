package org.bsanalytics.client;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.bsanalytics.dashboard.FileChooserForLoadingData;

public class ClientLoadDataCall {
	
	String response_str=null;
	ClientObject cObj = new ClientObject();
	RestClient client_wink= cObj.getClientObject();
	FileChooserForLoadingData choose_file = new FileChooserForLoadingData();
	
	
	
	public void sendLoadRequest(){
	
	    String file_name = choose_file.getFileName();
	    
	    Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs/load_data/"+file_name);
		String response = resource.accept("text/plain").get(String.class);
		
		response_str = response.toString();
		System.out.println(response_str);
		
	}
	
    public void setCreateTableRequest(){
    	
    	Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs/load_data/");
		String response = resource.accept("text/plain").get(String.class);
    }

}

