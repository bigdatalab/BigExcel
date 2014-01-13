package org.bsanalytics.client.loaddata;


import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

public class ClientLoadDataCall extends Thread {
	
	String response_str=null;
	ClientObject cObj = new ClientObject();
	RestClient client_wink= cObj.getClientObject();
	FileChooserForLoadingData choose_file = new FileChooserForLoadingData();
	LoadDataBean create_del_query = new LoadDataBean();
	
	
	public String sendLoadRequest(){
	
	    /*String file_name = choose_file.getFileName();
	    String file_path = choose_file.getPath();
	    String path_and_file = file_path + ":" + file_name;*/
	    //starting Thread
	    new SendLoadRequest().start();
	    /*Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs/load_data/load_hive_data");
		String response = resource.accept("text/plain").post(String.class,path_and_file);
		
		response_str = response.toString();
		System.out.println(response_str);
		return response_str;*/
	    return "";
		
	}
	
    public String sendCreateTableRequest(){
    	
    	String table_string= create_del_query.getCreatequery().trim();
    	System.out.println(table_string);
    	Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs/load_data/create_hive_table");
		String response = resource.accept("text/plain").post(String.class,table_string);
		System.out.println(response);
		return response;
	
    }
    
    
    public String sendDeleteTableRequest(){
    	
    	String table_string= create_del_query.getDeletequery().trim();
    	Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs/load_data/delete_hive_table");
		String response = resource.accept("text/plain").post(String.class,table_string);
		//setting Response
		create_del_query.setApplicationresponse(response);
		return response;
	
    }	
	
}

