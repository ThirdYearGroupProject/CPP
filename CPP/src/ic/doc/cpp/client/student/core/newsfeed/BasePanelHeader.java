package ic.doc.cpp.client.student.core.newsfeed;

import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class BasePanelHeader extends HLayout {
	private final Label titleLabel;
	private final Label backLabel;
	private final Label editLabel;
	
	public BasePanelHeader(boolean hasParent) {
		setHeight(43);
	    setAlign(VerticalAlignment.CENTER);
		
		titleLabel = new Label();
		backLabel = new Label();
		editLabel = new Label();
		
		if (hasParent) {
			backLabel.setContents("Back");
			backLabel.setHeight(30);
			backLabel.setAlign(Alignment.CENTER);
			backLabel.setStyleName("backButton");
		}
		
		backLabel.setWidth(43);
		
		  
	    titleLabel.setWidth100();
	    titleLabel.setAlign(Alignment.CENTER);
	    titleLabel.setStyleName("title");
	    
	    editLabel.setWidth(43);
	    
	    addMember(backLabel);
	    addMember(titleLabel);
	    addMember(editLabel);
	    
	    setStyleName("header");
	}
	
	public void setTitle(String title) {
		titleLabel.setContents(title);
	}
	
	public HandlerRegistration setEditLabel(String label, String title, ClickHandler handler) {
//		editLabel.setContents(label);
//		editLabel.setTitle(title);
//		editLabel.setAlign(Alignment.CENTER);
//		editLabel.setHeight(30);
//		editLabel.setStyleName("goButton");
		return editLabel.addClickHandler(handler);
	}

	public HandlerRegistration addBackClickHandler(ClickHandler handler) {
		return backLabel.addClickHandler(handler);
	}
	
	public HandlerRegistration addTitleClickHandler(ClickHandler handler) {
		return titleLabel.addClickHandler(handler);
	} 
}
