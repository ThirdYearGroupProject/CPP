package ic.doc.cpp.server.handler;


import java.util.LinkedList;
import java.util.List;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.server.dao.CompanyCategoryDao;
import ic.doc.cpp.server.dao.CompanyDao;
import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.server.domain.CompanyCategory;
import ic.doc.cpp.shared.action.AdminGetAllC;
import ic.doc.cpp.shared.action.AdminGetAllCResult;
import ic.doc.cpp.shared.dto.CompanyCategoryDto;
import ic.doc.cpp.shared.dto.CompanyDto;
import ic.doc.cpp.shared.dto.EventDto;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class AdminGetAllCActionHandler implements
		ActionHandler<AdminGetAllC, AdminGetAllCResult> {

	@Inject
	public AdminGetAllCActionHandler() {
	}

	@Override
	// list of event is not pass back
	public AdminGetAllCResult execute(AdminGetAllC action, ExecutionContext context)
			throws ActionException {
		AdminGetAllCResult result;
		CompanyDao allC = new CompanyDao();
		CompanyCategoryDao categoryDao = new CompanyCategoryDao();
		List<Company> listOfCompany = allC.retrieveCompanys();
		List<CompanyDto> listOfCompanyDto = new LinkedList<CompanyDto>();
		for(Company each : listOfCompany ){
			CompanyCategory cc = categoryDao.retrieveCompanyCategory(each.getCategory().getCategoryId());
			CompanyCategoryDto ccDto = new CompanyCategoryDto(cc.getCategoryId(),cc.getCategoryName(),cc.getParentId());
		
			listOfCompanyDto.add(new CompanyDto(each.getCompanyId(), each.getName(),
					ccDto , new LinkedList<EventDto>(),
					each.getDescription(), each.getWebsite(), each.getLogo()) );
		}
		result = new AdminGetAllCResult(listOfCompanyDto);
		
		return result;
	}

	@Override
	public void undo(AdminGetAllC action, AdminGetAllCResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<AdminGetAllC> getActionType() {
		return AdminGetAllC.class;
	}
}
