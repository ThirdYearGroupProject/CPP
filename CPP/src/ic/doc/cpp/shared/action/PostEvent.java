package ic.doc.cpp.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;

import ic.doc.cpp.shared.action.PostEventResult;
import ic.doc.cpp.shared.dto.EventDto;

public class PostEvent extends ActionImpl<PostEventResult> {

	private EventDto eventDto;

	@SuppressWarnings("unused")
	private PostEvent() {
		// For serialization only
	}

	public PostEvent(EventDto eventDto) {
		this.eventDto = eventDto;
	}

	public EventDto getEventDto() {
		return eventDto;
	}
}
