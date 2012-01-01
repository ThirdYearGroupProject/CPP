package ic.doc.cpp.client.admin;

import ic.doc.cpp.client.LoggedInGatekeeper;
import ic.doc.cpp.client.place.NameTokens;
import ic.doc.cpp.shared.CompanyRecord;
import ic.doc.cpp.shared.action.AdminGetAllC;
import ic.doc.cpp.shared.action.AdminGetAllCResult;
import ic.doc.cpp.shared.action.GetInvitation;
import ic.doc.cpp.shared.action.GetInvitationResult;
import ic.doc.cpp.shared.action.RegCompany;
import ic.doc.cpp.shared.action.RegCompanyResult;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
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
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;

public class AdminPresenter extends
		Presenter<AdminPresenter.MyView, AdminPresenter.MyProxy> {

	@SuppressWarnings("unused")
	private final EventBus eventBus;
	private final DispatchAsync dispatcher;
	@SuppressWarnings("unused")
	private final PlaceManager placeManager;

	public interface MyView extends View {

		void fillCompanyGrid(ListGridRecord[] records);

		HandlerRegistration addbSubmitHandler(ClickHandler handle);

		HandlerRegistration addGridListHandler(CellClickHandler handle);
		
		String getName();

		String getWebsite();

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

		registerHandler(getView().addbSubmitHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sendRegToServer();

			}
		}));

		registerHandler(getView().addGridListHandler(new CellClickHandler(){

			@Override
			public void onCellClick(CellClickEvent event) {
				CompanyRecord cell = (CompanyRecord) event.getRecord();
				final String name = cell.getName();
				Long id = cell.getId();
				
				dispatcher.execute(new GetInvitation(id), 
						new AsyncCallback<GetInvitationResult>(){

							@Override
							public void onFailure(Throwable caught) {
								SC.say(caught.getLocalizedMessage());
								
							}

							@Override
							public void onSuccess(GetInvitationResult result) {
								SC.say("invitation code for company " +name+" : " + result.getInvitationCode());
							}
					
					
				});
			}
			
		}));
		
		dispatcher.execute(new AdminGetAllC(null),
				new AsyncCallback<AdminGetAllCResult>() {

					@Override
					public void onFailure(Throwable caught) {
						SC.say("Fail to get list of company:"
								+ caught.getLocalizedMessage());
					}

					@Override
					public void onSuccess(AdminGetAllCResult result) {
						getView().fillCompanyGrid(result.getListOfCompany());

					}

				});

	}
	
	protected void sendRegToServer() {
		dispatcher.execute(new RegCompany( getView().getWebsite() , "" ,
				getView().getName() ,getView().getCategory() ,getView().getDescription() ),
				new AsyncCallback<RegCompanyResult>() {

					@Override
					public void onFailure(Throwable caught) {
						SC.say(caught.getLocalizedMessage());

					}

					@Override
					public void onSuccess(RegCompanyResult result) {
						SC.say("the invitation code for newly registered company is: "+result.getResult());

					}

				});
	}
}
