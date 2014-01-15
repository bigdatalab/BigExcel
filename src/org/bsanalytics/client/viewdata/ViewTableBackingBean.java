package org.bsanalytics.client.viewdata;

import java.util.ArrayList;
import java.util.List;


import org.bsanalytics.apis.viewdata.LoadTableFromDataBase;

public class ViewTableBackingBean {

	private static String table_name;
	private List<List<String>> table;
	private List<String> column_names;
	//DynamicPaginationLoadingViewModel dp = 
			//new DynamicPaginationLoadingViewModel();
	
	public List<String> getColumn_names() {
		column_names = gettingColumnNames();
		return column_names;
	}

	public void setColumn_names(List<String> column_names) {
		this.column_names = column_names;
	}

	public void setTable(List<List<String>> table) {
		this.table = table;
	}

	public String getTable_name() {


		return table_name;
	}

	public void setTable_name(String table_name) {
		ViewTableBackingBean.table_name = table_name;
	}
	
	
	public List<List<String>> getTable(){
		System.out.println("Call Came.....");
		LoadTableFromDataBase lTFD = new LoadTableFromDataBase();
		table_name = "years_frequency";
		System.out.println("Table Name=" + table_name);
		//table=lTFD.loadDataFromTable(table_name);
		
		
		return table;
	}
	
		
	

	public void Display(){
		System.out.println(getTable_name());
		
	}
	
	public List<String> gettingColumnNames(){
	
		List<String> li = new ArrayList<String>();
		li.add("years");
		li.add("prince");
		System.out.println("returning column heres------");
		return li;
	}	
	
	
}
