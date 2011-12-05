package ic.doc.cpp.server.handler.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.shared.action.student.GetStudentLogin;
import ic.doc.cpp.shared.action.student.GetStudentLoginResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetStudentLoginActionHandler implements
		ActionHandler<GetStudentLogin, GetStudentLoginResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public GetStudentLoginActionHandler(final Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public GetStudentLoginResult execute(GetStudentLogin action,
			ExecutionContext context) throws ActionException {
		HttpSession session = provider.get().getSession();
		String login = session.getAttribute("login.authenticated").toString();
		return new GetStudentLoginResult(login);
	}

	@Override
	public void undo(GetStudentLogin action, GetStudentLoginResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<GetStudentLogin> getActionType() {
		return GetStudentLogin.class;
	}
}
