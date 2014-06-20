package org.bsanalytics.apis.analysis;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;


public class QueryDataServlet extends Application{
	
	 @Override
	 public Set<Class<?>> getClasses() {
	 Set<Class<?>> classes = new HashSet<Class<?>>();
	 classes.add(AnalyticsInterface.class);
	 return classes;
	 }

}
