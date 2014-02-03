package org.bsanalytics.apis.analysis;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/query_data")
public class QueryData {
	
	@POST
	@Path("/hive_query")	
	@Produces("text/plain")
	public String collectQueryResult(String hive_query){	
        return new QueryDataLogic().getQuery(hive_query);
		
		
	}

}
