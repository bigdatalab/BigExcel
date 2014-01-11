package org.bsanalytics.apis.viewdata;

import java.io.IOException;
import java.sql.RowId;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;


public class DynamicDataModel extends ExtendedDataModel{
	
	private Integer rowId;
	private Map wrappedData = new HashMap();
	private List wrappedKeys = null;
	private Integer totalRows = 0;
	private Integer firstRow = 0;
	private Integer numberOfRows = 0;
	LoadTableFromDataBase lTFD;
	int column_count=0;
	public DynamicDataModel(){
		System.out.println("constructor");
		lTFD = new LoadTableFromDataBase();		
      	lTFD.loadDataFromTable("sample");
      	column_count = lTFD.getColumnCount();
      	lTFD.getTCustomRowsList(column_count, 10);
	}

	@Override
	public Object getRowKey() {
		return rowId;
	}

	@Override
	public void setRowKey(Object key) {

		this.rowId = (Integer) key;
		
	}

	@Override
	public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument) {
		 System.out.println("==walk invoked");
	    // This is method we have to populate a data for DataTable
	    //Range parameter provides the firstRow value and no. of rows value. Extract those values to local variables
	    this.firstRow = ((SequenceRange) range).getFirstRow();
	    this.numberOfRows = ((SequenceRange) range).getRows();
	 
	    //we can populate the data from Service Call/Web Service Call For eg. Data from database or Search results using Range values
	    //Define getItemsByrange() method to extract the required data by passing the range values
	    //List<String> sampleDataList = getItemsByrange(this.firstRow, this.numberOfRows.intValue());
	    
	  //sequence is really important here
	  		
	      	List<String> sampleDataList = lTFD.getTCustomRowsList(column_count, this.numberOfRows);
	        System.out.println(sampleDataList);
	    // Now store the data to wrappedKeys & wrappedData components for the framework to make use of.
	    wrappedKeys = new ArrayList();
	    if (!sampleDataList.isEmpty()) {
	        for (String item : sampleDataList) {
	        wrappedKeys.add(item);
	        wrappedData.put(item, "Test");
	        visitor.process(context, item, argument);
	        }
	    }
	}

	@Override
	public int getRowCount() {

		if (null == totalRows) {
	        return this.totalRows;
	    }
	    return 0;
	}

	@Override
	public Object getRowData() {

		if (null == rowId) {
	        return null;
	    }
	    return wrappedData.get(rowId);
	}

	@Override
	public int getRowIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getWrappedData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRowIndex(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWrappedData(Object arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public ExtendedDataModel getExtendedModel(Range range) {
	    if (null != wrappedKeys) {
	        return this;
	    }
	    return null;
	}

	

}
