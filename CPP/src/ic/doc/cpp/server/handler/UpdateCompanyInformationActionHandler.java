package ic.doc.cpp.server.handler;

import javax.servlet.http.HttpServletRequest;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.server.dao.CompanyCategoryDao;
import ic.doc.cpp.server.dao.CompanyDao;
import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.server.domain.CompanyCategory;
import ic.doc.cpp.server.domain.CompanyUser;
import ic.doc.cpp.server.util.GetEntityThroughDao;
import ic.doc.cpp.shared.action.UpdateCompanyInformation;
import ic.doc.cpp.shared.action.UpdateCompanyInformationResult;
import ic.doc.cpp.shared.dto.CompanyDto;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class UpdateCompanyInformationActionHandler implements
		ActionHandler<UpdateCompanyInformation, UpdateCompanyInformationResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public UpdateCompanyInformationActionHandler(
			final Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public UpdateCompanyInformationResult execute(UpdateCompanyInformation action,
			ExecutionContext context) throws ActionException {
		UpdateCompanyInformationResult result = null;
		
		try {
			CompanyUser user = GetEntityThroughDao.getCompanyUser(provider);
			
			if (user != null) {
				Company company = user.getCompany();
				updateCompanyFromDto(company, action.getCompanyDto());
				
				CompanyDao companyDao = new CompanyDao();
				companyDao.updateCompany(company);
			} else {
				throw new Exception("No Such User!");
			}
		} catch (Exception e){
			throw new ActionException(e);
		}
		
		return result;
	}

	private void updateCompanyFromDto(Company company, CompanyDto companyDto) throws Exception {
		company.setName(companyDto.getName());
		company.setLogo(companyDto.getLogo());
		company.setDescription(companyDto.getDescription());
		company.setWebsite(companyDto.getWebsite());
		
		CompanyCategoryDao categoryDao = new CompanyCategoryDao();
		CompanyCategory category = 
				categoryDao.retrieveCompanyCategory(companyDto.getCategoryDto().getCategoryId());
		if (category != null) {
			company.setCategory(category);
		} else {
			throw new Exception("No such category!");
		}
	}

	@Override
	public void undo(UpdateCompanyInformation action,
			UpdateCompanyInformationResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<UpdateCompanyInformation> getActionType() {
		return UpdateCompanyInformation.class;
	}
}
