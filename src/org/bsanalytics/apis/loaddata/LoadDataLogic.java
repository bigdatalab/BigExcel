package org.bsanalytics.apis.loaddata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bsanalytics.hive.LoadHiveConnection;

public class LoadDataLogic {
	
	public String loadData(String file_name){
		
		LoadHiveConnection hcon = new LoadHiveConnection();
		Statement stmt = hcon.getHiveConnection();
		
		try {
		    StringBuilder strb = new StringBuilder(file_name);
		    int point_index = strb.indexOf(".");
		    String table_name = strb.substring(0, point_index);
			String tabl_exists = "select * from "+table_name;
		    String sql= "load data inpath '"+file_name+"' into table "+ table_name;
			ResultSet res = stmt.executeQuery(sql);
				
		} catch (SQLException e) {
			e.printStackTrace(); 
			//System.out.println("Connection Refused");
		}
		return file_name;
	}

}
