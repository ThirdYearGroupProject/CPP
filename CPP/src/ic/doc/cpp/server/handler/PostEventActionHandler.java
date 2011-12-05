package ic.doc.cpp.server.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.server.dao.CompanyDao;
import ic.doc.cpp.server.dao.EventCategoryDao;
import ic.doc.cpp.server.dao.EventDao;
import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.server.domain.CompanyUser;
import ic.doc.cpp.server.domain.Event;
import ic.doc.cpp.server.util.GetEntityThroughDao;
import ic.doc.cpp.shared.action.PostEvent;
import ic.doc.cpp.shared.action.PostEventResult;
import ic.doc.cpp.shared.dto.EventDto;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class PostEventActionHandler implements
		ActionHandler<PostEvent, PostEventResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public PostEventActionHandler(final Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public PostEventResult execute(PostEvent action, ExecutionContext context)
			throws ActionException {
		PostEventResult result = null;
		try {
			EventDto eventDto = action.getEventDto();
			CompanyUser user = GetEntityThroughDao.getCompanyUser(provider);
			Company company = user.getCompany();
			eventDto.setCompanyId(company.getCompanyId());
			eventDto.setPicture("companyLogo/" + company.getLogo());
			EventDao eventDao = new EventDao();
			eventDao.createEvent(createEventFromEventDto(eventDto));
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return result;
	}

	private Event createEventFromEventDto(EventDto eventDto) {
		Event event = new Event();

		CompanyDao companyDao = new CompanyDao();
		event.setCompany(companyDao.retrieveCompany(eventDto.getCompanyId()));
		
		EventCategoryDao eventCategoryDao = new EventCategoryDao();
		event.setCategory(eventCategoryDao.retrieveEventCategory(eventDto.getCategoryId()));
		
		CompanyUser user = GetEntityThroughDao.getCompanyUser(provider);
		event.setCreatedBy(user.getLogin());
		event.setUpdatedBy(user.getLogin());
		
		Date today = new Date();
		event.setCreatedTimestamp(today);
		event.setUpdatedTimestamp(today);
		
		event.setTitle(eventDto.getTitle());
		event.setDescription(eventDto.getDescription());
		event.setPicture(eventDto.getPicture());
		event.setStart_date(eventDto.getStart_date());
		event.setEnd_date(eventDto.getEnd_date());
		event.setWebsite(eventDto.getWebsite());
		
		return event;
	}

	@Override
	public void undo(PostEvent action, PostEventResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<PostEvent> getActionType() {
		return PostEvent.class;
	}
}
