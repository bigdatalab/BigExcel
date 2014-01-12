package org.bsanalytics.apis.viewdata;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.hive.jdbc.HiveBaseResultSet;
import org.apache.hive.jdbc.HiveCallableStatement;
import org.bsanalytics.hive.LoadHiveConnection;

public class LoadTableFromDataBase {
	
	
	LoadHiveConnection hcon = new LoadHiveConnection();
	Statement stmt = hcon.getHiveConnection();
	List<List<String>> table_rows_list = new ArrayList<>();
	ResultSetMetaData metadata;
	ResultSet res;
	static int column_count;
	
	
	public List<List<String>> loadDataFromTable(String table_name){
		
		String table_exists = "select * from "+table_name;
		try {
			//stmt.setFetchSize(10);
			res = stmt.executeQuery(table_exists);			
			metadata = res.getMetaData();
			column_count = metadata.getColumnCount();
		
		} catch (SQLException e) {
			e.printStackTrace();		}
		
		return table_rows_list;
	}
	
	public List<List<String>> getListTwo(){
		return this.table_rows_list;
	}
	
		
	public List<String> getTCustomRowsList(int column_count, int fetch_size){
		
		//variable for getting the rows according to fetch_size
		int check=1;
		List<String> rows=null;
		
		//for accessing fresh chunked data every time
		table_rows_list = new ArrayList<>();
		
		try {
			
			//short circuit is necessary not to skip the record		
			while (check<=fetch_size && res.next()){
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
			return rows;
              
		} catch (SQLException e) {
			//breaking point of the cursor
			return null;
		}		
}
	public int getColumnCount(){
		return column_count;
	}
	
	
	
	
	/*public static void main(String args[]){
		LoadTableFromDataBase l = new LoadTableFromDataBase();
	    l.loadDataFromTable("sample");
	    //System.out.println(l.getRowCount("sample"));
	    while(true){
	    	
	    	if (l.getTCustomRowsList(column_count, 7) == null){
	    		System.out.println("breaking---");
	    		break;
	    	}
	    	System.out.println("call---");
	    	System.out.println(l.getListTwo());
	    	//System.out.println(l.getRowCount("sample"));
	    	
	    	
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
        
	    //System.out.println(l.getTCustomRowsList(6));
	}*/
	
}