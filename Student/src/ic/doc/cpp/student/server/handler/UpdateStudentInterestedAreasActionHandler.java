package ic.doc.cpp.student.server.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;

import ic.doc.cpp.student.server.dao.CompanyCategoryDao;
import ic.doc.cpp.student.server.dao.StudentUserDao;
import ic.doc.cpp.student.server.domain.CompanyCategory;
import ic.doc.cpp.student.server.domain.StudentUser;
import ic.doc.cpp.student.server.util.GetEntityThroughDao;
import ic.doc.cpp.student.shared.action.UpdateStudentInterestedAreas;
import ic.doc.cpp.student.shared.action.UpdateStudentInterestedAreasResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class UpdateStudentInterestedAreasActionHandler
		implements
		ActionHandler<UpdateStudentInterestedAreas, UpdateStudentInterestedAreasResult> {

	private final Provider<HttpServletRequest> provider;
	
	@Inject
	public UpdateStudentInterestedAreasActionHandler(
			final Provider<HttpServletRequest> provider) {
		this.provider = provider;
	}

	@Override
	public UpdateStudentInterestedAreasResult execute(
			UpdateStudentInterestedAreas action, ExecutionContext context)
			throws ActionException {
		UpdateStudentInterestedAreasResult result = new UpdateStudentInterestedAreasResult();
		
		try {
			CompanyCategoryDao companyCategoryDao = new CompanyCategoryDao();
			List<Long> newInterestedAreaIds = action.getCompanyCategoryId();
			List<CompanyCategory> newInterestedAreas = new ArrayList<CompanyCategory>();
			for (Long id : newInterestedAreaIds) {
				CompanyCategory category = companyCategoryDao.retrieveCompanyCategory(id);
				if (category != null) {
					newInterestedAreas.add(category);
				}
			}
			
			StudentUser student = GetEntityThroughDao.getStudentUser(provider);
			student.setInterestedArea(newInterestedAreas);
			StudentUserDao studentUserDao = new StudentUserDao();
			studentUserDao.updateUser(student);
			
		} catch (Exception e) {
			throw new ActionException(e);
		}
		
		return result;
	}

	@Override
	public void undo(UpdateStudentInterestedAreas action,
			UpdateStudentInterestedAreasResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<UpdateStudentInterestedAreas> getActionType() {
		return UpdateStudentInterestedAreas.class;
	}
}
