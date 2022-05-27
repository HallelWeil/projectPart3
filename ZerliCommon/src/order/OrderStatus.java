package order;

public enum OrderStatus {
<<<<<<< Updated upstream
	
	WaitingForAprroval,Canceled,Approved,NotApproved,Completed

=======
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
	CANECELED,
	/**
	 * waiting for cancellation approval from branch manager
	 */
	WAITING_FOR_CANCELATION_APPROVAL,
	/**
	 * 
	 */
	WAITING_FOR_PAYMENT,
	/**
	 * approved but not collected yet
	 */
	APPROVED,
	/**
	 * branchManger didn't accept to approve the order 
	 */
	NOT_APPROVED,
>>>>>>> Stashed changes
}
