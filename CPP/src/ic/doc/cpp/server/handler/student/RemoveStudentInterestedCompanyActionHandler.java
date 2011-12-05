package ic.doc.cpp.server.handler.student;

import ic.doc.cpp.server.dao.StudentUserDao;
import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.server.domain.StudentUser;
import ic.doc.cpp.shared.action.student.RemoveStudentInterestedCompany;
import ic.doc.cpp.shared.action.student.RemoveStudentInterestedCompanyResult;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class RemoveStudentInterestedCompanyActionHandler
		implements
		ActionHandler<RemoveStudentInterestedCompany, RemoveStudentInterestedCompanyResult> {
	
	private final Provider<HttpServletRequest> requestProvider;
	
	@Inject
	public RemoveStudentInterestedCompanyActionHandler(
			final Provider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
	}

	@Override
	public RemoveStudentInterestedCompanyResult execute(
			RemoveStudentInterestedCompany action, ExecutionContext context)
			throws ActionException {
		RemoveStudentInterestedCompanyResult result = null;
		
		try {
			HttpSession session = requestProvider.get().getSession();
			String login = session.getAttribute("login.authenticated").toString();
			StudentUserDao studentDao = new StudentUserDao();
			StudentUser student = studentDao.retrieveUser(login);
			List<Company> companys = student.getCompanys();
			for (int i = 0; i < companys.size(); i++) {
				if (companys.get(i).getCompanyId().equals(action.getCompanyId())) {
					companys.remove(i);
					break;
				}
			}
			student.setCompanys(companys);
			studentDao.updateUser(student);
			result = new RemoveStudentInterestedCompanyResult();
					
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	@Override
	public void undo(RemoveStudentInterestedCompany action,
			RemoveStudentInterestedCompanyResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<RemoveStudentInterestedCompany> getActionType() {
		return RemoveStudentInterestedCompany.class;
	}
}
