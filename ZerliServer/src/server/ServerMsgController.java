package server;

import java.io.Serializable;
import java.util.ArrayList;

import cart.Cart;
import catalog.Product;
import complaint.Complaint;
import msg.Msg;
import msg.MsgType;
import order.Order;
import promotion.Promotion;
import survey.Survey;
import user.User;

public class ServerMsgController {

	private MsgType type;
	private Complaint complaint;
	private Promotion promotion;
	private int pageNumber;
	private String category;
	private Cart cart;
	private Product product;
	private Survey survey;
	private Serializable resultFile;
	private int surveyNumber;
	private ArrayList<Integer> answers;
	private String userName;
	private String password;
	private User user;
	private Order order;

	private void resetParser() {
		this.type = MsgType.NONE;
		complaint = null;
		promotion = null;
		pageNumber = -1;
		category = null;
		cart = null;
		product = null;
		survey = null;
		resultFile = null;
		surveyNumber = -1;
		answers = null;
		userName = null;
		password = null;
		user = null;
		order = null;
	}

	/**
	 * parse given msg, return true if the msg was ok, can get the result using
	 * getters
	 * 
	 * @param msg
	 * @return true if the msg was ok
	 */
	@SuppressWarnings("unchecked")
	public boolean mgsParser(Object msg) {
		resetParser();
		if (msg == null)
			return false;
		if (!(msg instanceof Msg))
			return false;
		Msg newMsg = (Msg) msg;
		type = newMsg.type;
		switch (type) {// save the relevant data for each type
		case CREATE_COMPLAINT:
		case UPDATE_COMPLAINT:
			complaint = (Complaint) newMsg.data;
			break;
		case ACTIVATE_PROMOTION:
			promotion = (Promotion) newMsg.data;
			break;
		case GET_CATALOG_PAGE:
			ArrayList<Serializable> data = (ArrayList<Serializable>) newMsg.data;
			pageNumber = (int) data.get(0);
			category = (String) data.get(2);
			break;
		case PLACE_ORDER_REQUEST:
			cart = (Cart) newMsg.data;
			break;
		case UPDATE_CATALOG:
			product = (Product) newMsg.data;
			break;
		case CREATE_SURVEY:
			survey = (Survey) newMsg.data;
			break;
		case ADD_SURVEY_RESULT:
			ArrayList<Serializable> surveyData = (ArrayList<Serializable>) newMsg.data;
			surveyNumber = (int) surveyData.get(0);
			resultFile = surveyData.get(1);
			break;
		case ADD_SURVEY_ANSWERS:
			answers = (ArrayList<Integer>) newMsg.data;
			break;
		case GET_SURVEY:
			surveyNumber = (int) newMsg.data;
			break;
		case LOGIN_REQUEST:
			ArrayList<Serializable> loginData = (ArrayList<Serializable>) newMsg.data;
			userName = (String) loginData.get(0);
			password = (String) loginData.get(1);
			break;
		case UPATE_USER_DATA:
			user = (User) newMsg.data;
			break;
		case UPDATE_ORDER_STATUS:
			order = (Order) newMsg.data;
			break;
		case GET_ALL_COMPLAINTS:
		case GET_ALL_ORDERS:
		case LOG_OUT_REQUEST:
		case GET_ALL_SURVEY:
		case PAY_FOR_ORDER:
		case EXIT:
		case ERROR:
			break;
		default:// no type was found, return false
			return false;
		}
		return true;
	}
	// getters

	public MsgType getType() {
		return type;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public String getCategory() {
		return category;
	}

	public Cart getCart() {
		return cart;
	}

	public Product getProduct() {
		return product;
	}

	public Survey getSurvey() {
		return survey;
	}

	public Serializable getResultFile() {
		return resultFile;
	}

	public int getSurveyNumber() {
		return surveyNumber;
	}

	public ArrayList<Integer> getAnswers() {
		return answers;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public User getUser() {
		return user;
	}

	public Order getOrder() {
		return order;
	}

	// create msg static methods
	/**
	 * create new exit msg
	 * 
	 * @return
	 */
	public static Msg createEXITMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.EXIT;
		msg.data = null;
		return msg;
	}

	/**
	 * create new ERROR msg
	 * 
	 * @return
	 */
	public static Msg createERRORMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.ERROR;
		msg.data = null;
		return msg;
	}

	/**
	 * create new COMPLETED msg
	 * 
	 * @return
	 */
	public static Msg createCOMPLETEDMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.COMPLETED;
		msg.data = null;
		return msg;
	}

	/**
	 * create new RETURN_ALL_COMPLAINTS msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_ALL_COMPLAINTSMsg(ArrayList<Complaint> complaints) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_ALL_COMPLAINTS;
		msg.data = complaints;
		return msg;
	}

	/**
	 * create new RETURN_CATALOG_PAGE msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_CATALOG_PAGEMsg(ArrayList<Product> catalogPage) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_CATALOG_PAGE;
		msg.data = catalogPage;
		return msg;
	}

	/**
	 * create new RETURN_ORDER msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_ORDERMsg(Order order) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_ORDER;
		msg.data = order;
		return msg;
	}

	/**
	 * create new RETURN_SURVEY msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_SURVEYMsg(Survey survey) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_SURVEY;
		msg.data = survey;
		return msg;
	}

	/**
	 * create new RETURN_ALL_SURVEY msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_ALL_SURVEYMsg(ArrayList<Survey> surveys) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_ALL_SURVEY;
		msg.data = surveys;
		return msg;
	}

	/**
	 * create new RETURN_ALL_ORDERS msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_ALL_ORDERSMsg(ArrayList<Order> orders) {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_ALL_ORDERS;
		msg.data = orders;
		return msg;
	}

	/**
	 * create new COMPLETED msg
	 * 
	 * @return
	 */
	public static Msg createAPPROVE_LOGINMsg(User user) {
		Msg msg = new Msg();
		msg.type = MsgType.COMPLETED;
		msg.data = user;
		return msg;
	}

	/**
	 * create new RETURN_PAYMENT_APPROVAL msg
	 * 
	 * @return
	 */
	public static Msg createRETURN_PAYMENT_APPROVALMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.RETURN_PAYMENT_APPROVAL;
		msg.data = null;
		return msg;
	}

	/**
	 * create new APPROVE_LOGOUT msg
	 * 
	 * @return
	 */
	public static Msg createAPPROVE_LOGOUTMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.APPROVE_LOGOUT;
		msg.data = null;
		return msg;
	}
}
