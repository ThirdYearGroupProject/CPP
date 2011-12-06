package ic.doc.cpp.server.handler.student;

import ic.doc.cpp.server.dao.CompanyCategoryDao;
import ic.doc.cpp.server.domain.CompanyCategory;
import ic.doc.cpp.server.util.CreateDto;
import ic.doc.cpp.shared.action.student.RetrieveCompanyCategory;
import ic.doc.cpp.shared.action.student.RetrieveCompanyCategoryResult;

import java.util.List;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveCompanyCategoryActionHandler implements
		ActionHandler<RetrieveCompanyCategory, RetrieveCompanyCategoryResult> {

	
	@Inject
	public RetrieveCompanyCategoryActionHandler() {
	}
	
	@Override
	public RetrieveCompanyCategoryResult execute(RetrieveCompanyCategory action,
			ExecutionContext context) throws ActionException {
		RetrieveCompanyCategoryResult result = null;
		
		try {
			CompanyCategoryDao companyCategoryDao = new CompanyCategoryDao();
			List<CompanyCategory> companyCategorys = companyCategoryDao.retrieveCompanyCategorys();
			if (companyCategorys != null) {
				result = new RetrieveCompanyCategoryResult(CreateDto.createCompanyCategoryDtos(companyCategorys));
			}
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	@Override
	public void undo(RetrieveCompanyCategory action,
			RetrieveCompanyCategoryResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RetrieveCompanyCategory> getActionType() {
		return RetrieveCompanyCategory.class;
	}
}
