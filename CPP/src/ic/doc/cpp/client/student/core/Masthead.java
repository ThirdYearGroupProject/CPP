package ic.doc.cpp.client.student.core;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class Masthead extends HLayout {
	
	private static final int MASTHEAD_HEIGHT = 58;
	private final Label signedInUser;
	  public Masthead() {
		super();
			
		GWT.log("init Masthead()...", null);
		
	    // initialise the Masthead layout container
		setStyleName("masthead");	
	    setHeight(MASTHEAD_HEIGHT);
		// initialise the Logo image
	    Img logo = new Img("logo.png", 48, 48); 
	    logo.addStyleName("masthead-Logo");	  
	    
		// initialise the Name label	
		Label name = new Label();  
		name.setStyleName("mastHead-Name");  
		name.setContents("Student"); 
	    
	    // initialise the West layout container
	    HLayout westLayout = new HLayout();
	    westLayout.setHeight(MASTHEAD_HEIGHT);	
	    westLayout.setWidth("50%");
	    westLayout.addMember(logo);
	    westLayout.addMember(name);
	    
	    // initialise the Signed In User label
		signedInUser = new Label();  
		signedInUser.addStyleName("mastHead-SignedInUser");  
	    
	    // initialise the East layout container
	    HLayout eastLayout = new HLayout();
	    eastLayout.setAlign(Alignment.RIGHT);  
	    eastLayout.setHeight(MASTHEAD_HEIGHT);
	    eastLayout.setWidth("50%");
	    eastLayout.addMember(signedInUser);
	    
	    // add the West and East layout containers to the Masthead layout container
		this.addMember(westLayout);  	
		this.addMember(eastLayout); 
	  }	
	  
	  public void setSignedInUser(String name) {
		  signedInUser.setContents("<b>" + name + "</b>");   
	  }
}
