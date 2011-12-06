package ic.doc.cpp.server.handler.student;

import ic.doc.cpp.server.dao.EventDao;
import ic.doc.cpp.server.domain.Event;
import ic.doc.cpp.server.util.CreateDto;
import ic.doc.cpp.shared.action.student.RetrieveEvents;
import ic.doc.cpp.shared.action.student.RetrieveEventsResult;

import java.util.List;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveEventsActionHandler implements
		ActionHandler<RetrieveEvents, RetrieveEventsResult> {

	@Inject
	public RetrieveEventsActionHandler() {
	}

	@Override
	public RetrieveEventsResult execute(RetrieveEvents action,
			ExecutionContext context) throws ActionException {
		RetrieveEventsResult result = null;
		
		try {
			EventDao eventDao = new EventDao();
			List<Event> events = eventDao.retrieveEvents();
			if (events != null)
				result = new RetrieveEventsResult(CreateDto.createEventDtos(events));
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return result;
	}

	@Override
	public void undo(RetrieveEvents action, RetrieveEventsResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<RetrieveEvents> getActionType() {
		return RetrieveEvents.class;
	}
}
