package org.bsanalytics.general.client;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class CSVHandler {
	
	
	
	public List<String> getColumnNamesFromCsvFile(String path){
		
		try {
			CSVReader csvR = new CSVReader(new FileReader(path));
			String[] header;
			List<String> header_list = new ArrayList<>();
			try {
				header = csvR.readNext();
				for(int i=0 ; i<header.length ; i++){
					header_list.add(header[i]);
					System.out.print(header[i] + " , ");
				}
				csvR.close();
				return header_list;
				
			} catch (IOException e) {
				List<String> error = new ArrayList<>(); 
					error.add("Unable to read the file");
				  return error;
			}
			
		} catch (FileNotFoundException e) {
			List<String> error = new ArrayList<>(); 
			error.add("Unable to open the file");
		    return error;
		}		
		
	}

}
