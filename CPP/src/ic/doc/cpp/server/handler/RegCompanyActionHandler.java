package ic.doc.cpp.server.handler;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.shared.action.RegCompany;
import ic.doc.cpp.shared.action.RegCompanyResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RegCompanyActionHandler implements
		ActionHandler<RegCompany, RegCompanyResult> {

	@Inject
	public RegCompanyActionHandler() {
	}

	@Override
	public RegCompanyResult execute(RegCompany action, ExecutionContext context)
			throws ActionException {
		return null;
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
