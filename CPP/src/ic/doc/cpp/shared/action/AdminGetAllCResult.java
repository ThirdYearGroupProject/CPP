package ic.doc.cpp.shared.action;

import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.shared.CompanyRecord;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class AdminGetAllCResult implements Result {

	private List<Company> ListOfCompany;

	@SuppressWarnings("unused")
	private AdminGetAllCResult() {
		// For serialization only
	}

	public AdminGetAllCResult(List<Company> ListOfCompany) {
		this.ListOfCompany = ListOfCompany;
	}

	public ListGridRecord[] getListOfCompany() {
		ListGridRecord[] gridData = new ListGridRecord[ListOfCompany.size()];
		for(int i = 0; i < ListOfCompany.size() ;i++){
			gridData[i] = new CompanyRecord(ListOfCompany.get(i));
		}
		return gridData;
	}
}
