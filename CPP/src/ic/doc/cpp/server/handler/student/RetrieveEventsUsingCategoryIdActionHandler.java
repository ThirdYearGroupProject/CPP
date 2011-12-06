package ic.doc.cpp.server.handler.student;

import ic.doc.cpp.server.dao.EventCategoryDao;
import ic.doc.cpp.server.dao.EventDao;
import ic.doc.cpp.server.domain.Event;
import ic.doc.cpp.server.domain.EventCategory;
import ic.doc.cpp.server.domain.StudentUser;
import ic.doc.cpp.server.util.CreateDto;
import ic.doc.cpp.server.util.GetEntityThroughDao;
import ic.doc.cpp.shared.action.student.RetrieveEventsUsingCategoryId;
import ic.doc.cpp.shared.action.student.RetrieveEventsUsingCategoryIdResult;
import ic.doc.cpp.shared.dto.EventDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveEventsUsingCategoryIdActionHandler
		implements
		ActionHandler<RetrieveEventsUsingCategoryId, RetrieveEventsUsingCategoryIdResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public RetrieveEventsUsingCategoryIdActionHandler(
			final Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public RetrieveEventsUsingCategoryIdResult execute(
			RetrieveEventsUsingCategoryId action, ExecutionContext context)
			throws ActionException {
		RetrieveEventsUsingCategoryIdResult result = null;
		
		try {
			Long categoryId = action.getId();
			EventCategoryDao eventCategoryDao = new EventCategoryDao();
			EventCategory eventCategory = eventCategoryDao.retrieveEventCategory(categoryId);
			String categoryName = eventCategory.getCategoryName();
			Date updateTime = action.getUpdateTime();
			EventDao eventDao = new EventDao();

			StudentUser currentUser = GetEntityThroughDao.getStudentUser(provider);
			List<Event> cleanEvents = removeDislikeEvents(currentUser, 
					eventDao.retrieveEvents(categoryName, updateTime));
			List<EventDto> eventDtos = CreateDto.createEventDtos(cleanEvents);
			
			checkIfLikedByCurrentUser(currentUser, eventDtos);
			
			result = new RetrieveEventsUsingCategoryIdResult(categoryId, eventDtos);
		} catch(Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	private List<Event> removeDislikeEvents(StudentUser currentUser,
			List<Event> events) {
		List<Event> result = new ArrayList<Event>();
		
		List<Event> dislikeEvent = currentUser.getDislikeEvents();
		Map<Long, Event> map = new HashMap<Long, Event>();
		
		for (Event e : dislikeEvent) {
			map.put(e.getEventId(), e);
		}
		
		for (Event e : events) {
			if (map.get(e.getEventId()) == null) {
				result.add(e);
			}
		}
		
		return result;
	}

	private void checkIfLikedByCurrentUser(StudentUser currentUser,
			List<EventDto> eventDtos) {
		List<Event> likeEvents = currentUser.getEvents();
		
		Map<Long, Event> eventMap = new HashMap<Long, Event>();
		
		for (Event e : likeEvents) {
			eventMap.put(e.getEventId(), e);
		}
		
		for (EventDto eventDto : eventDtos) {
			if (eventMap.get(eventDto.getEventId()) != null) {
				eventDto.setLikedByCurrentUser(true);
			}
		}
	}

	@Override
	public void undo(RetrieveEventsUsingCategoryId action,
			RetrieveEventsUsingCategoryIdResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RetrieveEventsUsingCategoryId> getActionType() {
		return RetrieveEventsUsingCategoryId.class;
	}
}
