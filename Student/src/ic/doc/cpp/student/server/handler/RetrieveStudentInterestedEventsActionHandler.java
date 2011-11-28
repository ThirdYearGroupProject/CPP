package ic.doc.cpp.student.server.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.StudentUserDao;
import ic.doc.cpp.student.server.domain.StudentUser;
import ic.doc.cpp.student.shared.action.RetrieveStudentInterestedEvents;
import ic.doc.cpp.student.shared.action.RetrieveStudentInterestedEventsResult;
import ic.doc.cpp.student.shared.dto.EventDto;
import ic.doc.cpp.student.shared.dto.util.CreateDto;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveStudentInterestedEventsActionHandler
		implements
		ActionHandler<RetrieveStudentInterestedEvents, RetrieveStudentInterestedEventsResult> {

	private final Provider<HttpServletRequest> request;
	
	@Inject
	public RetrieveStudentInterestedEventsActionHandler(
			final Provider<HttpServletRequest> request) {
		this.request = request;
	}

	@Override
	public RetrieveStudentInterestedEventsResult execute(
			RetrieveStudentInterestedEvents action, ExecutionContext context)
			throws ActionException {
		RetrieveStudentInterestedEventsResult result = null;
		
		try {
			HttpSession session = request.get().getSession();
			String login = session.getAttribute("login.authenticated").toString();
			StudentUserDao studentDao = new StudentUserDao();
			StudentUser student = studentDao.retrieveUser(login);
			List<EventDto> eventDtos = CreateDto.createEventDtos(student.getEvents());
			result = new RetrieveStudentInterestedEventsResult(eventDtos);
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	@Override
	public void undo(RetrieveStudentInterestedEvents action,
			RetrieveStudentInterestedEventsResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RetrieveStudentInterestedEvents> getActionType() {
		return RetrieveStudentInterestedEvents.class;
	}
}
