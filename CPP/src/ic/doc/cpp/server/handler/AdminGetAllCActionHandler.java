package ic.doc.cpp.server.handler;


import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.server.dao.CompanyDao;
import ic.doc.cpp.shared.action.AdminGetAllC;
import ic.doc.cpp.shared.action.AdminGetAllCResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class AdminGetAllCActionHandler implements
		ActionHandler<AdminGetAllC, AdminGetAllCResult> {

	@Inject
	public AdminGetAllCActionHandler() {
	}

	@Override
	public AdminGetAllCResult execute(AdminGetAllC action, ExecutionContext context)
			throws ActionException {
		AdminGetAllCResult result;
		CompanyDao allC = new CompanyDao();
		result = new AdminGetAllCResult( allC.retrieveCompanys() );
		
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
