package ic.doc.cpp.client.student.core.profile;

import ic.doc.cpp.shared.dto.StudentUserDto;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.layout.VLayout;

public class StudentGeneralInfoView extends ViewImpl implements
		StudentGeneralInfoPresenter.MyView {

	private final VLayout container;
	
	private IButton updateButton;
	private DynamicForm basicInformationForm;
	private LinkItem cvUploadLinkItem;
	
	@Inject
	public StudentGeneralInfoView() {
		container = new VLayout();
		
		VLayout header = StudentProfileView.initHeader("Basic Information", "You can update your information here.");
		header.setStyleName("wizard-Header");
		
		VLayout southLayout = new VLayout();
		
		VLayout body = initBasicInformationTabBody();
		
		updateButton = new IButton("Update");
		VLayout footer = StudentProfileView.initFooter(updateButton);

		southLayout.addMember(body);
		southLayout.addMember(footer);
		
		container.addMember(header);
	    container.addMember(southLayout);
	}
	
	private VLayout initBasicInformationTabBody() {
		basicInformationForm = new DynamicForm();
		basicInformationForm.setCellPadding(2);
		basicInformationForm.setWrapItemTitles(false);
		
		TextItem firstNameItem = new TextItem("firstName", "First Name");
		firstNameItem.setLength(100);
		firstNameItem.setRequired(true);
		
		TextItem lastNameItem = new TextItem("lastName", "Last Name");
		lastNameItem.setLength(100);
		lastNameItem.setRequired(true);
		
		TextItem genderItem = new TextItem("gender", "Gender");
		genderItem.setLength(20);
		genderItem.setRequired(true);
		
		TextItem emailItem = new TextItem("email", "Email");
		emailItem.setRequired(true);
		RegExpValidator emailValidator = new RegExpValidator();
		emailValidator.setErrorMessage("Invalid email address");
		emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");
		emailItem.setValidators(emailValidator);
		
		cvUploadLinkItem = new LinkItem("uploadCV");
		cvUploadLinkItem.setTitle("Upload CV");
		cvUploadLinkItem.setLinkTitle("Click here");
		
		basicInformationForm.setFields(firstNameItem, lastNameItem, genderItem, emailItem, cvUploadLinkItem);
		
		VLayout body = new VLayout();
		body.setStyleName("wizard-Body");
		body.addMember(basicInformationForm);
		
		return body;
	}

	@Override
	public Widget asWidget() {
		return container;
	}

	@Override
	public void setData(StudentUserDto studentUserDto) {
		basicInformationForm.setValue("firstName", studentUserDto.getFirstName());
		basicInformationForm.setValue("lastName", studentUserDto.getLastName());
		basicInformationForm.setValue("gender", studentUserDto.getGender());
		basicInformationForm.setValue("email", studentUserDto.getEmail());
	}

	@Override
	public HandlerRegistration addUpdateButtonClickHandler(
			ClickHandler handler) {
		return updateButton.addClickHandler(handler);
	}

	@Override
	public boolean validateBasicInformationForm() {
		return basicInformationForm.validate();
	}

	@Override
	public StudentUserDto getBasicFormValueDto() {
		StudentUserDto studentDto = new StudentUserDto();
		studentDto.setEmail(basicInformationForm.getValue("email").toString());
		studentDto.setFirstName(basicInformationForm.getValue("firstName").toString());
		studentDto.setLastName(basicInformationForm.getValue("lastName").toString());
		studentDto.setGender(basicInformationForm.getValue("gender").toString());
		return studentDto;
	}

	@Override
	public HandlerRegistration addUploadCVLinkItemClickHandler(
			com.smartgwt.client.widgets.form.fields.events.ClickHandler handler) {
		return cvUploadLinkItem.addClickHandler(handler);
	}
}
