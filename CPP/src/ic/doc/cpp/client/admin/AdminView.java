package ic.doc.cpp.client.admin;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.DrawEvent;
import com.smartgwt.client.widgets.events.DrawHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class AdminView extends ViewImpl implements AdminPresenter.MyView {

	VLayout mainLayout;
	final ListGrid companyGrid;
	private TextItem nameField;
	private TextItem descriptionField;
	private TextItem websiteField;
	private TextItem categoryField;

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
		
		
		DynamicForm companyRegForm = new DynamicForm();
		companyRegForm.setFields(new FormItem[] { nameField,
				categoryField,websiteField,descriptionField });
		
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
		// TODO: company category better to be a pop-up frame
		//regTabSet.addTab(initialiseCategoryTab());
		
		southPanel.addMember(regTabSet);
		
		mainLayout.addMember(topPanel);
		mainLayout.addMember(southPanel);
		mainLayout.draw();
	}

	private Tab initialiseCategoryTab() {
		Tab tRegCategory = new Tab();
		tRegCategory.setTitle("Company Category");
	
		
        TreeGrid treeGrid = new TreeGrid();  
        treeGrid.setWidth(300);  
        treeGrid.setHeight(400);  
  
        TreeGridField field = new TreeGridField("Name", "Tree from local data");  
        field.setCanSort(false);  
  
        treeGrid.setFields(field);  
  
        final Tree tree = new Tree();  
        tree.setModelType(TreeModelType.PARENT);  
        tree.setNameProperty("Name");  
        tree.setIdField("EmployeeId");  
        tree.setParentIdField("ReportsTo");  
        tree.setShowRoot(true);  
        
        //CategoryTreeNode root = new EmployeeTreeNode("4", "1", "Charles Madigen");  

        //tree.setData(new TreeNode[]{root, node2, node3, node4, node5});  
  
        treeGrid.addDrawHandler(new DrawHandler() {  
            public void onDraw(DrawEvent event) {  
                tree.openAll();  
            }  
        });  
        
        treeGrid.setData(tree);  
        
        treeGrid.draw();
		return tRegCategory;  
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
	public HandlerRegistration addbSubmitHandler(
			com.smartgwt.client.widgets.events.ClickHandler handle) {
		return submitReg.addClickHandler(handle);
		
	}

	@Override
	public String getName() {
		return nameField.getValueAsString();
	}

	@Override
	public String getWebsite() {
		return websiteField.getValueAsString();
	}

	@Override
	public String getDescription() {
		return  descriptionField.getValueAsString();
	}

	@Override
	public String getCategory() {
		return categoryField.getValueAsString();
	}

	@Override
	public HandlerRegistration addGridListHandler(CellClickHandler handle) {
		return companyGrid.addCellClickHandler(handle);
	}
	
}
