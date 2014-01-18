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
			@PathParam("fetch_size") String data){
		return LoadTableLogic.getInitialTable(fetch_size);		
		
	}
	
	
	@GET
	@Path("/{table_name}/{load}/{columns}")	
	@Produces("text/json")
	public String load_columns(
			@PathParam("table_name") String table_name){	
        return LoadTableLogic.getcolumnNamesonFirstCall(table_name);
		
		
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
