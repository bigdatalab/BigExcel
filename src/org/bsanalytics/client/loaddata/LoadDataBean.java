package org.bsanalytics.client.loaddata;




public class LoadDataBean {
	
	private static String createquery;
	public static String deletequery;
	private static String applicationresponse;
	private static String selectedfilename;
	private static String load_text_file;
	
	public String getLoad_text_file() {
		return load_text_file;
	}
	public void setLoad_text_file(String load_text_file) {
		LoadDataBean.load_text_file = load_text_file;
	}
	
	
	public String getCreatequery() {
		return createquery;
	}
	public void setCreatequery(String createquery) {
		LoadDataBean.createquery = createquery;
	}
	public String getDeletequery() {
		System.out.println("==deletequery Query==" + deletequery);
		return deletequery;
	}
	public void setDeletequery(String deletequery) {
		LoadDataBean.deletequery = deletequery;
	}
	public String getApplicationresponse() {
		return applicationresponse;
	}
	public void setApplicationresponse(String applicationresponse) {
		LoadDataBean.applicationresponse = applicationresponse;
	}
	public String getSelectedfilename() {
		return selectedfilename;
	}
	public void setSelectedfilename(String selectedfilename) {
		LoadDataBean.selectedfilename = selectedfilename;
	}

}
