package org.bsanalytics.client.viewdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.bsanalytics.apis.viewdata.LoadTableFromDataBase;
import org.bsanalytics.client.loaddata.ReadCSVFileNumberCount;

public class DynamicPaginationLoadingViewModel extends ExtendedDataModel{

	
	private Long rowKey = null;    
    private Map<Long,Object> wrappedData = new HashMap<Long,Object>();
    private List<Long> wrappedKeys = null;
	private Integer totalRows = 0;
	private Integer firstRow = 0;
	private Integer numberOfRows = 0;
	
	//creating DataBase Connection
    LoadTableFromDataBase lTFD;
    ReadCSVFileNumberCount getting_total_rows;
    long map_index;
    int column_count=0;

    
    public DynamicPaginationLoadingViewModel(){
    	
    	
    	lTFD = new LoadTableFromDataBase();
    	
    	//executing the database statement
    	lTFD.loadDataFromTable("sample");
    	
    	//getting column count
    	column_count = lTFD.getColumnCount();
    	
    	//reading total number of rows from CSV file
    	getting_total_rows = new ReadCSVFileNumberCount();
    	
    	
    	totalRows = getting_total_rows.GetLineCount("");
    	map_index=1;	
    	
    	
    }
    
    @Override  
    public void setRowKey(Object o) {  
    	rowKey = (Long) o;  
    }  
  
    @Override  
    public Object getRowKey() {  
        return rowKey;  
    }  
  
    @Override  
    public void walk(FacesContext fc, DataVisitor dv, Range range, Object o) {  
       	  
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     firstRow = ((SequenceRange) range).getFirstRow();
	     numberOfRows = ((SequenceRange) range).getRows();
    	    	
	  //sequence is really important here
      if (o != null)
      {      
	      	lTFD.getTCustomRowsList(column_count, numberOfRows);
	      	//System.out.println("intermediate =" + inter_list);
	      	List<List<String>> list_chunck = new ArrayList<>(); 
	      	list_chunck = lTFD.getListTwo();
	        System.out.println(list_chunck);
	        //totalRows = sampleDataList.size();
	    // Now store the data to wrappedKeys & wrappedData components for the framework to make use of.
	        wrappedKeys = new ArrayList<Long>();
	    
	    if (!list_chunck.isEmpty()) {
	    	
	        for (List<String> item : list_chunck) {
	        	//System.out.println(item);	        	
	        		wrappedKeys.add(map_index);
	    	        wrappedData.put(map_index, item);
	    	        dv.process(fc, map_index, o);
	    	        map_index++;	        
	        }
	        
	    }
      }
    }  
  
    @Override  
    public boolean isRowAvailable() {  
        return rowKey != null;  
    }  
  
    @Override  
    public int getRowCount() {  
      	//return data.length;
    	return totalRows;    	   		
    }  
  
    @Override  
    public Object getRowData() {  
       // return data[rowKey];    	
        return wrappedData.get(rowKey);
    }  
  
    @Override  
    public int getRowIndex() {  
        return -1;  
    }  
  
    @Override  
    public void setRowIndex(int rowIndex) {  
        throw new UnsupportedOperationException();  
    }  
  
    @Override  
    public Object getWrappedData() {  
        throw new UnsupportedOperationException();  
    }  
  
    @Override  
    public void setWrappedData(Object data) {  
        throw new UnsupportedOperationException();  
    }  
    
}
