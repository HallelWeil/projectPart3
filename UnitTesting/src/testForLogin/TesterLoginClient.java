package testForLogin;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import client.MsgController;
import msg.Msg;
import msg.MsgType;
import user.User;
import usersManagment.IUserController;
import usersManagment.UserBoundary;

public class TesterLoginClient {

	Msg message;
	stubUserController userCon;
	MsgController msgCon;
	User user;
	UserBoundary userBoun;

	public class stubUserController implements IUserController {

		public MsgController login(String username, String password) {
			return msgCon;
		}

		public void logout() {

		}
	}

	@Before
	public void setUp() throws Exception {
		message = new Msg();
		msgCon = new MsgController();
		user = new User();
		userCon = new stubUserController();
		userBoun = new UserBoundary(userCon);

	}

	// checking functionality - Test the client response to successful connection
	// input data: message with type = APPROVE_LOGIN
	// expected result: true, saved the user info
	@Test
	public void testLoginInCorrectDeatilsForBranchMananger() {
		message.type = MsgType.APPROVE_LOGIN;
		message.data = user;
		msgCon.mgsParser(message);
		boolean expectedRes = true;
		boolean actualRes = userBoun.requestLogin("HallelWeil", "7891011");
		assertEquals(expectedRes, actualRes);
		assertEquals(UserBoundary.CurrentUser, user);

	}

	// checking functionality - Test the client response to failed connection
	// input data: message with type = error
	// expected result: false, correct error message
	@Test
	public void testLoginInNotCorrectDeatilsForBranchManagerEmptyPassword() {
		message.type = MsgType.ERROR;
		message.data = "Error";
		msgCon.mgsParser(message);
		boolean actualRes = userBoun.requestLogin("HallelWeil", null);
		String expectedRes = "Error";
		assertFalse(actualRes);
		assertEquals(expectedRes, userBoun.errorMsg);
	}

	// checking functionality - Test the client response to incorrect server answer
	// message
	// input data: message with type = none
	// expected result: false
	@Test
	public void testLoginMsgForLoginIsNotCorrect() {
		message.type = MsgType.NONE;
		msgCon.mgsParser(message);
		boolean expectedRes = false;
		boolean actualRes = userBoun.requestLogin("HallelWeil", "7891011");
		assertEquals(actualRes, expectedRes);
	}

}
