package ic.doc.cpp.student.client.core.eventdata;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import ic.doc.cpp.student.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

import ic.doc.cpp.student.client.core.EventCategoryTreeGrid;
import ic.doc.cpp.student.client.core.StudentPagePresenter;

public class EventDataPresenter extends
		Presenter<EventDataPresenter.MyView, EventDataPresenter.MyProxy> {

	public interface MyView extends View {

		SectionStack getDataview();

		ListGrid getEventList();

		EventDetailViewer getEventDetailViewer();

		EventSearchForm getSearchForm();

		EventCategoryTreeGrid getCategoryTree();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.eventdata)
	public interface MyProxy extends ProxyPlace<EventDataPresenter> {
	}

	@Inject
	public EventDataPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				StudentPagePresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		registerHandler(getView().getCategoryTree().addNodeClickHandler(new NodeClickHandler() {  
            public void onNodeClick(NodeClickEvent event) {
            	CheckboxItem findInCategory = getView().getSearchForm().getFindInCategory();
            	findInCategory.setValue(true);
                findItems();
            }  
        }));
		
		registerHandler(getView().getSearchForm().addFindListener(new ClickHandler() {
      		public void onClick(ClickEvent event) {  
      			findItems();  
      		}  
      	}));
		
		registerHandler(getView().getEventList().addRecordClickHandler(new RecordClickHandler() {

			public void onRecordClick(RecordClickEvent event) {
				getView().getEventDetailViewer().updateDetails();
			}
      		
      	}));
	}
	
	public void findItems() {
        Criteria findValues = null;  
        String categoryName = "";
        String useCategoryTreeValue = getView().getSearchForm().getValueAsString("findInCategory");
        ListGridRecord selectedCategory = getView().getCategoryTree().getSelectedRecord();

        if (useCategoryTreeValue.equals("true") && selectedCategory != null) {
        	categoryName = selectedCategory.getAttribute("categoryName");  
            findValues = getView().getSearchForm().getValuesAsCriteria();
            findValues.addCriteria("category", categoryName);  
        } else if (!useCategoryTreeValue.equals("true") && selectedCategory != null) {
        	findValues = getView().getSearchForm().getValuesAsCriteria();  
            findValues.addCriteria("category", categoryName); 
        } else {
        	SC.say("Please select a category to use category!");
        }
        
        if (findValues != null)
        	getView().getEventList().filterData(findValues);
        
    	getView().getEventDetailViewer().clearDetails();
    }  
}