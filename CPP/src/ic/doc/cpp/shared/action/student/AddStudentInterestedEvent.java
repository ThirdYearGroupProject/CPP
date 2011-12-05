package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.ActionImpl;
import java.lang.Long;

public class AddStudentInterestedEvent extends
		ActionImpl<AddStudentInterestedEventResult> {

	private Long eventId;

	@SuppressWarnings("unused")
	private AddStudentInterestedEvent() {
		// For serialization only
	}

	public AddStudentInterestedEvent(Long eventId) {
		this.eventId = eventId;
	}

	public Long getEventId() {
		return eventId;
	}
}
