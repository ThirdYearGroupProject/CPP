package ic.doc.cpp.server.handler.student;

import ic.doc.cpp.server.dao.CompanyDao;
import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.shared.action.student.RetrieveCompanysUsingNameAndCategory;
import ic.doc.cpp.shared.action.student.RetrieveCompanysUsingNameAndCategoryResult;
import ic.doc.cpp.shared.dto.util.CreateDto;

import java.util.List;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveCompanysUsingNameAndCategoryActionHandler
		implements
		ActionHandler<RetrieveCompanysUsingNameAndCategory, RetrieveCompanysUsingNameAndCategoryResult> {

	@Inject
	public RetrieveCompanysUsingNameAndCategoryActionHandler() {
	}

	@Override
	public RetrieveCompanysUsingNameAndCategoryResult execute(
			RetrieveCompanysUsingNameAndCategory action, ExecutionContext context)
			throws ActionException {
		RetrieveCompanysUsingNameAndCategoryResult result = null;
		try {
			CompanyDao companyDao = new CompanyDao();
			List<Company> companys = companyDao.retrieveCompanys(
					action.getName(), action.getCategory());
			result = new RetrieveCompanysUsingNameAndCategoryResult(
					CreateDto.createCompanyDtos(companys));
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return result;
	}

	@Override
	public void undo(RetrieveCompanysUsingNameAndCategory action,
			RetrieveCompanysUsingNameAndCategoryResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<RetrieveCompanysUsingNameAndCategory> getActionType() {
		return RetrieveCompanysUsingNameAndCategory.class;
	}
}
