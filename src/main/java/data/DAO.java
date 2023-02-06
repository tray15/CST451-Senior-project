package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Product;
import beans.User;

@Stateless
@Local(DataAccessInterface.class)
@LocalBean
@Alternative
public class DAO implements DataAccessInterface {
	//database connect strings
	private static final String DB_URL = "jdbc:mysql://localhost:3307/cst-451?autoReconnect=true&useSSL=true";
	private static final String DB_USER = "root";
	private static final String PASSWORD = "root";

	//User queries
	private static final String INSERT_USER = "INSERT INTO `user` (username, password) VALUES (?, ?)";
	private static final String FIND_USER = "SELECT * FROM `user` WHERE username=?";

	//Product queries
	private static final String CREATE_ONE = "INSERT INTO `products` (`product_name`, `description`, `price`) VALUES (?, ?, ?)";
	private static final String GET_ALL_PRODUCTS = "SELECT * FROM `products`";
	private static final String ADD_TO_CART = "INSERT INTO `cart` (`user_id`, `product_name`, `product_id`, `price`) VALUES (?, ?, ?, ?)";
	private static final String DELETE_PRODUCT = "DELETE FROM `products` WHERE `product_name`=?";
	private static final String GET_CART = "SELECT * FROM `cart` WHERE `user_id`=?";
	private static final String GET_USER_ORDERS = "SELECT * FROM `orders` WHERE `user_id`=?";
	private static final String DELETE_FROM_CART = "DELETE FROM `cart` WHERE `product_name`=?";
	private static final String UPDATE_PRODUCT = "UPDATE `products` SET `product_name`=?, `description`=?, `price`=? WHERE `product_id`=?";
	
	public void register(User u) throws RuntimeException, SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = getConnection();

			stmt = conn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, u.getUsername());	
			stmt.setString(2, u.getPassword());
			
			stmt.executeUpdate();
			
			
			close(stmt);
			close(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public User getByUsername(User user) throws RuntimeException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(FIND_USER, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, user.getUsername());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User found = new User();
				found.setUserId(rs.getInt("user_id"));
				found.setUsername(rs.getString("username"));
				found.setPassword(rs.getString("password"));
				found.setAdmin(rs.getInt("admin"));
				
				return found;
			}
			close(stmt);
			close(conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public List<Product> getAllProducts() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
			System.out.println("Connection successful!");
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(GET_ALL_PRODUCTS);
			
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				int id = rs.getInt("product_id");
				String orderNumber = rs.getString("product_name");
				String name = rs.getString("description");
				float price = rs.getFloat("price");
				
				products.add(new Product(id, orderNumber, name , price));
			}
			rs.close();
			return products;
			
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addProduct(Product p) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
			System.out.println("Connection successful!");
			
			PreparedStatement stmt = conn.prepareStatement(CREATE_ONE, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, p.getProductName());
			stmt.setString(2, p.getDescription());
			
			stmt.executeUpdate();
			System.out.println("Executing query for create... ");
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Connection getConnection() throws RuntimeException, SQLException {
		try {
			return DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void close(Statement stmt) throws RuntimeException, SQLException {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void deleteFromInventory(Product p) throws RuntimeException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		conn = getConnection();
		stmt = conn.prepareStatement(DELETE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, p.getProductName());

		stmt.executeUpdate();

		close(stmt);
		close(conn);
	}

	public void addToCart(Product p, User u) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
			System.out.println("Connection successful!");
			
			PreparedStatement stmt = conn.prepareStatement(ADD_TO_CART, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, u.getUserId());
			stmt.setString(2, p.getProductName());
			stmt.setInt(3, p.getId());
			stmt.setFloat(4, p.getPrice());
			
			stmt.executeUpdate();
			System.out.println("Executing query for create... ");
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Product> getUserCart(User u) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
			System.out.println("Connection successful!");
			
			PreparedStatement stmt = conn.prepareStatement(GET_CART);
			stmt.setInt(1, u.getUserId());
			
			ResultSet rs = stmt.executeQuery();
			
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				int id = rs.getInt("product_id");
				String name = rs.getString("product_name");
				float price = rs.getFloat("price");
				
				products.add(new Product(id, name, price));
			}
			rs.close();
			return products;
			
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Product> submitOrder(User u) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
			System.out.println("Connection successful!");
			
			PreparedStatement stmt = conn.prepareStatement(GET_CART);
			stmt.setInt(1, u.getUserId());
			
			ResultSet rs = stmt.executeQuery();
			
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String name = rs.getString("product_name");
				float price = rs.getFloat("price");
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				LocalDateTime date = LocalDateTime.now();
				
				products.add(new Product(userId, name, price, dtf.format(date)));
			}
			rs.close();
			
			stmt = conn.prepareStatement("INSERT INTO `orders` (`user_id`, `product_name`, `price`, `date`) VALUES (?, ?, ?, ?)");
			conn.setAutoCommit(true);
			List<Product> orders = new ArrayList<Product>();
			for (Product p : products) {
				stmt.setInt(1, u.getUserId());
				stmt.setString(2, p.getProductName());
				stmt.setFloat(3, p.getPrice());
				stmt.setString(4, p.getDate());
				stmt.execute();
				System.out.println("added item to order");
			}
			String deleteQuery = "DELETE FROM `cart` WHERE `user_id`=?";
			PreparedStatement stmt2 = conn.prepareStatement(deleteQuery);
			stmt2.setInt(1, u.getUserId());
			
			stmt2.executeUpdate();
			
			return orders;
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Product> getUserOrders(User u) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
			System.out.println("Connection successful!");
			
			PreparedStatement stmt = conn.prepareStatement(GET_USER_ORDERS, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, u.getUserId());
			
			ResultSet rs = stmt.executeQuery();
			
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				int id = rs.getInt("user_id");
				String name = rs.getString("product_name");
				float price = rs.getFloat("price");
				String date = rs.getString("date");				
				
				products.add(new Product(id, name, price, date));
			}
			rs.close();
			return products;
		
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteFromCart(Product p) throws RuntimeException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;

		conn = getConnection();
		stmt = conn.prepareStatement(DELETE_FROM_CART, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, p.getProductName());

		stmt.executeUpdate();

		close(stmt);
		close(conn);
	}

	public void updateProduct(Product p) throws RuntimeException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		conn = getConnection();
		
		stmt = conn.prepareStatement(UPDATE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
		
		stmt.setString(1, p.getProductName());
		stmt.setString(2, p.getDescription());
		stmt.setFloat(3, p.getPrice());
		stmt.setInt(4, p.getId());
		
		stmt.executeUpdate();
		
		close(stmt);
		close(conn);
	}
}
