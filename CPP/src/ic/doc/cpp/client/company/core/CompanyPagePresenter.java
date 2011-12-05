package ic.doc.cpp.client.company.core;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import ic.doc.cpp.client.place.NameTokens;

import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import ic.doc.cpp.client.LoggedInGatekeeper;

import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class CompanyPagePresenter extends
		Presenter<CompanyPagePresenter.MyView, CompanyPagePresenter.MyProxy> {

	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetContextAreaContent = new Type<RevealContentHandler<?>>();
	
	private final PlaceManager placeManager;

	public interface MyView extends View {

		HandlerRegistration addNavigationPaneRecordClickHandler(
				String string,
				NavigationPaneRecordClickHandler navigationPaneRecordClickHandler);
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.companypage)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<CompanyPagePresenter> {
	}

	@Inject
	public CompanyPagePresenter(final EventBus eventBus, final MyView view,
			final PlaceManager placeManager,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
	}

	@Override
	protected void revealInParent() {
		RevealRootLayoutContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		registerHandler(getView().addNavigationPaneRecordClickHandler("Main", new NavigationPaneRecordClickHandler()));
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		PlaceRequest myRequset = new PlaceRequest(NameTokens.postevent);
		placeManager.revealPlace(myRequset);
	}
	
	public class NavigationPaneRecordClickHandler implements RecordClickHandler {

		@Override
		public void onRecordClick(RecordClickEvent event) {
			Record record = event.getRecord();
			String place = record.getAttributeAsString("place");
			GWT.log("NavigationPaneRecordClickHandler.onRecordClick() - " + place);
			PlaceRequest myRequest = new PlaceRequest(place);
			placeManager.revealPlace(myRequest);
		}
		
	}
}
