package database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import catalog.Product;
import common.Status;
import complaint.Complaint;
import order.Order;
import order.OrderStatus;
import order.ProductInOrder;
import report.Report;
import survey.Survey;
import user.User;
import user.UserStatus;
import user.UserType;

public class DBObjectsManager {

	String objectToBlobString(Object object) {
		byte[] data;
		String sdata = "";
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(object);
			oos.flush();
			data = bos.toByteArray();
			sdata = Base64.getEncoder().encodeToString(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sdata;
	}

	Object blobToObject(java.sql.Blob blob) {
		Object object = null;
		byte[] data;
		try {
			blob.getBytes(1, (int) blob.length());
			data = blob.getBytes(1, (int) blob.length());
			ByteArrayInputStream bis = new ByteArrayInputStream(Base64.getDecoder().decode(data));
			ObjectInputStream ois = new ObjectInputStream(bis);
			object = ois.readObject();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return object;
	}

	int lastID(ResultSet res) {
		int lastID = -1;
		try {
			if (res.next()) {
				lastID = res.getInt("last_id");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lastID;

	}

	ArrayList<Order> orderDB(ResultSet res) {
		ArrayList<Order> orders = new ArrayList<>();
		try {
			while (res.next()) {
				Order order = new Order();
				order.setOrderNumber(res.getInt("orderNumber"));
				order.setOrderDate(res.getTimestamp("orderDate"));
				order.setArrivalDate(res.getTimestamp("arrivalDate"));
				order.setHomeDelivery(res.getBoolean("deliveryType"));
				order.setBranchName(res.getString("branchName"));
				order.setPrice(res.getDouble("price"));
				order.setUsername(res.getString("customerID"));
				order.setPersonalLetter(res.getString("personalLetter"));
				order.setOrderStatus(OrderStatus.valueOf(res.getString("orderStatus")));
				order.setOrderData(res.getString("data"));
				orders.add(order);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return orders;
	}

	ArrayList<Survey> surveyDB(ResultSet res) {
		ArrayList<Survey> surveys = new ArrayList<>();
		try {
			while (res.next()) {
				Survey survey = new Survey(res.getString("q1"), res.getString("q2"), res.getString("q3"),
						res.getString("q4"), res.getString("q5"), res.getString("q6"));
				int[] answers = new int[6];
				for (int i = 1; i <= 6; i++) {
					answers[i] = res.getInt("a" + i);
				}
				surveys.add(survey);
				survey.setSurveyNumber(res.getInt("surveyNumber"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return surveys;
	}

	public ArrayList<Complaint> complaintDB(ResultSet res) {
		ArrayList<Complaint> complaints = new ArrayList<>();
		try {
			while (res.next()) {
				Complaint complaint = new Complaint(res.getString("responsibleEmployeeID"), res.getString("complaint"),
						res.getString("customerID"));
				complaint.setAnswer(res.getString("answer"));
				complaint.setCompensation(res.getDouble("compensation"));
				complaint.setComplaintsNumber(res.getInt("complaintNumber"));
				complaint.setStatus(Status.valueOf(res.getString("status")));
				complaints.add(complaint);
				// need set for participants and answers
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return complaints;
	}

	public ArrayList<Product> productDB(ResultSet res) {
		ArrayList<Product> products = new ArrayList<>();
		try {
			while (res.next()) {
				Product product = new Product(res.getInt("productID"));
				product.setName(res.getString("name"));
				product.setPrice(res.getDouble("price"));
				product.setDescription(res.getString("description"));
				product.setColors(res.getString("colors"));
				product.setImage(null);
				products.add(product);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return products;
	}

	public ArrayList<ProductInOrder> productsInOrderDB(ResultSet res) {
		ArrayList<ProductInOrder> products = new ArrayList<>();
		try {
			while (res.next()) {
				ProductInOrder product = new ProductInOrder();
				product.setOrderNumber(res.getInt("orderNumber"));
				product.setName(res.getString("name"));
				product.setPrice(res.getDouble("price"));
				product.setAmount(res.getInt("amount"));
				product.setCategory(res.getString("category"));
				products.add(product);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return products;
	}

	public ArrayList<Report> reportDB(ResultSet res) {
		ArrayList<Report> reports = new ArrayList<>();
		try {
			while (res.next()) {
				reports.add((Report) blobToObject(res.getBlob("data")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return reports;
	}

	public User userDB(ResultSet res) {
		User user = null;
		try {
			if (res.next()) {
				user = new User();
				user.setUsername(res.getString("username"));
				user.setUserType(UserType.valueOf(res.getString("userType")));
				user.setConnected(res.getBoolean("connected"));
				user.setFirstName(res.getString("firstName"));
				user.setLastName(res.getString("lastName"));
				user.setEmail(res.getString("email"));
				user.setPhoneNumber(res.getString("phoneNumber"));
				user.setPersonID(res.getString("personID"));
				user.setStatus(UserStatus.valueOf(res.getString("status")));
				user.setPersonID(res.getString("personID"));
				user.setBranchName(res.getString("branch"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}

	public String cardDB(ResultSet res) {
		String info = null;
		try {
			if (res.next()) {
				info = res.getString("cardInfo");
				// need set for participants and answers
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return info;
	}

	public Blob surveyResultDB(ResultSet res) {
		java.sql.Blob pdf;
		try {
			if (res.next()) {
				pdf = res.getBlob("surveyResult");
				// need set for participants and answers
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public ArrayList<String> branchNameDB(ResultSet res) {
		ArrayList<String> branches = new ArrayList<>();
		try {
			while (res.next()) {
				branches.add(res.getString("branchName"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return branches;
	}

}