package ic.doc.cpp.client.student.core;

import com.smartgwt.client.widgets.layout.SectionStackSection;

public class CategorySectionStackSection extends SectionStackSection {
	protected String contextAreaName;
	
	public CategorySectionStackSection(String sectionTitle, String contextAreaName) {
		super(sectionTitle);
		this.contextAreaName = contextAreaName;
	}

	public String getContextAreaName() {
		return contextAreaName;
	}
	
	
}
