package ic.doc.cpp.client.company.core.postingevent;

import ic.doc.cpp.shared.dto.EventCategoryDto;
import ic.doc.cpp.shared.dto.EventDto;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class PostingEventView extends ViewImpl implements
		PostingEventPresenter.MyView {

	private final VLayout container;
	private final DynamicForm mainForm;
	private final IButton submitButton;
	private final SelectItem categoryItem;
	private final RichTextEditor richTextEditor;
	private final CheckboxItem onlySendToStudentLikeCompany;
	
	@Inject
	public PostingEventView() {
		container = new VLayout();
		container.setShowEdges(true);
		VLayout header = initHeader("Posting Event", "You can post your new event here.");
		header.setStyleName("company-postevent-Header");
		
		VLayout body = new VLayout();
		body.setBorder("1px solid black");
		body.setStyleName("company-postevent-Body");
		
		mainForm = new DynamicForm();
		mainForm.setBorder("1px solid black");
		mainForm.setNumCols(4);
		
		TextItem titleTextItem = new TextItem("title");
		titleTextItem.setRequired(true);
        titleTextItem.setTitle("Title");
        
        TextItem websiteTextItem = new TextItem("website");
        websiteTextItem.setTitle("Website");
        
        TextItem startDate = new TextItem("startDate");
        startDate.setTitle("Start Date");
        startDate.setRequired(true);
        startDate.setHint("<nobr>yyyy-mm-dd</nobr>");
        
        TextItem endDate = new TextItem("endDate");
        endDate.setTitle("End Date");
        endDate.setRequired(true);;
        endDate.setHint("<nobr>yyyy-mm-dd</nobr>");
        
        RegExpValidator dateValidator = new RegExpValidator();
        dateValidator.setErrorMessage("Invalid Date");
        dateValidator.setExpression("\\d{4}-\\d{2}-\\d{2}");
        
        startDate.setValidators(dateValidator);
        endDate.setValidators(dateValidator);

        TextItem startTime = new TextItem("startTime");
        startTime.setRequired(true);
        startTime.setTitle("Start Time");
        startTime.setHint("<nobr>HH:MM</nobr>");
        
        TextItem endTime = new TextItem("endTime");
        endTime.setRequired(true);
        endTime.setTitle("End Time");
        endTime.setHint("<nobr>HH:MM</nobr>");
        
        RegExpValidator timeValidator = new RegExpValidator();
        timeValidator.setErrorMessage("Invalid Date");
        timeValidator.setExpression("\\d{2}:\\d{2}");
        
        startTime.setValidators(timeValidator);
        endTime.setValidators(timeValidator);
        
        categoryItem = new SelectItem("category");
        categoryItem.setRequired(true);
        categoryItem.setTitle("Category");
        categoryItem.setMultiple(true);
        categoryItem.setMultipleAppearance(MultipleAppearance.GRID);
        categoryItem.setHeight(60);
        categoryItem.setDefaultValues("Loading");
        
        onlySendToStudentLikeCompany = new CheckboxItem("sendToInterestedStudent");
        onlySendToStudentLikeCompany.setTitle("Only send to Student who is <b>intersted</b> in the company?");
        
        mainForm.setItems(titleTextItem, websiteTextItem, 
        		startDate, endDate, startTime, endTime, 
        		categoryItem, onlySendToStudentLikeCompany);

        richTextEditor = new RichTextEditor();
        richTextEditor.setOverflow(Overflow.HIDDEN);
        richTextEditor.setBorder("1px solid black");
        richTextEditor.setControlGroups("formatControls", "styleControls", "colorControls");
        richTextEditor.setValue("<font face=\"Bookman Old Style, Book Antiqua, Garamond\"></font>");
        
		body.addMember(mainForm);
		body.addMember(richTextEditor);
		
		submitButton = new IButton("Submit");
		VLayout footer = initFooter(submitButton);
		
		container.addMember(header);
		container.addMember(body);
		container.addMember(footer);
	}

	@Override
	public Widget asWidget() {
		return container;
	}
	
	@Override
	public boolean validateForm() {
		boolean result = true;

		if (result = mainForm.validate()) {
			if (!(result = result && (getStartDate().before(getEndDate()))))
				SC.say("End date should not before start date");
		} 
		
		return result;
	}
	
	
	
	public String getCategory() {
		return mainForm.getValueAsString("category");
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
	    name.setStyleName("company-postevent-Name");
	    name.setContents(title);
	    name.setWidth100();

	    // initialise the Description label
	    Label descriptionLabel = new Label();
	    descriptionLabel.setStyleName("company-postevent-Description");
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
		footer.setStyleName("company-postevent-Footer");
		footer.setWidth100();
		footer.setHeight(footerHeight);
		
		HLayout hLayout = new HLayout();
		hLayout.setWidth100();
		hLayout.setHeight(footerHeight);
		
		updateButton.setShowRollOver(true);
		updateButton.setShowDisabled(true);
		updateButton.setShowDown(true);
		
		
		// layout the OK
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
	public void SetCategoryValues(List<EventCategoryDto> categories) {
		LinkedHashMap<String , String> map = new LinkedHashMap<String, String>();
		for (EventCategoryDto c : categories) {
			map.put(String.valueOf(c.getCategoryId()), getShorterName(c.getCategoryName()));
		}
		categoryItem.clearValue();
		categoryItem.setValueMap(map);
		
	}

	private String getShorterName(String categoryName) {
		return categoryName.substring(categoryName.lastIndexOf("/") + 1);
	}
	
	@Override
	public boolean isOnlySendToInterstedStudent() {
		return onlySendToStudentLikeCompany.getValueAsBoolean();
	}

	@Override
	public HandlerRegistration addSubmmitButtionClickHandler(
			ClickHandler clickHandler) {
		return submitButton.addClickHandler(clickHandler);
	}

	@Override
	public EventDto getEventInformation() {
		EventDto eventDto = new EventDto();
		eventDto.setTitle(mainForm.getValueAsString("title"));
		eventDto.setWebsite(mainForm.getValueAsString("website"));
		eventDto.setStart_date(getStartDate());
		eventDto.setEnd_date(getEndDate());
		eventDto.setDescription(richTextEditor.getValue());
		eventDto.setCategoryId(stringToLong(categoryItem.getValueAsString()));
		return eventDto;
	}
	
	private Long stringToLong(String str) {
		Long l = 0L;
		char zero = '0';
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			int diff = c - zero;
			l = l*10 + diff;
		}
		return l;
	}
	
	private Date getStartDate() {
		String date = mainForm.getValueAsString("startDate");
		String time = "";
		Date startDate;
		if ((time = mainForm.getValueAsString("startTime")) != null) {
			startDate = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm").parse(date + " " + time);
		} else {
			startDate = DateTimeFormat.getFormat("yyyy-MM-dd").parse(date);
		}
		return startDate;
	}
	
	private Date getEndDate() {
		String dateStr = mainForm.getValueAsString("endDate");
		String time = "";
		Date date;
		if ((time = mainForm.getValueAsString("endTime")) != null) {
			date = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm").parse(dateStr + " " + time);
		} else {
			date = DateTimeFormat.getFormat("yyyy-MM-dd").parse(dateStr);
		}
		return date;
	}
	
}
