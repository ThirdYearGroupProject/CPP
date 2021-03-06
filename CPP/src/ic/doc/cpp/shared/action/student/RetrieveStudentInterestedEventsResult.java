package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.Result;

import ic.doc.cpp.shared.dto.EventDto;

import java.util.List;

public class RetrieveStudentInterestedEventsResult implements Result {

	private List<EventDto> eventDtos;

	@SuppressWarnings("unused")
	private RetrieveStudentInterestedEventsResult() {
		// For serialization only
	}

	public RetrieveStudentInterestedEventsResult(List<EventDto> eventDtos) {
		this.eventDtos = eventDtos;
	}

	public List<EventDto> getEventDtos() {
		return eventDtos;
	}
}
