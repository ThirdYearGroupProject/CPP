package ic.doc.cpp.student.client.core.profile;

import java.util.List;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DrawEvent;
import com.smartgwt.client.widgets.events.DrawHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;

public class InterestedAreaWidgetView extends ViewImpl implements
		InterestedAreaWidgetPresenter.MyView {
	
	private final VLayout container;
	private final TreeGrid categoryTreeGrid;
	private final IButton updateButton;
	
	@Inject
	public InterestedAreaWidgetView() {
		container = new VLayout();
		
		categoryTreeGrid = new TreeGrid();

		categoryTreeGrid.setWidth100();
	 	categoryTreeGrid.setHeight100();
        categoryTreeGrid.setNodeIcon("icons/16/dashboards.png");
        categoryTreeGrid.setFolderIcon("icons/16/dashboards.png");
        categoryTreeGrid.setShowOpenIcons(false);
        categoryTreeGrid.setShowDropIcons(false);
        categoryTreeGrid.setClosedIconSuffix("");
        categoryTreeGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        categoryTreeGrid.setShowSelectedStyle(false);
        categoryTreeGrid.setShowPartialSelection(true);
        categoryTreeGrid.setCascadeSelection(true);

        categoryTreeGrid.addDrawHandler(new DrawHandler() {
            public void onDraw(DrawEvent event) {
                categoryTreeGrid.getTree().openAll();
            }
        });
        
        VLayout header = StudentUserProfileView.initHeader("Interested Areas", "View/Change your interested areas");
	    header.setStyleName("wizard-Header");
	    
	    VLayout body = new VLayout();
	    body.setStyleName("wizard-Body");
	    body.setMembers(categoryTreeGrid);
	    
	    updateButton = new IButton();
	    updateButton.setTitle("Update");
	    VLayout footer = StudentUserProfileView.initFooter(updateButton);
	    
		container.setMembers(header, body, footer);
	}

	@Override
	public Widget asWidget() {
		return container;
	}
	
	@Override
	public void setData(Tree tree) {
		categoryTreeGrid.setData(tree);
	}

	@Override
	public void setSelectedRecord(List<Integer> selectedRecordIndex) {
		for (Integer i : selectedRecordIndex)
			categoryTreeGrid.selectRecord((int)i);
	}
	
	@Override
	public ListGridRecord[] getSelectedRecord() {
		return categoryTreeGrid.getSelectedRecords(true);
	}
	
	@Override
	public HandlerRegistration addUpdateButtonClickHandler(ClickHandler handler) {
		return updateButton.addClickHandler(handler);
	}
}
