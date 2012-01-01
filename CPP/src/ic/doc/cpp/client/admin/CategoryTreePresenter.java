package ic.doc.cpp.client.admin;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;

public class CategoryTreePresenter extends
		PresenterWidget<CategoryTreePresenter.MyView> {

	public interface MyView extends View {
	}

	@Inject
	public CategoryTreePresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}
}
