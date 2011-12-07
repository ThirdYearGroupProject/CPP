package ic.doc.cpp.client.student.core.profile;

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
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import ic.doc.cpp.client.student.core.StudentPagePresenter;

public class StudentProfilePresenter
		extends
		Presenter<StudentProfilePresenter.MyView, StudentProfilePresenter.MyProxy> {

	private final PlaceManager placeManager;
	
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetStudentProfileContent = new Type<RevealContentHandler<?>>();

	
	public interface MyView extends View {

		HandlerRegistration addGeneralInformationClickHandler(
				ClickHandler clickHandler);

		HandlerRegistration addInterestedAreaClickHandler(
				ClickHandler clickHandler);

		HandlerRegistration addInterestedCompanyClickHandler(
				ClickHandler clickHandler);

		HandlerRegistration addSecuritySettingClickHandler(
				ClickHandler clickHandler);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.studentprofile)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<StudentProfilePresenter> {
	}

	@Inject
	public StudentProfilePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy , final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				StudentPagePresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		registerHandler(getView().addGeneralInformationClickHandler(
				new ToolStripClickHandler(NameTokens.studentprofilegeneralinfo)));
		registerHandler(getView().addInterestedAreaClickHandler(
				new ToolStripClickHandler(NameTokens.studentprofileinterestedarea)));
		registerHandler(getView().addInterestedCompanyClickHandler(
				new ToolStripClickHandler(NameTokens.studentprofileinterestedcompany)));
		registerHandler(getView().addSecuritySettingClickHandler(
				new ToolStripClickHandler(NameTokens.studentprofilesecurity)));
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		PlaceRequest myRequset = new PlaceRequest(NameTokens.studentprofilegeneralinfo);
		placeManager.revealPlace(myRequset);
	}
	
	public class ToolStripClickHandler implements ClickHandler {
		
		private String place;

		public ToolStripClickHandler(String place) {
			this.place = place;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			PlaceRequest myRequset = new PlaceRequest(place);
			placeManager.revealPlace(myRequset);
		}
		
	}
	
}
