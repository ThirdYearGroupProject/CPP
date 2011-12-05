package ic.doc.cpp.shared.action.student;

import ic.doc.cpp.shared.dto.EventDto;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;

public class RetrieveEventsResult implements Result {

	private List<EventDto> events;

	@SuppressWarnings("unused")
	private RetrieveEventsResult() {
		// For serialization only
	}

	public RetrieveEventsResult(List<EventDto> events) {
		this.events = events;
	}

	public List<EventDto> getEvents() {
		return events;
	}
}
