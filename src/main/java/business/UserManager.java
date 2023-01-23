package business;

import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import beans.User;
import data.DAO;

/**
 * 
 * @author tanner ray
 * handles user data logic
 */

@Stateless
@LocalBean
public class UserManager {
	@Inject
	DAO dao;
	
	public void register(User user) throws RuntimeException, SQLException {
		try {
			dao.register(user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public User findUser(User user) throws RuntimeException, SQLException {
		try {
			return dao.getByUsername(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Boolean userExists(User user) throws RuntimeException, SQLException {
		try {
			if (dao.getByUsername(user) != null) {
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return false;
	}

}