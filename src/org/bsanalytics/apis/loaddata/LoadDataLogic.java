package org.bsanalytics.apis.loaddata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bsanalytics.hadoop.AccessHadoopFileSystem;
import org.bsanalytics.hive.LoadHiveConnection;

public class LoadDataLogic {
	
	static String LOAD_HDFS = "hdfs dfs -copyFromLocal";
	
	LoadHiveConnection hcon = new LoadHiveConnection();
	Statement stmt = hcon.getHiveConnection();
	AccessHadoopFileSystem hadoop_file_system =  new AccessHadoopFileSystem();
	
	public String LoadData(String file_name_path){
		String table_name=null;
		
		try {
			table_name = getTableName(file_name_path);
			String file_name = getFileName(file_name_path);
			String path = getPathName(file_name_path);
			
			boolean result = checkTableExists(table_name);
			
			if (result)
			{   
				LoadFiletoHDFS(file_name,path);
				String sql= "load data inpath '"+file_name+"' into table "+ table_name;
				System.out.println(sql);
				stmt.executeUpdate(sql);
			}
			else
				return "Table " + "'"+table_name +"'"+ " Not Found";
				    
				
		} catch (SQLException e) {
			e.printStackTrace(); 
			//System.out.println("Connection Refused");
		}
		return "Data Loaded Successfully into table " + table_name;
	}
	
	
	public String CreateTable(String table_string){
		
		/*StringBuilder stb = new StringBuilder(table_string);
		int table_index = stb.indexOf("table");
		
		if (table_index == -1){
			
			int table_index_capital = stb.indexOf("TABLE"); 
			table_index = table_index_capital;
		}
		
		table_index = table_index+5;
		int left_brace_index = stb.indexOf("(");
		String table_name = stb.substring(table_index,left_brace_index);
		table_name = table_name.trim();
		System.out.println("===table_name=== " + table_name);
		
		boolean result = checkTableExists(table_name);		
		if (result)
			return "Table already exists";*/
		
		try {
			
			stmt.executeUpdate(table_string + " row format delimited fields terminated by ',' stored as textfile");
			
		} catch (SQLException e) {
			return "Table already exists";
		}		
		return "Table Create Successfully";
	}
	
	
	
	public String DeleteTable(String table_string){
		
		String sql = "DROP TABLE " + table_string;
		boolean result = checkTableExists(table_string);
		
		if (!result)
			return "Table not exists";
		
		try {
			stmt.executeUpdate(sql);
										
		} catch (SQLException e) {
			return "Deletion Not Successfull";
		}
		
		return "Deletion Successfull";
	}
	
	
	public void LoadFiletoHDFS(String file_name, String path){
		
		try {
			//String file_name = LOAD_HDFS+" "+getPathName(file_name_path);
			hadoop_file_system.LoadFilesToHDFS(file_name,path);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String getTableName(String file_name_path){
		
		    String[] file_path_and_name = file_name_path.split(":");
		 	StringBuilder strb = new StringBuilder(file_path_and_name[1]);
		    int point_index = strb.indexOf(".");
		    String table_name = strb.substring(0, point_index);
		    return table_name;
		
	}
	
	public String getPathName(String file_name_path){
		
	    String[] file_path_and_name = file_name_path.split(":");
	 	 return file_path_and_name[0];
	
	}
	
	
	public String getFileName(String file_name_path){
		
	    String[] file_path_and_name = file_name_path.split(":");
	 	 return file_path_and_name[1];
	
	}
	
	
	public boolean checkTableExists(String table_name){
		
		String table_exists = "select * from "+table_name;
		try {
			ResultSet res = stmt.executeQuery(table_exists);
			if (res != null)
				return true;
			else
				return false;
		
		} catch (SQLException e) {
			return false;
		}
		
		
	}

}
