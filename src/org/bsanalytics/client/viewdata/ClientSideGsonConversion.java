package org.bsanalytics.client.viewdata;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ClientSideGsonConversion {
	
	String list;
	Gson gson_lib = new Gson();
	List<List<Object>> converted_list;
	
	@SuppressWarnings("unchecked")
	public List<List<Object>> getConvertedList(){
		converted_list = new ArrayList<>();
		converted_list = gson_lib.fromJson(this.list, List.class);
		return converted_list;
	}
	
	public void setListForConversion(String list){
		
		this.list = list;
	}

}
