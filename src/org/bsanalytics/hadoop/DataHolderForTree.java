package org.bsanalytics.hadoop;

import org.richfaces.model.TreeNodeImpl;

public class DataHolderForTree extends TreeNodeImpl  {
	private Object data;
	
	public DataHolderForTree() {
        super();
    }

    public DataHolderForTree(boolean leaf, Object data) {
        super(leaf);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        //return super.toString() + " >> " + data;
    	return data.toString();
    }
}
