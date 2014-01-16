package org.bsanalytics.client.loaddata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSVFileRowCount {
	
	
	public int GetLineCount(String path)
	{
	    int lineCount=0;
	    
	    try {
			BufferedReader bf = new BufferedReader(new FileReader(path));
			try {
				while(bf.readLine() != null){
					lineCount++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return lineCount;
	}
	
	/*public static void main(String args[]){
		System.out.println(new ReadCSVFileRowCount().GetLineCount(""));
	}*/
	

}
