package org.bsanalytics.client.loaddata;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.bsanalytics.general.client.ApplicationMessages;



public class ClientLoadDataCall extends Thread {
	
	String response_str=null;
	ClientObject cObj = new ClientObject();
	RestClient client_wink= cObj.getClientObject();
	FileChooserForLoadingData choose_file = new FileChooserForLoadingData();
	LoadDataBean create_del_query = new LoadDataBean();
	ApplicationMessages faceMessage = new ApplicationMessages();
		
	
	
	public String sendLoadRequest(){
	
	    /*String file_name = choose_file.getFileName();
	    String file_path = choose_file.getPath();
	    String path_and_file = file_path + ":" + file_name;*/
	    //starting Thread
	    //new SendLoadRequest().start();
	    /*Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs/load_data/load_hive_data");
		String response = resource.accept("text/plain").post(String.class,path_and_file);
		
		response_str = response.toString();
		System.out.println(response_str);
		return response_str;*/
		
		int total_csv_file_rows = choose_file.total_csv_file_rows;		
	    String file_name = choose_file.getFileName();
	    String file_path = choose_file.getPath();
	    String path_and_file = file_path + ":" + file_name +
	    		":" + Integer.toString(total_csv_file_rows);
	    
	    System.out.println("Three = " + path_and_file);
	    
	    	    
	    Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs_load/load_data/load_hive_data");
		String response = resource.accept("text/plain").post(String.class,path_and_file);
		setFaceMessageForThisCall(response);
		System.out.println(response);		
	    return response;
		
	}
	
    public String sendCreateTableRequest(){
    	
    	//setFaceMessageForThisCall("");		
    	String table_string= create_del_query.getCreatequery().trim();
    	System.out.println(table_string);
    	Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs_load/load_data/create_hive_table");
		String response = resource.accept("text/plain").post(String.class,table_string);
		setFaceMessageForThisCall(response);
		System.out.println(response);
		return response;
	
    }
    
    
    public String sendDeleteTableRequest(){
    	
    	
    	String table_string= create_del_query.getDeletequery().trim();
    	System.out.println("query = " + table_string);
    	Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs_load/load_data/delete_hive_table");
    	System.out.println("After Web Call");
		String response = resource.accept("text/plain").post(String.class,table_string);
		System.out.println("After Getting response");
		setFaceMessageForThisCall(response);
		return response;
	
    }	
    
    public void setFaceMessageForThisCall(String message){
    	//for setting bean
    	create_del_query.setApplicationresponse(message);
		//for message rendering
		faceMessage.addFacesMessage(message);
    }
	
}

