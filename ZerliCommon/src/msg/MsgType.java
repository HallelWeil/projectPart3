package msg;

public enum MsgType {

	// common types
	/**
	 * no type
	 */
	NONE,
	/**
	 * End connection
	 */
	EXIT,
	/**
	 * An error occurred
	 */
	ERROR,
	/**
	 * Task completed successfully
	 */
	COMPLETED,

	// client -> server
	/**
	 * Send to complaint to the server, expected answer=COMPLETED
	 */
	CREATE_COMPLAINT,
	/**
	 * request for all the complaints belonging to the employee, expected answer=RETURN_ALL_COMPLAINTS
	 */
	GET_ALL_COMPLAINTS,
	/**
	 * Update a specific complaint, expected answer=COMPLETED
	 */
	UPDATE_COMPLAINT,
	/**
	 * Activate new promotion, expected answer=COMPLETED
	 */
	ACTIVATE_PROMOTION,
	/**
	 * get a specific page of a specific category, expected answer=RETURN_CATALOG_PAGE
	 */
	GET_CATALOG_PAGE,
	/**
	 * Send the cart to the server, expect order in return, expected answer=RETURN_ORDER
	 */
	PLACE_ORDER_REQUEST,
	/**
	 * Pay for the order with the given order number, expected answer=RETURN_PAYMENT_APPROVAL
	 */
	PAY_FOR_ORDER,
	/**
	 * update the catalog, expected answer=COMPLETED
	 */
	UPDATE_CATALOG,
	/**
	 * create new survey, expected answer=COMPLETED
	 */
	CREATE_SURVEY,
	/**
	 * add survey result file to a survey, expected answer=COMPLETED
	 */
	ADD_SURVEY_RESULT,
	/**
	 * add answers to a survey, expected answer=COMPLETED
	 */
	ADD_SURVEY_ANSWERS,
	/**
	 * Get survey, expected answer=RETURN_SURVEY
	 */
	GET_SURVEY,
	/**
	 * GET all the surveys, expected answer=RETURN_ALL_SURVEY
	 */
	GET_ALL_SURVEY,
	/**
	 * LOGIN REQUEST, expected answer=APPROVE_LOGIN
	 */
	LOGIN_REQUEST,
	/**
	 * LOG OUT REQUEST, expected answer=APPROVE_LOGOUT
	 */
	LOG_OUT_REQUEST,
	/**
	 * UPDATE USER DATA, expected answer=COMPLETED
	 */
	UPATE_USER_DATA,
	/**
	 * get all the user's orders, expected answer=RETURN_ALL_ORDERS
	 */
	GET_ALL_ORDERS,
	/**
	 * update order status(approve, not approved, canceled, refunded), expected answer=COMPLETED
	 */
	UPDATE_ORDER_STATUS,
	/**
	 * get report with type month and year
	 */
	GET_REPORT,
	// server -> client
	/**
	 * return a report
	 */
	RETURN_REPORT,
	/**
	 * Approve the login, return the user entity
	 */
	APPROVE_LOGIN,
	/**
	 * approve the logout request
	 */
	APPROVE_LOGOUT,
	/**
	 * return the complaints as arraylist
	 */
	RETURN_ALL_COMPLAINTS,
	/**
	 * return the catalog page as product arraylist
	 */
	RETURN_CATALOG_PAGE,
	/**
	 * return a single order
	 */
	RETURN_ORDER,
	/**
	 * return approval for the payment
	 */
	RETURN_PAYMENT_APPROVAL,
	/**
	 * return single survey
	 */
	RETURN_SURVEY,
	/**
	 * return all the active survey
	 */
	RETURN_ALL_SURVEY,
	/**
	 * return all the user's order
	 */
	RETURN_ALL_ORDERS,

}
