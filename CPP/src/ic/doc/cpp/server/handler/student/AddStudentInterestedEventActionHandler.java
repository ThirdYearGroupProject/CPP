package ic.doc.cpp.server.handler.student;

import ic.doc.cpp.server.dao.StudentUserDao;
import ic.doc.cpp.server.domain.Event;
import ic.doc.cpp.server.domain.StudentUser;
import ic.doc.cpp.server.util.GetEntityThroughDao;
import ic.doc.cpp.shared.action.student.AddStudentInterestedEvent;
import ic.doc.cpp.shared.action.student.AddStudentInterestedEventResult;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class AddStudentInterestedEventActionHandler
		implements
		ActionHandler<AddStudentInterestedEvent, AddStudentInterestedEventResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public AddStudentInterestedEventActionHandler(final Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public AddStudentInterestedEventResult execute(
			AddStudentInterestedEvent action, ExecutionContext context)
			throws ActionException {
		AddStudentInterestedEventResult result = null;
		
		try {
			StudentUser student = GetEntityThroughDao.getStudentUser(provider);
			if (student != null) {
				List<Event> likeEvents = student.getEvents();
				List<Event> dislikeEvents = student.getDislikeEvents();
				
				deleteFromDislikeEvents(action.getEventId(), dislikeEvents);
				
				result = addIntoLikeEvents(action.getEventId(), likeEvents);
				
				StudentUserDao studentDao = new StudentUserDao();
				studentDao.updateUser(student);
			} else {
				throw new Exception("No such student!");
			}
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}
	
	private AddStudentInterestedEventResult addIntoLikeEvents(Long eventId, List<Event> likeEvents) {
		AddStudentInterestedEventResult result = null;
		if (!isDuplicatedInterestedEvent(likeEvents, eventId)) {
			Event event = GetEntityThroughDao.getEvent(eventId);
			likeEvents.add(event);
			result = new AddStudentInterestedEventResult("Successfully add Event: " + event.getTitle());
		} else {
			result = new AddStudentInterestedEventResult("You've already add this event");
		}
		return result;
	}

	private void deleteFromDislikeEvents(Long eventId, List<Event> dislikeEvents) {
		for (Event event : dislikeEvents) {
			if (event.getEventId().equals(eventId)) {
				dislikeEvents.remove(event);
				break;
			}
		}
	}

	private boolean isDuplicatedInterestedEvent(
			List<Event> events, Long eventId) {
		boolean flag = false;
		for (Event evnet : events) {
			if (evnet.getEventId().equals(eventId)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public void undo(AddStudentInterestedEvent action,
			AddStudentInterestedEventResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<AddStudentInterestedEvent> getActionType() {
		return AddStudentInterestedEvent.class;
	}
}
