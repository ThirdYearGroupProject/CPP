package ic.doc.cpp.shared;

import ic.doc.cpp.server.domain.Company;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class CompanyRecord extends ListGridRecord {

	public String getLogo() {
		return getAttributeAsString("logo");
	}

	public void setLogo(String logo) {
		setAttribute("logo", logo);
	}

	public String getName() {
		return getAttributeAsString("name");
	}

	public void setName(String name) {
		setAttribute("name", name);
	}

	public String getWebsite() {
		return getAttributeAsString("website");
	}

	public void setWebsite(String website) {
		setAttribute("website", website);
	}

	public CompanyRecord(Company c) {
		setLogo(c.getLogo());
		setWebsite(c.getWebsite());
		setName(c.getName());
	}

}
