package org.bsanalytics.client.loaddata;

import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.bsanalytics.general.client.CSVHandler;
import org.bsanalytics.general.client.SelectedFilePath;

public class FileChooserForLoadingData {
	
	JFileChooser chooser = null;
	private static String file_name;
	private static String absolute_path;
	public static int total_csv_file_rows;
	
	FileChooserForLoadingData(){
		chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "CSV File", "csv");
		    chooser.setFileFilter(filter);
	}
	
	public String selectFileFromSystem(){
		
		 
		    
		    int returnVal = chooser.showOpenDialog(chooser);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	file_name=chooser.getSelectedFile().getName();
		    	absolute_path = chooser.getSelectedFile().getAbsolutePath();
		    	
		    	//setting the absolute path for accessing the appliaction
		    	//SelectedFilePath.file_path_from_the_disk=absolute_path;
		    	
		    	//getting column names
		    	//getCsvFileColumnNamesUsingAsbPath(absolute_path);
		    	
		    	//getting row count of csv file
		    	total_csv_file_rows = getCsvRowCount(absolute_path);
		    	
		       System.out.println("You chose to open this file: " +file_name);
		       System.out.println("You chose file path: " +absolute_path);
		    }
		    return null;
	}
	
	
	public String convertToCSV(){
		
		/*JFileChooser chooser = new JFileChooser();
		 FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "CSV File", "csv");
			    chooser.setFileFilter(filter);
			    
		chooser.showSaveDialog(chooser);*/
		System.out.println("=======" + file_name);
		TextToCSV csv_converter = new TextToCSV();
		csv_converter.loadDataSet(absolute_path);
		return null;
	}
	
	
	public String getFileName(){
		return file_name;
	}
	
	public String getPath(){		
		return absolute_path;
	}
	
	public List<String> getCsvFileColumnNamesUsingAsbPath(String path){
		   CSVHandler csvH = new CSVHandler();
		   List<String> column_name = csvH.getColumnNamesFromCsvFile(path);
		return column_name;
		
	}
	
	public int getCsvRowCount(String file_absolute_path){
		
		ReadCSVFileRowCount rcsv_row_count =  new ReadCSVFileRowCount();
		return rcsv_row_count.GetLineCount(file_absolute_path);
		
	}

}
