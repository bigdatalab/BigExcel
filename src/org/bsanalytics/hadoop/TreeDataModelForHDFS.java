package org.bsanalytics.hadoop;

import java.util.List;

public class TreeDataModelForHDFS {
	private DataHolderForTree rootNodes;
	private DataHolderForTree hdfsNodes;
	AccessHadoopFileSystem hdfs_fs = new AccessHadoopFileSystem();
	public DataHolderForTree getRootNodes() {
		
		System.out.println("coming here -1");
	    if (rootNodes == null) {
	    	System.out.println("coming here -1- Inside Root");
	    	/*String[] kickRadioFeed = {"Hall & Oates - Kiss On My List",
	                "David Bowie - Let's Dance",
	                "Lyn Collins - Think (About It)",
	                "Kim Carnes - Bette Davis Eyes",
	                "KC & the Sunshine Band - Give It Up"};   
	        */
	        List<String> hdfs = hdfs_fs.listHDFSFiles();
	        
	        /*hdfsNodes = new DataHolderForTree(false, "FirstKickRadio");
	        for (int i = 0; i<kickRadioFeed.length; i++) {
	            DataHolderForTree child = new DataHolderForTree(true, kickRadioFeed[i]);
	            hdfsNodes.addChild(i, child);
	        }*/
	        
	        hdfsNodes = new DataHolderForTree(false, "HDFS Files");
	        for ( int j=0; j<hdfs.size() ; j++){
	        	DataHolderForTree child = new DataHolderForTree(true, hdfs.get(j));
	            hdfsNodes.addChild(j, child);
	        }
	        
	        rootNodes = new DataHolderForTree();	        
	        rootNodes.addChild(0,hdfsNodes);
	        rootNodes.addChild(1, hdfsNodes);
	     }
		
		return rootNodes;
	}

	public void setRootNodes(DataHolderForTree rootNodes) {
		this.rootNodes = rootNodes;
	}

}
