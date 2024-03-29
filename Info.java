/**
 * This class is used by the application 
 * to exchange information with the server
 * 
 * @author user1
 */

package pr4;

public class Info {
	/**
	 * 
	 */
	private String login;
	private String password;
	private boolean result;
	private String message;
	private Object resultObject;

	
	public Info(String login, String password, String message, Object resultObject) {
		setLogin(login);
		setPassword(password);
		setMessage(message);
		setResultObject(resultObject);
	}
	
	public Info(String login, String password, String message) {
		setLogin(login);
		setPassword(password);
		setMessage(message);
	}

	public Info(boolean result) {
		setResult(result);
	}

	public Info(boolean result, Object resultObject) {
		setResult(result);
		setResultObject(resultObject);
	}

	public Info(boolean result, String message) {
		setResult(result);
		setMessage(message);
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

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResultObject() {
		return resultObject;
	}

	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}

}
