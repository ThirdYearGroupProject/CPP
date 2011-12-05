package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.ActionImpl;

public class RetrieveEvents extends ActionImpl<RetrieveEventsResult> {

	public RetrieveEvents() {
	}
	
	@Override
	public boolean isSecured() {
		return true;
	}
}
