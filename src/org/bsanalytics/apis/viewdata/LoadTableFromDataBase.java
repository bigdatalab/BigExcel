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
	
	
	static LoadHiveConnection hcon = new LoadHiveConnection();
	static Statement stmt = hcon.getHiveConnection();
	static List<List<Object>> table_rows_list = new ArrayList<>();
	static ResultSetMetaData metadata;
	static ResultSet res;
	static int column_count;
	
	public static List<List<Object>> loadDataFromTable(String table_name){
		
		String table_exists = "select * from "+table_name;
		try {
			//stmt.setFetchSize(10);
			res = stmt.executeQuery(table_exists);			
			metadata = res.getMetaData();
			column_count = metadata.getColumnCount();
			
			for(int i=1 ; i<=column_count ; i++){
			 List<String> li = new ArrayList<>();
			 System.out.println(li.add(metadata.getColumnLabel(i)));
			}
			
		
		} catch (SQLException e) {
			List<List<Object>> li = new ArrayList<>();
			List<Object> lii = new ArrayList<>();
			lii.add("Table not exists");
			li.add(lii);
			return li;
			//e.printStackTrace();		}			
		}
		
		return table_rows_list;
	}
	
	public static List<List<Object>> getListTwo(){
		return table_rows_list;
	}
	
		
	public static List<Object> getTCustomRowsList(int column_count, int fetch_size){
		
		//variable for getting the rows according to fetch_size
		int check=1;
		List<Object> rows=null;
		
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
	public static int getColumnCount(){
		return column_count;
	}
	
	/*not supported*/
	/*public void checkPrevious(int column_count, int fetch_size){
		
		
		//variable for getting the rows according to fetch_size
				int check=1;
				List<String> rows=null;
		try {
			while(check<=fetch_size && res.previous()){
			
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
				System.out.println(rows);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	
	
	
	public static void main(String args[]){
		LoadTableFromDataBase l = new LoadTableFromDataBase();
	    l.loadDataFromTable("sample");
	    //System.out.println(l.getRowCount("sample"));
	    while(true){
	    	
	    	if (l.getTCustomRowsList(column_count, 2) == null){
	    		System.out.println("breaking---");
	    		break;
	    	}
	    	System.out.println("call---");
	    	System.out.println(l.getListTwo());
	    	//System.out.println(l.getRowCount("sample"));
	    	
	    	
	    	try {
				Thread.sleep(1000);
				/*l.checkPrevious(column_count,2);
				System.exit(0);*/
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    	
	    	
	    }
	    
	    
        
	    //System.out.println(l.getTCustomRowsList(6));
	}
	
}