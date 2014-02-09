package org.bsanalytics.client.analysis;

public class CustomTableBean {
	
	private static String range;
	private static String table_name;
	
	public static String getTable_name() {
		return table_name;
	}

	public static void setTable_name(String table_name) {
		CustomTableBean.table_name = table_name;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		CustomTableBean.range = range;
	}
	

}
