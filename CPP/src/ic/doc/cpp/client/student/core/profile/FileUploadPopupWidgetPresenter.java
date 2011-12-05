package ic.doc.cpp.client.student.core.profile;

import ic.doc.cpp.shared.action.student.GetStudentLogin;
import ic.doc.cpp.shared.action.student.GetStudentLoginResult;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.PopupView;
import com.google.inject.Inject;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;

public class FileUploadPopupWidgetPresenter extends
		PresenterWidget<FileUploadPopupWidgetPresenter.MyView> {

	private final DispatchAsync dispatcher;
	
	public interface MyView extends PopupView {

		void submmitForm();

		HandlerRegistration addOKButtonClickHandler(ClickHandler handler);

		HandlerRegistration addCancelButtonClickHandler(ClickHandler handler);

		Object getDisplayValue();
	}

	@Inject
	public FileUploadPopupWidgetPresenter(final EventBus eventBus, final MyView view,
			final DispatchAsync dispatcher) {
		super(eventBus, view);
		this.dispatcher = dispatcher;
	}
	
	

	@Override
	protected void onReset() {
		super.onReset();
	}



	@Override
	protected void onBind() {
		super.onBind();
		getView().addOKButtonClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final Object filePath = getView().getDisplayValue();
				if (!filePath.toString().equals("")) {
					dispatcher.execute(new GetStudentLogin(), new AsyncCallback<GetStudentLoginResult>() {

						@Override
						public void onFailure(Throwable caught) {
							GWT.log("Fail on getStudentLogin()...");
						}

						@Override
						public void onSuccess(GetStudentLoginResult result) {
							String validFileName = result.getLogin() + "-cv";
							if (getFileName(filePath.toString()).equals(validFileName)) {
								getView().submmitForm();
							} else {
								SC.say("Invalid File name - rename file to " + validFileName );
							}
							
						}
					});
					
				} else {
					SC.say("Please select a file.");
				}
			}
		});
		
		getView().addCancelButtonClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				getView().hide();
			}
		});
	}
	
	private String getFileName(String path) {
		String fileName = path.substring(path.lastIndexOf("\\") + 1);

		String result = fileName;
		int dotIndex = fileName.lastIndexOf(".");
		if (dotIndex != -1)
			result = fileName.substring(0, dotIndex);
		
		return result;
	}
}
