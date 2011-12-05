package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.ActionImpl;
import java.lang.String;

public class RetrieveCompanysUsingNameAndCategory extends
		ActionImpl<RetrieveCompanysUsingNameAndCategoryResult> {

	private String name;
	private String category;

	@SuppressWarnings("unused")
	private RetrieveCompanysUsingNameAndCategory() {
		// For serialization only
	}

	public RetrieveCompanysUsingNameAndCategory(String name, String category) {
		this.name = name;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}
}
