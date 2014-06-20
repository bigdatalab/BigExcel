package org.bsanalytics.client.analysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.bsanalytics.client.loaddata.ClientObject;
import org.bsanalytics.client.loaddata.FileChooserForLoadingData;
import org.bsanalytics.dashboard.ServerAccessPath;
import org.bsanalytics.general.client.ApplicationMessages;
import org.bsanalytics.general.client.ClientSideGsonConversion;

import com.google.gson.Gson;




/**
 * @author asif
 * This class is responsible for calling REST API
 * for all analysis related requests
 *
 */
public class QueryHiveCall {
	
	String response_str;
	String graph_response_str;
	
	public String getGraph_response_str() {
		return graph_response_str;
	}

	public void setGraph_response_str(String graph_response_str) {
		this.graph_response_str = graph_response_str;
	}

	ClientObject cObj = new ClientObject();
	RestClient client_wink= cObj.getClientObject();
	
	HiveQueryBean take_hive_query = new HiveQueryBean();
	UtilityFunctionBean take_utility_function = new UtilityFunctionBean();
	CustomTableViewBean take_rows_range = new CustomTableViewBean();
	
	ApplicationMessages faceMessage = new ApplicationMessages();
	ClientSideGsonConversion cGson = new ClientSideGsonConversion();
	
	String hive_query=null;
	String compute_function_query=null;
	String rows_range_query=null;
	
	StringBuilder stb_str1,stb_str2=null;
	
	public String getResponse_str() {
		return response_str;
	}

	public void setResponse_str(String response_str) {
		this.response_str = response_str;
	}
	
	public void queryHive(){
		
		
		hive_query = take_hive_query.getHive_query().trim();
    	System.out.println("hive query = " + hive_query);
    	//System.out.println("============Before Client Call===========");
    	Resource resource = client_wink.resource(
    	ServerAccessPath.SERVER_PATH + "/bsanalytics/jaxrs_query/query_data/hive_query");
    	response_str = resource.accept("text/plain").post(String.class,hive_query);
    	setResponse_str(response_str);
    	setFaceMessageForThisCall(response_str);
    	//System.out.println("============After Client Call=============");
		//System.out.println("After Getting response");		
	}
	
	
	public void computeUtilityFunctions(){
		String table_name = take_utility_function.getTable_name();
		String column_name = take_utility_function.getColumn_name();
		String function_name = take_utility_function.getSelected_item();
				
		if (table_name.length() == 0)
		{
			setResponse_str("Please enter table name");
			setFaceMessageForThisCall("Please enter table name");
			return;
		}
				
		if (column_name.length() == 0)
		{
			setResponse_str("Please enter column name");
			setFaceMessageForThisCall("Please enter column name");
			return;
		}
		
		if (function_name.length() == 0)
		{
			setResponse_str("Please select the function");
			setFaceMessageForThisCall("Please select the function");
			return;
		}
		
		
		compute_function_query = table_name + ":" + column_name + ":" + function_name;
		Resource resource = client_wink.resource(
		    	ServerAccessPath.SERVER_PATH + "/bsanalytics/jaxrs_query/query_data/utility_functions");
		    	response_str = resource.accept("text/plain").post(String.class,compute_function_query);
		setResponse_str(response_str);
		setFaceMessageForThisCall(response_str);
	}
		
	
	public void loadRowsRange(){
		
		String table_name = "sample";//take_rows_range.getView_table_name();
		String row_range = "20";//take_rows_range.getRange();
		rows_range_query = table_name + ":" + row_range;
		Resource resource = client_wink.resource(
	 			"http://localhost:8080/bsanalytics/jaxrs_view/view_data/"+table_name+"/"+row_range+"/pl/analytics");
	 			String response = resource.accept("text/json").get(String.class);
	 			System.out.println(response);
	 	take_rows_range.setTable_list(getConvertedList(response));
		
	}
	
	public List<List<Object>> getConvertedList(String response)
	{
		cGson.setListForConversion(response);
			List<List<Object>> list_chunck = new ArrayList<>(); 
      	list_chunck = cGson.getConvertedList();
      	return list_chunck;
	}
	
	public void setFaceMessageForThisCall(String message){
    	//for message rendering
		faceMessage.addFacesMessage(message);
    }
	

	
	
	
	public String generateGraph(){
		
		System.out.println("Call to generateGraph");
		FileChooserForLoadingData fcld = new FileChooserForLoadingData();
		String abs_path = fcld.getPath();
		String split[] =  abs_path.split("/");
		int len = split.length;
		StringBuilder stb = new StringBuilder();
		for (int i=0 ; i<len ; i++){
			stb.append(split[i]);
			if (i!= 0 && i != len-1)
				stb.append("-");
		}
		
		String tmp = stb.toString();
		System.out.println(tmp);
		System.out.println("Before Calling Path: " + fcld.getPath());
		hive_query = take_hive_query.getHive_query().trim();
		String where_clause = take_hive_query.getWhere_clause().trim();
		String dates = take_hive_query.getDates().trim();
		hive_query = hive_query + "@" + where_clause + "@" + dates;
		
		Resource resource = client_wink.resource(
		    	ServerAccessPath.SERVER_PATH + "/bsanalytics/jaxrs_query/query_data/"+hive_query+"/"+tmp+"/partial_load/analytics/graph_creation");
		    	response_str = resource.accept("text/json").get(String.class);
	 			System.out.println(response_str);
	 			//String json = "[[\"2005-05-23\",\" 1:00:00\",\" 10.3742886731065\"],[\"2005-05-23\",\" 2:00:00\",\" 8.46807959722938\"],[\"2005-05-23\",\" 3:00:00\",\" 10.0826387551814\"]]";
	 			//response_str = json;
	 			cGson.setListForConversion(response_str);
	 			
	 			List<List<Object>> table_rows_list = cGson.getConvertedList();	
	 			List<List<Object>> lii = intermediateConversionEPRP(table_rows_list);
	 			
	 			response_str = convertJsonExecutable(lii);
	 			
		return null;
	}
	
	
	public List<List<Object>> intermediateConversionEPRP(List<List<Object>> li){
		
		ExtrapoationbasedPrediction exp = new ExtrapoationbasedPrediction();
		RegressionbasedPrediction rbp = new RegressionbasedPrediction();
		
		//getting list last element, which is predicted date
		int l_size = li.size();
		List<Object> last_list = li.get(l_size-1);
		double last_value = Double.parseDouble((String) last_list.get(1));
		
		double res = exp.calculateExtrapolation(li);
		double regression_res = rbp.calculateRegressionbasedPrediction(li);
		
		System.out.println("Double value = " + res);
		List<List<Object>> return_list = new ArrayList<>();
		
		List<Object> liii3 = new ArrayList<>();
		liii3.add(last_value);
		liii3.add("Actual");
		
		List<Object> liii = new ArrayList<>();
		liii.add(res);
		liii.add("EP");
		
		List<Object> liii2 = new ArrayList<>();
		liii2.add(regression_res);
		liii2.add("RP");
		
		return_list.add(liii3);
		return_list.add(liii);
		return_list.add(liii2);
		
		
		/*List<Object> liii2 = new ArrayList<>();
		liii2.add(res);
		
		return_list.add(liii2);*/
		System.out.println(return_list);
		return return_list;
		
		//RegressionbasedPrediction rbp = new RegressionbasedPrediction();
		//double res_rbp = rbp.calculateRegressionbasedPrediction(null,null);
		
	}
		

	 //dataTable: [['', 'Germany', 'USA', 'Brazil', 'Canada', 'France', 'RU'],
	//  ['', 700, 300, 400, 500, 600, 800]],
	//	options: {'title': 'Countries'},
	
	public void dataConversionForPlotting(List<List<Object>> li){
		
		int len=0;
		stb_str1 = new StringBuilder();
		stb_str2 = new StringBuilder();
		
		stb_str1.append("[");
		
	    stb_str1.append("['',");
		stb_str2.append("['',");
		
		int double_size = li.size();
		int cnt=0;
		
		for (List list : li){
			cnt++;
			len = list.size();
			
			for (int i=1 ; i<len ; i++){
						if (i%2 != 0)
						{
							if (cnt != double_size)
								stb_str1.append("'"+list.get(i)+"',");
							else
								stb_str1.append("'"+list.get(i)+"'");
							
						}
						else
						{
							if (cnt != double_size)
								stb_str2.append(list.get(i)+",");
							else
								stb_str2.append(list.get(i));
						}
				
			}
		}
				
		
			stb_str1.append("]");
			stb_str2.append("]");
			
			stb_str2.append("]");
		
			//Concatenating the string
			String complete_str = stb_str1.toString()+","+stb_str2.toString();
			
			//writing Json string to file
			//read by the ajax call from graph xhtml file
			wirteJsonToFile(complete_str);
			
			
			System.out.println(complete_str);
		
	}
	
	
	public void wirteJsonToFile(String comp_string){
		
		BufferedWriter buff=null;
		try {
			
			System.out.println("Before writing the file");
			buff = new BufferedWriter(new FileWriter(new File("/home/hduser/workspace/bsanalytics/WebContent/dashboard/analysis/test")));
			buff.write(comp_string);
			buff.close();
			
		} catch (IOException e) {
			try {
				if (buff != null)
				buff.close();
			} catch (IOException e1) {
				System.out.println("unable to close the buffer for writer");
				e1.printStackTrace();
			}
			System.out.println("Unable to write the file");
			e.printStackTrace();
		}
	
		
	}
	
	
	/*{
		  "cols": [
		        {"label":"Topping","type":"string"},
		        {"label":"Slices","type":"number"}
		      ],
		  "rows": [
		        {"c":[{"v":"Mushrooms"},{"v":4}]},
		        {"c":[{"v":"Onions"},{"v":1}]},
		        {"c":[{"v":"Olives"},{"v":1}]},
		        {"c":[{"v":"Zucchini"},{"v":1}]},
		        {"c":[{"v":"Pepperoni"},{"v":2}]}
		      ]
		}*/
	
	public String convertJsonExecutable(List<List<Object>> li){
		
		
			
		int len=0;
		stb_str1 = new StringBuilder();
		stb_str2 = new StringBuilder();
		
		String temp_str0=new String();
		String temp_str1=new String();
		String temp_str2=new String();
		
		StringBuilder rows = new StringBuilder();
		
		String row_const1= "\"rows\": [";
		String row_const2= "]";
		
		String col_const1= "\"cols\": [";
		String col_const2= "]";
	    
		boolean flag1=true;
		boolean flag2=true;
		
		StringBuilder stb_col0 = new StringBuilder();
		StringBuilder stb_col1 = new StringBuilder();
		StringBuilder stb_col2 = new StringBuilder();
		
	    int double_size = li.size();
		int cnt=0;
		int check_flag1=0;
		int check_flag2=0;
		
		for (List list : li){
			cnt++;
			len = list.size();
			
			//i variable for the loop
			int i=0;
			if (len > 2){
				
				i=1;
			}
			
			for (; i<len ; i++){
				System.out.println("I value =" + i);
						if (i%2 != 0)
						{	
							System.out.println("First");
							if (flag1){
								//stb_col0.append("{\"label\":\"Date\",\"type\":\"string\"}");
								stb_col1.append("{\"label\":\"Time\",\"type\":\"string\"}");
								flag1=false;
							}
							   //"rows": [{"c":[{"v":" 1:00:00"}]},,{"c":[{"v":" 1:00:00"}]}
							
									//stb_str1.append("{\"c\":[{"+"\"v\":"+"\""+list.get(i)+"\"}]}");
									//temp_str0 = "{\"c\":[{"+"\"v\":"+"\""+list.get(i-1)+"\"}";
									temp_str1 = "{\"c\":[{"+"\"v\":"+"\""+list.get(i)+"\"}";
									check_flag1=1;
						}
						else
						{
							System.out.println("Second");
							if (flag2){
								stb_col2.append("{\"label\":\"Score\",\"type\":\"number\"}");
								flag2=false;
								
							}
							
								//stb_str2.append("{\"v\":"+list.get(i)+"}]}");
								temp_str2 = "{\"v\":"+list.get(i)+"}]}";
								check_flag2=1;
						}
						
						if (check_flag1==1 && check_flag2==1)
						{
							System.out.println("Yes");
							if (cnt != double_size)
							{
								System.out.println("Yes count is less then double_size");
								rows.append(temp_str1+","+temp_str2+",");
							}
							else
								rows.append(temp_str1+","+temp_str2);
							
							check_flag1=0;
							check_flag2=0;
						}
			}
		}
		
		String tmp = "{" + col_const1+stb_col1.toString()+","+stb_col2.toString()+col_const2+","
				+ row_const1 + rows.toString() + row_const2 + "}";
		
		wirteJsonToFile(tmp);
		
		setGraph_response_str(tmp);
		System.out.println("Value set to response graph string");
		System.out.println(getGraph_response_str());
		return tmp;
		
	}
	
	
	
	
	public static void main(String args[]){
		
		//String json = "[[\"2005-05-23\",\" 1:00:00\",\" 10.3742886731065\"],[\"2005-05-23\",\" 2:00:00\",\" 8.46807959722938\"],[\"2005-05-23\",\" 3:00:00\",\" 10.0826387551814\"]]";
		String json = "[[\"2005-05-23\",\" 10.3742886731065\"],[\"2005-05-23\",\" 8.46807959722938\"],[\"2005-05-23\",\" 10.0826387551814\"]]";
		ClientSideGsonConversion cGson = new ClientSideGsonConversion();
			System.out.println(json);
			List<List<Object>> table_rows_list = new ArrayList<>();
									
			cGson.setListForConversion(json);
			table_rows_list = cGson.getConvertedList();
			QueryHiveCall qh = new QueryHiveCall();
			List<List<Object>> lii = qh.intermediateConversionEPRP(table_rows_list);
			//qh.convertJsonExecutable(table_rows_list);
			qh.convertJsonExecutable(lii);
			
			//qh.generateGraph();

		
	}
	
}
