package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import order.Order;
import order.OrderStatus;
import survey.Survey;

public class DBObject {
	
	ArrayList<Order> orderDB(ResultSet res) {
		ArrayList<Order> orders = new ArrayList<>();
		try {
			while(res.next()) {
				Order order = new Order();
				order.setOrderID(res.getInt("orderID"));
				order.setOrderDate(res.getTimestamp("orderDate"));
				order.setArrivalDate(res.getTimestamp("arrivalDate"));
				order.setHomeDelivery(res.getBoolean("deliveryType"));
				order.setBranchName(res.getString("branchName"));
				order.setPrice(res.getDouble("price"));
				order.setUsername(res.getString("customerID"));
				order.setPersonalLetter(res.getString("personalLetter"));
				//order.setOrderStatus(OrderStatus.valueOf(res.getInt("orderStatus")));
				order.setOrderData(res.getString("data"));
				orders.add(order);
			}
		} catch (SQLException e) {
		}
		return orders;
	}
	
	ArrayList<Survey> surveyDB(ResultSet res) {
		ArrayList<Survey> surveys = new ArrayList<>() ;
		try {
			while(res.next()) {
				Survey survey = new Survey(
						res.getString("q1"), res.getString("q2"), res.getString("q3"),
						res.getString("q4"), res.getString("q5"), res.getString("q6"));
				int[] answers = new int[6];
				for(int i=1; i<=6; i++) {
					answers[i] = res.getInt("a" + i);
				}
				//survey.addAnswers(answers); will count at 1 participant
				surveys.add(survey);
				survey.setSurveyNumber(res.getInt("surveyNumber"));
				//need set for participants and answers 
			}
		} catch (Exception e) {
		}
		return surveys;
	}
	
	
	
	
	
	

}
