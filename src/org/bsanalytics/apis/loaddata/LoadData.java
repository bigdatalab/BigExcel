package org.bsanalytics.apis.loaddata;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/load_data")
public class LoadData {
	
	@GET
	@Path("/{load_hive_data}")
	@Produces("text/plain")
	public String loadDataIntoToHiveTable(@PathParam("load_hive_data") String file_name){
		return new LoadDataLogic().loadData(file_name);
	}

}
