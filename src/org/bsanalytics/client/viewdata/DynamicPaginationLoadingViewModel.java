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

	private String[] data = new String[51];// {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11"};
	private List<String> li = new ArrayList<>(10);
	private List<List<String>> org_list = new ArrayList<>();
    private Integer rowKey = null;  
    List<String> columns;
    
    private Integer rowId;
    private Map<Integer,Object> wrappedData = new HashMap<Integer,Object>();
    private List<Integer>                           wrappedKeys      = null;
	private Integer                        totalRows        = 0;
	private Integer                        firstRow         = 0;
	private Integer                        numberOfRows     = 0;
    LoadTableFromDataBase lTFD = new LoadTableFromDataBase();
    int it;
    int column_count=0;
    public DynamicPaginationLoadingViewModel(){
    	
    	
    	//LoadTableFromDataBase lTFD = new LoadTableFromDataBase();
    	lTFD.loadDataFromTable("sample");
    	column_count = lTFD.getColumnCount();
    	
    	//total_number_of_rows = lTFD.getTotalNumberofRows();
    	//System.out.println(total_number_of_rows);
    	//li = lTFD.getTCustomRowsList(FETCH_SIZE);
    	//System.out.println(li);*/
    	li.add("1");li.add("2");li.add("3");li.add("4");li.add("5");
    	li.add("6");li.add("7");li.add("8");li.add("9");li.add("10");
    	org_list.add(li);
    	org_list.add(li);
    	org_list.add(li);
    	org_list.add(li);
    	org_list.add(li);
    	org_list.add(li);
    	org_list.add(li);
    	org_list.add(li);
    	org_list.add(li);
    	org_list.add(li);
    	ReadCSVFileNumberCount total_rows = new ReadCSVFileNumberCount();
    	totalRows = total_rows.GetLineCount("");
    	//data base
    	getTestCol();
    	it=1;
    }
    
    @Override  
    public void setRowKey(Object o) {  
    	rowKey = (Integer) o;  
    }  
  
    @Override  
    public Object getRowKey() {  
        return rowKey;  
    }  
  
    @Override  
    public void walk(FacesContext fc, DataVisitor dv, Range range, Object o) {  
        System.out.println("===walk invoked====");
               
    	try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	SequenceRange sr = (SequenceRange) range;  
        int currentRow = sr.getFirstRow();  
       
       /* System.out.println("Current Row =" + currentRow);
        int rows = sr.getRows();  
        System.out.println("Total Rows =" + rows);
        
        String[] temp = getPartialData(currentRow,rows);
        for(int j=0 ; j<temp.length ; j++)
        System.out.println("Partial Data " + temp[j]);
        
        wrappedKeys = new ArrayList<Integer>();
        for(int i = 0; i < rows; i++) {  
        	wrappedKeys.add(it);
	        wrappedData.put(it, temp[i]);
            dv.process(fc, it, o);
            it++;
        } */
    	
    	/*System.out.println("==walk invoked");
    	System.out.println("====1====");*/
	    // This is method we have to populate a data for DataTable
	    //Range parameter provides the firstRow value and no. of rows value. Extract those values to local variables
	   this.firstRow = ((SequenceRange) range).getFirstRow();
	    System.out.println("====2====");
	    this.numberOfRows = ((SequenceRange) range).getRows();
	    System.out.println("====3====");
	 
	    //we can populate the data from Service Call/Web Service Call For eg. Data from database or Search results using Range values
	    //Define getItemsByrange() method to extract the required data by passing the range values
	    //List<String> sampleDataList = getItemsByrange(this.firstRow, this.numberOfRows.intValue());
	    
	  //sequence is really important here
	  		
	      	List<String> inter_list = lTFD.getTCustomRowsList(column_count, this.numberOfRows);
	      	System.out.println("intermediate =" + inter_list);
	      	List<List<String>> sampleDataList = new ArrayList<>(); 
	      	sampleDataList = lTFD.getListTwo();
	        System.out.println(sampleDataList);
	        //totalRows = sampleDataList.size();
	    // Now store the data to wrappedKeys & wrappedData components for the framework to make use of.
	        wrappedKeys = new ArrayList<Integer>();
	    
	    if (!sampleDataList.isEmpty()) {
	    	
	        for (List<String> item : sampleDataList) {
	        	//System.out.println(item);	        	
	        		wrappedKeys.add(it);
	    	        wrappedData.put(it, item);
	    	        dv.process(fc, it, o);
	    	        it++;	        
	        }
	        
	    }
    }  
  
    @Override  
    public boolean isRowAvailable() {  
        return rowKey != null;  
    }  
  
    @Override  
    public int getRowCount() {  
        //return org_list.size();
    	//return total_number_of_rows;
    	//return data.length;
    	return totalRows;
    	
    	

		/*if (null == totalRows) {
			System.out.println("came here...");
	        return this.totalRows;
	    }*/
	    //return this.totalRows;
    }  //
  
    @Override  
    public Object getRowData() {  
       // return data[rowKey];
    	//return org_list.get(rowKey);
    
    	/*if (null == rowId) {
            return null;
        }*/
    	//System.out.println("==coming here==getting row data");
    	//System.out.println(wrappedData.get(rowKey));
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
    
    
    public List<String> getColumns() {
		columns = takeColumns();
		System.out.println("coming... Columns");
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	
	public List<String> takeColumns(){
		 List<String> li = new ArrayList<String>();
		 li.add("First");
		 //li.add("Second");
		 return li;
		 
	}  
	//database
	public String[] getTestCol(){
		//String[] st = new String[50];
		for (int i=0 ; i<50 ; i++){
			data[i] = "500"+i;
		}
		return data;
	}
	
	//partially fetch
	public String[] getPartialData(int start, int limit){
		
		int sum = start+limit;
		String[] tem = new String[limit];
		System.out.println("First Call=== " + sum);
		int j=0;
		for(int i=start ; i<sum; i++){
			System.out.println("OutOfBound = " + i);
			tem[j] = data[i];
			System.out.println("===" + tem[j]);
			j++;
		}
		return tem;
	}

}
