package org.bsanalytics.client.analysis;

import java.util.ArrayList;
import java.util.List;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.bsanalytics.client.loaddata.ClientObject;
import org.bsanalytics.general.client.ClientSideGsonConversion;

public class PartialPaginationTableColumns {
	
	List<List<Object>> list;
	List<String> columns;
	String first_name;
	String second_name;
	int flag=0;
	ClientObject cObj = new ClientObject();
    RestClient client_wink = cObj.getClientObject();
    //ViewTableBackingBean table_name_obj = new ViewTableBackingBean();
    CustomTableViewBean table_name_obj = new CustomTableViewBean();
    ClientSideGsonConversion cGson = new ClientSideGsonConversion();
    
	//CSVHandler csv_dat = new CSVHandler();
	static List<String> column_names_list;

	
	
	public String getFirst_name() {
		first_name="First Name";
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getSecond_name() {
		second_name = "Second Name";
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public List<List<Object>> getList() {
		list = takeList();
		//System.out.println("coming... List =" + list);
		return list;
	}

	public void setList(List<List<Object>> list) {
		this.list = list;
	}
	
	public List<String> getColumns() {
		columns = takeColumns();
		System.out.println("coming... Columns");
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public List<List<Object>> takeList(){
		/*System.out.println("Invoked");
		List<List<String>> li = new ArrayList<>();
		List<String> return_list;
		
		ReadCSVFileNumberCount total_rows = new ReadCSVFileNumberCount();
		
		//sequence is really important here
		LoadTableFromDataBase lTFD = new LoadTableFromDataBase();
    	lTFD.loadDataFromTable("sample");
    	int column_count = lTFD.getColumnCount();
    	if (flag == 0){
    		li = new ArrayList<>(total_rows.GetLineCount(""));
    		System.out.println("flag 0===");
    		flag=1;
    		return li;
    	}
    	return_list = lTFD.getTCustomRowsList(column_count, 10);
    	li = lTFD.getListTwo();
    	//System.out.println(li.get(0));
    	//end sequence
    	
    	if (return_list == null){
    		System.out.println("breaking---");
    		return null;
    	}*/
    			
		
		List<List<Object>> li = new ArrayList<>();
		List<Object> innli = new ArrayList<>();
		innli.add(10);
		innli.add(20);
		li.add(innli);
		
		List<Object> innli1 = new ArrayList<>();
		innli1.add(10);
		innli1.add(20);
		li.add(innli1);
		
		List<Object> innli2 = null;
		for (int i=1 ; i<100 ; i++){
			innli2 = new ArrayList<>();
			innli2.add(i);
			innli2.add(i+1);
			li.add(innli2);
			
		}
		
		return li;
	}
	
	public List<String> takeColumns(){
		 /*List<String> li = new ArrayList<String>();
		 li.add("FirstName");
		 li.add("LastName");li.add("Company");li.add("Address");li.add("City");
		 li.add("County");li.add("State");li.add("Zip");li.add("Phone1");
		 li.add("Phone2");li.add("Email");li.add("Web");*/
		 /*li.add("Web");li.add("Web");li.add("Web");li.add("Web");li.add("Web");li.add("Web");
		 li.add("Web");li.add("Web");li.add("Web");li.add("Web");li.add("Web");li.add("Web");
		 li.add("Web");li.add("Web");li.add("Web");li.add("Web");li.add("Web");li.add("Web");*/
		 //li = csv_dat.getColumnNamesFromCsvFile(SelectedFilePath.file_path_from_the_disk);
		 //return li;
		
		//String table_name = table_name_obj.getView_table_name();
		String table_name="sample";
    	Resource resource = client_wink.resource("http://localhost:8080/bsanalytics/jaxrs_view/view_data/"+table_name+"/l/c");
		String response = resource.accept("text/json").get(String.class);
		cGson.setSingleListForConversion(response);
		//Pagination.column_names_list = cGson.getSingleConvertedList();
		System.out.println(response);
		return cGson.getSingleConvertedList();
		 
	}  


}
