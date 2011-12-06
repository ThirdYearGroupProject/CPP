package ic.doc.cpp.server.handler;

import java.util.List;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.server.dao.EventCategoryDao;
import ic.doc.cpp.server.domain.EventCategory;
import ic.doc.cpp.server.util.CreateDto;
import ic.doc.cpp.shared.action.RetrieveEventCategories;
import ic.doc.cpp.shared.action.RetrieveEventCategoriesResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveEventCategoriesActionHandler implements
		ActionHandler<RetrieveEventCategories, RetrieveEventCategoriesResult> {

	@Inject
	public RetrieveEventCategoriesActionHandler() {
	}

	@Override
	public RetrieveEventCategoriesResult execute(RetrieveEventCategories action,
			ExecutionContext context) throws ActionException {
		RetrieveEventCategoriesResult result = null;
		
		try {
			EventCategoryDao eventCategoryDao = new EventCategoryDao();
			List<EventCategory> eventCategorys = eventCategoryDao.retrieveEventCategorys();
			if (eventCategorys != null) {
				result = new RetrieveEventCategoriesResult(CreateDto.createEventCategoryDtos(eventCategorys));
			}
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	@Override
	public void undo(RetrieveEventCategories action,
			RetrieveEventCategoriesResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RetrieveEventCategories> getActionType() {
		return RetrieveEventCategories.class;
	}
}
