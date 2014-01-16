package org.bsanalytics.general.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLLiteDBAccess {
	
	Connection conn = null;
    Statement stmt = null;
    
    public Statement getLocalSQLLiteDBConnection(){
    	
    	 try {
    	      Class.forName("org.sqlite.JDBC");
    	      conn = DriverManager.getConnection("jdbc:sqlite:WebContent/db/csv_data.db");
    	      stmt = conn.createStatement();
    	      System.out.println("Opened database successfully");
    	    }catch(Exception ex){
    	    	ex.printStackTrace();
    	    }
    	 return stmt;
    	 }
   
    
    public static void main(String args[]){
    	new SQLLiteDBAccess().getLocalSQLLiteDBConnection();
    }

}
