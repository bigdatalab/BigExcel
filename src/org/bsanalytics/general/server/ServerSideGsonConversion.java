package org.bsanalytics.general.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ServerSideGsonConversion {
	
	List<List<Object>> list;
	Gson gson_lib = new Gson();
	String converted_list;
	
	List<Object> single_list;
	
	@SuppressWarnings("unchecked")
	public String getConvertedString(){
		converted_list = gson_lib.toJson(this.list);
		return converted_list;
	}
	
	public void setListForConversion(List<List<Object>>  list){
		
		this.list = list;
	}
	
	
	public void setSingleListForConversion(List<Object> list){
		this.single_list = list;
	}
	
	@SuppressWarnings("unchecked")
	public String getSingleConvertedString(){
		converted_list = gson_lib.toJson(this.single_list);
		return converted_list;
	}


}
