package ic.doc.cpp.client.login;


import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class SignInPageView extends ViewImpl implements
		SignInPagePresenter.MyView {

	VLayout mainLayout;
	private TextItem userNameField;
	private PasswordItem passwordField;
	private SelectItem userType;
	private Button signInButton;
	
	//---------field below are for registration------
	private TextItem regUserName;
	private PasswordItem regPassword;
	private Button regButton;
	private TextItem emailField;
	private TextItem FirstNameField;
	private TextItem LastNameField;
	private SelectItem gender;
	private TabSet regTabSet;

	//---------field below are for company registration------
	private TextItem regUserName2;
	private PasswordItem regPassword2;
	private TextItem emailField2;
	private TextItem FirstNameField2;
	private TextItem LastNameField2;
	private SelectItem gender2;
	private TextItem Company;
	
	
	@Inject
	public SignInPageView() {
		mainLayout = new VLayout();
		mainLayout.setHeight100();
		mainLayout.setWidth100();

		HLayout topPanel = new HLayout();
		topPanel.setBackgroundColor("#3B5998");
		topPanel.setHeight("10%");

		Label CppHeader = new Label(
				"Cooperative Partnership Programme");
		CppHeader.setWidth("50%");
		CppHeader.setStyleName("CppHeader");

		DynamicForm loginForm = new DynamicForm();
		userNameField = new TextItem();
		userNameField.setTitle("<font FACE='arial' size='3' color='white'>Username</font>");
		userNameField.setRequired(true);

		passwordField = new PasswordItem();
		passwordField.setTitle("<font FACE='arial' size='3' color='white'>Password</font>");
		passwordField.setRequired(true);
		
		userNameField.setValue("DuZhouzhou");
		passwordField.setValue("N0More$ecret");
		
		userType = new SelectItem();
		userType.setTitle("<font FACE='arial' size='3' color='white'><b>User Type</b></font>");
		userType.setValueMap("admin", "student", "company");
		userType.setDefaultValue("student");
		
		signInButton = new Button("LogIn");
		signInButton.setStyleName("bSignIn");
		signInButton.setPosition(Positioning.RELATIVE);

		loginForm.setFields(new FormItem[] { userNameField, passwordField,
				userType });
		loginForm.setTitleOrientation(TitleOrientation.TOP);
		loginForm.setColWidths(new Object[] { "20%", "20%", "20%" });
		loginForm.setNumCols(3);
		loginForm.setStyleName("loginForm");
		
		topPanel.addMember(CppHeader);
		topPanel.addMember(loginForm);
		topPanel.addMember(signInButton);

		HLayout middlePanel = new HLayout();
		HTMLPane cppWelcomeLabel = new HTMLPane();
		VLayout regPanel = new VLayout();

		cppWelcomeLabel.setPadding(20);
		String cpp = "<img src='http://www.q2.ltd.uk/files/Section%20Images/recruit_si.gif' alt='Recruit Logo' width='150' height='150' />"
				+ "<p>Our Corporate Partnership Programme is designed to promote the relationship "
				+ "between the Department of Computing and organisations who wish to recruit our students "
				+ "whilst investing in academic sponsorship. Students register their CVs in a database. Corporate "
				+ "Partners access the database to recruit students for graduate positions, summer internships "
				+ "and industrial placements. Corporate Partners also receive a package of other benefits aimed "
				+ "at raising their profile amongst our students. Companies who are interested in applying for "
				+ "membership should email cpp@doc.ic.ac.uk, or post us a completed application form.</p>";

		cppWelcomeLabel.setContents("<font FACE='arial' size='4' color='#3B5998'>" + cpp
				+ "</font>");
		
		Label regWelcome = new Label(
				"<font FACE='arial' size='4' color='#3B5998' >Welcome to register</font>");
		regWelcome.setHeight(50);
	
		DynamicForm studentRegForm = new DynamicForm();

		regUserName = new TextItem();
		regUserName.setTitle("<font size='2' color='black'>Username</font>");
		regUserName.setRequired(true);

		regPassword = new PasswordItem();
		regPassword.setTitle("<font size='2' color='black'>Password</font>");
		regPassword.setRequired(true);

		FirstNameField = new TextItem();
		FirstNameField.setTitle("<font size='2' color='black'>First Name</font>");
		FirstNameField.setRequired(true);
		
		LastNameField = new TextItem();
		LastNameField.setTitle("<font size='2' color='black'>Last Name</font>");
		LastNameField.setRequired(true);
		
		emailField = new TextItem();
		emailField.setTitle("<font size='2' color='black'>Email</font>");
		emailField.setRequired(true);
		
		gender = new SelectItem();
		gender.setTitle("<font size='2' color='black'><b>gender</b></font>");
		gender.setValueMap("Male", "Female");
		gender.setDefaultValue("Male");
	
		studentRegForm.setFields(new FormItem[] { regUserName,
				regPassword,LastNameField,FirstNameField,emailField,gender});

		DynamicForm companyRegForm = new DynamicForm();
		
		regUserName2 = new TextItem();
		regUserName2.setTitle("<font size='2' color='black'>Username</font>");
		regUserName2.setRequired(true);

		regPassword2 = new PasswordItem();
		regPassword2.setTitle("<font size='2' color='black'>Password</font>");
		regPassword2.setRequired(true);

		FirstNameField2 = new TextItem();
		FirstNameField2.setTitle("<font size='2' color='black'>First Name</font>");
		FirstNameField2.setRequired(true);
		
		LastNameField2 = new TextItem();
		LastNameField2.setTitle("<font size='2' color='black'>Last Name</font>");
		LastNameField2.setRequired(true);
		
		emailField2 = new TextItem();
		emailField2.setTitle("<font size='2' color='black'>Email</font>");
		emailField2.setRequired(true);
		
		gender2 = new SelectItem();
		gender2.setTitle("<font size='2' color='black'><b>gender</b></font>");
		gender2.setValueMap("Male", "Female");
		gender2.setDefaultValue("Male");
		
		Company = new TextItem();
		Company.setTitle("<font size='2' color='black'>Company Name</font>");
		Company.setRequired(true);
		
		companyRegForm.setFields(new FormItem[] { regUserName2,
				regPassword2,LastNameField2,FirstNameField2,emailField2,gender2,Company });

		regTabSet = new TabSet();
		regTabSet.setTabBarPosition(Side.TOP);
		regTabSet.setWidth("50%");
		regTabSet.setStyleName("regTabSet");
		
		Tab tTabStudent = new Tab();
		tTabStudent.setPane(studentRegForm);
		tTabStudent.setTitle("student");

		Tab tTabCompany = new Tab();
		tTabCompany.setTitle("company");
		tTabCompany.setPane(companyRegForm);

		regTabSet.addTab(tTabStudent);
		regTabSet.addTab(tTabCompany);
		regTabSet.setHeight(240);
		
		regButton = new Button("Registrate");
		regButton.setPosition(Positioning.RELATIVE);
		regButton.setStyleName("bRegTabSet");
		
		regPanel.addMember(regWelcome);
		regPanel.addMember(regTabSet);
		regPanel.addMember(regButton);

		middlePanel.addMember(cppWelcomeLabel);
		middlePanel.addMember(regPanel);
		middlePanel.setStyleName("middlePanel");
		
		HLayout southPanel = new HLayout();
		Label copyRightLabel = new Label("This website and its content is copyright of Imperial College London - © Imperial College London 2011. All rights reserved.");
		copyRightLabel.setStyleName("copyRight");
		copyRightLabel.setWidth100();
		copyRightLabel.setPosition(Positioning.RELATIVE);

		southPanel.addMember(copyRightLabel);
		southPanel.setHeight("15%");
		
		mainLayout.addMember(topPanel);
		mainLayout.addMember(middlePanel);
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
	public void resetAndFocus() {
	}

	@Override
	public void resetAndFocusCompanyUser() {

	}

	@Override
	public String getUserType() {
		return userType.getValueAsString();
	}

	@Override
	public HandlerRegistration addSignInButtonClickHandler(
			ClickHandler clickHandler) {
		return signInButton.addClickHandler(clickHandler);
	}

	@Override
	public HandlerRegistration addRegButtonClickHandler(
			ClickHandler clickHandler) {
		return regButton.addClickHandler( clickHandler);
	}

	@Override
	public String getRegType() {
		return regTabSet.getSelectedTab().getTitle();
	}

	@Override
	public String getRegUserName() {
		if(getRegType().equals("company")){
			return regUserName2.getValueAsString();
		}else{
			return regUserName.getValueAsString();
		}

	}
	
	@Override
	public String getRegPassword() {
		if(getRegType().equals("company")){
			return regPassword2.getValueAsString();
		}else{
			return regPassword.getValueAsString();
		}

	}

	@Override
	public String getRegLastName() {
		if(getRegType().equals("company")){
			return LastNameField2.getValueAsString();
		}else{
			return LastNameField.getValueAsString();
		}

	}

	@Override
	public String getRegFirstName() {
		if(getRegType().equals("company")){
			return FirstNameField2.getValueAsString();
		}else{
			return FirstNameField.getValueAsString();
		}	

	}

	@Override
	public String getRegEmail() {
		if(getRegType().equals("company")){
			return emailField2.getValueAsString();
		}else{
			return emailField.getValueAsString();
		}

	}

	@Override
	public String getRegGender() {
		if(getRegType().equals("company")){
			return gender2.getValueAsString();
		}else{
			return gender.getValueAsString();
		}
		
	}

	@Override
	public String getRegCompany() {
		return Company.getValueAsString();
	}




}
