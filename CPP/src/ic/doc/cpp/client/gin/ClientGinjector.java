package ic.doc.cpp.client.gin;

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;

import ic.doc.cpp.client.LoggedInGatekeeper;
import ic.doc.cpp.client.errorpage.ErrorPagePresenter;
import ic.doc.cpp.client.gin.ClientModule;
import ic.doc.cpp.client.login.SignInPagePresenter;
import ic.doc.cpp.client.student.core.StudentPagePresenter;
import ic.doc.cpp.client.student.core.automatch.AutoMatchPresenter;
import ic.doc.cpp.client.student.core.calendar.CalendarPresenter;
import ic.doc.cpp.client.student.core.companydata.CompanyDataPresenter;
import ic.doc.cpp.client.student.core.eventdata.EventDataPresenter;
import ic.doc.cpp.client.student.core.newsfeed.EventsFeedPresenter;

import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import ic.doc.cpp.client.company.core.CompanyPagePresenter;
import ic.doc.cpp.client.company.core.postingevent.PostingEventPresenter;
import ic.doc.cpp.client.student.core.profile.StudentProfilePresenter;
import ic.doc.cpp.client.student.core.profile.StudentGeneralInfoPresenter;
import ic.doc.cpp.client.student.core.profile.StudentInterestedAreaPresenter;
import ic.doc.cpp.client.student.core.profile.StudentInterestedCompanyPresenter;
import ic.doc.cpp.client.student.core.profile.StudentProfileSecurityPresenter;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();
	
	AsyncProvider<StudentPagePresenter> getStudentPagePresenter();

	AsyncProvider<CalendarPresenter> getCalendarPresenter();

	AsyncProvider<CompanyDataPresenter> getCompanyDataPresenter();

	AsyncProvider<EventDataPresenter> getEventDataPresenter();

	Provider<SignInPagePresenter> getSignInPagePresenter();
	
	LoggedInGatekeeper getLoggedInGatekeeper();

	AsyncProvider<ErrorPagePresenter> getErrorPagePresenter();

	AsyncProvider<AutoMatchPresenter> getAutoMatchPresenter();

	AsyncProvider<EventsFeedPresenter> getEventsFeedPresenter();

	AsyncProvider<CompanyPagePresenter> getCompanyPagePresenter();

	AsyncProvider<PostingEventPresenter> getPostingEventPresenter();

	AsyncProvider<StudentProfilePresenter> getStudentProfilePresenter();

	AsyncProvider<StudentGeneralInfoPresenter> getStudentGeneralInfoPresenter();

	AsyncProvider<StudentInterestedAreaPresenter> getStudentInterestedAreaPresenter();

	AsyncProvider<StudentInterestedCompanyPresenter> getStudentInterestedCompanyPresenter();

	AsyncProvider<StudentProfileSecurityPresenter> getStudentProfileSecurityPresenter();

}
