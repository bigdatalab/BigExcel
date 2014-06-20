package org.bsanalytics.client.loaddata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextToCSV {
	
	BufferedReader file_reader = null;
	BufferedWriter file_writer = null;
	boolean two_string_flag = false;
	
	private static String READ_PATH = "/home/hduser/datasets/ngram_data/ngrams.2";
	private static String WRITE_PATH = "/home/hduser/datasets/ngram_data/ngram.2.csv";
	
	public void loadDataSet(String Path){
		
		this.two_string_flag = two_string_flag;
		
		try {
			file_reader = new BufferedReader(new FileReader(Path));
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found in the system; please check the path or file exists");
			System.out.println("==========Trace==============");
			e.printStackTrace();
		}
		
		String data_types=null;
		String[] splitted=null;
		int header_length =0;
		int data_type_index=0;
		int max_length=0;
		int split_data_length=0;
		
		/*try {
			data_types = file_reader.readLine();
			splitted = data_types.split(",");
			System.out.println(splitted[1]);
			System.out.println(splitted.length);
			header_length = splitted.length;

			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//System.exit(0);
		
		//reading the rest of the data
		String temp_data_line = new String();
		StringBuilder stb = new StringBuilder(temp_data_line);
		try {
			file_writer = new BufferedWriter(new FileWriter(this.changeExtensiontoCSV(Path)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Processing.....");
		try {
			while (true){
				
				String single_text_line = file_reader.readLine();
				
				
				
				
				if (single_text_line== null)
					break;
				else
				{
					//first check the data types we needed
					//System.out.println(single_text_line);
					String[] split_data = single_text_line.split("\\s+");		
					
					split_data_length = split_data.length;
					
					if (split_data_length > max_length){
						max_length = split_data_length;
					}
					
					//System.out.println(split_data.length);
					for(int i=0 ; i<split_data_length ; i++){
						
					
						/*if (splitted[data_type_index].trim().equals("string")){*/
							
							//checking the last
							if (i != split_data.length-1)
							{
								//System.out.println("Coming Inside");
								//stb.append("\""+split_data[i]+"\""+",");
								stb.append(split_data[i]+",");
							}
							else
							{
								//stb.append("\""+split_data[i]+"\"");
								stb.append(split_data[i]);
							}
						}
						/*else if (splitted[i].trim().equals("date"))
						{
							String[] date_split = split_data[i].split("/");
							String st = new String();
							StringBuilder stb_date = new StringBuilder(st);
							stb_date.append(date_split[0]+"-");
							stb_date.append(date_split[1]+"-");
							stb_date.append(date_split[2]);
							
							//checking the last
							if (i != split_data.length-1)
							{
								//System.out.println("Coming Inside");
								stb.append(stb_date +",");
							}
							else
								stb.append(stb_date);
							
							
						}*/
						/*else
						{
							//checking for the last one
							if (!(i == split_data.length-1))
								stb.append(split_data[i]+",");
							else
								stb.append(split_data[i]);
						}*/
						
					}
					file_writer.write(stb.toString()+"\n");
					//System.out.println(stb.toString());
					
					temp_data_line = new String();
					stb = new StringBuilder(temp_data_line);
					
				}//end of the else
				
			//}
			System.out.println("Finished.....");
			System.out.println("Max length = " + max_length);
			file_writer.close();
			
		} catch (IOException e) {
			System.out.println("Unable to read/write from/to file; Please check file is valid");
			System.out.println("==========Trace==============");
			e.printStackTrace();
		}
		
	}
	
	
	public String changeExtensiontoCSV(String Path){
		
		String[] path = Path.split("/");
		String temp = path[path.length-1];
		//String file_name = temp.substring(0, temp.length()-4);
		
	
		String new_path = new String();
		StringBuilder stb = new StringBuilder(new_path);

		for (int i=0 ; i<path.length-1 ; i++){
			stb.append(path[i]+"/");
		}
		
		stb.append(temp+".csv");	
		System.out.println("stb.toString()" + stb.toString());
		return stb.toString();
		
	}
	
	public static void main(String args[]){
		
		//new TextToCSV().changeExtensiontoCSV("/home/hduser/datasets/Webscope_A2/ydata-yrbuzzgame-transactions-period1-v1_0.txt");
		new TextToCSV().loadDataSet("/run/media/hduser/CA78EED378EEBCF7/datasets/"+args[0]);
	}

}
