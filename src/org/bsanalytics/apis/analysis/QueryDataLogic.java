package org.bsanalytics.apis.analysis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bsanalytics.hive.ConnectionManager;


public class QueryDataLogic {
	ConnectionManager hcon = new ConnectionManager();
	Statement stmt_hive = null;
	ResultSet res;
	
	public String getQuery(String query){
		
		getHiveResources();
		
		try {
			res = stmt_hive.executeQuery(query);
		    while(res.next())
		    {
		    	System.out.println(res.getInt(1));
		    }
			closeHiveResources();
			return Integer.toString(res.getInt(1));			
		} catch (SQLException e) {
			closeHiveResources();
			e.printStackTrace();
			return "Error while fetching the query result";
		}		
	}
	
	
	public void getHiveResources(){	
		stmt_hive = hcon.getHiveConnection();		
	}
	
	public void closeHiveResources(){		
		hcon.closeHiveConnection();
	}
	
/*	public static void main(String args[]){
		
		new QueryDataLogic().getQuery("select count(*) from sample");
		
	}*/

}
