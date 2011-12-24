package ic.doc.cpp.client.admin;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class AdminView extends ViewImpl implements AdminPresenter.MyView {

	VLayout mainLayout;
	final ListGrid companyGrid;
	private TextItem nameField;
	private TextItem descriptionField;
	private TextItem websiteField;
	private TextItem categoryField;
	private TextItem Company;
	private Button submitReg;

	@Inject
	public AdminView() {

		mainLayout = new VLayout();
		mainLayout.setHeight100();
		mainLayout.setWidth100();

		HLayout topPanel = new HLayout();
		topPanel.setBackgroundColor("#3B5998");
		topPanel.setHeight("10%");

		Label CppHeader = new Label(
				"<span><font size='6' color='white'>Cooperative Partnership Programme</font></span>");
		CppHeader.setWidth("150%");

		topPanel.addMember(CppHeader);

		HLayout southPanel = new HLayout();
		southPanel.setBackgroundColor("grey");
		
		VLayout viewCompany = new VLayout();
		VLayout regCompany = new VLayout();
		HTMLPane description = new HTMLPane();
		description.setPadding(50);
		String cpp = "<p>You can view all company here and select a company to generate invitation code</p>"
				+ "<p>Or create a new company in the right, company user will need invitation code to register</p>";

		description.setContents("<font size='5' color='black'>" + cpp
				+ "</font>");

		companyGrid = new ListGrid();
		ListGridField NameField = new ListGridField("name", "name");
		ListGridField LogoField = new ListGridField("logo", "logo");
		ListGridField WebsiteField = new ListGridField("website", "website");

		companyGrid.setFields(LogoField, NameField, WebsiteField);

		// initial sort on name, A to Z
		companyGrid.setSortField(2);
		companyGrid.setSortDirection(SortDirection.ASCENDING);
		
		viewCompany.addMember(description);
		viewCompany.addMember(companyGrid);
		
		TabSet regTabSet = new TabSet();
		regTabSet.setTabBarPosition(Side.TOP);
		
		Tab tViewAllCompany = new Tab();
		tViewAllCompany.setPane(viewCompany);
		tViewAllCompany.setTitle("view company");
		
		nameField = new TextItem();
		nameField.setTitle("<font size='2' color='black'>Company Name</font>");
		nameField.setRequired(true);
		
		descriptionField = new TextItem();
		descriptionField.setTitle("<font size='2' color='black'>description</font>");
		descriptionField.setRequired(true);
		
		websiteField = new TextItem();
		websiteField.setTitle("<font size='2' color='black'>website</font>");
		websiteField.setRequired(true);
		
		categoryField = new TextItem();
		categoryField.setTitle("<font size='2' color='black'>category</font>");
		categoryField.setRequired(true);
		
		Company = new TextItem();
		Company.setTitle("<font size='2' color='black'>Company Name</font>");
		Company.setRequired(true);
		
		DynamicForm companyRegForm = new DynamicForm();
		companyRegForm.setFields(new FormItem[] { nameField,
				categoryField,websiteField,descriptionField,Company });
		
		submitReg = new Button();
		submitReg.setTitle("Submit");
		submitReg.setStyleName("submitButton");
		
		regCompany.addMember(companyRegForm);
		regCompany.addMember(submitReg);
		
		Tab tRegCompany = new Tab();
		tRegCompany.setTitle("register company");
		tRegCompany.setPane(regCompany);
		
		regTabSet.addTab(tRegCompany);
		regTabSet.addTab(tViewAllCompany);
		
		southPanel.addMember(regTabSet);
		
		mainLayout.addMember(topPanel);
		mainLayout.addMember(southPanel);
		mainLayout.draw();
	}

	@Override
	public Widget asWidget() {
		return mainLayout;
	}

	@Override
	public void fillCompanyGrid(ListGridRecord[] records) {
		companyGrid.setData(records);
	}

	@Override
	public void addbSubmitHandler(
			com.smartgwt.client.widgets.events.ClickHandler handle) {
		submitReg.addClickHandler(handle);
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWebsite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
