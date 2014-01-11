package org.bsanalytics.apis.viewdata;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("/view_table")
public class LoadTable {

	
	@GET
	@Path("/{view_hive_table}")
	@Produces("text/plain")
	public List<List<String>> loadDataFromToHiveTable(@PathParam("view_hive_table") String table_name){
		return new LoadTableFromDataBase().loadDataFromTable(table_name);
		
	}
	
	
}
