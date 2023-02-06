package business;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import beans.Product;
import beans.User;
import data.DAO;

@Stateless
@LocalBean
@Local(ProductManagerInterface.class)
@Alternative
public class ProductManager implements ProductManagerInterface {
	@Inject
	DAO dao;
	
	public ProductManager() {
		this.dao = new DAO();
	}

	@Override
	public List<Product> getAllProducts() throws RuntimeException, SQLException {
		return this.dao.getAllProducts();
	}

	@Override
	public void addProduct(Product p) throws RuntimeException, SQLException {
		this.dao.addProduct(p);
	}

	@Override
	public void deleteFromInventory(Product p) throws RuntimeException, SQLException {
		this.dao.deleteFromInventory(p);		
	}

	@Override
	public void addToCart(Product p, User u) throws RuntimeException, SQLException {
		this.dao.addToCart(p, u);
	}

	@Override
	public List<Product> getUserCart(User u) throws RuntimeException, SQLException {
		return this.dao.getUserCart(u);
	}
	
	

	@Override
	public float calculateCartTotal(User u) {
		float total = 0.0f;
		List<Product> products = this.dao.getUserCart(u);
		for (Product p : products) {
			total += p.getPrice();
		}
		total = (float)(total + (total*.1));
		return total;
	}

	@Override
	public float calculateTax(User u) {
		float total = 0.0f;
		List<Product> products = this.dao.getUserCart(u);
		for (Product p : products) {
			total += p.getPrice();
		}
		total = total/10;
		return total;
	}
	
	@Override
	public void submitOrder(User u) {
		this.dao.submitOrder(u);
	}

	@Override
	public List<Product> getUserOrders(User u) {
		return this.dao.getUserOrders(u);
	}

	@Override
	public void deleteFromCart(Product p) throws RuntimeException, SQLException {
		this.dao.deleteFromCart(p);
	}

	@Override
	public void updateProduct(Product p) throws RuntimeException, SQLException {
		this.dao.updateProduct(p);
	}
	
	
	
}