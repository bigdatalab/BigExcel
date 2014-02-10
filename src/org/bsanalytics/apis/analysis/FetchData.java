package org.bsanalytics.apis.analysis;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bsanalytics.general.server.ServerSideGsonConversion;
import org.bsanalytics.hive.LoadHiveConnection;

public class FetchData {
	
	LoadHiveConnection hcon=null;
	Statement stmt=null;
	ResultSet res=null;
	ResultSetMetaData metadata=null;
	int column_count=0;
	List<List<Object>> table_rows_list = null;
	ServerSideGsonConversion gson_conv;
	
	
	public void getHiveResources(){
		hcon = new LoadHiveConnection();
		stmt = hcon.getHiveConnection();
	}
	
	
	public void closeHiveResources(){
		
		hcon.closeHiveConnection();
	}
	
	
	public String loadDataFromTabe(String table_name, String range_rows){
		
		getHiveResources();
		
		String table_exists = "select * from "+table_name;
		try {

			res = stmt.executeQuery(table_exists);			
			metadata = res.getMetaData();
			column_count = metadata.getColumnCount();
			
			//getting the range
			String[] range = range_rows.split(":");
			int first_number = Integer.parseInt(range[0]);
			int second_number = Integer.parseInt(range[1]);
			
			int total_rows = second_number - first_number;
			
			for (int i=1 ; i<first_number ; i++)
				res.next();
			
			//getting the desired rows			
			int check=1;
			List<Object> rows=null;
			
			//for accessing fresh chunked data every time
			table_rows_list = new ArrayList<>();
			
							
				//short circuit is necessary not to skip the record		
				while (check<=total_rows && res.next()){
					//new list for every row
					rows = new ArrayList<>(); 
					//System.out.println(column_count);
					int count=1;
					while (count <= column_count) {  
						//System.out.println(res.getString(count));
						//System.out.println(count);
						String temp = res.getString(count++);
						if (temp != null){
							int lenth = temp.length();
							temp = temp.substring(1, lenth-1);
							}
						//System.out.println(temp);  
						   rows.add(temp);
						 
				    }
					//System.out.println(rows);
					table_rows_list.add(rows);					
					check++;
				}
				
	    		
				gson_conv = new ServerSideGsonConversion();
				gson_conv.setListForConversion(table_rows_list); 
				
						
		} catch (SQLException e) {
			closeHiveResources();
			e.printStackTrace();
		}
		
		closeHiveResources();
		return gson_conv.getConvertedString();
	}
	
	
	
	public static void main(String args[]){
		
		System.out.println(new FetchData().loadDataFromTabe("sample", "3:4"));
	}

}
