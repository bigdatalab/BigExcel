package org.bsanalytics.apis.loaddata;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public class LoadData {
	
	@GET
	@Path("/load_data")
	@Produces("text/plain")
	public String loadDataIntoToHiveTable(){
		return new LoadDataLogic().loadData();
	}

}
