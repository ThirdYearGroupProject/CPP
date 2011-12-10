package ic.doc.cpp.client.login;


import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
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
	private TextItem userNameField2;
	private PasswordItem passwordField2;
	private Button regButton;
	private TextItem emailField;
	private TextItem FirstNameField;
	private TextItem LastNameField;
	private SelectItem gender;
	private TextItem Company;
	private TabSet regTabSet;

	@Inject
	public SignInPageView() {
		mainLayout = new VLayout();
		mainLayout.setHeight100();
		mainLayout.setWidth100();

		HLayout topPanel = new HLayout();
		topPanel.setBackgroundColor("#0B43C4");
		topPanel.setHeight("10%");

		Label CppHeader = new Label(
				"<span><font size='6' color='white'>Cooperative Partnership Programme</font></span>");
		CppHeader.setWidth("150%");

		DynamicForm loginForm = new DynamicForm();
		userNameField = new TextItem();
		userNameField.setTitle("<font size='3' color='white'>Username</font>");
		userNameField.setRequired(true);

		passwordField = new PasswordItem();
		passwordField.setTitle("<font size='3' color='white'>Password</font>");
		passwordField.setRequired(true);

		userNameField.setValue("DuZhouzhou");
		passwordField.setValue("N0More$ecret");
		
		userType = new SelectItem();
		userType.setTitle("<font size='3' color='white'>User Type</font>");
		userType.setValueMap("admin", "student", "company");
		userType.setDefaultValue("student");

		signInButton = new Button("LogIn");

		loginForm.setWidth100();
		loginForm.setFields(new FormItem[] { userNameField, passwordField,
				userType });
		loginForm.setTitleOrientation(TitleOrientation.TOP);
		loginForm.setColWidths(new Object[] { "30%", "30%", "30%" });
		loginForm.setNumCols(3);

		topPanel.addMember(CppHeader);
		topPanel.addMember(loginForm);
		topPanel.addMember(signInButton);

		HLayout southPanel = new HLayout();
		southPanel.setBackgroundColor("grey");
		HTMLPane CPPdescription = new HTMLPane();
		VLayout regPanel = new VLayout();

		CPPdescription.setPadding(50);
		String cpp = "<img src='http://www.q2.ltd.uk/files/Section%20Images/recruit_si.gif' alt='Recruit Logo' width='150' height='150' />"
				+ "<p>Our Corporate Partnership Programme is designed to promote the relationship "
				+ "between the Department of Computing and organisations who wish to recruit our students "
				+ "whilst investing in academic sponsorship. Students register their CVs in a database. Corporate "
				+ "Partners access the database to recruit students for graduate positions, summer internships "
				+ "and industrial placements. Corporate Partners also receive a package of other benefits aimed "
				+ "at raising their profile amongst our students. Companies who are interested in applying for "
				+ "membership should email cpp@doc.ic.ac.uk, or post us a completed application form.</p>"
				+ "<p>We have launched an Applications of Computing in Industry lecture series. This timetabled "
				+ "series of talks is given by our Corporate Partners and is designed to inform our students about"
				+ " how Computer Science is applied to meet business challenges.</p>";

		CPPdescription.setContents("<font size='5' color='white'>" + cpp
				+ "</font>");

		Label regWelcome = new Label(
				"<font size='4' color='black'>Welcome to register</font>");
		DynamicForm studentRegForm = new DynamicForm();

		userNameField2 = new TextItem();
		userNameField2.setTitle("<font size='2' color='black'>Username</font>");
		userNameField2.setRequired(true);

		passwordField2 = new PasswordItem();
		passwordField2.setTitle("<font size='2' color='black'>Password</font>");
		passwordField2.setRequired(true);

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
		gender.setTitle("<font size='2' color='black'>gender</font>");
		gender.setValueMap("Male", "Female");
		gender.setDefaultValue("Male");
	
		studentRegForm.setFields(new FormItem[] { userNameField2,
				passwordField2,LastNameField,FirstNameField,emailField,gender});

		DynamicForm companyRegForm = new DynamicForm();

		Company = new TextItem();
		Company.setTitle("<font size='2' color='black'>Company Name</font>");
		Company.setRequired(true);
		
		companyRegForm.setFields(new FormItem[] { userNameField2,
				passwordField2,LastNameField,FirstNameField,emailField,gender,Company });

		regTabSet = new TabSet();
		regTabSet.setTabBarPosition(Side.TOP);
		regTabSet.setWidth("50%");

		Tab tTabStudent = new Tab();
		tTabStudent.setPane(studentRegForm);
		tTabStudent.setTitle("Student");

		Tab tTabCompany = new Tab();
		tTabCompany.setTitle("Company");
		tTabCompany.setPane(companyRegForm);

		regTabSet.addTab(tTabStudent);
		regTabSet.addTab(tTabCompany);
		
		regButton = new Button("Registrate");
		
		regPanel.addMember(regWelcome);
		regPanel.addMember(regTabSet);
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
		return userNameField2.getValueAsString();
	}
	
	@Override
	public String getRegPassword() {
		return passwordField2.getValueAsString();
	}

	@Override
	public String getRegLastName() {
		return LastNameField.getValueAsString();
	}

	@Override
	public String getRegFirstName() {
		return FirstNameField.getValueAsString();
	}

	@Override
	public String getRegEmail() {
		return emailField.getValueAsString();
	}

	@Override
	public String getRegGender() {
		return gender.getValueAsString();
	}

	@Override
	public String getRegCompany() {
		return Company.getValueAsString();
	}




}
