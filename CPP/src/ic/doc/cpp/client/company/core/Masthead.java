package ic.doc.cpp.client.company.core;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

public class Masthead extends VLayout {
	
	private static final int HEADER_WIDTH = 58;
	
	public Masthead() {
		super();
			
		GWT.log("init Masthead()...", null);
		
		setHeight(HEADER_WIDTH);
		setStyleName("companyHeader");	
		
		// initialise the Name label	
		Label name = new Label("Company");  
		name.setStyleName("companyHeaderTitle");  
		name.setHeight(38);
		
		addMember(name);
	  }	
	  
}
