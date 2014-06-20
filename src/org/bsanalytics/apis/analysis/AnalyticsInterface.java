package org.bsanalytics.apis.analysis;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.bsanalytics.apis.viewdata.LoadTableLogic;

@Path("/query_data")
public class AnalyticsInterface {
	
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
        return new UtilityFunctionsComputations().performFunction(table_column_function_name);
		
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
		return new CustomeScriptAnalysis().loadDataFromTabe(table_name, range);				
		
	}
	
	
	@GET
	@Path("/{query}/{path}/{partial_load}/{analytics}/{graph_creation}")
	@Produces("text/json")
	public String createGraph(
			@PathParam("query") String query,
			@PathParam("path") String path){
		//return "Hello";
		return new CustomeScriptAnalysis().fetchDataForGraphCreation(query,path);				
		
	}
	
	
	@GET
	@Path("/{query}/{path}/{partial_load}/{analytics}/{graph_creation}/{result}")
	@Produces("text/json")
	public String createGraphREsult(
			@PathParam("query") String query,
			@PathParam("path") String path){
		//return "Hello";
		return new CustomeScriptAnalysis().fetchDataForGraphCreationResult();				
		
	}


}
