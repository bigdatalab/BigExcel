package org.bsanalytics.apis.viewdata;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/view_data")
public class LoadTable {

	
	@GET
	@Path("/{table_name}")	
	@Produces("text/json")
	public String connectionEstablishement(
			@PathParam("table_name") String table_name){	
        return new LoadTableLogic().initializeDBConnection(table_name);
		
		
	}
	
	
	@GET
	@Path("/{fetch_size}")
	@Produces("text/json")
	public List<List<Object>> loadInitialDataFromToHiveTable(
			@PathParam("fetch_size") String fetch_size){
		return new LoadTableLogic().getInitialTable(fetch_size);		
		
	}
	
	
/*	@GET
	@Path("/{table_name}/{column_count}/{fetch_size}/{partial}")
	@Produces("text/json")
	public List<List<Object>> loadPartialDataFromToHiveTable(
			@PathParam("table_name") String table_name,
			@PathParam("column_count") String column_count,
			@PathParam("fetch_size") String fetch_size,
			@PathParam("partial") String partial){
		return null;
		
	}*/
	
	
}
