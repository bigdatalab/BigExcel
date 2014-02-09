package org.bsanalytics.client.analysis;

import java.util.ArrayList;
import java.util.List;

public class UtilityFunctionBean {
	
	private static String table_name;
	private static String column_name;
	private List<String> function_selection;
	private static String selected_item;
		
	
	public String getSelected_item() {
		System.out.println("Selected item = " + selected_item);
		return selected_item;
	}
	public void setSelected_item(String selected_item) {
		UtilityFunctionBean.selected_item = selected_item;
	}
	public String getTable_name() {
		System.out.println("table_name = " + table_name);
		return table_name;
	}
	public void setTable_name(String table_name) {
		UtilityFunctionBean.table_name = table_name;
	}
	public String getColumn_name() {
		System.out.println("column_name = " + column_name);
		return column_name;
	}
	public void setColumn_name(String column_name) {
		UtilityFunctionBean.column_name = column_name;
	}
	public List<String> getFunction_selection() {
		function_selection = getFunctionList();
		return function_selection;
	}
	public void setFunction_selection(List<String> function_selection) {
		this.function_selection = function_selection;
	}
	
	public List<String> getFunctionList()
	{
		List<String> list = new ArrayList<String>();
		list.add("Sum");
		list.add("Average");
		list.add("Standarad Deviation");
		return list;
	}
}