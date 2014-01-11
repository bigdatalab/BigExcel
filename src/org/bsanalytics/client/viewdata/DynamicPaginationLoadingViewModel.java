package org.bsanalytics.client.viewdata;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.bsanalytics.apis.viewdata.LoadTableFromDataBase;

public class DynamicPaginationLoadingViewModel extends ExtendedDataModel<List<String>>{

	private String[] data = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	private List<String> li = new ArrayList<>(10);
	private List<List<String>> org_list = new ArrayList<>();
    private Integer rowKey = null;  
    List<String> columns;

    public DynamicPaginationLoadingViewModel(){
    	
    	
    	//LoadTableFromDataBase lTFD = new LoadTableFromDataBase();
    	//org_list = lTFD.loadDataFromTable("sample");
    	
    	
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
        
        int rows = sr.getRows();  
        for(int i = 0; i < rows; i++) {  
            dv.process(fc, currentRow++, o);  
        }  
    }  
  
    @Override  
    public boolean isRowAvailable() {  
        return rowKey != null;  
    }  
  
    @Override  
    public int getRowCount() {  
        return org_list.size();
    	//return total_number_of_rows;
    	//return data.length;
    }  
  
    @Override  
    public List<String> getRowData() {  
        //return data[rowKey];
    	return org_list.get(rowKey);
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

}
