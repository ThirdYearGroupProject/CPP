package ic.doc.cpp.server.handler;

import ic.doc.cpp.server.dao.CompanyDao;
import ic.doc.cpp.server.dao.CompanyUserDao;
import ic.doc.cpp.server.dao.StudentUserDao;
import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.server.domain.CompanyUser;
import ic.doc.cpp.server.domain.StudentUser;
import ic.doc.cpp.server.util.InvitationKeyManager;
import ic.doc.cpp.server.util.Security;
import ic.doc.cpp.shared.action.Registration;
import ic.doc.cpp.shared.action.RegistrationResult;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class RegistrationActionHandler implements
		ActionHandler<Registration, RegistrationResult> {

	private final Provider<HttpServletRequest> requestProvider;

	@Inject
	public RegistrationActionHandler(
			final Provider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
	}

	@Override
	public RegistrationResult execute(Registration action,
			ExecutionContext context) throws ActionException {

		RegistrationResult result = null;
		String regType = action.getUserType();
		String salt = Security.randomCharString();
		Boolean successReg = false;

		if (regType.equals("student")) {
			StudentUserDao userDao = new StudentUserDao();

			// check exist user
			try {
				if (userDao.retrieveUser(action.getUserName()) != null) {
					throw new ActionException("student user already exist");
				}
			} catch (NoResultException e) {
			}

			userDao.createUser(new StudentUser(action.getUserName(), salt,
					action.getPassword(), action.getFirstName(), action
							.getLastName(), action.getEmail(), action
							.getGender()));
			successReg = true;
		} else if (regType.equals("company")) {
			CompanyUserDao companyUserDao = new CompanyUserDao();

			try {
				if (companyUserDao.retrieveUser(action.getUserName()) != null) {
					throw new ActionException("Company user already exist");
				}
			} catch (NoResultException e) {
			}
			
			CompanyDao companyDao = new CompanyDao();
			Company company;
			try {
				String companyCode = InvitationKeyManager.decodeInvitaion(action.getCompany());
				Long companyId = Long.parseLong(companyCode);
				company = companyDao.retrieveCompany(companyId );
				if (company == null) {
					throw new ActionException("Company noty exist");
				}
			} catch (NoResultException e) {
				throw new ActionException("invitation code error: Company not exist");
			} catch (NumberFormatException e){
				throw new ActionException("invitation code error not valid");
			}
			
			
			companyUserDao.createUser(new CompanyUser(action.getUserName(),
					salt, action.getPassword(), action.getFirstName(), action
							.getLastName(), action.getEmail(), action
							.getGender(), company , null ));
			successReg = true;
		} else {
			throw new ActionException("user type invalid");
		}

		if (successReg) {
			HttpSession session = requestProvider.get().getSession();
			session.setAttribute("login.authenticated", action.getUserName());
			result = new RegistrationResult(session.getId());
			return result;
		} else {
			throw new ActionException("registration fail, please contact admin");
		}

	}

	@Override
	public void undo(Registration action, RegistrationResult result,
			ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<Registration> getActionType() {
		return Registration.class;
	}
}
