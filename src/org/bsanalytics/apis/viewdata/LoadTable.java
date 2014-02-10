package org.bsanalytics.apis.viewdata;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("/view_data")
public class LoadTable {

	
	@GET
	@Path("/{table_name}")	
	@Produces("text/json")
	public String connectionEstablishement(
			@PathParam("table_name") String table_name){	
        return LoadTableLogic.initializeDBConnection(table_name);
		
		
	}
	
	
	@GET
	@Path("/{fetch_size}/{data}")
	@Produces("text/json")
	public String loadInitialDataFromToHiveTable(
			@PathParam("fetch_size") String fetch_size,
			@PathParam("data") String data){
		return LoadTableLogic.getInitialTable(fetch_size);		
		
	}
	
	//getting column names
	@GET
	@Path("/{table_name}/{load}/{columns}")	
	@Produces("text/json")
	public String load_columns(
			@PathParam("table_name") String table_name){	
        return LoadTableLogic.getcolumnNamesonFirstCall(table_name);
		
		
	}
	
	/*@GET
	@Path("/{table_name}/{fetch_size}/{partial_load}/{analytics}")
	@Produces("text/json")
	public String loadPartialDataFromToHiveTable(
			@PathParam("table_name") String table_name,
			@PathParam("fetch_size") String fetch_size){
		System.out.println("call is coming here");
		return LoadTableLogic.getCustomPartialData(table_name,fetch_size);
		
	}*/
	
}
