package org.bsanalytics.client;

public class CreateTableBean {
	
	private String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

   
	public void showQuery(){
		System.out.println(query);
	}

}

