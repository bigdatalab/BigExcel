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
import org.bsanalytics.client.loaddata.ReadCSVFileRowCount;
import org.bsanalytics.dashboard.ServerAccessPath;
import org.bsanalytics.general.client.ApplicationMessages;
import org.bsanalytics.general.client.ClientSideGsonConversion;

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
    ReadCSVFileRowCount getting_total_rows;
    long map_index;
    int column_count=0;
    ClientObject cObj;
    RestClient client_wink;
    int initial_partial_flag;
    ClientSideGsonConversion cGson;
    //ApplicationMessages faceMessage;
    
    public DynamicPaginationLoadingViewModel(){
    	System.out.println("--came in constructure");
    	table_name_obj = new ViewTableBackingBean();
    	cObj = new ClientObject();
    	client_wink= cObj.getClientObject();
    	cGson = new ClientSideGsonConversion();
    	//faceMessage = new ApplicationMessages();
    	//connectToDBandInitiateCursor();
    	callAPItoGetData();
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
    	 			String response = callAPItoGetPartialData();
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
    	LoadTableFromDataBase.loadDataFromTable(table_name_obj.getTable_name());
    	
    	//getting column count
    	column_count = LoadTableFromDataBase.getColumnCount();
    	
    	//reading total number of rows from CSV file
    	getting_total_rows = new ReadCSVFileRowCount();
    	
    	
    	totalRows = getting_total_rows.GetLineCount("");
    	map_index=1;	
    	
    }

    //first call
    public int callAPItoGetData(){
    	String table_name = table_name_obj.getTable_name();
    	Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs_view/view_data/"+table_name);
		String response = resource.accept("text/json").get(String.class);
		System.out.println(response);
		//String tmp_string[] = response.split(":");
		totalRows = Integer.parseInt(response);
		//System.out.println(tmp_string[1]);
		//cGson.setSingleListForConversion(tmp_string[1].toString());
		//Pagination.column_names_list = cGson.getSingleConvertedList();
		return totalRows;
    }
    
    //second call
    
    public String callAPItoGetPartialData(){
    	
    	Resource resource = client_wink.resource(
	 			"http://localhost:8080/bsanalytics/jaxrs_view/view_data/"+numberOfRows+"/data");
	 			String response = resource.accept("text/json").get(String.class);
	 			return response;
    }
    
}
