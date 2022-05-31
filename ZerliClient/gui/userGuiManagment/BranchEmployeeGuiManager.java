package userGuiManagment;

public class BranchEmployeeGuiManager implements IUserGuiManager {

	private static BranchEmployeeGuiManager branchEmployeeGuiManager;
	// the gui controllers

	private BranchEmployeeGuiManager() {

	}

	public static BranchEmployeeGuiManager getInstance() {
		if (branchEmployeeGuiManager == null)
			branchEmployeeGuiManager = new BranchEmployeeGuiManager();
		return branchEmployeeGuiManager;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

}
