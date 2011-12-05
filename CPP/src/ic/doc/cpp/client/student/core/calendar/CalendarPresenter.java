package ic.doc.cpp.client.student.core.calendar;

import ic.doc.cpp.client.LoggedInGatekeeper;
import ic.doc.cpp.client.place.NameTokens;
import ic.doc.cpp.client.student.core.StudentPagePresenter;
import ic.doc.cpp.client.util.CreateRecordFromDto;
import ic.doc.cpp.shared.action.student.RetrieveStudentInterestedEvents;
import ic.doc.cpp.shared.action.student.RetrieveStudentInterestedEventsResult;
import ic.doc.cpp.shared.dto.EventDto;

import java.util.List;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;

import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.smartgwt.client.widgets.calendar.CalendarEvent;

public class CalendarPresenter extends
		Presenter<CalendarPresenter.MyView, CalendarPresenter.MyProxy> {

	public interface MyView extends View {

		void setData(CalendarEvent[] calendarEvents);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.calendar)
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends ProxyPlace<CalendarPresenter> {
	}
	
	private final DispatchAsync dispatcher;

	@Inject
	public CalendarPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				StudentPagePresenter.TYPE_SetContextAreaContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		refresh();
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		refresh();
	}
	
	private void refresh() {
		dispatcher.execute(new RetrieveStudentInterestedEvents(), 
				new AsyncCallback<RetrieveStudentInterestedEventsResult>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("CalendarPresenter: Fail on RetrieveStudentInterestedEvents...");
			}

			@Override
			public void onSuccess(RetrieveStudentInterestedEventsResult result) {
				updateCalendar(result.getEventDtos());
			}
		});
	}

	protected void updateCalendar(List<EventDto> eventDtos) {
		List<CalendarEvent> calendarEventsList =
				CreateRecordFromDto.createCalendarEventFromDtos(eventDtos);
		getView().setData(calendarEventsList.toArray(new CalendarEvent[0]));
	}
	
}
