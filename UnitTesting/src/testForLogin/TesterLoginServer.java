package testForLogin;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import UnitTestinData.DBINFO;
import database.DBController;
import msg.Msg;
import msg.MsgType;
import user.User;
import user.UserStatus;
import user.UserType;
import usersManagment.LoginConrtroller;

public class TesterLoginServer {

	DBController DBcon;
	LoginConrtroller loginCon;
	Msg message;
	User user;

	@Before
	public void setUp() throws Exception {
		DBcon = DBController.getInstance();
		DBcon.connectToDB(DBINFO.DBname, DBINFO.DBUser, DBINFO.DBPassword);
		loginCon = new LoginConrtroller();
		message = new Msg();
		user = new User();
		DBcon.disconnectUser("HallelWeil");
	}

//test for login on server side//
//tested with the actual database//

	// checking functionality - login with correct username + password
	// input data: "HallelWeil", "7891011"
	// expected result: getting APPROVE_LOGIN message with the correct user data
	@Test
	public void cheackLoginCorrectUserDetails() {
		Msg res = loginCon.login("HallelWeil", "7891011");
		assertEquals(res.type, MsgType.APPROVE_LOGIN);
		user = (User) res.data;
		assertEquals(user.getUsername(), "HallelWeil");
		DBcon.disconnectUser("HallelWeil");
	}

	// checking functionality - login with twice with correct username + password
	// input data: "HallelWeil", "7891011"
	// expected result: can't connect the second time, the correct error message
	@Test
	public void checkLoginUserAlreadyConnected() {

		Msg res = loginCon.login("HallelWeil", "7891011");
		assertEquals(res.type, MsgType.APPROVE_LOGIN);
		user = (User) res.data;
		assertEquals(user.getUsername(), "HallelWeil");
		res = loginCon.login("HallelWeil", "7891011");
		assertEquals(res.type, MsgType.ERROR);
		String errorStr = (String) res.data;
		assertEquals(errorStr, "The user already connected");
		DBcon.disconnectUser("HallelWeil");
	}

	// checking functionality - login with null username + password
	// input data:null, null
	// expected result: getting ERROR message with the correct error message
	@Test
	public void checkLoginUserDetailsAreNull() {

		Msg res = loginCon.login(null, null);
		String errorStr = (String) res.data;
		assertEquals(errorStr, "Wrong username and password");
	}

	// checking functionality - login with incorrect password
	// input data: "HallelWeil", "789111"
	// expected result: getting ERROR message with the correct error message
	@Test
	public void checkLoginUserDetailsAreNotVaild() {

		Msg res = loginCon.login("HallelWeil", "789111");
		assertEquals(res.type, MsgType.ERROR);
		String errorStr = (String) res.data;
		assertEquals(errorStr, "Wrong username and password");
	}

	// checking: login with Correct details for BranchManger
	// input: username= "HallelWeil" & password= "7891011"
	// expected:login succeed and user is BranchManger
	@Test
	public void checkLoginCorrectBranchMangerDetails() {

		Msg res = loginCon.login("HallelWeil", "7891011");
		assertEquals(res.type, MsgType.APPROVE_LOGIN);
		user = (User) res.data;
		assertEquals(user.getUsername(), "HallelWeil");
		assertEquals(user.getUserType(), UserType.BranchManager);
		DBcon.disconnectUser("HallelWeil");
	}

	// checking: login with Correct details for nonAuthorizedCustomer
	// input: username= "OrBalmas" & password= "123456"
	// expected:login succeed and user is nonAuthorizedCustomer
	@Test
	public void checkLoginCorrectnonAuthorizedCustomerDetails() {
		DBcon.updateUser("OrBalmas", UserType.NonAuthorizedCustomer, UserStatus.Active);
		Msg res = loginCon.login("OrBalmas", "123456");
		assertEquals(res.type, MsgType.APPROVE_LOGIN);
		user = (User) res.data;
		assertEquals(user.getUsername(), "OrBalmas");
		assertEquals(user.getUserType(), UserType.NonAuthorizedCustomer);
		DBcon.updateUser("OrBalmas", UserType.AuthorizedCustomer, UserStatus.Active);
		DBcon.disconnectUser("OrBalmas");
	}

	// checking: login with Correct details for AuthorizedCustomer
	// input: username= "OrBalmas" & password= "123456"
	// expected:login succeed and user is AuthorizedCustomer
	@Test
	public void checkLoginCorrectAuthorizedCustomerDetails() {
		DBcon.updateUser("OrBalmas", UserType.AuthorizedCustomer, UserStatus.Active);
		Msg res = loginCon.login("OrBalmas", "123456");
		assertEquals(res.type, MsgType.APPROVE_LOGIN);
		user = (User) res.data;
		assertEquals(user.getUsername(), "OrBalmas");
		assertEquals(user.getUserType(), UserType.AuthorizedCustomer);
		DBcon.disconnectUser("OrBalmas");
	}

	// checking: login with Correct details for BranchEmployee
	// input: username= "OfirGalai" & password= "223344"
	// expected:login succeed and user is BranchEmployee
	@Test
	public void checkLoginCorrectBranchEmployeeDetails() {
		Msg res = loginCon.login("OfirGalai", "223344");
		assertEquals(res.type, MsgType.APPROVE_LOGIN);
		user = (User) res.data;
		assertEquals(user.getUsername(), "OfirGalai");
		assertEquals(user.getUserType(), UserType.BranchEmployee);
		DBcon.disconnectUser("OfirGalai");
	}

	// checking: login with Correct details for CEO
	// input: username= "Omersh" & password= "1234567"
	// expected:login succeed and user is CEO
	@Test
	public void checkLoginCorrectCEODetails() {

		Msg res = loginCon.login("Omersh", "1234567");
		assertEquals(res.type, MsgType.APPROVE_LOGIN);
		user = (User) res.data;
		assertEquals(user.getUsername(), "Omersh");
		assertEquals(user.getUserType(), UserType.CEO);
		DBcon.disconnectUser("Omersh");
	}

	// checking: login with Correct details for Courier
	// input: username= "Omershami" & password= "667788"
	// expected:login succeed and user is Courier
	@Test
	public void checkLoginCorrectCourierDetails() {

		Msg res = loginCon.login("Omershami", "667788");
		assertEquals(res.type, MsgType.APPROVE_LOGIN);
		user = (User) res.data;
		assertEquals(user.getUsername(), "Omershami");
		assertEquals(user.getUserType(), UserType.Courier);
		DBcon.disconnectUser("Omershami");
	}

	// checking: login with Correct details for CustomerServiceEmloyee
	// input: username= "RonenZeyan" & password= "778899"
	// expected:login succeed and user is CustomerServiceEmloyee
	@Test
	public void checkLoginCorrectCustomerServiceEmloyeeDetails() {

		Msg res = loginCon.login("RonenZeyan", "778899");
		assertEquals(res.type, MsgType.APPROVE_LOGIN);
		user = (User) res.data;
		assertEquals(user.getUsername(), "RonenZeyan");
		assertEquals(user.getUserType(), UserType.CustomerServiceEmloyee);
		DBcon.disconnectUser("RonenZeyan");
	}

	// checking: login with Correct details for MarketingEmployee user
	// input: username= "WorkHallel" & password= "112233"
	// expected:login succeed and user is MarketingEmployee
	@Test
	public void checkLoginCorrectMarketingEmployeeDetails() {

		Msg res = loginCon.login("WorkHallel", "112233");
		assertEquals(res.type, MsgType.APPROVE_LOGIN);
		user = (User) res.data;
		assertEquals(user.getUsername(), "WorkHallel");
		assertEquals(user.getUserType(), UserType.MarketingEmployee);
		DBcon.disconnectUser("WorkHallel");
	}

	// checking:login with frozen Customer
	// input: username= "orBalmas" & password= "123456"
	// expected: login succeed and customer in frozen status
	@Test
	public void checkLoginCorrectFrozenCustomerDetails() {
		DBcon.updateUser("OrBalmas", UserType.AuthorizedCustomer, UserStatus.Frozen);
		Msg res = loginCon.login("OrBalmas", "123456");
		assertEquals(res.type, MsgType.APPROVE_LOGIN);
		user = (User) res.data;
		assertEquals(user.getUsername(), "OrBalmas");
		assertEquals(user.getStatus(), UserStatus.Frozen);
		DBcon.updateUser("OrBalmas", UserType.AuthorizedCustomer, UserStatus.Active);
		DBcon.disconnectUser("OrBalmas");
	}

	// checking: login with not activeCustomer
	// input: username= "orBalmas" & password= "123456"
	// expected: login succeed and customer in notActive Status
	@Test
	public void checkLoginCorrectNotActiveCustomerDetails() {
		DBcon.updateUser("OrBalmas", UserType.AuthorizedCustomer, UserStatus.NotActive);
		Msg res = loginCon.login("OrBalmas", "123456");
		assertEquals(res.type, MsgType.APPROVE_LOGIN);
		user = (User) res.data;
		assertEquals(user.getUsername(), "OrBalmas");
		assertEquals(user.getStatus(), UserStatus.NotActive);
		DBcon.updateUser("OrBalmas", UserType.AuthorizedCustomer, UserStatus.Active);
		DBcon.disconnectUser("OrBalmas");
	}

	// checking: login with empty user details
	// input: username= "" & password= ""
	// expected: Error message "Wrong username and password"
	@Test
	public void checkLoginUserDetailsAreEmpty() {
		Msg res = loginCon.login("", "");
		String errorStr = (String) res.data;
		assertEquals(errorStr, "Wrong username and password");
	}

}
