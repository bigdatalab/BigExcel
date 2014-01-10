package org.bsanalytics.client.loaddata;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;

public class CSVHandler {
	
	
	
	public String[] getColumnNamesFromCsvFile(String path){
		
		try {
			CSVReader csvR = new CSVReader(new FileReader(path));
			String[] header;
			try {
				header = csvR.readNext();
				for(int i=0 ; i<header.length ; i++)
					System.out.print(header[i] + " , ");
				csvR.close();
				return header;
				
			} catch (IOException e) {
				String[] error = {"Unable to read the file"};
				  return error;
			}
			
		} catch (FileNotFoundException e) {
			  String[] error = {"Unable to open the file"};
			  return error;
		}		
		
	}

}
