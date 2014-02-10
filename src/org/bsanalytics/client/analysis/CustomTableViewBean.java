package org.bsanalytics.client.analysis;

import java.util.List;

public class CustomTableViewBean {
	
	private static String range;
	private static String view_table_name;
	private static List<List<Object>> table_list;
	
	public String getRange() {
		return range;
	}
	public static void setRange(String range) {
		CustomTableViewBean.range = range;
	}
	public String getView_table_name() {
		return view_table_name;
	}
	public static void setView_table_name(String view_table_name) {
		CustomTableViewBean.view_table_name = view_table_name;
	}
	public List<List<Object>> getTable_list() {
		return table_list;
	}
	public static void setTable_list(List<List<Object>> table_list) {
		CustomTableViewBean.table_list = table_list;
	}
	

}
