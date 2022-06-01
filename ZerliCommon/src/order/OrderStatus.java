package order;

public enum OrderStatus {
	/**
	 * Approved and collected
	 */
	COMPLETED,
	/**
	 * waiting for approval from branch manager
	 */
	WAITING_FOR_APPROAVL,
	/**
	 * collected/delivered
	 */
	COLLECTED,
	/**
	 * the cancellation was approved
	 */
	CANCELED,
	/**
	 * waiting for cancellation approval from branch manager
	 */
	WAITING_FOR_CANCELLATION_APPROVAL,
	/**
	 * 
	 */
	WAITING_FOR_PAYMENT,
	/**
	 * 
	 */
	APPROVED, NOT_APPROVED,
}
