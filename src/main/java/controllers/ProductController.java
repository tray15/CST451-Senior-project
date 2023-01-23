package controllers;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Product;
import beans.User;
import business.ProductManagerInterface;

/**
 * 
 * @author tanner ray
 * sensor controller current use is just business layer injection
 */

@ManagedBean
@ViewScoped
public class ProductController {
	@Inject
	ProductManagerInterface service;
	
	public ProductManagerInterface getService() {
		return service;
	}
	public String getAllProducts() {
		try {
			service.getAllProducts();
		} catch (RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return "products.xhtml";
	}
	
	public String addProduct(Product p) {
		try {
			service.addProduct(p);
		} catch (RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return "products.xhtml";
	}
	public String addToCart(Product p) {
		FacesContext context = FacesContext.getCurrentInstance();
		User u = context.getApplication().evaluateExpressionGet(context, "#{User}", User.class);
		
		try {
			service.addToCart(p, u);
			context.addMessage(null, new FacesMessage("You added an item to the cart!"));
		}  catch (RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return "products.xhtml";
	}
	public String deleteFromInventory(Product p) {
		try {
			service.deleteFromInventory(p);
		} catch (RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return "products.xhtml";
	}
	public String deleteFromCart(Product p) {
		try {
			service.deleteFromCart(p);
		} catch (RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return "products.xhtml";
	}
	public String getUserCart() {
		FacesContext context = FacesContext.getCurrentInstance();
		User u = context.getApplication().evaluateExpressionGet(context, "#{User}", User.class);

		try {
			service.getUserCart(u);
		}  catch (RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return "cart.xhtml";
		
	}
	public String getUserOrders() {
		FacesContext context = FacesContext.getCurrentInstance();
		User u = context.getApplication().evaluateExpressionGet(context, "#{User}", User.class);

		try {
			service.getUserOrders(u);
		}  catch (RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return "cart.xhtml";
		
	}
	public String submitOrder() {
		FacesContext context = FacesContext.getCurrentInstance();
		User u = context.getApplication().evaluateExpressionGet(context, "#{User}", User.class);

		try {
			service.submitOrder(u);
		}  catch (RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return "orders.xhtml";
	}
	public float calculateCartTotal() {
		FacesContext context = FacesContext.getCurrentInstance();
		User u = context.getApplication().evaluateExpressionGet(context, "#{User}", User.class);
		
		return service.calculateCartTotal(u);
	}
	public float calculateTax() {
		FacesContext context = FacesContext.getCurrentInstance();
		User u = context.getApplication().evaluateExpressionGet(context, "#{User}", User.class);
		
		return service.calculateTax(u);
	}
}