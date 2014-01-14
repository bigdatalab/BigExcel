package org.bsanalytics.apis.viewdata;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;


public class ViewDataServlet extends Application{
	
	 @Override
	 public Set<Class<?>> getClasses() {
	 Set<Class<?>> classes = new HashSet<Class<?>>();
	 classes.add(LoadTable.class);
	 return classes;
	 }
}
