package ic.doc.cpp.client.student.core.profile;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import ic.doc.cpp.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import ic.doc.cpp.client.student.core.profile.StudentProfilePresenter;
import ic.doc.cpp.shared.action.student.RetrieveStudentUserInformation;
import ic.doc.cpp.shared.action.student.RetrieveStudentUserInformationResult;
import ic.doc.cpp.shared.action.student.UpdateStudentUserInformation;
import ic.doc.cpp.shared.action.student.UpdateStudentUserInformationResult;
import ic.doc.cpp.shared.dto.StudentUserDto;

public class StudentGeneralInfoPresenter
		extends
		Presenter<StudentGeneralInfoPresenter.MyView, StudentGeneralInfoPresenter.MyProxy> {

	private final DispatchAsync dispatcher;
	private final FileUploadPopupWidgetPresenter fileUploadPresenter;
	
	public interface MyView extends View {

		void setData(StudentUserDto studentUserDto);

		HandlerRegistration addUpdateButtonClickHandler(
				ClickHandler clickHandler);

		boolean validateBasicInformationForm();

		StudentUserDto getBasicFormValueDto();

		HandlerRegistration addUploadCVLinkItemClickHandler(
				com.smartgwt.client.widgets.form.fields.events.ClickHandler clickHandler);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.studentprofilegeneralinfo)
	public interface MyProxy extends ProxyPlace<StudentGeneralInfoPresenter> {
	}

	@Inject
	public StudentGeneralInfoPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher,
			final FileUploadPopupWidgetPresenter fileUploadPresenter) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
		this.fileUploadPresenter = fileUploadPresenter;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this,
				StudentProfilePresenter.TYPE_SetStudentProfileContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		registerHandler(getView().addUpdateButtonClickHandler(
				new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (getView().validateBasicInformationForm()) {
					StudentUserDto studentDto = getView().getBasicFormValueDto();
					dispatcher.execute(new UpdateStudentUserInformation(studentDto), new AsyncCallback<UpdateStudentUserInformationResult>() {

						@Override
						public void onFailure(Throwable caught) {
							GWT.log("Fail on updateBasicStudentInformation() - " + caught.getLocalizedMessage());
							SC.say("Error", "Fail on Updating Basic Information");
						}

						@Override
						public void onSuccess(
								UpdateStudentUserInformationResult result) {
							SC.say("Update Basic Information", "Update Successful");
						}
					});
				}
			}
		}));
		
		registerHandler(getView().addUploadCVLinkItemClickHandler(
				new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			
			@Override
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				addToPopupSlot(fileUploadPresenter);
			}
		}));
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		retrieveStudentInformation();
	}
	
	private void retrieveStudentInformation() {
		dispatcher.execute(new RetrieveStudentUserInformation(), new AsyncCallback<RetrieveStudentUserInformationResult>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Fail on retrieveStudentInformation() - " + caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(RetrieveStudentUserInformationResult result) {
				getView().setData(result.getStudentUserDto());
			}
		});
	}
}
