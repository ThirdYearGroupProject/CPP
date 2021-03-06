package ic.doc.cpp.server.guice;

import ic.doc.cpp.server.student.servlet.FileUploadServlet;
import ic.doc.cpp.shared.SharedTokens;

import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.shared.ActionImpl;
import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.dispatch.server.guice.DispatchServiceImpl;

public class DispatchServletModule extends ServletModule {
	public static final String FILE_UPLOAD_SERVICE_PATH = "/upload";
	
	@Override
	public void configureServlets() {
		
		 // Protect against XSRF attacks - securityCookieName = "JSESSIONID";
	    bindConstant().annotatedWith(SecurityCookie.class).to(SharedTokens.securityCookieName);
		
	    serve("/" + ActionImpl.DEFAULT_SERVICE_NAME)
				.with(DispatchServiceImpl.class);
	    
	    serve(FILE_UPLOAD_SERVICE_PATH).with(FileUploadServlet.class);
	}
}
