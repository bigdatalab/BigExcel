package org.bsanalytics.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class LoadHiveConnection {
	
	public static void main(String args[])
	{
		new LoadHiveConnection().getHiveConnection();
	}
	
	public Statement getHiveConnection() 
	{
		try{
			
			Class.forName("org.apache.hive.jdbc.HiveDriver");
			
		}catch(ClassNotFoundException ex){
		
			System.out.println("Unable to find : org.apache.hive.jdbc.HiveDriver");
			ex.printStackTrace();
		}
		
		
		
			Connection con;
			/*ResultSet res=null;*/
			try {
			
				con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "hduser", "");
				Statement stmt = con.createStatement();
				
				/*String tableName = "test_jdbc_table";
			    stmt.execute("drop table if exists " + tableName);
			    stmt.execute("create table " + tableName + " (key int, value string)");
			    
			 // show tables
			    String sql = "show tables '" + tableName + "'";
			    System.out.println("Running: " + sql);
			    ResultSet res = stmt.executeQuery(sql);
			    if (res.next()) {
			      System.out.println(res.getString(1));
			    }*/
			    
			 // regular hive query
			   /* String sql = "select count(ID) from " + "addresses";
			    System.out.println("Running: " + sql);
			    res = stmt.executeQuery(sql);
			    while (res.next()) {
			      System.out.println(res.getString(1));
			    }*/
			    
			    //return res.getString(1);
			    return stmt;
			    
			} catch (SQLException e) {
				System.out.println("Cannot get the connection to server");
				e.printStackTrace();
			}
			return null;
	}	


}
