package org.bsanalytics.client.analysis;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.bsanalytics.client.loaddata.ClientObject;
import org.bsanalytics.dashboard.ServerAccessPath;
import org.bsanalytics.general.client.ApplicationMessages;

public class QueryHiveCall {
	
	String response_str;
	ClientObject cObj = new ClientObject();
	RestClient client_wink= cObj.getClientObject();
	
	HiveQueryBean take_hive_query = new HiveQueryBean();
	UtilityFunctionBean take_utility_function = new UtilityFunctionBean();
	
	ApplicationMessages faceMessage = new ApplicationMessages();
	String hive_query=null;
	String compute_function_query=null;
	
	public String getResponse_str() {
		return response_str;
	}

	public void setResponse_str(String response_str) {
		this.response_str = response_str;
	}
	
	public void queryHive(){
		
		
		hive_query = take_hive_query.getHive_query().trim();
    	System.out.println("hive query = " + hive_query);
    	//System.out.println("============Before Client Call===========");
    	Resource resource = client_wink.resource(
    	ServerAccessPath.SERVER_PATH + "/bsanalytics/jaxrs_query/query_data/hive_query");
    	response_str = resource.accept("text/plain").post(String.class,hive_query);
    	setResponse_str(response_str);
    	setFaceMessageForThisCall(response_str);
    	//System.out.println("============After Client Call=============");
		//System.out.println("After Getting response");		
	}
	
	
	public void computeUtilityFunctions(){
		String table_name = take_utility_function.getTable_name();
		String column_name = take_utility_function.getColumn_name();
		String function_name = take_utility_function.getSelected_item();
				
		if (table_name.length() == 0)
		{
			setResponse_str("Please enter table name");
			setFaceMessageForThisCall("Please enter table name");
			return;
		}
				
		if (column_name.length() == 0)
		{
			setResponse_str("Please enter column name");
			setFaceMessageForThisCall("Please enter column name");
			return;
		}
		
		if (function_name.length() == 0)
		{
			setResponse_str("Please select the function");
			setFaceMessageForThisCall("Please select the function");
			return;
		}
		
		
		compute_function_query = table_name + ":" + column_name + ":" + function_name;
		Resource resource = client_wink.resource(
		    	ServerAccessPath.SERVER_PATH + "/bsanalytics/jaxrs_query/query_data/utility_functions");
		    	response_str = resource.accept("text/plain").post(String.class,compute_function_query);
		setResponse_str(response_str);
		setFaceMessageForThisCall(response_str);
	}
		
	public void setFaceMessageForThisCall(String message){
    	//for message rendering
		faceMessage.addFacesMessage(message);
    }
	
/*	public static void main(String args[]){
		
		hive_query = "select  count(*) from sample";
		new QueryHiveCall().QueryHive();
		
	}*/
	
}
