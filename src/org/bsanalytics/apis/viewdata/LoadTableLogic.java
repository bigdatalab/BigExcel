package org.bsanalytics.apis.viewdata;

import java.util.ArrayList;
import java.util.List;

import org.bsanalytics.client.loaddata.ReadCSVFileNumberCount;

public class LoadTableLogic {
	
	LoadTableFromDataBase lTFD;
    int column_count=0;
    ReadCSVFileNumberCount getting_total_rows_from_csv;
    private Integer totalRows;
	
	
	public String initializeDBConnection(String table_name){
	
		System.out.println("===This Time==== " + table_name);
		lTFD = new LoadTableFromDataBase();
    	
    	//executing the database statement
    	lTFD.loadDataFromTable(table_name);
    	
    	//getting column count
    	column_count = lTFD.getColumnCount();
    	
    	//reading total number of rows from CSV file
    	getting_total_rows_from_csv = new ReadCSVFileNumberCount();    	
    	setTotalRows(getting_total_rows_from_csv.GetLineCount(""));
    	
  
    	/*String st1 = Integer.toString(column_count);
    	String st2 = Integer.toString(getTotalRows());
    	String res = st1 + ":" + st2;*/
    	System.out.println("===column_count==== " + column_count);
    	return Integer.toString(getTotalRows());
	}

	public List<List<Object>> getInitialTable(String fetch_size) {
		System.out.println("fetch_size===" + Integer.parseInt(fetch_size));
		lTFD.getTCustomRowsList(column_count, Integer.parseInt(fetch_size));
      	List<List<Object>> list_chunck = new ArrayList<>(); 
      	list_chunck = lTFD.getListTwo();
		return list_chunck;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

}
