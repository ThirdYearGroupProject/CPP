package ic.doc.cpp.client.login;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
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
	private TextItem userNameField2;
	private PasswordItem passwordField2;
	private SelectItem userType2;
	private ButtonItem regButton;

	@Inject
	public SignInPageView() {
		mainLayout = new VLayout();
		mainLayout.setHeight100();
		mainLayout.setWidth100();

		HLayout topPanel = new HLayout();
		topPanel.setBackgroundColor("#0B43C4");
		topPanel.setHeight("10%");

		Label CppHeader = new Label(
				"<font size='6' color='white'>Cooperative Partnership Programme</font>");
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

		signInButton = new Button("Log In");

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

		userType2 = new SelectItem();
		userType2.setTitle("<font size='2' color='black'>UserType</font>");
		userType2.setValueMap("Admin", "Student", "Company");

		regButton = new ButtonItem("Registrate");
		studentRegForm.setFields(new FormItem[] { userNameField2,
				passwordField2, userType2, regButton });

		DynamicForm companyRegForm = new DynamicForm();

		userNameField2 = new TextItem();
		userNameField2.setTitle("<font size='2' color='black'>Username</font>");
		userNameField2.setRequired(true);

		passwordField2 = new PasswordItem();
		passwordField2.setTitle("<font size='2' color='black'>Password</font>");
		passwordField2.setRequired(true);

		userType2 = new SelectItem();
		userType2.setTitle("<font size='2' color='black'>UserType</font>");
		userType2.setValueMap("Admin", "Student", "Company");

		regButton = new ButtonItem("Registrate");
		companyRegForm.setFields(new FormItem[] { userNameField2,
				passwordField2, userType2, regButton });

		TabSet regTabSet = new TabSet();
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
		
		regPanel.addMember(regWelcome);
		regPanel.addMember(regTabSet);
		// regPanel.addMember(regButton);

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
	public HandlerRegistration addSignInButtonClickHandler(
			ClickHandler clickHandler) {
		return signInButton.addClickHandler(clickHandler);
	}

	@Override
	public String getUserType() {
		return userType.getValueAsString();
	}

}
