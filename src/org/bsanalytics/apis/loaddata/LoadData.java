package org.bsanalytics.apis.loaddata;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/load_data")
public class LoadData {
	
	@POST
	@Path("/load_hive_data")
	@Produces("text/plain")
	public String LoadDataIntoToHiveTable(String file_name_path){
		return new LoadDataLogic().LoadData(file_name_path);
		
	}

	
	@POST
	@Path("/create_hive_table")
	@Produces("text/plain")
	public String CreateHiveTable(String table_string){	
	return new LoadDataLogic().CreateTable(table_string);
		//return "Hello";
		
	}
		
	
	@POST
	@Path("/delete_hive_table")
	@Produces("text/plain")
	public String DeleteHiveTable(String table_string){	
		System.out.println("delete hive table");
		return new LoadDataLogic().DeleteTable(table_string);
	}
	
}
