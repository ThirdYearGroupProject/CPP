package ic.doc.cpp.server.guice;

import com.gwtplatform.dispatch.server.guice.HandlerModule;

import ic.doc.cpp.server.handler.student.AddStudentDislikeEventActionHandler;
import ic.doc.cpp.server.handler.student.AddStudentInterestedCompanyActionHandler;
import ic.doc.cpp.server.handler.student.AddStudentInterestedEventActionHandler;
import ic.doc.cpp.server.handler.student.LoginActionHandler;
import ic.doc.cpp.server.handler.student.RemoveStudentInterestedCompanyActionHandler;
import ic.doc.cpp.server.handler.student.RetrieveCompanyCategoryActionHandler;
import ic.doc.cpp.server.handler.student.RetrieveCompanyEventsActionHandler;
import ic.doc.cpp.server.handler.student.RetrieveCompanysActionHandler;
import ic.doc.cpp.server.handler.student.RetrieveCompanysUsingNameAndCategoryActionHandler;
import ic.doc.cpp.server.handler.student.RetrieveEventsActionHandler;
import ic.doc.cpp.server.handler.student.RetrieveEventsUsingCategoryIdActionHandler;
import ic.doc.cpp.server.handler.student.RetrieveStudentInterestedAreasActionHandler;
import ic.doc.cpp.server.handler.student.RetrieveStudentInterestedCompaniesActionHandler;
import ic.doc.cpp.server.handler.student.RetrieveStudentInterestedEventsActionHandler;
import ic.doc.cpp.server.handler.student.RetrieveStudentUserInformationActionHandler;
import ic.doc.cpp.server.handler.student.UpdatePasswordActionHandler;
import ic.doc.cpp.server.handler.student.UpdateStudentInterestedAreasActionHandler;
import ic.doc.cpp.server.handler.student.UpdateStudentUserInformationActionHandler;
import ic.doc.cpp.server.handler.validator.LoggedInActionValidator;
import ic.doc.cpp.shared.action.student.AddStudentDislikeEvent;
import ic.doc.cpp.shared.action.student.AddStudentInterestedCompany;
import ic.doc.cpp.shared.action.student.AddStudentInterestedEvent;
import ic.doc.cpp.shared.action.student.GetStudentLogin;
import ic.doc.cpp.shared.action.student.Login;
import ic.doc.cpp.shared.action.student.RemoveStudentInterestedCompany;
import ic.doc.cpp.shared.action.student.RetrieveCompanyCategory;
import ic.doc.cpp.shared.action.student.RetrieveCompanyEvents;
import ic.doc.cpp.shared.action.student.RetrieveCompanys;
import ic.doc.cpp.shared.action.student.RetrieveCompanysUsingNameAndCategory;
import ic.doc.cpp.shared.action.student.RetrieveEvents;
import ic.doc.cpp.shared.action.student.RetrieveEventsUsingCategoryId;
import ic.doc.cpp.shared.action.student.RetrieveStudentInterestedAreas;
import ic.doc.cpp.shared.action.student.RetrieveStudentInterestedCompanies;
import ic.doc.cpp.shared.action.student.RetrieveStudentInterestedEvents;
import ic.doc.cpp.shared.action.student.RetrieveStudentUserInformation;
import ic.doc.cpp.shared.action.student.UpdatePassword;
import ic.doc.cpp.shared.action.student.UpdateStudentInterestedAreas;
import ic.doc.cpp.shared.action.student.UpdateStudentUserInformation;
import ic.doc.cpp.shared.action.AdminGetAllC;
import ic.doc.cpp.shared.action.GetInvitation;
import ic.doc.cpp.shared.action.PostEvent;
import ic.doc.cpp.shared.action.RegCompany;
import ic.doc.cpp.shared.action.Registration;
import ic.doc.cpp.shared.action.RetrieveEventCategories;
import ic.doc.cpp.server.handler.RegistrationActionHandler;
import ic.doc.cpp.server.handler.RetrieveEventCategoriesActionHandler;
import ic.doc.cpp.server.handler.PostEventActionHandler;
import ic.doc.cpp.server.handler.student.GetStudentLoginActionHandler;
import ic.doc.cpp.server.handler.AdminGetAllCActionHandler;
import ic.doc.cpp.server.handler.RegCompanyActionHandler;
import ic.doc.cpp.server.handler.GetInvitationActionHandler;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(Login.class, LoginActionHandler.class);
		
		bindHandler(RetrieveCompanyCategory.class, 
				RetrieveCompanyCategoryActionHandler.class, 
				LoggedInActionValidator.class);

		bindHandler(RetrieveStudentUserInformation.class,
				RetrieveStudentUserInformationActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveEvents.class, RetrieveEventsActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(UpdateStudentUserInformation.class,
				UpdateStudentUserInformationActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(UpdatePassword.class, UpdatePasswordActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveStudentInterestedCompanies.class,
				RetrieveStudentInterestedCompaniesActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RemoveStudentInterestedCompany.class,
				RemoveStudentInterestedCompanyActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveCompanys.class,
				RetrieveCompanysActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveCompanyEvents.class,
				RetrieveCompanyEventsActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(AddStudentInterestedCompany.class,
				AddStudentInterestedCompanyActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveCompanysUsingNameAndCategory.class,
				RetrieveCompanysUsingNameAndCategoryActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveEventsUsingCategoryId.class,
				RetrieveEventsUsingCategoryIdActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(AddStudentInterestedEvent.class,
				AddStudentInterestedEventActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveStudentInterestedEvents.class,
				RetrieveStudentInterestedEventsActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(AddStudentDislikeEvent.class,
				AddStudentDislikeEventActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveStudentInterestedAreas.class,
				RetrieveStudentInterestedAreasActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(UpdateStudentInterestedAreas.class,
				UpdateStudentInterestedAreasActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RetrieveEventCategories.class,
				RetrieveEventCategoriesActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(PostEvent.class, PostEventActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(GetStudentLogin.class, GetStudentLoginActionHandler.class,
				LoggedInActionValidator.class);
		
		bindHandler(Registration.class, RegistrationActionHandler.class);

		bindHandler(AdminGetAllC.class, AdminGetAllCActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(RegCompany.class, RegCompanyActionHandler.class,
				LoggedInActionValidator.class);

		bindHandler(GetInvitation.class, GetInvitationActionHandler.class,
				LoggedInActionValidator.class);
	}
}
