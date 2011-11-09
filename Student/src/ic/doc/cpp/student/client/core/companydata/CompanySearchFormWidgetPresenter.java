package ic.doc.cpp.student.client.core.companydata;

import ic.doc.cpp.student.client.core.data.CompanyDetailXmlDS;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class CompanySearchFormWidgetPresenter extends
		PresenterWidget<CompanySearchFormWidgetPresenter.MyView> {

	public interface MyView extends View {
		CheckboxItem getFindInCategory();
		HandlerRegistration addFindListener(ClickHandler handler);
		void setDataSource(DataSource ds);
		String getValueAsString(String string);
		Criteria getValuesAsCriteria();
		
	}

	@Inject
	public CompanySearchFormWidgetPresenter(final EventBus eventBus,
			final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().setDataSource(CompanyDetailXmlDS.getInstance());
	}
}
