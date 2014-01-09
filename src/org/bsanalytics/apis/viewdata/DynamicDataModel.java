package org.bsanalytics.apis.viewdata;

import javax.faces.model.DataModel;

import org.ajax4jsf.model.SequenceDataModel;

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


public class DynamicDataModel extends ExtendedDataModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8687309180897048109L;
	private static final String RowId = null;
	private Integer rowId;
	private Map<Integer, SampleData> wrappedData      = new HashMap<Integer, SampleData>();
	private List<Integer>                  wrappedKeys      = null;
	private Integer                        totalRows        = null;
	private Integer                        firstRow         = 0;
	private Integer                        numberOfRows     = 0;
	//LoadTableFromDataBase lTFD = new LoadTableFromDataBase();
    private String test;
    
    
    public DynamicDataModel(){
    	System.out.println("Constructor invoked");
    }
	
	public String getTest() {
		TestInvoke();
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.model.ExtendedDataModel#getRowKey()
	 */
	@Override
	public Object getRowKey() {		
		return rowId;
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.model.ExtendedDataModel#setRowKey(java.lang.Object)
	 */
	@Override
	public void setRowKey(Object key) {
		this.rowId = (Integer) key;
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.model.ExtendedDataModel#walk(javax.faces.context.FacesContext, org.ajax4jsf.model.DataVisitor, org.ajax4jsf.model.Range, java.lang.Object)
	 */
	@Override
	public void walk(FacesContext context, DataVisitor visitor, Range range,
			Object argument) {
		// This is method we have to populate a data for DataTable
		System.out.println("Walk Invoked");
		//Range parameter provides the firstRow value and no. of rows value
		this.firstRow = ((SequenceRange) range).getFirstRow();
        this.numberOfRows = ((SequenceRange) range).getRows();
		
		//we can populate the data from Service Call/Web Service Call For eg. Data from database or Search results using Range values
		
        wrappedKeys = new ArrayList<Integer>();
        SampleData sd1 = new SampleData();
        sd1.setYears(2001);
        sd1.setFirstname("asif");
        sd1.setLastname("saleem");
        List<SampleData> sampleDataList = new ArrayList<SampleData>(); // populate this from any source
        sampleDataList.add(sd1);
        
        //get the total no of rows from data source
        //this.totalRows = dataProvider.getTotalRowsFromDataSource(); 
        //this.totalRows = lTFD.loadDataFromTable("years_frequency").size();
        this.totalRows = sampleDataList.size();
        System.out.println(totalRows);
        if (!sampleDataList.isEmpty()) {
            for (SampleData item : sampleDataList) {
                wrappedKeys.add(item.getYears());
                wrappedData.put(item.getYears(), item);
              //Once data populated to wrappedKeys and wrappedData call the process method using visitor parameter
                visitor.process(context, item.getYears(), argument);
            }
        }

	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		
        return 1;
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getRowData()
	 */
	@Override
	public Object getRowData() {
		if (null == rowId) {
            return null;
        }
        return wrappedData.get(rowId);
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getRowIndex()
	 */
	@Override
	public int getRowIndex() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getWrappedData()
	 */
	@Override
	public Object getWrappedData() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#isRowAvailable()
	 */
	@Override
	public boolean isRowAvailable() {
		return false;
        	
            //return dataProvider.hasItemByRowId(rowId);
        
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#setRowIndex(int)
	 */
	@Override
	public void setRowIndex(int rowIndex) {
		//ignore
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#setWrappedData(java.lang.Object)
	 */
	@Override
	public void setWrappedData(Object data) {
		throw new UnsupportedOperationException();
	}
	
	public ExtendedDataModel getSerializableModel(Range range) {
        if (null != wrappedKeys) {
            return this;
        }
        return null;
    }
	
	public void TestInvoke(){
		System.out.println("Yes invoking");
	}

}
