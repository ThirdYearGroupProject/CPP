package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.Result;

import ic.doc.cpp.student.shared.dto.EventDto;

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
