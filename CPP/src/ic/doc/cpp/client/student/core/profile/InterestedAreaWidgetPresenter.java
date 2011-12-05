package ic.doc.cpp.client.student.core.profile;

import ic.doc.cpp.client.student.core.CompanyCategoryTreeNode;
import ic.doc.cpp.shared.action.student.RetrieveCompanyCategory;
import ic.doc.cpp.shared.action.student.RetrieveCompanyCategoryResult;
import ic.doc.cpp.shared.action.student.RetrieveStudentInterestedAreas;
import ic.doc.cpp.shared.action.student.RetrieveStudentInterestedAreasResult;
import ic.doc.cpp.shared.action.student.UpdateStudentInterestedAreas;
import ic.doc.cpp.shared.action.student.UpdateStudentInterestedAreasResult;
import ic.doc.cpp.shared.dto.CompanyCategoryDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;

public class InterestedAreaWidgetPresenter extends
		PresenterWidget<InterestedAreaWidgetPresenter.MyView> {

	private final DispatchAsync dispatcher;
	
	public interface MyView extends View {

		void setData(Tree tree);

		void setSelectedRecord(List<Integer> selectedRecordIndex);

		HandlerRegistration addUpdateButtonClickHandler(ClickHandler handler);

		ListGridRecord[] getSelectedRecord();
	}

	@Inject
	public InterestedAreaWidgetPresenter(final EventBus eventBus, final MyView view,
			final DispatchAsync dispatcher) {
		super(eventBus, view);
		this.dispatcher = dispatcher;
	}

	@Override
	protected void onBind() {
		super.onBind();
		registerHandler(getView().addUpdateButtonClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ListGridRecord[] selectedRecords = getView().getSelectedRecord();
				List<Long> categoryId = new ArrayList<Long>();
				for (ListGridRecord r : selectedRecords) {
					Long id = r.getAttributeAsLong("categoryId");
					if (!id.equals(0L)) {
						categoryId.add(id);
					}
				}
				
				dispatcher.execute(new UpdateStudentInterestedAreas(categoryId),
						new AsyncCallback<UpdateStudentInterestedAreasResult>() {

							@Override
							public void onFailure(Throwable caught) {
								GWT.log("Fail on UpdateStudentInterestedAreas: " + caught.getLocalizedMessage());
							}

							@Override
							public void onSuccess(
									UpdateStudentInterestedAreasResult result) {
								SC.say("Successfully update interested areas");
							}
				});
			}
		}));
	}

	@Override
	protected void onReset() {
		super.onReset();
		retrieveCategoryData();
	}

	private void retrieveCategoryData() {
		dispatcher.execute(new RetrieveCompanyCategory(), 
				new AsyncCallback<RetrieveCompanyCategoryResult>() {

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("Fail on retrieveCompanyCategoryData() - " + caught.getLocalizedMessage());
					}

					@Override
					public void onSuccess(RetrieveCompanyCategoryResult result) {
						Tree tree = constructCategoryTree(result.getCompanyCategoryDtos());
						getView().setData(tree);
						SetSelectedRecord(result.getCompanyCategoryDtos());
					}
			
		});
	}
	
	protected void SetSelectedRecord(
			final List<CompanyCategoryDto> companyCategoryDtos) {
		dispatcher.execute(new RetrieveStudentInterestedAreas(), 
				new AsyncCallback<RetrieveStudentInterestedAreasResult>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Fail on retrieveStudentInterestedArea() - " + caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(RetrieveStudentInterestedAreasResult result) {
				Map<Long, CompanyCategoryDto> map = new HashMap<Long, CompanyCategoryDto>();
				List<CompanyCategoryDto> interestedAreas = result.getInterestedAreas();
				
				for (CompanyCategoryDto area : interestedAreas) {
					map.put(area.getCategoryId(), area);
				}
				
				List<Integer> selectedRecordIndex = new ArrayList<Integer>();
				for (CompanyCategoryDto category : companyCategoryDtos) {
					if (map.get(category.getCategoryId()) != null) {
						selectedRecordIndex.add(safeLongToInt(category.getCategoryId()));
					}
				}
				
				getView().setSelectedRecord(selectedRecordIndex);
			}
		} );
	}
	
	public static int safeLongToInt(long l) {
	    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException
	            (l + " cannot be cast to int without changing its value.");
	    }
	    return (int) l;
	}
	
	protected Tree constructCategoryTree(
			List<CompanyCategoryDto> companyCategoryDtos) {
		Tree tree = new Tree();
	    tree.setModelType(TreeModelType.PARENT);
	    tree.setNameProperty("categoryName");
	    tree.setIdField("categoryId");
	    tree.setParentIdField("parentId");
	    tree.setOpenProperty("isOpen");
	    
	    TreeNode root = new CompanyCategoryTreeNode(0L, "All", "All", null, true);
	    
	    TreeNode[] nodes = new TreeNode[companyCategoryDtos.size() + 1];
	    nodes[companyCategoryDtos.size()] = root;
	    
		for (int i = 0; i < companyCategoryDtos.size(); i++) {
			CompanyCategoryDto companyCategoryDto = companyCategoryDtos.get(i);
			String name = companyCategoryDto.getCategoryName();
			String display = name.substring(name.lastIndexOf("/") + 1);
			nodes[i] = new CompanyCategoryTreeNode(companyCategoryDto.getCategoryId(),
					display, name, companyCategoryDto.getParentId(), true);
		}

		tree.setData(nodes);
		return tree;
	}
	
}
