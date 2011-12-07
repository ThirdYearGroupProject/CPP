package ic.doc.cpp.client.login;

import ic.doc.cpp.client.CurrentUser;
import ic.doc.cpp.client.place.NameTokens;
import ic.doc.cpp.shared.FieldVerifier;
import ic.doc.cpp.shared.action.student.Login;
import ic.doc.cpp.shared.action.student.LoginResult;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;

import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;
import com.smartgwt.client.util.SC;

public class SignInPagePresenter extends
		Presenter<SignInPagePresenter.MyView, SignInPagePresenter.MyProxy> {
	
	private final EventBus eventBus;
	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;
	
	public interface MyView extends View {

		String getUserName();

		String getPassword();

		void resetAndFocus();

		String getCompanyUserPassword();

		String getCompanyUserName();

		HandlerRegistration addSignInButtonClickHandler(
				ClickHandler clickHandler);

		void resetAndFocusCompanyUser();

		HandlerRegistration addCompanyUserSignInButtonClickHandler(
				ClickHandler clickHandler);

		String getUserName(String type);

		String getPassword(String type);
		
	}

	@ProxyStandard
	@NameToken(NameTokens.signin)
	@NoGatekeeper
	public interface MyProxy extends ProxyPlace<SignInPagePresenter> {
	}

	@Inject
	public SignInPagePresenter(final EventBus eventBus, final MyView view, final DispatchAsync dispatcher,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.eventBus = eventBus;
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;
	}

	  @Override
	  protected void onBind() {
	    super.onBind();
	    registerHandler(getView().addCompanyUserSignInButtonClickHandler(
	    		new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (FieldVerifier.isValidUserName(getView().getUserName()) 
						&& (FieldVerifier.isValidPassword(getView().getPassword()))) {
					sendCredentialsToServer("company");
				} else {
					SC.say("Sign in", "You must enter a valid User name and Password.");
					getView().resetAndFocus();
				}
			}
		}));
	    
	    registerHandler(getView().addSignInButtonClickHandler(
	        new ClickHandler() {
	          @Override
	          public void onClick(ClickEvent event) {
	        	  if (FieldVerifier.isValidUserName(getView().getUserName()) 
	        			  && (FieldVerifier.isValidPassword(getView().getPassword()))) {
	        		  sendCredentialsToServer("student");
	        	  } else {
	        		  SC.say("Sign in", "You must enter a valid User name and Password.");
	        		  getView().resetAndFocus();
	        	  }
	          }
	        }));
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
		  
		  String login = getView().getUserName(type);
		  String password = getView().getPassword(type);
		  
		  dispatcher.execute(new Login(type, login, password),
				  new AsyncCallback<LoginResult>() {

					@Override
					public void onFailure(Throwable caught) {
						getView().resetAndFocus();
						SC.say("Invalid User name or password.");
					}

					@Override
					public void onSuccess(LoginResult result) {
						CurrentUser currentUser = new CurrentUser(getView().getUserName());
					        
				        LoginAuthenticatedEvent.fire(eventBus, currentUser);
					    
				        PlaceRequest placeRequest;
				        if (type.equals("student")) {
				        	placeRequest = new PlaceRequest(NameTokens.studentpage);
				        	placeManager.revealPlace(placeRequest);       
				        } else if (type.equals("company")) {
				        	placeRequest = new PlaceRequest(NameTokens.companypage);
				        	placeManager.revealPlace(placeRequest);       
				        }
				        	
					}
			  
		  });
	    
	  }

}
