package ic.doc.cpp.server.handler.student;

import ic.doc.cpp.server.domain.StudentUser;
import ic.doc.cpp.server.util.GetEntityThroughDao;
import ic.doc.cpp.shared.action.student.RetrieveStudentInterestedCompanies;
import ic.doc.cpp.shared.action.student.RetrieveStudentInterestedCompaniesResult;
import ic.doc.cpp.shared.dto.util.CreateDto;

import javax.servlet.http.HttpServletRequest;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveStudentInterestedCompaniesActionHandler
		implements
		ActionHandler<RetrieveStudentInterestedCompanies, RetrieveStudentInterestedCompaniesResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public RetrieveStudentInterestedCompaniesActionHandler(
			final Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public RetrieveStudentInterestedCompaniesResult execute(
			RetrieveStudentInterestedCompanies action, ExecutionContext context)
			throws ActionException {
		RetrieveStudentInterestedCompaniesResult result = null;
		
		try {
			StudentUser student = GetEntityThroughDao.getStudentUser(provider);
			
			result = new RetrieveStudentInterestedCompaniesResult(
					CreateDto.createCompanyDtos(student.getCompanys()));
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	@Override
	public void undo(RetrieveStudentInterestedCompanies action,
			RetrieveStudentInterestedCompaniesResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<RetrieveStudentInterestedCompanies> getActionType() {
		return RetrieveStudentInterestedCompanies.class;
	}
}
