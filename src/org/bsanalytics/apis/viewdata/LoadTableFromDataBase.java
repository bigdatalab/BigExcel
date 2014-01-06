package org.bsanalytics.apis.viewdata;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bsanalytics.hive.LoadHiveConnection;

public class LoadTableFromDataBase {
	
	LoadHiveConnection hcon = new LoadHiveConnection();
	Statement stmt = hcon.getHiveConnection();
	List<List<Integer>> table_rows_list = new ArrayList<>();
	
	public List<List<Integer>> loadDataFromTable(String table_name){
		
		String table_exists = "select * from "+table_name;
		try {
			ResultSet res = stmt.executeQuery(table_exists);
			ResultSetMetaData metadata = res.getMetaData();
			int column_count = metadata.getColumnCount();
			
			while (res.next()){
				//new list for every row
				List<Integer> row = new ArrayList<>(column_count); 
				
				int count=1;
				while (count <= column_count) {  
					row.add(res.getInt(count++));
			    }
			     table_rows_list.add(row); // add it to the nested list
			}
		
		} catch (SQLException e) {
			e.printStackTrace();		}
		
		return table_rows_list;
	}

}
