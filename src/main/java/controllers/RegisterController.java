package controllers;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import business.UserManager;

/**
 * 
 * @author tanner ray
 * handles user registration form
 */

@ManagedBean
@SessionScoped
public class RegisterController {
	@Inject
	UserManager um;
	
	public String register() {
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		
		if (user != null) {
			try {
				if (this.um.userExists(user)) {
					context.addMessage(null, new FacesMessage("A user with these credentials already exists."));
					context.getExternalContext().getSessionMap().clear();
					return "";
				}
				this.um.register(user);
			} catch (RuntimeException | SQLException e) {
				context.addMessage(null, new FacesMessage("Unable to connect to the database. Try again later."));
				context.getExternalContext().getSessionMap().clear();
				return "";
			}
		}
		return "login.xhtml";
	}
}