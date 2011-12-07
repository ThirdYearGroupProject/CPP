package ic.doc.cpp.client.student.core.profile;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.validator.LengthRangeValidator;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.layout.VLayout;

public class StudentProfileSecurityView extends ViewImpl implements
		StudentProfileSecurityPresenter.MyView {

	private final VLayout container;
	private IButton updateButton;
	private DynamicForm passwordSettingForm;
	
	@Inject
	public StudentProfileSecurityView() {
		container = new VLayout();
		VLayout header = StudentProfileView.initHeader("Security Settings", "Change your security settings here.");
		header.setStyleName("wizard-Header");
		
		VLayout southLayout = new VLayout();
		
		VLayout body = initPasswordSettingTabBody();
		
		updateButton = new IButton("Update");
		VLayout footer = StudentProfileView.initFooter(updateButton);
		
		southLayout.addMember(body);
		southLayout.addMember(footer);

		container.addMember(header);
		container.addMember(southLayout);
	}
	
	public VLayout initPasswordSettingTabBody() {
		VLayout body = new VLayout();
		
		passwordSettingForm = new DynamicForm();
		
		PasswordItem originalPassword = new PasswordItem("originalPassword", "Original Password");
		originalPassword.setRequired(true);
		originalPassword.setLength(64);
		LengthRangeValidator lengthValidator = new LengthRangeValidator();
		lengthValidator.setMin(8);
		originalPassword.setValidators(lengthValidator);
		
		PasswordItem newPasswordItem = new PasswordItem("newPassword", "New Password");
		newPasswordItem.setRequired(true);
		newPasswordItem.setLength(64);
		
		PasswordItem newPasswordItem2 = new PasswordItem("newPassword2", "New Password Again");
		newPasswordItem2.setTitle("Password Again");
		newPasswordItem2.setRequired(true);
		newPasswordItem2.setLength(64);
		MatchesFieldValidator matchesValidator = new MatchesFieldValidator();
		matchesValidator.setOtherField("newPassword");
		matchesValidator.setErrorMessage("Passwords do not match");
		newPasswordItem2.setValidators(matchesValidator);
		passwordSettingForm.setFields(originalPassword, newPasswordItem, newPasswordItem2);
		
		body.setStyleName("wizard-Body");
		body.addMember(passwordSettingForm);
		
		return body;
	}

	@Override
	public Widget asWidget() {
		return container;
	}

	@Override
	public HandlerRegistration addUpdateButtonClickHandler(
			ClickHandler handler) {
		return updateButton.addClickHandler(handler);
	}

	@Override
	public boolean validatePasswordSettingForm() {
		return passwordSettingForm.validate();
	}

	@Override
	public String getNewPassword() {
		return passwordSettingForm.getValueAsString("newPassword");
	}

	@Override
	public String getOrinalPassword() {
		return passwordSettingForm.getValueAsString("originalPassword");
	}
}
