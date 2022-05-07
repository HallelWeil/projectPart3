package client;

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

public class MsgController {

	private MsgType type;
	private ArrayList<Complaint> complaints;
	private ArrayList<Product> catalogPage;
	private ArrayList<Survey> surveys;
	private ArrayList<Order> orders;
	private Order order;
	private Survey survey;

	public MsgController() {
		resetParser();
	}

	private void resetParser() {
		this.type = MsgType.NONE;
		this.complaints = null;
		this.catalogPage = null;
		this.surveys = null;
		this.orders = null;
		this.order = null;
		this.survey = null;
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
		case RETURN_ALL_COMPLAINTS:
			complaints = (ArrayList<Complaint>) newMsg.data;
			break;
		case RETURN_CATALOG_PAGE:
			catalogPage = (ArrayList<Product>) newMsg.data;
			break;
		case RETURN_ORDER:
			order = (Order) newMsg.data;
			break;
		case RETURN_SURVEY:
			survey = (Survey) newMsg.data;
			break;
		case RETURN_ALL_SURVEY:
			surveys = (ArrayList<Survey>) newMsg.data;
			break;
		case RETURN_ALL_ORDERS:
			orders = (ArrayList<Order>) newMsg.data;
			break;
		case RETURN_PAYMENT_APPROVAL:
		case APPROVE_LOGIN:
		case APPROVE_LOGOUT:
		case EXIT:
		case ERROR:
		case COMPLETED:
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

	public ArrayList<Complaint> getComplaints() {
		return complaints;
	}

	public ArrayList<Product> getCatalogPage() {
		return catalogPage;
	}

	public ArrayList<Survey> getSurveys() {
		return surveys;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public Order getOrder() {
		return order;
	}

	public Survey getSurvey() {
		return survey;
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
	 * create new CREATE_COMPLAINT msg
	 * 
	 * @return
	 */
	public static Msg createCREATE_COMPLAINTMsg(Complaint complaint) {
		Msg msg = new Msg();
		msg.type = MsgType.CREATE_COMPLAINT;
		msg.data = complaint;
		return msg;
	}

	/**
	 * create new CREATE_COMPLAINT msg
	 * 
	 * @return
	 */
	public static Msg createGET_ALL_COMPLAINTSMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.GET_ALL_COMPLAINTS;
		msg.data = null;
		return msg;
	}

	/**
	 * create new CREATE_COMPLAINT msg
	 * 
	 * @return
	 */
	public static Msg createUPDATE_COMPLAINTMsg(Complaint complaint) {
		Msg msg = new Msg();
		msg.type = MsgType.UPDATE_COMPLAINT;
		msg.data = complaint;
		return msg;
	}

	/**
	 * create new ACTIVATE_PROMOTION msg
	 * 
	 * @return
	 */
	public static Msg createACTIVATE_PROMOTIONMsg(Promotion promotion) {
		Msg msg = new Msg();
		msg.type = MsgType.ACTIVATE_PROMOTION;
		msg.data = promotion;
		return msg;
	}

	/**
	 * create new GET_CATALOG_PAGE msg
	 * 
	 * @return
	 */
	public static Msg createACTIVATE_PROMOTIONMsg(int pageNumber, String category) {
		Msg msg = new Msg();
		msg.type = MsgType.GET_CATALOG_PAGE;
		ArrayList<Serializable> data = new ArrayList<Serializable>();
		data.add(pageNumber);
		data.add(category);
		msg.data = data;
		return msg;
	}

	/**
	 * create new PLACE_ORDER_REQUEST msg
	 * 
	 * @return
	 */
	public static Msg createPLACE_ORDER_REQUESTMsg(Cart cart) {
		Msg msg = new Msg();
		msg.type = MsgType.PLACE_ORDER_REQUEST;
		msg.data = cart;
		return msg;
	}

	/**
	 * create new PAY_FOR_ORDER msg
	 * 
	 * @return
	 */
	public static Msg createPAY_FOR_ORDERMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.PAY_FOR_ORDER;
		msg.data = null;
		return msg;
	}

	/**
	 * create new UPDATE_CATALOG msg
	 * 
	 * @return
	 */
	public static Msg createUPDATE_CATALOGMsg(Product product) {
		Msg msg = new Msg();
		msg.type = MsgType.UPDATE_CATALOG;
		msg.data = product;
		return msg;
	}

	/**
	 * create new CREATE_SURVEY msg
	 * 
	 * @return
	 */
	public static Msg createCREATE_SURVEYMsg(Survey survey) {
		Msg msg = new Msg();
		msg.type = MsgType.CREATE_SURVEY;
		msg.data = survey;
		return msg;
	}

	/**
	 * create new ADD_SURVEY_RESULT msg
	 * 
	 * @return
	 */
	public static Msg createADD_SURVEY_RESULTMsg(Serializable resultFile, int surveyNumber) {
		Msg msg = new Msg();
		msg.type = MsgType.ADD_SURVEY_RESULT;
		ArrayList<Serializable> data = new ArrayList<Serializable>();
		data.add(surveyNumber);
		data.add(resultFile);
		msg.data = data;
		return msg;
	}

	/**
	 * create new ADD_SURVEY_ANSWERS msg
	 * 
	 * @return
	 */
	public static Msg createADD_SURVEY_ANSWERSMsg(ArrayList<Integer> answers) {
		Msg msg = new Msg();
		msg.type = MsgType.ADD_SURVEY_ANSWERS;
		msg.data = answers;
		return msg;
	}

	/**
	 * create new GET_SURVEY msg
	 * 
	 * @return
	 */
	public static Msg createGET_SURVEYMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.GET_SURVEY;
		msg.data = null;
		return msg;
	}

	/**
	 * create new GET_ALL_SURVEY msg
	 * 
	 * @return
	 */
	public static Msg createGET_ALL_SURVEYMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.GET_ALL_SURVEY;
		msg.data = null;
		return msg;
	}

	/**
	 * create new LOGIN_REQUEST msg
	 * 
	 * @return
	 */
	public static Msg createLOGIN_REQUESTMsg(String userName, String password) {
		Msg msg = new Msg();
		msg.type = MsgType.LOGIN_REQUEST;
		ArrayList<Serializable> data = new ArrayList<Serializable>();
		data.add(userName);
		data.add(password);
		msg.data = data;
		return msg;
	}

	/**
	 * create new LOG_OUT_REQUEST msg
	 * 
	 * @return
	 */
	public static Msg createLOG_OUT_REQUESTMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.LOG_OUT_REQUEST;
		msg.data = null;
		return msg;
	}

	/**
	 * create new UPATE_USER_DATA msg
	 * 
	 * @return
	 */
	public static Msg createUPATE_USER_DATAMsg(User user) {
		Msg msg = new Msg();
		msg.type = MsgType.UPATE_USER_DATA;
		msg.data = user;
		return msg;
	}

	/**
	 * create new GET_ALL_ORDERS msg
	 * 
	 * @return
	 */
	public static Msg createGET_ALL_ORDERSMsg() {
		Msg msg = new Msg();
		msg.type = MsgType.GET_ALL_ORDERS;
		msg.data = null;
		return msg;
	}

	/**
	 * create new UPDATE_ORDER_STATUS msg
	 * 
	 * @return
	 */
	public static Msg createUPDATE_ORDER_STATUSMsg(Order order) {
		Msg msg = new Msg();
		msg.type = MsgType.UPDATE_ORDER_STATUS;
		msg.data = order;
		return msg;
	}

}
