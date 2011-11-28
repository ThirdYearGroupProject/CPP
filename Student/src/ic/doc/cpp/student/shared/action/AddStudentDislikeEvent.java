package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;
import ic.doc.cpp.student.shared.action.AddStudentDislikeEventResult;
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
