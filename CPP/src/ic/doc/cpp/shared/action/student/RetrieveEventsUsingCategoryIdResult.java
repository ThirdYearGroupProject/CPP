package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.Result;

import ic.doc.cpp.shared.dto.EventDto;

import java.util.List;

public class RetrieveEventsUsingCategoryIdResult implements Result {

	private Long id;
	private List<EventDto> eventDtos;

	@SuppressWarnings("unused")
	private RetrieveEventsUsingCategoryIdResult() {
		// For serialization only
	}

	public RetrieveEventsUsingCategoryIdResult(Long id, List<EventDto> eventDtos) {
		this.eventDtos = eventDtos;
	}

	public List<EventDto> getEventDtos() {
		return eventDtos;
	}
	
	public Long getId() {
		return id;
	}
}