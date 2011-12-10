package ic.doc.cpp.client.login;

import ic.doc.cpp.client.CurrentUser;
import ic.doc.cpp.client.place.NameTokens;
import ic.doc.cpp.shared.FieldVerifier;
import ic.doc.cpp.shared.action.Registration;
import ic.doc.cpp.shared.action.RegistrationResult;
import ic.doc.cpp.shared.action.student.Login;
import ic.doc.cpp.shared.action.student.LoginResult;
import ic.doc.cpp.shared.exception.LoginException;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class SignInPagePresenter extends
		Presenter<SignInPagePresenter.MyView, SignInPagePresenter.MyProxy> {

	private final EventBus eventBus;
	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;

	public interface MyView extends View {

		String getUserName();

		String getPassword();

		String getUserType();

		void resetAndFocus();

		HandlerRegistration addSignInButtonClickHandler(
				ClickHandler clickHandler);

		HandlerRegistration addRegButtonClickHandler(ClickHandler clickHandler);

		void resetAndFocusCompanyUser();

		String getRegType();

		String getRegUserName();

		String getRegPassword();

		String getRegLastName();

		String getRegFirstName();

		String getRegEmail();

		String getRegGender();

		String getRegCompany();

	}

	@ProxyStandard
	@NameToken(NameTokens.signin)
	@NoGatekeeper
	public interface MyProxy extends ProxyPlace<SignInPagePresenter> {
	}

	@Inject
	public SignInPagePresenter(final EventBus eventBus, final MyView view,
			final DispatchAsync dispatcher, final MyProxy proxy,
			final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.eventBus = eventBus;
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
	}

	@Override
	protected void onBind() {
		super.onBind();

		registerHandler(getView().addSignInButtonClickHandler(new ClickHandler() {
	          public void onClick(ClickEvent event) {

						if (FieldVerifier.isValidUserName(getView()
								.getUserName())
								&& (FieldVerifier.isValidPassword(getView()
										.getPassword()))) {
							sendCredentialsToServer(getView().getUserType());
						} else {
							SC.say("Sign in",
									"You must enter a valid User name and Password.");
							getView().resetAndFocus();
						
	          }
						
		}}));

		registerHandler(getView().addRegButtonClickHandler(new ClickHandler() {
			  @Override
	          public void onClick(ClickEvent event) {

						if (FieldVerifier.isValidUserName(getView()
								.getUserName())
								&& (FieldVerifier.isValidPassword(getView()
										.getPassword()))) {
							sendRegistrationToServer();
						} else {
							SC.say("Registration",
									"You must enter a valid User name and Password.");
							getView().resetAndFocus();
						
	          }

		}}));

	}

	protected void sendRegistrationToServer() {
		final String type = getView().getRegType();
		if (type == null)
			return;

		String login = getView().getRegUserName();
		String password = getView().getRegPassword();
		String lastName = getView().getRegLastName();
		String firstName = getView().getRegFirstName();
		String email = getView().getRegEmail();
		String gender = getView().getRegGender();
		String company = getView().getRegCompany();
		
		dispatcher.execute(new Registration( login, password, email, gender, firstName, lastName, company, type),
				new AsyncCallback<RegistrationResult>() {

					@Override
					public void onFailure(Throwable caught) {
						String message = "onFailure() - "
								+ caught.getLocalizedMessage();
 
						String title = "Registration Failed";

						SC.say(title,message);
					}

					@Override
					public void onSuccess(RegistrationResult result) {
						CurrentUser currentUser = new CurrentUser(getView()
								.getRegUserName());

						LoginAuthenticatedEvent.fire(eventBus, currentUser);

						PlaceRequest placeRequest;
						if (type.equals("student")) {
							placeRequest = new PlaceRequest(
									NameTokens.studentpage);
							placeManager.revealPlace(placeRequest);
						} else if (type.equals("company")) {
							placeRequest = new PlaceRequest(
									NameTokens.companypage);
							placeManager.revealPlace(placeRequest);
						}

						
					}
			
			
		});
		
	}

	@Override
	protected void onReset() {
		super.onReset();
	}

	@Override
	protected void revealInParent() {
		RevealRootLayoutContentEvent.fire(this, this);
	}

	private void sendCredentialsToServer(final String type) {
		if (type == null)
			return;

		String login = getView().getUserName();
		String password = getView().getPassword();

		dispatcher.execute(new Login(type, login, password),
				new AsyncCallback<LoginResult>() {

					@Override
					public void onFailure(Throwable caught) {
						String message = "onFailure() - "
								+ caught.getLocalizedMessage();

						if (caught instanceof LoginException) {
							message = "onFailure() - "
									+ "Invalid User name or Password.";
						}

						getView().resetAndFocus();
						SC.say(message);
					}

					@Override
					public void onSuccess(LoginResult result) {
						CurrentUser currentUser = new CurrentUser(getView()
								.getUserName());

						LoginAuthenticatedEvent.fire(eventBus, currentUser);

						PlaceRequest placeRequest;
						if (type.equals("student")) {
							placeRequest = new PlaceRequest(
									NameTokens.studentpage);
							placeManager.revealPlace(placeRequest);
						} else if (type.equals("company")) {
							placeRequest = new PlaceRequest(
									NameTokens.companypage);
							placeManager.revealPlace(placeRequest);
						}

					}

				});

	}

}
