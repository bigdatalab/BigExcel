package org.bsanalytics.dashboard;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooserForLoadingData {
	
	JFileChooser chooser = new JFileChooser();
	private static String file_name;
	
	public void selectFileFromSystem(){
		
		 
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "CSV File", "csv");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(chooser);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	file_name=chooser.getSelectedFile().getName();
		       System.out.println("You chose to open this file: " +file_name);
		    }
	}
	
	
	public String getFileName(){
		return file_name;
	}

}
