package ic.doc.cpp.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

import ic.doc.cpp.client.errorpage.ErrorPagePresenter;
import ic.doc.cpp.client.errorpage.ErrorPageView;
import ic.doc.cpp.client.login.SignInPagePresenter;
import ic.doc.cpp.client.login.SignInPageView;
import ic.doc.cpp.client.place.ClientPlaceManager;
import ic.doc.cpp.client.place.DefaultPlace;
import ic.doc.cpp.client.place.ErrorPlace;
import ic.doc.cpp.client.place.NameTokens;
import ic.doc.cpp.client.student.core.CompanyCategoryWidgetPresenter;
import ic.doc.cpp.client.student.core.CompanyCategoryWidgetView;
import ic.doc.cpp.client.student.core.StudentPagePresenter;
import ic.doc.cpp.client.student.core.StudentPageView;
import ic.doc.cpp.client.student.core.automatch.AutoMatchPresenter;
import ic.doc.cpp.client.student.core.automatch.AutoMatchView;
import ic.doc.cpp.client.student.core.calendar.CalendarPresenter;
import ic.doc.cpp.client.student.core.calendar.CalendarView;
import ic.doc.cpp.client.student.core.companydata.CompanyDataPresenter;
import ic.doc.cpp.client.student.core.companydata.CompanyDataView;
import ic.doc.cpp.client.student.core.companydata.CompanyDetailTabSetWidgetPresenter;
import ic.doc.cpp.client.student.core.companydata.CompanyDetailTabSetWidgetView;
import ic.doc.cpp.client.student.core.companydata.CompanySearchFormWidgetPresenter;
import ic.doc.cpp.client.student.core.companydata.CompanySearchFormWidgetView;
import ic.doc.cpp.client.student.core.companydata.CompanyTileGridWidgetPresenter;
import ic.doc.cpp.client.student.core.companydata.CompanyTileGridWidgetView;
import ic.doc.cpp.client.student.core.eventdata.EventDataPresenter;
import ic.doc.cpp.client.student.core.eventdata.EventDataView;
import ic.doc.cpp.client.student.core.eventdata.EventListGridWidgetPresenter;
import ic.doc.cpp.client.student.core.eventdata.EventListGridWidgetView;
import ic.doc.cpp.client.student.core.eventdata.EventSearchFormWidgetPresenter;
import ic.doc.cpp.client.student.core.eventdata.EventSearchFormWidgetView;
import ic.doc.cpp.client.student.core.eventdata.EvetnDetailTabsetWidgetPresenter;
import ic.doc.cpp.client.student.core.eventdata.EvetnDetailTabsetWidgetView;
import ic.doc.cpp.client.student.core.newsfeed.EventsFeedPresenter;
import ic.doc.cpp.client.student.core.newsfeed.EventsFeedView;
import ic.doc.cpp.client.student.core.newsfeed.EventsPanelWidgetPresenter;
import ic.doc.cpp.client.student.core.newsfeed.EventsPanelWidgetView;
import ic.doc.cpp.client.student.core.newsfeed.MainfestWidgetPresenter;
import ic.doc.cpp.client.student.core.newsfeed.MainfestWidgetView;
import ic.doc.cpp.client.student.core.profile.FileUploadPopupWidgetPresenter;
import ic.doc.cpp.client.student.core.profile.FileUploadPopupWidgetView;
import ic.doc.cpp.shared.SharedTokens;
import ic.doc.cpp.client.company.core.CompanyPagePresenter;
import ic.doc.cpp.client.company.core.CompanyPageView;
import ic.doc.cpp.client.company.core.postingevent.PostingEventPresenter;
import ic.doc.cpp.client.company.core.postingevent.PostingEventView;
import ic.doc.cpp.client.student.core.profile.StudentProfilePresenter;
import ic.doc.cpp.client.student.core.profile.StudentProfileView;
import ic.doc.cpp.client.student.core.profile.StudentGeneralInfoPresenter;
import ic.doc.cpp.client.student.core.profile.StudentGeneralInfoView;
import ic.doc.cpp.client.student.core.profile.StudentInterestedAreaPresenter;
import ic.doc.cpp.client.student.core.profile.StudentInterestedAreaView;
import ic.doc.cpp.client.student.core.profile.StudentInterestedCompanyPresenter;
import ic.doc.cpp.client.student.core.profile.StudentInterestedCompanyView;
import ic.doc.cpp.client.student.core.profile.StudentProfileSecurityPresenter;
import ic.doc.cpp.client.student.core.profile.StudentProfileSecurityView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		// Protect against XSRF attacks - securityCookieName = "JSESSIONID";
	    bindConstant().annotatedWith(SecurityCookie.class).to(SharedTokens.securityCookieName);
	    
	    bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
	    bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
	    bind(RootPresenter.class).asEagerSingleton();
	    bind(PlaceManager.class).to(ClientPlaceManager.class).in(Singleton.class);
		
	    // set default page
	    bindConstant().annotatedWith(DefaultPlace.class).to(
	    		NameTokens.signin);
	    
	    // set error page
	    bindConstant().annotatedWith(ErrorPlace.class).to(
	    		NameTokens.errorpage);
	    

	    bindPresenter(StudentPagePresenter.class,
				StudentPagePresenter.MyView.class, StudentPageView.class,
				StudentPagePresenter.MyProxy.class);


		bindPresenter(CalendarPresenter.class, CalendarPresenter.MyView.class,
				CalendarView.class, CalendarPresenter.MyProxy.class);

		bindPresenter(CompanyDataPresenter.class,
				CompanyDataPresenter.MyView.class, CompanyDataView.class,
				CompanyDataPresenter.MyProxy.class);


		bindPresenter(EventDataPresenter.class,
				EventDataPresenter.MyView.class, EventDataView.class,
				EventDataPresenter.MyProxy.class);

		bindPresenter(SignInPagePresenter.class,
				SignInPagePresenter.MyView.class, SignInPageView.class,
				SignInPagePresenter.MyProxy.class);


		bindSingletonPresenterWidget(CompanySearchFormWidgetPresenter.class,
				CompanySearchFormWidgetPresenter.MyView.class,
				CompanySearchFormWidgetView.class);

		bindSingletonPresenterWidget(CompanyTileGridWidgetPresenter.class,
				CompanyTileGridWidgetPresenter.MyView.class,
				CompanyTileGridWidgetView.class);

		bindSingletonPresenterWidget(CompanyDetailTabSetWidgetPresenter.class,
				CompanyDetailTabSetWidgetPresenter.MyView.class,
				CompanyDetailTabSetWidgetView.class);

		bindSingletonPresenterWidget(EventListGridWidgetPresenter.class,
				EventListGridWidgetPresenter.MyView.class,
				EventListGridWidgetView.class);

		bindSingletonPresenterWidget(EventSearchFormWidgetPresenter.class,
				EventSearchFormWidgetPresenter.MyView.class,
				EventSearchFormWidgetView.class);

		bindSingletonPresenterWidget(EvetnDetailTabsetWidgetPresenter.class,
				EvetnDetailTabsetWidgetPresenter.MyView.class,
				EvetnDetailTabsetWidgetView.class);

		bindSingletonPresenterWidget(CompanyCategoryWidgetPresenter.class,
				CompanyCategoryWidgetPresenter.MyView.class,
				CompanyCategoryWidgetView.class);

		bindPresenter(ErrorPagePresenter.class,
				ErrorPagePresenter.MyView.class, ErrorPageView.class,
				ErrorPagePresenter.MyProxy.class);

		bindPresenter(AutoMatchPresenter.class,
				AutoMatchPresenter.MyView.class, AutoMatchView.class,
				AutoMatchPresenter.MyProxy.class);

		bindSingletonPresenterWidget(MainfestWidgetPresenter.class,
				MainfestWidgetPresenter.MyView.class, MainfestWidgetView.class);

		bindSingletonPresenterWidget(EventsPanelWidgetPresenter.class,
				EventsPanelWidgetPresenter.MyView.class,
				EventsPanelWidgetView.class);


		bindPresenter(EventsFeedPresenter.class,
				EventsFeedPresenter.MyView.class, EventsFeedView.class,
				EventsFeedPresenter.MyProxy.class);

		bindSingletonPresenterWidget(FileUploadPopupWidgetPresenter.class,
				FileUploadPopupWidgetPresenter.MyView.class,
				FileUploadPopupWidgetView.class);

		bindPresenter(CompanyPagePresenter.class,
				CompanyPagePresenter.MyView.class, CompanyPageView.class,
				CompanyPagePresenter.MyProxy.class);

		bindPresenter(PostingEventPresenter.class,
				PostingEventPresenter.MyView.class, PostingEventView.class,
				PostingEventPresenter.MyProxy.class);


		bindPresenter(StudentProfilePresenter.class,
				StudentProfilePresenter.MyView.class, StudentProfileView.class,
				StudentProfilePresenter.MyProxy.class);

		bindPresenter(StudentGeneralInfoPresenter.class,
				StudentGeneralInfoPresenter.MyView.class,
				StudentGeneralInfoView.class,
				StudentGeneralInfoPresenter.MyProxy.class);

		bindPresenter(StudentInterestedAreaPresenter.class,
				StudentInterestedAreaPresenter.MyView.class,
				StudentInterestedAreaView.class,
				StudentInterestedAreaPresenter.MyProxy.class);

		bindPresenter(StudentInterestedCompanyPresenter.class,
				StudentInterestedCompanyPresenter.MyView.class,
				StudentInterestedCompanyView.class,
				StudentInterestedCompanyPresenter.MyProxy.class);

		bindPresenter(StudentProfileSecurityPresenter.class,
				StudentProfileSecurityPresenter.MyView.class,
				StudentProfileSecurityView.class,
				StudentProfileSecurityPresenter.MyProxy.class);
	}
}
