/**
 * This class is used to represent the user
 * 
 * @author user1
 */

package pr4;

public class User {
	private String login;
	private String password;
	
	
	public User(String login, String password) {
		setLogin(login);
		setPassword(password);
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
