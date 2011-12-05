package ic.doc.cpp.client.login;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.inject.Inject;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SignInPageView extends ViewImpl implements
		SignInPagePresenter.MyView {

	 private static String html = "<div>\n"
			    + "<table align=\"center\">\n"
			    + "  <tr>\n" + "<td> </td>\n" + "<td> </td>\n" + "</tr>\n"
			    + "  <tr>\n" + "<td> </td>\n" + "<td> </td>\n" + "</tr>\n"
			    + "  <tr>\n" + "<td> </td>\n" + "<td> </td>\n" + "</tr>\n"
			    + "  <tr>\n"    
			    + "    <td colspan=\"2\" style=\"font-weight:bold;\">Student Sign In</td>\n"
			    + "  </tr>\n"
			    + "  <tr>\n"
			    + "    <td>User name</td>\n"
			    + "    <td id=\"userNameFieldContainer\"></td>\n"    
			    + "  </tr>\n" 
			    + "  <tr>\n"
			    + "    <td>Password</td>\n"
			    + "    <td id=\"passwordFieldContainer\"></td>\n"    
			    + "  </tr>\n" 
			    + "  <tr>\n"
			    + "    <td></td>\n"
			    + "    <td id=\"signInButtonContainer\"></td>\n"  
			    + "  </tr>\n"     
			    + "  <tr>\n" + "<td> </td>\n" + "<td> </td>\n" + "</tr>\n"
			    + "  <tr>\n"
			    + "    <td colspan=\"2\">Forget your password?</td>\n"
			    + "  </tr>\n"
			    + "  <tr>\n"
			    + "    <td colspan=\"2\">Contact your CPP Administrator.</td>\n"
			    + "  </tr>\n"    
			    + "</table>\n"
			    + "<table align=\"center\">\n"
			    + "  <tr>\n" + "<td> </td>\n" + "<td> </td>\n" + "</tr>\n"
			    + "  <tr>\n" + "<td> </td>\n" + "<td> </td>\n" + "</tr>\n"
			    + "  <tr>\n" + "<td> </td>\n" + "<td> </td>\n" + "</tr>\n"
			    + "  <tr>\n"    
			    + "    <td colspan=\"2\" style=\"font-weight:bold;\">Company User Sign In</td>\n"
			    + "  </tr>\n"
			    + "  <tr>\n"
			    + "    <td>User name</td>\n"
			    + "    <td id=\"companyUserNameFieldContainer\"></td>\n"    
			    + "  </tr>\n" 
			    + "  <tr>\n"
			    + "    <td>Password</td>\n"
			    + "    <td id=\"companyUserPasswordFieldContainer\"></td>\n"    
			    + "  </tr>\n" 
			    + "  <tr>\n"
			    + "    <td></td>\n"
			    + "    <td id=\"companyUserSignInButtonContainer\"></td>\n"  
			    + "  </tr>\n"     
			    + "  <tr>\n" + "<td> </td>\n" + "<td> </td>\n" + "</tr>\n"
			    + "  <tr>\n"
			    + "    <td colspan=\"2\">Forget your password?</td>\n"
			    + "  </tr>\n"
			    + "  <tr>\n"
			    + "    <td colspan=\"2\">Contact your CPP Administrator.</td>\n"
			    + "  </tr>\n"    
			    + "</table>\n"
			    + "</div>\n";

	 HTMLPanel panel = new HTMLPanel(html);

	 private final TextBox userNameField, companyUserNameField;
	 private final PasswordTextBox passwordField, companyUserPasswordField;
	 private final Button signInButton, companyUserSignInButton;

	 @Inject
	 public SignInPageView() {
		 userNameField = new TextBox();
		 passwordField = new PasswordTextBox();
		 signInButton = new Button("Sign in");

		 userNameField.setText("DuZhouzhou");
		 passwordField.setText("N0More$ecret");
		 
		 companyUserNameField = new TextBox();
		 companyUserPasswordField = new PasswordTextBox();
		 companyUserSignInButton = new Button("Sign in");
		 
		 companyUserNameField.setText("TestCompany");
		 companyUserPasswordField.setText("N0More$ecret");

		 panel.add(userNameField, "userNameFieldContainer");
		 panel.add(passwordField, "passwordFieldContainer");
		 panel.add(signInButton, "signInButtonContainer");
		 
		 panel.add(companyUserNameField, "companyUserNameFieldContainer");
		 panel.add(companyUserPasswordField, "companyUserPasswordFieldContainer");
		 panel.add(companyUserSignInButton, "companyUserSignInButtonContainer");
	 }

	 @Override
	 public Widget asWidget() {
		 return panel;
	 }

	 @Override
	 public String getUserName() {
		 return userNameField.getText();
	 }
  
	 @Override
	 public String getPassword() {
		 return passwordField.getText();
	 }  

	 
	 @Override
	 public String getCompanyUserName() {
		 return companyUserNameField.getText();
	 }
	 @Override
	 public String getCompanyUserPassword() {
		 return companyUserPasswordField.getText();
	 }

	@Override
	 public void resetAndFocus() {
		 userNameField.setFocus(true);
		 userNameField.selectAll();
	 }
	
	@Override
	 public void resetAndFocusCompanyUser() {
		 companyUserNameField.setFocus(true);
		 companyUserNameField.selectAll();
	 }


	@Override
	public HandlerRegistration addSignInButtonClickHandler(
			ClickHandler clickHandler) {
		return signInButton.addClickHandler(clickHandler);
	}
	
	@Override
	public HandlerRegistration addCompanyUserSignInButtonClickHandler(
			ClickHandler clickHandler) {
		return companyUserSignInButton.addClickHandler(clickHandler);
	}

	@Override
	public String getUserName(String type) {
		if (type.equals("student"))
			return getUserName();
		else if (type.equals("company"))
			return getCompanyUserName();
		return null;
	}

	@Override
	public String getPassword(String type) {
		if (type.equals("student"))
			return getPassword();
		else if (type.equals("company"))
			return getCompanyUserPassword();
		return null;
	}
	 
}
