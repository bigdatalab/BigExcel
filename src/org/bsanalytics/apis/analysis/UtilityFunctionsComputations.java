package org.bsanalytics.apis.analysis;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bsanalytics.hive.ConnectionManager;

public class UtilityFunctionsComputations {
	
	ConnectionManager hcon=null;
	Statement stmt_hive = null;
	ResultSet res;
	
	public String performFunction(String table_column_function_name){
		getHiveResources();
		System.out.println("After getting hive sources");
		String subStrings[] = table_column_function_name.split(":");
		String table_name = subStrings[0];
		String column_name = subStrings[1];
		String function_name = subStrings[2];
		
		System.out.println("First Check");
		if (!checkTableExists(table_name))
			return "Table not exists"; 
		
		System.out.println("Second Check");
		if (!checkColumnExists(table_name, column_name))
			return "No such column"; 
		
		System.out.println("Third Check");
		String result = calculateAverage(table_name, column_name, function_name);
		
		closeHiveResources();
		
		return result;
	}

	
	public void getHiveResources(){	
		hcon = new ConnectionManager();
		stmt_hive = hcon.getHiveConnection();		
	}
	
	public void closeHiveResources(){		
		hcon.closeHiveConnection();
	}
	
	
	public boolean checkTableExists(String table_name){
		
		String table_exists = "select * from "+table_name;
		try {
			ResultSet res = stmt_hive.executeQuery(table_exists);
			if (res != null)
				return true;
			else
				return false;
		
		} catch (SQLException e) {
			return false;
		}
		
	}
	
	
	public boolean checkColumnExists(String table_name , String column_name){
		
		String column_exists = "select "+ column_name +" from "+table_name;
		try {
			ResultSet res = stmt_hive.executeQuery(column_exists);
			if (res != null)
				return true;
			else
				return false;
		
		} catch (SQLException e) {
			return false;
		}
		
	}
	
	public String calculateAverage(String table_name, 
			String column_name, String function_name){
		
		String return_result=null;
		
		switch(function_name){
		
		//sum case
		case "Sum":
			System.out.println("Sum Case");
			String query_sum = "select sum("+column_name+") from "+table_name;
			
			try {
				res = stmt_hive.executeQuery(query_sum);
				while(res.next())
			    {
			    	System.out.println(res.getInt(1));
			    	return_result = Integer.toString(res.getInt(1));
			    }
				
			} catch (SQLException e) {
				e.printStackTrace();
				return "Unable to compute the function";
			}
			break;
		
		
			//average case
			case "Average":
			
			String query_average = "select avg("+column_name+") from "+table_name;
			
			try {
				res = stmt_hive.executeQuery(query_average);
				while(res.next())
			    {
			    	System.out.println(res.getDouble(1));
			    	return_result = Double.toString(res.getDouble(1));
			    }
								
			} catch (SQLException e) {
				e.printStackTrace();
				return "Unable to compute the function";
			}
			break;
			
			
			//average case
			case "Standarad Deviation":
			
			String query_sd = "select stddev_pop("+column_name+") from "+table_name;
			
			try {
				res = stmt_hive.executeQuery(query_sd);
				while(res.next())
			    {
			    	System.out.println(res.getDouble(1));
			    	return_result = Double.toString(res.getDouble(1));
			    }
								
			} catch (SQLException e) {
				e.printStackTrace();
				return "Unable to compute the function";
			}
			break;
		
		
		}//End of switch statement
		
				
		return return_result;
		
		
	} //End of calculate average
	
	/*
	public static void main(String args[]){
		new UtilityFunctionsComputations().performFunction("sample:first_name:Average");
	}*/
}
