package org.bsanalytics.client.viewdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.bsanalytics.apis.viewdata.LoadTableFromDataBase;
import org.bsanalytics.client.loaddata.ClientObject;
import org.bsanalytics.client.loaddata.ReadCSVFileNumberCount;
import org.bsanalytics.dashboard.ServerAccessPath;

public class DynamicPaginationLoadingViewModel extends ExtendedDataModel{

	
	private Long rowKey = null;    
    private Map<Long,Object> wrappedData = new HashMap<Long,Object>();
    private List<Long> wrappedKeys = null;
	private Integer totalRows = 0;
	private Integer firstRow = 0;
	private Integer numberOfRows = 0;
	
	//creating DataBase Connection
    LoadTableFromDataBase lTFD;
    ViewTableBackingBean table_name_obj;
    ReadCSVFileNumberCount getting_total_rows;
    long map_index;
    int column_count=0;
    ClientObject cObj;
    RestClient client_wink;
    int initial_partial_flag;
    ClientSideGsonConversion cGson;
    
    public DynamicPaginationLoadingViewModel(){
    	System.out.println("--came in constructure");
    	table_name_obj = new ViewTableBackingBean();
    	cObj = new ClientObject();
    	client_wink= cObj.getClientObject();
    	//connectToDBandInitiateCursor();
    	callAPItoGetData();
    	cGson = new ClientSideGsonConversion();
    	initial_partial_flag=0;
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
       	  
    	System.out.println("--came in walk-1");
	    firstRow = ((SequenceRange) range).getFirstRow();
	     numberOfRows = ((SequenceRange) range).getRows();
    	    	
	  //sequence is really important here
     /* if (o != null)
      {      
	      	lTFD.getTCustomRowsList(column_count, numberOfRows);
	      	List<List<String>> list_chunck = new ArrayList<>(); 
	      	list_chunck = lTFD.getListTwo();
	        //wrapping data and their corresponding keys
	        wrappedKeys = new ArrayList<Long>();
	    
	    if (!list_chunck.isEmpty()) {
	    	
	        for (List<String> item : list_chunck) {
	        	          	
	        		wrappedKeys.add(map_index);
	    	        wrappedData.put(map_index, item);
	    	        dv.process(fc, map_index, o);
	    	        map_index++;	        
	        }
	        
	    }
      }*/
    	        System.out.println("--came in walk " + numberOfRows);
    	 		if (o != null)
    	 		{ 
    	 			Resource resource = client_wink.resource(
    	 			"http://localhost:8080/bsanalytics/jaxrs_view/view_data/"+numberOfRows);
    	 			String response = resource.accept("text/json").get(String.class);
    	 			System.out.println(response);
    	 			cGson.setListForConversion(response);
    	 			List<List<Object>> list_chunck = new ArrayList<>(); 
    		      	list_chunck = cGson.getConvertedList();
    		      	 //wrapping data and their corresponding keys
    		        wrappedKeys = new ArrayList<Long>();
    		    
    		    if (!list_chunck.isEmpty()) {
    		    	
    		        for (List<Object> item : list_chunck) {
    		        	          	
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
    
    
    public void connectToDBandInitiateCursor(){
    	
    	lTFD = new LoadTableFromDataBase();
    	
    	//executing the database statement
    	lTFD.loadDataFromTable(table_name_obj.getTable_name());
    	
    	//getting column count
    	column_count = lTFD.getColumnCount();
    	
    	//reading total number of rows from CSV file
    	getting_total_rows = new ReadCSVFileNumberCount();
    	
    	
    	totalRows = getting_total_rows.GetLineCount("");
    	map_index=1;	
    	
    }

    public int callAPItoGetData(){
    	System.out.println("Coming====Here");
    	String table_name = table_name_obj.getTable_name();
    	Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs_view/view_data/"+table_name);
		String response = resource.accept("text/json").get(String.class);
		System.out.println(response);
		/*String st1[] = response.split(":");
		totalRows = Integer.parseInt(st1[1]);*/
		//cGson.setListForConversion(response);
		totalRows = Integer.parseInt(response);
		return totalRows;
    }
    
}
