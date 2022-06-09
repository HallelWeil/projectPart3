package usersManagment;

import client.MsgController;

public interface IUserController {

	public MsgController login(String username, String password);

	public void logout();
}
