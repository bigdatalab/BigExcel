package org.bsanalytics.apis.analysis;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.bsanalytics.apis.viewdata.LoadTableLogic;

@Path("/query_data")
public class QueryData {
	
	@POST
	@Path("/hive_query")	
	@Produces("text/plain")
	public String collectQueryResult(String hive_query){	
        return new QueryDataLogic().getQuery(hive_query);
		
		
	}
	
	
	@POST
	@Path("/utility_functions")	
	@Produces("text/plain")
	public String performUtilityFunction(String table_column_function_name){	
        return new UtilityFunctionsLogic().performFunction(table_column_function_name);
		
	}
	
	//using view data API
	@GET
	@Path("/{partial_data}")	
	@Produces("text/json")
	public String getPartialData(
			@PathParam("partial_data") String partial_data){	
		return LoadTableLogic.getInitialTable(partial_data);
		
	}
	
	@GET
	@Path("/{table_name}/{range}/{partial_load}/{analytics}")
	@Produces("text/json")
	public String loadPartialDataFromToHiveTable(
			@PathParam("table_name") String table_name,
			@PathParam("range") String range){
		return new FetchData().loadDataFromTabe(table_name, range);				
		
	}

}
