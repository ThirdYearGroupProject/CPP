package ic.doc.cpp.client.company.core;

import ic.doc.cpp.client.company.core.CompanyPagePresenter.NavigationPaneRecordClickHandler;
import ic.doc.cpp.client.student.core.NavigationPane;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class CompanyPageView extends ViewImpl implements
		CompanyPagePresenter.MyView {

	
	private final VLayout container, header;
	private final HLayout body;
	private final VLayout bodyLeft, bodyRight;
	private final NavigationPane navigationPane;
	
	@Inject
	public CompanyPageView() {
		Window.enableScrolling(true);
		Window.setMargin("0px");
		
		header = new Masthead();
		body = new HLayout();
		
		navigationPane = new NavigationPane("navigationPane");
		navigationPane.addListgridSection("Main", MainNavigationPaneSectionData.getNewRecords());
		
		bodyLeft = navigationPane;

		bodyRight = new VLayout();
		
		
		body.addMember(bodyLeft);
		body.addMember(bodyRight);
		
		container = new VLayout();
		container.setWidth100();
		container.setHeight100();
		container.addMember(header);
		container.addMember(body);
	}

	@Override
	public Widget asWidget() {
		return container;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == CompanyPagePresenter.TYPE_SetContextAreaContent) {
			if (content != null) {
				bodyRight.setMembers((VLayout)content);
			}
		}
	}

	@Override
	public HandlerRegistration addNavigationPaneRecordClickHandler(
			String name, NavigationPaneRecordClickHandler clickHandler) {
		return navigationPane.addRecordClickHandler(name, clickHandler);
	}
	
	
}
