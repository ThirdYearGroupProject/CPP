package ic.doc.cpp.client.company.core.postingevent;

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
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import ic.doc.cpp.client.company.core.CompanyPagePresenter;
import ic.doc.cpp.shared.action.PostEvent;
import ic.doc.cpp.shared.action.PostEventResult;
import ic.doc.cpp.shared.action.RetrieveEventCategories;
import ic.doc.cpp.shared.action.RetrieveEventCategoriesResult;
import ic.doc.cpp.shared.dto.EventCategoryDto;
import ic.doc.cpp.shared.dto.EventDto;

public class PostingEventPresenter extends
		Presenter<PostingEventPresenter.MyView, PostingEventPresenter.MyProxy> {

	private final DispatchAsync dispatcher;
	
	public interface MyView extends View {

		boolean validateForm();

		HandlerRegistration addSubmmitButtionClickHandler(
				ClickHandler clickHandler);

		EventDto getEventInformation();

		void SetCategoryValues(List<EventCategoryDto> categories);

		boolean isOnlySendToInterstedStudent();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.postevent)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<PostingEventPresenter> {
	}

	@Inject
	public PostingEventPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				CompanyPagePresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		registerHandler(getView().addSubmmitButtionClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (getView().validateForm()) {
					final EventDto eventDto = getView().getEventInformation();
					
					dispatcher.execute(new PostEvent(eventDto), new AsyncCallback<PostEventResult>() {

						@Override
						public void onFailure(Throwable caught) {
							GWT.log("Fail on PostEvent()...");
						}

						@Override
						public void onSuccess(PostEventResult result) {
							SC.say("Successfully post event: " + eventDto.getTitle());
						}
					});
					
					if (getView().isOnlySendToInterstedStudent()) {
						
					}
				}
			}
		}));
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		dispatcher.execute(new RetrieveEventCategories(), new AsyncCallback<RetrieveEventCategoriesResult>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Fail on RetrieveEventCategories()...");
			}

			@Override
			public void onSuccess(RetrieveEventCategoriesResult result) {
				getView().SetCategoryValues(result.getEventCategoryDtos());
			}
		});
	}
}
