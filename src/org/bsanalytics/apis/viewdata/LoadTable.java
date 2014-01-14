package org.bsanalytics.apis.viewdata;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/view_table")
public class LoadTable {

	
	@GET
	@Path("/{view_hive_table_initial}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<List<Object>> loadInitialDataFromToHiveTable(@PathParam("view_hive_table_initial") String table_name){
		return new LoadTableLogic().getInitialTable(table_name);
		
	}
	
	
	@GET
	@Path("/{view_hive_table_partial}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<List<Object>> loadPartialDataFromToHiveTable(@PathParam("view_hive_table_partial") String table_name){
		return new LoadTableLogic().getInitialTable(table_name);
		
	}
	
	
}
