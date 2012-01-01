package ic.doc.cpp.server.handler;

import ic.doc.cpp.server.util.InvitationKeyManager;
import ic.doc.cpp.shared.action.GetInvitation;
import ic.doc.cpp.shared.action.GetInvitationResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetInvitationActionHandler implements
		ActionHandler<GetInvitation, GetInvitationResult> {

	@Inject
	public GetInvitationActionHandler() {
	}

	@Override
	public GetInvitationResult execute(GetInvitation action,
			ExecutionContext context) throws ActionException {
		
		Long companyId = action.getCompanyId();
		String code;
		try {
			code = InvitationKeyManager.generateInvitaion(companyId.toString());
		} catch (Exception e) {
			throw new ActionException("generateInvitaion fail: "+e.getLocalizedMessage());
		} 
		return new GetInvitationResult(code);
	}

	@Override
	public void undo(GetInvitation action, GetInvitationResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<GetInvitation> getActionType() {
		return GetInvitation.class;
	}
}
