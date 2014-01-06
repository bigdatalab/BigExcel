package org.bsanalytics.client.viewdata;

import java.util.List;

import org.bsanalytics.apis.viewdata.LoadTableFromDataBase;

public class ViewTableBackingBean {

	private String table_name;
	private List<List<Integer>> table;
	private static String[] column_headers;
	
	public void setTable(List<List<Integer>> table) {
		this.table = table;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	
	
	public List<List<Integer>> getTable(){
		
		LoadTableFromDataBase lTFD = new LoadTableFromDataBase();
		System.out.println("Table Name=" + table_name);
		//table=lTFD.loadDataFromTable(table_name);
		return table;
	}
	
	public static String[] getColumn_headers() {
		return column_headers;
	}

	public static void setColumn_headers(String[] column_headers) {
		ViewTableBackingBean.column_headers = column_headers;
	}
	
	public void Display(){
		System.out.println(getTable_name());
		
	}
	
	
}
