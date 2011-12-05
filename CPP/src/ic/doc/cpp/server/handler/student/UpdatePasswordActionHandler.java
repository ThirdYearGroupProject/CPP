package ic.doc.cpp.server.handler.student;

import ic.doc.cpp.server.dao.StudentUserDao;
import ic.doc.cpp.server.domain.StudentUser;
import ic.doc.cpp.server.util.Security;
import ic.doc.cpp.shared.action.student.UpdatePassword;
import ic.doc.cpp.shared.action.student.UpdatePasswordResult;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class UpdatePasswordActionHandler implements
		ActionHandler<UpdatePassword, UpdatePasswordResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public UpdatePasswordActionHandler(Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public UpdatePasswordResult execute(UpdatePassword action,
			ExecutionContext context) throws ActionException {
		UpdatePasswordResult result = null;
		try {
			HttpSession session = provider.get().getSession();
			String login = session.getAttribute("login.authenticated").toString();
			StudentUserDao studentUserDao = new StudentUserDao();
			StudentUser student = studentUserDao.retrieveUser(login);
			String hash = Security.sha256(student.getSalt() + action.getOriginalPassword());

			if (!hash.equals(student.getPassword())) {
				throw new ActionException("Invalid user name or password.");
			}
			
			// Generate new password & salt pair
			String newSalt = Security.randomCharString();
			String newHash = Security.sha256(newSalt + action.getNewPassword());
			student.setPassword(newHash);
			student.setSalt(newSalt);
			studentUserDao.updateUser(student);
			
			result = new UpdatePasswordResult();
			
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	@Override
	public void undo(UpdatePassword action, UpdatePasswordResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<UpdatePassword> getActionType() {
		return UpdatePassword.class;
	}
}
