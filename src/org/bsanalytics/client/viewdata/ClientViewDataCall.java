package org.bsanalytics.client.viewdata;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.bsanalytics.client.loaddata.ClientObject;
import org.bsanalytics.dashboard.ServerAccessPath;

public class ClientViewDataCall {
	
	ClientObject cObj = new ClientObject();
	RestClient client_wink= cObj.getClientObject();
	   	
	   public String sendViewDataRequest(){
	    	
	   // 	String table_string= create_del_query.getQuery().trim(); 
	    	Resource resource = client_wink.resource(ServerAccessPath.SERVER_PATH +"/bsanalytics/jaxrs/load_data/create_hive_table");
			String response=null;// = resource.accept("text/plain").post(String.class,table_string);
			System.out.println(response);
			return response;
		
	    }

}
