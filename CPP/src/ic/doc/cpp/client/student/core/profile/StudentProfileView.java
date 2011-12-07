package ic.doc.cpp.client.student.core.profile;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class StudentProfileView extends ViewImpl implements
		StudentProfilePresenter.MyView {
	
	private final VLayout container;
	private VLayout navigationPanel;
	private VLayout body;
	
	private ToolStripButton generalInformation;
	private ToolStripButton interestedCompany;
	private ToolStripButton interestedArea;
	private ToolStripButton securitySetting;
	
	@Inject
	public StudentProfileView() {
		container = new VLayout();
		
		navigationPanel = new VLayout();
		intialiseNavigationPanel();
		container.addMember(navigationPanel);
		
		body = new VLayout();
		container.addMember(body);
	}

	private void intialiseNavigationPanel() {
		navigationPanel.setHeight("50px");
		ToolStrip toolStrip = new ToolStrip();
		toolStrip.setHeight("50px");
		toolStrip.setWidth("100%");
		
		generalInformation = new ToolStripButton();
		generalInformation.setHeight(48);
		generalInformation.setIcon("icons/48/generalinfo.png");
		generalInformation.setIconSize(48);
		generalInformation.setTitle("General Information");
		generalInformation.setWidth("80px");
		toolStrip.addButton(generalInformation);

		interestedCompany = new ToolStripButton();
		interestedCompany.setHeight(48);
		interestedCompany.setIcon("icons/48/company.png");
		interestedCompany.setIconSize(48);
		interestedCompany.setTitle("Interested Company");
		interestedCompany.setWidth("80px");
		toolStrip.addButton(interestedCompany);

		interestedArea = new ToolStripButton();
		interestedArea.setHeight(48);
		interestedArea.setIcon("icons/48/area.png");
		interestedArea.setIconSize(48);
		interestedArea.setTitle("Interested Area");
		interestedArea.setWidth("80px");
		toolStrip.addButton(interestedArea);

		securitySetting = new ToolStripButton();
		securitySetting.setHeight(48);
		securitySetting.setIcon("icons/48/security.png");
		securitySetting.setIconSize(48);
		securitySetting.setTitle("Security Settings");
		securitySetting.setWidth("80px");
		toolStrip.addButton(securitySetting);
		
		navigationPanel.addMember(toolStrip);
	}
	
	public static VLayout initHeader(String title, String description) {
	    // initialise the Header layout container
	    VLayout header = new VLayout();
	    header.setWidth100();
	    header.setHeight(58);

	    HLayout line1 = new HLayout();
	    line1.setWidth100();
	    line1.setHeight100();

	    HLayout line2 = new HLayout();
	    line2.setWidth100();
	    line2.setHeight100();

	    // initialise the Name label
	    Label name = new Label();
	    name.setStyleName("wizard-Name");
	    name.setContents(title);
	    name.setWidth100();

	    // initialise the Description label
	    Label descriptionLabel = new Label();
	    descriptionLabel.setStyleName("wizard-Description");
	    descriptionLabel.setContents(description);
	    descriptionLabel.setWidth100();

	    // add the labels to the nested layout containers
	    line1.addMember(name);
	    line2.addMember(descriptionLabel);

	    // add the North and South layout containers to the main layout container
	    header.addMember(line1);
	    header.addMember(line2);
	    return header;
	}

	public static VLayout initFooter(IButton updateButton) {
		int footerHeight = 48;
		    // initialise the Footer layout container
		VLayout footer = new VLayout();
		footer.setStyleName("wizard-Footer");
		footer.setWidth100();
		footer.setHeight(footerHeight);
		
		HLayout hLayout = new HLayout();
		hLayout.setWidth100();
		hLayout.setHeight(footerHeight);
		
		updateButton.setShowRollOver(true);
		updateButton.setShowDisabled(true);
		updateButton.setShowDown(true);
		
		
		// layout the OK and Cancel buttons
		hLayout.setLayoutMargin(8);
		hLayout.addMember(new LayoutSpacer());
		hLayout.addMember(updateButton);
		LayoutSpacer padding = new LayoutSpacer();
		padding.setWidth(8);
		hLayout.addMember(padding);
		
		// add the nested layout container to the main layout container
		footer.addMember(hLayout);

		return footer;
	}

	@Override
	public Widget asWidget() {
		return container;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		GWT.log("StudentProfileView.setInSlot()...");
		
		if (slot == StudentProfilePresenter.TYPE_SetStudentProfileContent) {
			if (content != null) {
				body.setMembers((VLayout)content);
			}
		} else { 
			super.setInSlot(slot, content);
		}
	}

	@Override
	public HandlerRegistration addGeneralInformationClickHandler(
			ClickHandler clickHandler) {
		return generalInformation.addClickHandler(clickHandler);
	}

	@Override
	public HandlerRegistration addInterestedAreaClickHandler(
			ClickHandler clickHandler) {
		return interestedArea.addClickHandler(clickHandler);
	}
	
	@Override
	public HandlerRegistration addInterestedCompanyClickHandler(
			ClickHandler clickHandler) {
		return interestedCompany.addClickHandler(clickHandler);
	}
	
	@Override
	public HandlerRegistration addSecuritySettingClickHandler(
			ClickHandler clickHandler) {
		return securitySetting.addClickHandler(clickHandler);
	}
}
