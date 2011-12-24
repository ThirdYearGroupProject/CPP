package ic.doc.cpp.shared;

import ic.doc.cpp.shared.dto.CompanyDto;

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

	public CompanyRecord(CompanyDto c) {
		setLogo(c.getLogo());
		setWebsite(c.getWebsite());
		setName(c.getName());
	}

}
