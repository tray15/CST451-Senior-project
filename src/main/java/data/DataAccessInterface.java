package data;

import java.sql.SQLException;

import beans.User;

public interface DataAccessInterface {
	void register(User u) throws RuntimeException, SQLException;
	
}
