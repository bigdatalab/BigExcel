package org.bsanalytics.client.viewdata;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;

public class DynamicPaginationLoadingViewModel extends ExtendedDataModel{

	//private String[] data = new String[] {"10", "20", "3", "4", "5", "6", "7", "8", "9", "10"};
	private List<Integer> li = new ArrayList<>();
	
    private Integer rowKey = null;  

    public DynamicPaginationLoadingViewModel(){
    	li.add(1);li.add(2);li.add(3);li.add(4);li.add(5);
    	li.add(10);li.add(9);li.add(8);li.add(7);li.add(6);
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
        try {
			Thread.sleep(1000);
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
        return li.size();  
    }  
  
    @Override  
    public Integer getRowData() {  
        //return data[rowKey];
    	return li.get(rowKey);
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
