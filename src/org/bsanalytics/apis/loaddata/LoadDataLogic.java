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
	
		
		try {
			String table_name = getTableName(file_name_path);
			String file_name = getFileName(file_name_path);
			boolean result = checkTableExists(table_name);
			System.out.println("===Comming here=== boolean result = " + result);
			
			if (result)
			{   
				LoadFiletoHDFS(file_name);
				String sql= "load data inpath '"+file_name+"' into table "+ table_name;
				System.out.println(sql);
				ResultSet res = stmt.executeQuery(sql);
			}
				    
				
		} catch (SQLException e) {
			e.printStackTrace(); 
			//System.out.println("Connection Refused");
		}
		return "";
	}
	
	
	public String CreateTable(String table_string){
				
		try {
			
			ResultSet res = stmt.executeQuery(table_string);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return "success";
	}
	
	
	public String DeleteTable(String table_string){
		
		String sql = "DROP TABLE " + table_string;
		
		try {
			ResultSet res = stmt.executeQuery(sql);
			if (res == null)
				System.out.println("Yes its null");
				
		} catch (SQLException e) {
			return "Deletion Not Successfull";
		}
		
		return "Deletion Successfull";
	}
	
	
	public void LoadFiletoHDFS(String file_name){
		
		try {
			//String file_name = LOAD_HDFS+" "+getPathName(file_name_path);
			hadoop_file_system.LoadFilesToHDFS(file_name);

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
			e.printStackTrace();
		}
		
		return false;
	}

}
