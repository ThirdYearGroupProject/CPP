package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.ActionImpl;
import java.lang.Long;

public class AddStudentDislikeEvent extends
		ActionImpl<AddStudentDislikeEventResult> {

	private Long eventId;

	@SuppressWarnings("unused")
	private AddStudentDislikeEvent() {
		// For serialization only
	}

	public AddStudentDislikeEvent(Long eventId) {
		this.eventId = eventId;
	}

	public Long getEventId() {
		return eventId;
	}
}
