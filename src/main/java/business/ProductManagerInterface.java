package business;

import java.sql.SQLException;
import java.util.List;

import beans.Product;
import beans.User;

public interface ProductManagerInterface {
	public List<Product> getAllProducts() throws RuntimeException, SQLException;
	public void addProduct(Product p) throws RuntimeException, SQLException;
	public void deleteFromInventory(Product p) throws RuntimeException, SQLException;
	public void addToCart(Product p, User u) throws RuntimeException, SQLException;
	public List<Product> getUserCart(User u) throws RuntimeException, SQLException;
	public String calculateCartTotal(User u);
	public String calculateTax(User u);
	public void submitOrder(User u) throws RuntimeException, SQLException;
	public List<Product> getUserOrders(User u) throws RuntimeException, SQLException;
	public void deleteFromCart(Product p) throws RuntimeException, SQLException;
	public void updateProduct(Product p) throws RuntimeException, SQLException;
}
