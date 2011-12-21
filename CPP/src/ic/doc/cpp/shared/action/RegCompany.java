package ic.doc.cpp.shared.action;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

import ic.doc.cpp.shared.action.RegCompanyResult;

import java.lang.String;

public class RegCompany extends UnsecuredActionImpl<RegCompanyResult> {

	private String website;
	private String logo;
	private String name;
	private String category;
	private String description;

	@SuppressWarnings("unused")
	private RegCompany() {
		// For serialization only
	}

	public RegCompany(String website, String logo, String name, String category,
			String description) {
		this.website = website;
		this.logo = logo;
		this.name = name;
		this.category = category;
		this.description = description;
	}

	public String getWebsite() {
		return website;
	}

	public String getLogo() {
		return logo;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}
}
