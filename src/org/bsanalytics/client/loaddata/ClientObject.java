package org.bsanalytics.client.loaddata;

import org.apache.wink.client.ClientConfig;
import org.apache.wink.client.RestClient;

public class ClientObject {
	
	
	/*declaring the client and configuration*/
	RestClient client_wink=null;
	ClientConfig clientConfig=null;
	
	
	//setting all initial values
	public ClientObject(){
		
		clientConfig = new ClientConfig();
		
		//wait unlimited amount of time for socket read timeout
		clientConfig.readTimeout(0);
		
		//wait unlimited amount of time for connection timeout
		clientConfig.connectTimeout(0);
		client_wink = new RestClient(clientConfig);
		
	}
	
	public RestClient getClientObject(){
		return this.client_wink;
	}

}
