package ic.doc.cpp.client.admin;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.user.client.ui.Widget;

public class CategoryTreeView extends ViewImpl implements
		CategoryTreePresenter.MyView {

	@Inject
	public CategoryTreeView() {
	}

	@Override
	public Widget asWidget() {
		return null;
	}
}
