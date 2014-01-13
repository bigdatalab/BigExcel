package org.bsanalytics.ActionListeners;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;


public class ActionListenerViewLoadData {
	
	private String testmethod;

	   public String getTestmethod() {
		return testmethod;
	}

	public void setTestmethod(String testmethod) {
		this.testmethod = testmethod;
	}

	public static final class ActionListenerImpl implements ActionListener {

	        public void processAction(ActionEvent event) throws AbortProcessingException {
	            //addFacesMessage("Implementation of ActionListener created and called: " + this);
	        }
	        
	    }
	    
	    private static final class BoundActionListener implements ActionListener {

	        public void processAction(ActionEvent event) throws AbortProcessingException {
	            addFacesMessage("Bound listener called...");
	            System.out.println("Here we go.. Bounded");
	        }
	        
	    }

	    private ActionListener actionListener = new BoundActionListener();
	    
	    public void setActionListener(ActionListener actionListener) {
			this.actionListener = actionListener;
		}

		private static void addFacesMessage(String messageText) {
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(messageText));
	    }
	    
	    public void handleActionMethod(ActionEvent event) throws AbortProcessingException {
	        addFacesMessage("Method expression listener called");
	    	System.out.println("Here we go..");
	    }
	    
	    public void handleActionMethodComposite(ActionEvent event) throws AbortProcessingException {
	        addFacesMessage("Method expression listener called from composite component");
	    }
	    
	    public ActionListener getActionListener() {
	        return actionListener;
	    }
	    
	    public void testmethod(){
	    	System.out.println("Yes it is---()()()()");
	    	
	    }
}