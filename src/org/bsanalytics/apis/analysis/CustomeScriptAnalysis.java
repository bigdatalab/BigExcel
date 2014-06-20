package org.bsanalytics.apis.analysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import org.bsanalytics.apis.queryconstructor.QueryConstructor;
import org.bsanalytics.general.server.ServerSideGsonConversion;
import org.bsanalytics.hive.ConnectionManager;


/**
 * @author asif
 * This class is handling custom scripts and preparing data for graph generation,
 * also handling partial data loading of a large table
 * 
 */
public class CustomeScriptAnalysis {
	
	ConnectionManager hcon=null;
	Statement stmt=null;
	ResultSet res=null;
	ResultSetMetaData metadata=null;
	int column_count=0;
	List<List<Object>> table_rows_list = null;
	ServerSideGsonConversion gson_conv;
	QueryConstructor qc=null;
	
	
	public void getHiveResources(){
		hcon = new ConnectionManager();
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
	
	
	
	public String fetchDataForGraphCreation(String query, String path){
		
		
		getHiveResources();
		String temp=null;
		StringBuilder stb=null;
		
		String split[] =  path.split("-");
		int len = split.length;
		StringBuilder stb_path = new StringBuilder();
		for (int i=0 ; i<len ; i++){
			if (i==0)
				stb_path.append("/");
			
			stb_path.append(split[i]);
			
			if (i != len-1)
				stb_path.append("/");
		}
		
		String tmp = stb_path.toString();
		
		//adding file into hive store
		
		String file_addition = "add file " + tmp;
		
		try {
			
			System.out.println("Before adding a file to hive store");
			System.out.println(file_addition);
			stmt.execute(file_addition);
			System.out.println("After adding a file to hive store");
		} catch (SQLException e1) {
			System.out.println("Cannot add file to hvie store");
			
			e1.printStackTrace();		}
		
		
		qc = new QueryConstructor();
		String resulting_query = qc.hourlyAnalysis(query);
		
		String table_exists = "select * from csv_dump";
		
		try {
			stmt.execute("drop table csv_dump");
			stmt.execute(resulting_query);
			res = stmt.executeQuery(table_exists);			
			metadata = res.getMetaData();
			column_count = metadata.getColumnCount();
			
			
			//getting the range
			//String[] range = range_rows.split(":");
			//int first_number = Integer.parseInt(range[0]);
			//int second_number = Integer.parseInt(range[1]);
			
			//int total_rows = second_number - first_number;
			
			//for (int i=1 ; i<first_number ; i++)
				//res.next();
			
			//getting the desired rows			
			int check=1;
			List<Object> rows=null;
			
			//for accessing fresh chunked data every time
			table_rows_list = new ArrayList<>();
			
							
				//short circuit is necessary not to skip the record		
				while (res.next()){
					//new list for every row
					rows = new ArrayList<>(); 
					//System.out.println(column_count);
					int count=1;
					while (count <= column_count) {  
						//System.out.println(res.getString(count));
						//System.out.println(count);
						
						System.out.println("===========================");
						System.out.println(metadata.getColumnType(count));		
						
						
						//for string
						if (metadata.getColumnType(count) == 12){
							temp = res.getString(count++);
							System.out.println(temp);
						}
						//for integer
						else if (metadata.getColumnType(count) == -5){
							
							int ab = res.getInt(count++);
							temp = String.valueOf(ab);
							System.out.println(temp);
							
						}
						//for double
						else if (metadata.getColumnType(count) == 8){
							
							double ab = res.getInt(count++);
							temp = String.valueOf(ab);		
							System.out.println(temp);
						}
						
						
					/*	if (temp != null){
							int lenth = temp.length();
							temp = temp.substring(1, lenth-1);
							}*/
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
	
	
	/*public static void main(String args[]){
		
		System.out.println(new CustomeScriptAnalysis().fetchDataForGraphCreation("yahoo_buzz_test","home-hduser-current_project_Aanlysis-first_case_study-test_perl.pl"));
	}*/


	public String fetchDataForGraphCreationResult() {
		
//		/home/hduser/workspace/bsanalytics/WebContent/dashboard/analysis/test
		
		
		BufferedReader br = null;
		 
		try {
 
			String sCurrentLine;
			StringBuilder stb = new StringBuilder();
 
			br = new BufferedReader(new FileReader("/home/hduser/workspace/bsanalytics/WebContent/dashboard/analysis/test"));
			sCurrentLine = br.readLine();
			while (sCurrentLine != null){
				stb.append(sCurrentLine);
				sCurrentLine = br.readLine();
			}
			
			br.close();
			return stb.toString();
				
 
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
		
	}
		
}
