package ic.doc.cpp.server.handler.student;

import ic.doc.cpp.server.dao.StudentUserDao;
import ic.doc.cpp.server.domain.Event;
import ic.doc.cpp.server.domain.StudentUser;
import ic.doc.cpp.server.util.GetEntityThroughDao;
import ic.doc.cpp.shared.action.student.AddStudentDislikeEvent;
import ic.doc.cpp.shared.action.student.AddStudentDislikeEventResult;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class AddStudentDislikeEventActionHandler implements
		ActionHandler<AddStudentDislikeEvent, AddStudentDislikeEventResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public AddStudentDislikeEventActionHandler(final Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public AddStudentDislikeEventResult execute(AddStudentDislikeEvent action,
			ExecutionContext context) throws ActionException {
		AddStudentDislikeEventResult result = null;
		try {
			StudentUser student = GetEntityThroughDao.getStudentUser(provider);
			
			if (student != null) {
				List<Event> likeEvents = student.getEvents();
				List<Event> dislikeEvents = student.getDislikeEvents();
				
				deleteFromLikeEvents(action.getEventId(), likeEvents);
				Event dislikeEvent = addIntoDislikeEvents(action.getEventId(), dislikeEvents);
					
				StudentUserDao studentDao = new StudentUserDao();
				studentDao.updateUser(student);
				
				result = new AddStudentDislikeEventResult("Successfully add dislike event: " + dislikeEvent.getTitle());
			} else {
				throw new Exception("No such student!");
			}
			
				
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return result;
	}

	private Event addIntoDislikeEvents(Long eventId, List<Event> dislikeEvents) {
		Event event = GetEntityThroughDao.getEvent(eventId);
		dislikeEvents.add(event);
		return event;
	}

	private void deleteFromLikeEvents(Long eventId, List<Event> likeEvents) {
		for (Event event : likeEvents) {
			if (event.getEventId().equals(eventId)) {
				likeEvents.remove(event);
				break;
			}
		}
	}

	@Override
	public void undo(AddStudentDislikeEvent action,
			AddStudentDislikeEventResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<AddStudentDislikeEvent> getActionType() {
		return AddStudentDislikeEvent.class;
	}
}
