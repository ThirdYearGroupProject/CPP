package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.ActionImpl;
import java.lang.Long;
import java.util.Date;

public class RetrieveEventsUsingCategoryId extends
		ActionImpl<RetrieveEventsUsingCategoryIdResult> {

	private Long id;
	private Date upDateTime;

	@SuppressWarnings("unused")
	private RetrieveEventsUsingCategoryId() {
		// For serialization only
	}

	public RetrieveEventsUsingCategoryId(Long id, Date upDateTime) {
		this.id = id;
		this.upDateTime = upDateTime;
	}

	public Date getUpdateTime() {
		return upDateTime;
	}
	
	public Long getId() {
		return id;
	}
}
