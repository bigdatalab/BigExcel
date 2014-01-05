package org.bsanalytics.client.loaddata;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.bsanalytics.dashboard.FileChooserForLoadingData;

public class ClientLoadDataCall {
	
	String response_str=null;
	ClientObject cObj = new ClientObject();
	RestClient client_wink= cObj.getClientObject();
	FileChooserForLoadingData choose_file = new FileChooserForLoadingData();
	CreateDeleteTableBean create_del_query = new CreateDeleteTableBean();
	
	
	public String sendLoadRequest(){
	
	    String file_name = choose_file.getFileName();
	    String file_path = choose_file.getPath();
	    
	    String path_and_file = file_path + ":" + file_name;
	    System.out.println("===Coming Here===");
	    System.out.println(path_and_file);
	    
	    Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs/load_data/load_hive_data");
		String response = resource.accept("text/plain").post(String.class,path_and_file);
		
		response_str = response.toString();
		System.out.println(response_str);
		return response_str;
		
	}
	
    public String sendCreateTableRequest(){
    	
    	String table_string= create_del_query.getQuery().trim(); 
    	Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs/load_data/create_hive_table");
		String response = resource.accept("text/plain").post(String.class,table_string);
		return response;
	
    }
    
    
    public String sendDeleteTableRequest(){
    	
    	String table_string= create_del_query.getDelete_table_query().trim();
    	Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs/load_data/delete_hive_table");
		String response = resource.accept("text/plain").post(String.class,table_string);
		System.out.println(response);
		return response;
	
    }

}

