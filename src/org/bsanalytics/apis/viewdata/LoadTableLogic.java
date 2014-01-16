package org.bsanalytics.apis.viewdata;

import java.util.ArrayList;
import java.util.List;

import org.bsanalytics.client.loaddata.ReadCSVFileRowCount;

public class LoadTableLogic {
	
	//static LoadTableFromDataBase lTFD;
    static int column_count=0;
    static ReadCSVFileRowCount getting_total_rows_from_csv;
    private static Integer totalRows;
    static ServerSideGsonConversion gson_conv = new ServerSideGsonConversion();
	
	
	public static String initializeDBConnection(String table_name){
	
    	
    	//executing the database statement
		LoadTableFromDataBase.loadDataFromTable(table_name);
    	
    	//getting column count
    	column_count = LoadTableFromDataBase.getColumnCount();
    	
    	//reading total number of rows from CSV file
    	getting_total_rows_from_csv = new ReadCSVFileRowCount();    	
    	setTotalRows(getting_total_rows_from_csv.GetLineCount(""));
    	
      	return Integer.toString(getTotalRows());
	}

	public static String getInitialTable(String fetch_size) {
		System.out.println("fetch_size : " + Integer.parseInt(fetch_size));
		LoadTableFromDataBase.getTCustomRowsList(column_count, Integer.parseInt(fetch_size));
		List<List<Object>> list_chunck = new ArrayList<>();
      	list_chunck = LoadTableFromDataBase.getListTwo();
      	gson_conv.setListForConversion(list_chunck);      	
		return gson_conv.getConvertedString();
	}

	public static Integer getTotalRows() {
		return totalRows;
	}

	public static void setTotalRows(Integer totalRows) {
		LoadTableLogic.totalRows = totalRows;
	}

}
