package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 
 * @author tanner ray
 * user class model
 */

@ManagedBean
@SessionScoped
public class User {

	@NotNull()
	@Size(min = 3, max = 50)
	private String username;

	@NotNull()
	@Size(min = 3, max = 50)
	private String password;
	private int userId;
	
	private int admin;
	
	private Boolean loggedIn;

	public User() {
		
	}

	public User(String username, String password, Boolean loggedIn) {
		this.username = username;
		this.password = password;
		this.loggedIn = loggedIn;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}