package ic.doc.cpp.server.handler;

import ic.doc.cpp.server.dao.CompanyCategoryDao;
import ic.doc.cpp.server.dao.CompanyDao;
import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.server.domain.CompanyCategory;
import ic.doc.cpp.server.domain.Event;
import ic.doc.cpp.server.util.InvitationKeyManager;
import ic.doc.cpp.shared.action.RegCompany;
import ic.doc.cpp.shared.action.RegCompanyResult;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class RegCompanyActionHandler implements
		ActionHandler<RegCompany, RegCompanyResult> {

	@Inject
	public RegCompanyActionHandler() {
	}

	@Override
	public RegCompanyResult execute(RegCompany action, ExecutionContext context)
			throws ActionException {
		RegCompanyResult result;

		
		CompanyDao cDao = new CompanyDao();
		CompanyCategoryDao ccDao = new CompanyCategoryDao();
		
		List<CompanyCategory> listOfCategory = ccDao.retrieveCompanyCategorys();
		Iterator<CompanyCategory> iterator = listOfCategory.iterator();
		CompanyCategory found = null;
		while( iterator.hasNext() ){
			CompanyCategory each = iterator.next();
			if(each.getCategoryName().equals(action.getCategory())){
				found = each;
				break;
			}
		}
		
		if(found == null){
			throw new ActionException("category not found");
		}
		
		Company company = new Company();
		company.setCategory(found);
		company.setDescription(action.getDescription());
		company.setLogo(action.getLogo());
		company.setName(action.getName());
		company.setWebsite(action.getWebsite());
		company.setEvents(new LinkedList<Event>());
		
		Long companyId = cDao.createCompany(company);
		String invitationCode = null;
		try {
			invitationCode = InvitationKeyManager.generateInvitaion(companyId.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ActionException("error during generating Invitaion: "+e.getLocalizedMessage());
		} 

		result = new RegCompanyResult(invitationCode);
		return result;
	}

	@Override
	public void undo(RegCompany action, RegCompanyResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<RegCompany> getActionType() {
		return RegCompany.class;
	}
}
