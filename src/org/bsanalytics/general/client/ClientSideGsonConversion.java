package org.bsanalytics.general.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ClientSideGsonConversion {
	
	String list;
	String single_list;
	Gson gson_lib = new Gson();
	List<List<Object>> converted_list;
	List<String> converted_list_single;
	
	@SuppressWarnings("unchecked")
	public List<List<Object>> getConvertedList(){
		converted_list = new ArrayList<>();
		converted_list = gson_lib.fromJson(this.list, List.class);
		return converted_list;
	}
	
	public void setListForConversion(String list){
		
		this.list = list;
	}

	
	@SuppressWarnings("unchecked")
	public List<String> getSingleConvertedList(){
		converted_list_single = new ArrayList<>();
		converted_list_single = gson_lib.fromJson(this.single_list, List.class);
		return converted_list_single;
	}
	
	public void setSingleListForConversion(String list){
		System.out.println("===Coming here====");
		this.single_list = list;
		System.out.println("===Coming here====");
	}
}
