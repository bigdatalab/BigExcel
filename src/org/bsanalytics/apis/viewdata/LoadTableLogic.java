package org.bsanalytics.apis.viewdata;

import java.util.List;

import org.bsanalytics.client.loaddata.ReadCSVFileNumberCount;

public class LoadTableLogic {
	
	LoadTableFromDataBase lTFD;
	long map_index;
    int column_count=0;
    ReadCSVFileNumberCount getting_total_rows_from_csv;
    private Integer totalRows;
	
	
	public void initializeDBConnectionAndNextMethod(String table_name){
	
		System.out.println("===This Time==== " + table_name);
		lTFD = new LoadTableFromDataBase();
    	
    	//executing the database statement
    	lTFD.loadDataFromTable(table_name);
    	
    	//getting column count
    	column_count = lTFD.getColumnCount();
    	
    	//reading total number of rows from CSV file
    	getting_total_rows_from_csv = new ReadCSVFileNumberCount();    	
    	setTotalRows(getting_total_rows_from_csv.GetLineCount(""));
    	
    	map_index=1;	
	}

	public List<List<Object>> getInitialTable(String table_name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

}
