package beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Product {
	
	int id = 0;
	String productName = "";
	String description = "";
	float price = 0;
	String date = "";
	int userId = 0;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Product() {
		
	}
	public Product(String productName, String description, float price) {
		this.productName = productName;
		this.description = description;
		this.price = price;
	}
	public Product(int id, String productName, String description, float price) {
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.price = price;
	}

	public Product(int id, String productName, float price) {
		this.id = id;
		this.productName = productName;
		this.price = price;
	}
	public Product(int userId, String productName, float price, String date) {
		this.userId = userId;
		this.productName = productName;
		this.price = price;
		this.date = date;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
}
