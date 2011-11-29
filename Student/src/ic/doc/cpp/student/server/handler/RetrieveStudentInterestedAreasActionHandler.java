package ic.doc.cpp.student.server.handler;

import javax.servlet.http.HttpServletRequest;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.domain.StudentUser;
import ic.doc.cpp.student.server.util.GetEntityThroughDao;
import ic.doc.cpp.student.shared.action.RetrieveStudentInterestedAreas;
import ic.doc.cpp.student.shared.action.RetrieveStudentInterestedAreasResult;
import ic.doc.cpp.student.shared.dto.util.CreateDto;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveStudentInterestedAreasActionHandler
		implements
		ActionHandler<RetrieveStudentInterestedAreas, RetrieveStudentInterestedAreasResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public RetrieveStudentInterestedAreasActionHandler(final Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public RetrieveStudentInterestedAreasResult execute(
			RetrieveStudentInterestedAreas action, ExecutionContext context)
			throws ActionException {
		RetrieveStudentInterestedAreasResult result = null;
		
		try {
			StudentUser student = GetEntityThroughDao.getStudentUser(provider);
			
			result = new RetrieveStudentInterestedAreasResult(
					CreateDto.createCompanyCategoryDtos(student.getInterestedArea()));
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return result;
	}

	@Override
	public void undo(RetrieveStudentInterestedAreas action,
			RetrieveStudentInterestedAreasResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RetrieveStudentInterestedAreas> getActionType() {
		return RetrieveStudentInterestedAreas.class;
	}
}
