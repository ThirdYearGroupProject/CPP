package ic.doc.cpp.client.student.core.profile;

import java.util.List;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import ic.doc.cpp.client.place.NameTokens;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import ic.doc.cpp.client.LoggedInGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.events.ShowContextMenuEvent;
import com.smartgwt.client.widgets.events.ShowContextMenuHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tile.TileRecord;

import ic.doc.cpp.client.student.core.profile.StudentProfilePresenter;
import ic.doc.cpp.shared.action.student.RemoveStudentInterestedCompany;
import ic.doc.cpp.shared.action.student.RemoveStudentInterestedCompanyResult;
import ic.doc.cpp.shared.action.student.RetrieveStudentInterestedCompanies;
import ic.doc.cpp.shared.action.student.RetrieveStudentInterestedCompaniesResult;
import ic.doc.cpp.shared.dto.CompanyDto;

public class StudentInterestedCompanyPresenter
		extends
		Presenter<StudentInterestedCompanyPresenter.MyView, StudentInterestedCompanyPresenter.MyProxy> {

	private final DispatchAsync dispatcher;
	
	public interface MyView extends View {
		void setData(List<CompanyDto> companys);
		HandlerRegistration addRemoveMenuItemClickHandler(ClickHandler handler);
		TileRecord getSelectedRecord();
		HandlerRegistration addShowContextMenuHandler(ShowContextMenuHandler showContextMenuHandler);
		Menu getInterestedCompanyListMenu();
		HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler);
		Canvas getDetailViewer();
		void setDeatilViewerData(Record record);
		HandlerRegistration addWinModalCloseClickHandler(
				CloseClickHandler closeClickHandler);
		void hideWinModal();
		void showWinModal();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.studentprofileinterestedcompany)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<StudentInterestedCompanyPresenter> {
	}

	@Inject
	public StudentInterestedCompanyPresenter(final EventBus eventBus,
			final MyView view, final MyProxy proxy,
			final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				StudentProfilePresenter.TYPE_SetStudentProfileContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		registerHandler(getView().addWinModalCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				getView().hideWinModal();
			}
		}));
		
		registerHandler(getView().addDoubleClickHandler(new DoubleClickHandler() {
			
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				Record record = getView().getSelectedRecord();
				if (record != null) {
					getView().setDeatilViewerData(record);
					getView().showWinModal();
				}
			}
		}));
		
		registerHandler(getView().addShowContextMenuHandler(new ShowContextMenuHandler() {
			
			@Override
			public void onShowContextMenu(ShowContextMenuEvent event) {
				getView().getInterestedCompanyListMenu().showContextMenu();
				event.cancel();
			}
		}));
		
		registerHandler(getView().addRemoveMenuItemClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(MenuItemClickEvent event) {
				final Record record = getView().getSelectedRecord();
				if (record != null) {
					Long companyId = record.getAttributeAsLong("companyId");
					dispatcher.execute(new RemoveStudentInterestedCompany(companyId), 
							new AsyncCallback<RemoveStudentInterestedCompanyResult>() {

						@Override
						public void onFailure(Throwable caught) {
							GWT.log("Fail on removeStudentInteresteCompany() RPC call - " + caught.getLocalizedMessage());
						}

						@Override
						public void onSuccess(
								RemoveStudentInterestedCompanyResult result) {
							SC.say("Remove Interested List", "Remove " + record.getAttribute("name") + " from list Successful!");
							onReset();
						}
					});
				}
			}
		}));
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		retrieveInterestedCompanyData();
	}
	
	private void retrieveInterestedCompanyData() {
		GWT.log("Retrieveing interesting company...");
		
		dispatcher.execute(new RetrieveStudentInterestedCompanies(), 
				new AsyncCallback<RetrieveStudentInterestedCompaniesResult>() {
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("fail on retrieveInterestedCompanyData()...");
			}

			@Override
			public void onSuccess(RetrieveStudentInterestedCompaniesResult result) {
				if (result != null) {
					getView().setData(result.getCompanyDto());
				}
			}
			
		});
	}
}
