package org.bsanalytics.apis.loaddata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bsanalytics.general.server.SQLLiteDBAccess;
import org.bsanalytics.general.server.ServerSideGsonConversion;
import org.bsanalytics.hadoop.AccessHadoopFileSystem;
import org.bsanalytics.hive.LoadHiveConnection;

public class LoadDataLogic {
	
	static String LOAD_HDFS = "hdfs dfs -copyFromLocal";
	
	LoadHiveConnection hcon = new LoadHiveConnection();
	AccessHadoopFileSystem hadoop_file_system =  new AccessHadoopFileSystem();
	Statement stmt_hive = null;//hcon.getHiveConnection();
	static SQLLiteDBAccess sqlLiteDB = new SQLLiteDBAccess();
	//opening the connection
	public static Statement stmt_sqlite =  getConnectionToSQLLite(); 
	ServerSideGsonConversion server_side_gson = new ServerSideGsonConversion();
	
	
	public void getHiveResources(){	
		stmt_hive = hcon.getHiveConnection();		
	}
	
	public void closeHiveResources(){		
		hcon.closeHiveConnection();
	}
	
	
	public void getSQLLiteResources(){
		
	}
	
	
	public String LoadData(String file_name_path){
		String table_name=null;
		
		/*try {*/
			
			table_name = getTableName(file_name_path);
			String file_name = getFileName(file_name_path);
			String path = getPathName(file_name_path);
	
			try{
				
			getHiveResources();
			//System.out.println("Before Checking table exists");
			boolean result = checkTableExists(table_name);
			//System.out.println("After Checking table exists");
			if (result)
			{   
				LoadFiletoHDFS(file_name,path);
				String sql= "load data inpath '"+file_name+"' into table "+ table_name;
				System.out.println(sql);
				stmt_hive.executeUpdate(sql);
				System.out.println("updating rows======");
				//insert csv information to sql_lite data-base
				insertCSVInformationIntoSQLLite(table_name,
						getTotalNumberOfRowsInCSVFile(file_name_path));
			}
			else
				return "Table " + "'"+table_name +"'"+ " Not Found";
				    
				
		} catch (SQLException e) {
			closeHiveResources();
			e.printStackTrace(); 
			//System.out.println("Connection Refused");
		}
		closeHiveResources();
		return "Data Loaded Successfully into table " + table_name;	
	}
	
	
	public String CreateTable(String table_string){
		
		String query_string = table_string.toLowerCase();		
		StringBuilder stb = new StringBuilder(query_string);
		int table_index = stb.indexOf("table");

		if (table_index == -1){
			
			return "Invalid Statement: please add table keyword";
		}
		
		
		int create_index = stb.indexOf("create");

		if (create_index == -1){
			
			return "Invalid Statement: please add create keyword";
		}
		
		int left_brace_index = stb.indexOf("(");
		if (left_brace_index == -1){
			
			return "Invalid Statement: ( character is missing";
		}

		int right_brace_index = stb.indexOf(")");
		if (right_brace_index == -1){
			
			return "Invalid Statement: ) character is missing";
		}
		
		if (stb.substring(left_brace_index, right_brace_index).length() ==0){
			return "Invalid Statement";
		}
		
		//System.out.println("===" + table_string);
		List<Object> li = extractingColumnNames(table_string);
		
		String table_name = stb.substring(table_index+5, left_brace_index);
		table_name = table_name.trim();
		//System.out.println("==="+table_name);
	
		getHiveResources();
		
		try {			
			stmt_hive.executeUpdate(table_string + " row format delimited fields terminated by ',' stored as textfile");	
			//inserting into to SQL-lite database
			insertingColumnNamestoDataBase(table_name,li);
		} catch (SQLException e) {
			closeHiveResources();
			return "Table already exists";
		}		
		closeHiveResources();
		return "Table Create Successfully";
	}
	
	
	
	public String DeleteTable(String table_string){
        
		getHiveResources();
		
		String sql = "DROP TABLE " + table_string;
		boolean result = checkTableExists(table_string);
		
		if (!result)
			return "Table not exists";
		
		try {
			stmt_hive.executeUpdate(sql);
			dropSQLiteTable(table_string);
										
		} catch (SQLException e) {
			closeHiveResources();
			return "Deletion Not Successfull";
		}
		closeHiveResources();
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
	
	public int getTotalNumberOfRowsInCSVFile(String file_name_path){
		
		String[] file_path_and_name = file_name_path.split(":");
	 	 return Integer.parseInt(file_path_and_name[2]);
		
	}
	
	
	public boolean checkTableExists(String table_name){
		
		System.out.println("Before Checking table exists-inside");
		
		String table_exists = "select * from "+table_name;
		try {
			ResultSet res = stmt_hive.executeQuery(table_exists);
			System.out.println("Before Checking table exists-inside-1");
			if (res != null)
				return true;
			else
				return false;
		
		} catch (SQLException e) {
			return false;
		}
		
		
	}
	

	//all about sql-lite
	
	public static Statement getConnectionToSQLLite(){		
		return sqlLiteDB.getLocalSQLLiteDBConnection();		
	}
	
	public void closeSQLLiteConnection(){
		sqlLiteDB.closeSQLLiteConnection();
	}
	
	
	public void insertCSVInformationIntoSQLLite(String table_name, int total_number_of_rows){
		
		/*System.out.println("table name = " + table_name);
		
		String sql = "select * from " + table_name;
		
		String sql_create = "create table " + table_name +
                "(tablename TEXT NOT NULL," +
                "total_rows INT NOT NULL)";
		String sql_insert = "insert into tables_metadata(tablename,total_rows,column_names)" +
                "VALUES("+"'"+table_name+"',"+ total_number_of_rows+")";*/
		String sql_update = "UPDATE tables_metadata set total_rows="+
				total_number_of_rows+" where table_name="+"'"+table_name+"'";
		
	/*	try{
			
			boolean result = stmt_sqlite.execute(sql);
			System.out.println(result);
				
		}catch(Exception ex){	
			try {
				System.out.println("creating table");
				stmt_sqlite.executeUpdate(sql_create);
			} catch (SQLException e) {
				System.out.println("unable to create the table");				
			}
		}*/

		//update the table
		try{
			stmt_sqlite.executeUpdate(sql_update);
		}catch(Exception ex){
			System.out.println("unable to update the total rows");
			ex.printStackTrace();
		}
		

	}
	
	public List<Object> extractingColumnNames(String query){
	    //System.out.println("===Query " + query);
		List<Object> li = new ArrayList<>();
		StringBuilder sb = new StringBuilder(query);
		int left_brc_index = sb.indexOf("(");
		int right_brc_index = sb.indexOf(")");
		String column_strings = sb.substring(left_brc_index+1, right_brc_index);
		System.out.println(column_strings);
		String resulting[] = column_strings.split(",");
		for(String st : resulting){
			st = st.trim();	
			//System.out.println(st);
			String[] temp = st.split(" ");
			String column_name = temp[0];
			li.add(column_name);
			//System.out.println("=========");
			//System.out.println(column_name);
		}
		return li;
		
	}
	
	public void insertingColumnNamestoDataBase(String table_name, List<Object> query){
		
	/*	//for rows_count
		String sql_create_rows = "create table " + table_name +
                "(tablename TEXT NOT NULL," +
                "total_rows INT NOT NULL)";
		
		String sql = "select * from " + table_name+"_columns";
		String sql_create = "create table " + table_name + "_columns" +
                "(tablename TEXT NOT NULL," +
                "columnnames TEXT NOT NULL)";
		server_side_gson.setSingleListForConversion(query);
		String sql_insert = "insert into "+ table_name+"_columns"+" (tablename,columnnames)" +
                "VALUES("+"'"+table_name+"',"+"'"+server_side_gson.getSingleConvertedString()+"'"+")";
		
		try {
			boolean creat_row_result = stmt_sqlite.execute(sql_create_rows);
		} catch (SQLException e1) {
			try {
				stmt_sqlite.executeUpdate(sql_create_rows);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//e1.printStackTrace();
		}
		
		
		try{
			
			boolean result = stmt_sqlite.execute(sql);
			System.out.println(result);
				
		}catch(Exception ex){	
			try {
				System.out.println("creating table");
				System.out.println(sql_create);
				stmt_sqlite.executeUpdate(sql_create);
			} catch (SQLException e) {
				System.out.println("unable to create the table");	
				return;
			}
		}

		//update the table
		try{
			stmt_sqlite.executeUpdate(sql_insert);
		}catch(Exception ex){
			ex.printStackTrace();
		}*/
		server_side_gson.setSingleListForConversion(query);
		String sql_insert = "insert into  tables_metadata(table_name,total_rows,column_names)" +
              "VALUES("+"'"+table_name+"',"+0+","+"'"+server_side_gson.getSingleConvertedString()+"'"+")";
		
				
		try {
			stmt_sqlite.executeUpdate(sql_insert);
		} catch (SQLException e) {
			System.out.println("unable to insert row");
			e.printStackTrace();
		}

	}
	
	
	public void dropSQLiteTable(String table_name){
		

		/*String sql_drop = "DROP table " + table_name;
		String sql_drop_columns = "DROP table " + table_name+"_columns";
		*/
		
		String sql_delete = "delete from tables_metadata where table_name="+
		"'"+table_name+"'";
		try {
			stmt_sqlite.executeUpdate(sql_delete);
		} catch (SQLException e) {
			System.out.println("unable to delete row");
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		String sr = "/home/hduser/Documents/sample.csv:sample.csv:501";
		//System.out.println(new LoadDataLogic().LoadData(sr));
		LoadDataLogic ldg = new LoadDataLogic();
		//System.out.println(ldg.DeleteTable("sample"));
		
		String create_table = "create table sample(first_name string, last_name string,"+
				"company string, address string, city string, county string," +
				"state string, zip int, phone1 int, phone2 int, email string," +
				"web string)";
		
		  //ldg.CreateTable(create_table);
		  //ldg.LoadData("a:sample.:500");
		//ldg.lowerTest(create_table);
		//ldg.dropSQLiteTable("sample");
		
	}
	
	public void lowerTest(String str){
		
		System.out.println(str.toLowerCase());
		
	}

}
