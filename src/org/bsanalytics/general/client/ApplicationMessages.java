package org.bsanalytics.general.client;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ApplicationMessages {
	
	
	 public void addFacesMessage(String messageText) {
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(messageText));
	    }

}
