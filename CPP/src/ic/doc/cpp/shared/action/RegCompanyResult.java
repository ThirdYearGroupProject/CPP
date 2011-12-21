package ic.doc.cpp.shared.action;

import com.gwtplatform.dispatch.shared.Result;
import java.lang.String;

public class RegCompanyResult implements Result {

	private String result;

	@SuppressWarnings("unused")
	private RegCompanyResult() {
		// For serialization only
	}

	public RegCompanyResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}
}
