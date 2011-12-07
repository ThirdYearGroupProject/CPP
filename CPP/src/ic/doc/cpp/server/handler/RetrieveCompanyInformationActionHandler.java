package ic.doc.cpp.server.handler;

import javax.servlet.http.HttpServletRequest;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.server.domain.CompanyUser;
import ic.doc.cpp.server.util.CreateDto;
import ic.doc.cpp.server.util.GetEntityThroughDao;
import ic.doc.cpp.shared.action.RetrieveCompanyInformation;
import ic.doc.cpp.shared.action.RetrieveCompanyInformationResult;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RetrieveCompanyInformationActionHandler
		implements
		ActionHandler<RetrieveCompanyInformation, RetrieveCompanyInformationResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public RetrieveCompanyInformationActionHandler(
			final Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public RetrieveCompanyInformationResult execute(
			RetrieveCompanyInformation action, ExecutionContext context)
			throws ActionException {
		RetrieveCompanyInformationResult result = null;
		
		try {
			CompanyUser user = GetEntityThroughDao.getCompanyUser(provider);
			if (user != null) {
				Company company = user.getCompany();
				result = new RetrieveCompanyInformationResult(CreateDto.createCompanyDto(company));
			} else {
				throw new Exception("No Such User!");
			}
		} catch (Exception e){
			throw new ActionException(e);
		}
		
		return result;
	}

	@Override
	public void undo(RetrieveCompanyInformation action,
			RetrieveCompanyInformationResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RetrieveCompanyInformation> getActionType() {
		return RetrieveCompanyInformation.class;
	}
}
