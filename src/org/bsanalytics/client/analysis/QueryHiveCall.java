package org.bsanalytics.client.analysis;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.bsanalytics.client.loaddata.ClientObject;
import org.bsanalytics.client.loaddata.LoadDataBean;
import org.bsanalytics.dashboard.ServerAccessPath;
import org.bsanalytics.general.client.ApplicationMessages;

public class QueryHiveCall {
	
	String response_str;
	ClientObject cObj = new ClientObject();
	RestClient client_wink= cObj.getClientObject();
	HiveQueryBean take_hive_query = new HiveQueryBean();
	ApplicationMessages faceMessage = new ApplicationMessages();
	static String hive_query=null;
	
	public String getResponse_str() {
		return response_str;
	}

	public void setResponse_str(String response_str) {
		this.response_str = response_str;
	}
	
	public void QueryHive(){
		
		
		hive_query = take_hive_query.getHive_query().trim();
    	System.out.println("hive query = " + hive_query);
    	//System.out.println("============Before Client Call===========");
    	StringBuilder sb = new StringBuilder(hive_query);
    	Resource resource = client_wink.resource(
    	ServerAccessPath.SERVER_PATH + "/bsanalytics/jaxrs_query/query_data/hive_query");
    	response_str = resource.accept("text/plain").post(String.class,hive_query);
    	setResponse_str(response_str);
    	setFaceMessageForThisCall(response_str);
    	//System.out.println("============After Client Call=============");
		//System.out.println("After Getting response");		
	}
	
	
	public void setFaceMessageForThisCall(String message){
    	//for setting bean
    	//create_del_query.setApplicationresponse(message);
		//for message rendering
		faceMessage.addFacesMessage(message);
    }
	
/*	public static void main(String args[]){
		
		hive_query = "select  count(*) from sample";
		new QueryHiveCall().QueryHive();
		
	}*/
	
}
