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
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			service.deleteFromInventory(p);
			context.addMessage(null, new FacesMessage("The item: " + p.getProductName() + " was deleted."));
		} catch (RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return "products.xhtml";
	}
	public String updateProduct() {
		FacesContext context = FacesContext.getCurrentInstance();
		Product p = context.getApplication().evaluateExpressionGet(context, "#{product}", Product.class);
		System.out.println(p.getId() + p.getProductName());
		
		try {
			service.updateProduct(p);
			context.addMessage(null, new FacesMessage("The item: " + p.getProductName() + " was updated."));
		} catch (RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return "products.xhtml";
	}
	public String showUpdateForm(Product p) {
		System.out.println("Product being edited: " + p.getId() + p.getProductName());
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("product", p);
		
		return "updateproduct.xhtml";
	}
	public String deleteFromCart(Product p) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			service.deleteFromCart(p);
			context.addMessage(null, new FacesMessage("The item: " + p.getProductName() + " was removed from your cart."));

		} catch (RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return "cart.xhtml";
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
			context.addMessage(null, new FacesMessage("Order submitted!"));
		}  catch (RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return "orders.xhtml";
	}
	public String calculateCartTotal() {
		FacesContext context = FacesContext.getCurrentInstance();
		User u = context.getApplication().evaluateExpressionGet(context, "#{User}", User.class);
		
		return service.calculateCartTotal(u);
	}
	public String calculateTax() {
		FacesContext context = FacesContext.getCurrentInstance();
		User u = context.getApplication().evaluateExpressionGet(context, "#{User}", User.class);
		
		return service.calculateTax(u);
	}
}