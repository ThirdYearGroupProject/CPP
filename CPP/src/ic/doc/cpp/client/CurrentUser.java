package ic.doc.cpp.client;


public class CurrentUser {
	
	private String login;
	  
	private boolean loggedIn = true;
  
	public CurrentUser(String login) {
		this.login = login;
	}
	  
	public String getLogin() {
		return login;    
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
