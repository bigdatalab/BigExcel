package org.bsanalytics.client.analysis;

public class HiveQueryBean {
	
	public static String hive_query;	
	public static String where_clause;
	public static String dates;
	

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		HiveQueryBean.dates = dates;
	}

	public String getWhere_clause() {
		return where_clause;
	}

	public void setWhere_clause(String where_clause) {
		HiveQueryBean.where_clause = where_clause;
	}

	public String getHive_query() {
		return hive_query;
	}

	public void setHive_query(String hive_query) {
		HiveQueryBean.hive_query = hive_query;
	}

}
