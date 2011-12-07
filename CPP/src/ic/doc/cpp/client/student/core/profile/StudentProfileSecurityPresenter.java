package ic.doc.cpp.client.student.core.profile;

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
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import ic.doc.cpp.client.student.core.profile.StudentProfilePresenter;
import ic.doc.cpp.shared.FieldVerifier;
import ic.doc.cpp.shared.action.student.UpdatePassword;
import ic.doc.cpp.shared.action.student.UpdatePasswordResult;

public class StudentProfileSecurityPresenter
		extends
		Presenter<StudentProfileSecurityPresenter.MyView, StudentProfileSecurityPresenter.MyProxy> {

	private final DispatchAsync dispatcher;
	
	public interface MyView extends View {

		HandlerRegistration addUpdateButtonClickHandler(
				ClickHandler clickHandler);

		boolean validatePasswordSettingForm();

		String getNewPassword();

		String getOrinalPassword();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.studentprofilesecurity)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<StudentProfileSecurityPresenter> {
	}

	@Inject
	public StudentProfileSecurityPresenter(final EventBus eventBus,
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
		registerHandler(getView().addUpdateButtonClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (getView().validatePasswordSettingForm()){
					if (FieldVerifier.isValidPassword(getView().getNewPassword())) {
						dispatcher.execute(new UpdatePassword(getView().getOrinalPassword(), getView().getNewPassword()), 
								new AsyncCallback<UpdatePasswordResult>() {
	
							@Override
							public void onFailure(Throwable caught) {
								GWT.log("Fail on updatePassword() - " + caught.getLocalizedMessage());
								SC.say("Error", "Fail on Updating Passwrod");
							}
	
							@Override
							public void onSuccess(UpdatePasswordResult result) {
								SC.say("Upadate Password", "Updating Passwrod successful");
							}
						});
					} else {
						SC.say("Error", "Invalid new Password format, please read how to set a Password!");
					}
					
				}
			}
			
		}));
	}

	@Override
	protected void onReveal() {
		super.onReveal();
	}
}
