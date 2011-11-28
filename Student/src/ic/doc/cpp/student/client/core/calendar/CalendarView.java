package ic.doc.cpp.student.client.core.calendar;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceLinkField;
import com.smartgwt.client.data.fields.DataSourceSequenceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.calendar.CalendarEvent;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class CalendarView extends ViewImpl implements CalendarPresenter.MyView {

	private final VLayout widget;
	private final Calendar  calendar;
	@Inject
	public CalendarView() {
		// initialise the Calendar View layout container
		widget = new VLayout();
		widget.setStyleName("crm-ContextArea");
		widget.setWidth100(); 	
		
		DataSource eventDS = new DataSource();  
		DataSourceSequenceField eventIdField = new DataSourceSequenceField("eventId");  
		eventIdField.setPrimaryKey(true);  
		   
		DataSourceTextField nameField = new DataSourceTextField("name");  
		DataSourceTextField descField = new DataSourceTextField("description");
		DataSourceLinkField websiteField = new DataSourceLinkField("website");
		DataSourceDateField startDateField = new DataSourceDateField("startDate");  
		DataSourceDateField endDateField = new DataSourceDateField("endDate");  
		   
		eventDS.setFields(eventIdField, nameField, descField, startDateField, endDateField, websiteField);  
		eventDS.setClientOnly(true);  
		   
		calendar = new Calendar();  
		calendar.setShowWeekends(false);  
		calendar.setShowWorkday(true);  
		calendar.setScrollToWorkday(true);  
		calendar.setAutoFetchData(true);  
		calendar.setCanEditEvents(false);
		calendar.setDataSource(eventDS);
		
	    LayoutSpacer paddingTop = new LayoutSpacer(); 
	    paddingTop.setHeight(8);
		
	    widget.addMember(paddingTop);
	    widget.addMember(calendar);
	}
	
	

	@Override
	public Widget asWidget() {
		return widget;
	}



	@Override
	public void setData(CalendarEvent[] calendarEvents) {
		calendar.setData(calendarEvents);
	}
}
