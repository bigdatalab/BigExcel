package org.bsanalytics.apis.loaddata;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;



public class LoadDataServlet extends Application{
	
	 @Override
	 public Set<Class<?>> getClasses() {
	 Set<Class<?>> classes = new HashSet<Class<?>>();
	 classes.add(LoadData.class);
	 return classes;
	 }

}
