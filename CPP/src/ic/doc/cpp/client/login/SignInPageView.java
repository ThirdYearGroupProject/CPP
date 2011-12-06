package ic.doc.cpp.client.login;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class SignInPageView extends ViewImpl implements
		SignInPagePresenter.MyView {

	VLayout mainLayout;
	private TextItem userNameField;
	private PasswordItem passwordField;
	private SelectItem userType;
	private IButton signInButton;
	private TextItem userNameField2;
	private PasswordItem passwordField2;
	private SelectItem userType2;
	private IButton regButton;

	 @Inject
	 public SignInPageView() {
			mainLayout = new VLayout();
			mainLayout.setHeight100();
			mainLayout.setWidth100();
			
			HLayout topPanel = new HLayout();
			topPanel.setBackgroundColor("#0B43C4");
			topPanel.setHeight("10%");
			
			Label CppHeader = new Label("<font size='6' color='white'>Cooperative Partnership Programme</font>");
			CppHeader.setWidth("150%");

			DynamicForm loginForm = new DynamicForm();
	        userNameField = new TextItem();  
	        userNameField.setTitle("<font size='3' color='white'>Username</font>");  
	        userNameField.setRequired(true);  
	  
	        passwordField = new PasswordItem();  
	        passwordField.setTitle("<font size='3' color='white'>Password</font>");  
	        passwordField.setRequired(true);  
	        
	        userType = new SelectItem();
	        userType.setTitle("<font size='3' color='white'>User Type</font>");
	        userType.setValueMap("Admin","Student","Company");
	        
	        signInButton = new IButton("Log In");
	        
			loginForm.setWidth100();
	        loginForm.setFields(new FormItem[]{userNameField,passwordField,userType} );
	        loginForm.setTitleOrientation(TitleOrientation.TOP);
	        loginForm.setColWidths(new Object[]{"30%","30%","30%"});
	        loginForm.setNumCols(3);
	        
			topPanel.addMember(CppHeader);
	        topPanel.addMember(loginForm);
	        topPanel.addMember(signInButton);
			
	        HLayout southPanel = new HLayout();
	        HTMLPane CPPdescription = new HTMLPane();
	        VLayout regPanel = new VLayout();
	        
			Label regWelcome = new Label("<font size='4' color='black'>Welcome to register</font>");
			DynamicForm regForm = new DynamicForm();
			
	        userNameField2 = new TextItem();  
	        userNameField2.setTitle("<font size='2' color='black'>Username</font>");  
	        userNameField2.setRequired(true);  
	  
	        passwordField2 = new PasswordItem();  
	        passwordField2.setTitle("<font size='2' color='black'>Password</font>");  
	        passwordField2.setRequired(true);  
	        
	        userType2 = new SelectItem();
	        userType2.setTitle("<font size='2' color='black'>UserType</font>");
	        userType2.setValueMap("Admin","Student","Company");
			
	        regButton = new IButton("Registrate");
	        regForm.setFields(new FormItem[]{userNameField2,passwordField2,userType2} );
			
	        regPanel.addMember(regWelcome);
	        regPanel.addMember(regForm);
	        regPanel.addMember(regButton);
	        
			southPanel.addMember(CPPdescription);
			southPanel.addMember(regPanel);
			
			mainLayout.addMember(topPanel);
			mainLayout.addMember(southPanel);
			mainLayout.draw();
	 }

	 @Override
	 public Widget asWidget() {
		 return mainLayout;
	 }

	 @Override
	 public String getUserName() {
		 return userNameField.getValueAsString();
	 }
  
	 @Override
	 public String getPassword() {
		 return passwordField.getValueAsString();
	 }  

	 
	 @Override
	 public String getCompanyUserName() {
		 return getUserName();
	 }
	 @Override
	 public String getCompanyUserPassword() {
		 return getPassword();
	 }

	@Override
	 public void resetAndFocus() {
	 }
	
	@Override
	 public void resetAndFocusCompanyUser() {
		
	 }


	@Override
	public HandlerRegistration addSignInButtonClickHandler(
			ClickHandler clickHandler) {
		return signInButton.addClickHandler(clickHandler);
	}
	
	@Override
	public HandlerRegistration addCompanyUserSignInButtonClickHandler(
			ClickHandler clickHandler) {
		return companyUserSignInButton.addClickHandler(clickHandler);
	}

	@Override
	public String getUserName(String type) {
		if (type.equals("student"))
			return getUserName();
		else if (type.equals("company"))
			return getCompanyUserName();
		return null;
	}

	@Override
	public String getPassword(String type) {
		if (type.equals("student"))
			return getPassword();
		else if (type.equals("company"))
			return getCompanyUserPassword();
		return null;
	}
	 
}
