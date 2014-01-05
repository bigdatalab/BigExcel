package org.bsanalytics.dashboard;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooserForLoadingData {
	
	JFileChooser chooser = new JFileChooser();
	private static String file_name;
	private static String absolute_path;
	
	public String selectFileFromSystem(){
		
		 
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "CSV File", "csv");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(chooser);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	file_name=chooser.getSelectedFile().getName();
		    	absolute_path = chooser.getSelectedFile().getAbsolutePath();
		       System.out.println("You chose to open this file: " +file_name);
		       System.out.println("You chose file path: " +absolute_path);
		    }
		    return null;
	}
	
	
	public String getFileName(){
		return file_name;
	}
	
	public String getPath(){		
		return absolute_path;
	}

}
