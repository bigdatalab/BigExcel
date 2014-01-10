package org.bsanalytics.client.loaddata;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

public class SendLoadRequest extends Thread {
	
	ClientObject cObj = new ClientObject();
	RestClient client_wink= cObj.getClientObject();
	FileChooserForLoadingData choose_file = new FileChooserForLoadingData();
	CreateDeleteTableBean create_del_query = new CreateDeleteTableBean();

	 public void run() {
		    System.out.println("Thread Started");
		 	String file_name = choose_file.getFileName();
		    String file_path = choose_file.getPath();
		    String path_and_file = file_path + ":" + file_name;
		    
		    Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs/load_data/load_hive_data");
			String response = resource.accept("text/plain").post(String.class,path_and_file);
			System.out.println(response);
	    }
}
