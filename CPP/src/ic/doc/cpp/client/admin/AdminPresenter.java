package ic.doc.cpp.client.admin;


import ic.doc.cpp.client.LoggedInGatekeeper;
import ic.doc.cpp.client.place.NameTokens;
import ic.doc.cpp.shared.action.AdminGetAllC;
import ic.doc.cpp.shared.action.AdminGetAllCResult;
import ic.doc.cpp.shared.action.RegCompany;
import ic.doc.cpp.shared.action.RegCompanyResult;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class AdminPresenter extends
		Presenter<AdminPresenter.MyView, AdminPresenter.MyProxy> {

	private final EventBus eventBus;
	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;
	
	public interface MyView extends View {		

		void fillCompanyGrid(ListGridRecord[] records);
		void addbSubmitHandler(ClickHandler handle);
		String getName();
		String getWebsite();		
		String getLogo();
		String getDescription();
		String getCategory();
		
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.admin)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<AdminPresenter> {
	}

	@Inject
	public AdminPresenter(final EventBus eventBus, final MyView view,
			final DispatchAsync dispatcher, final MyProxy proxy,
			final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.eventBus = eventBus;
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		getView().addbSubmitHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				sendRegToServer();
				
			}});
		
		
		dispatcher.execute(new AdminGetAllC(null),
				new AsyncCallback<AdminGetAllCResult>() {

					@Override
					public void onFailure(Throwable caught) {
						SC.say("Fail to get list of company:"+caught.getLocalizedMessage());
					}

					@Override
					public void onSuccess(AdminGetAllCResult result) {
						getView().fillCompanyGrid(result.getListOfCompany());
						
					}
			
		}
		);

		
		
		
	}

	protected void sendRegToServer() {
		dispatcher.execute(new RegCompany(null, null, null, null, null),
				new AsyncCallback<RegCompanyResult>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(RegCompanyResult result) {
						// TODO Auto-generated method stub
						
					}

		
		}
		);
	}
}
