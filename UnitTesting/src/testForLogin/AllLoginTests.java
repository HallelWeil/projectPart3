package testForLogin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TesterLoginClient.class, TesterLoginServer.class })
public class AllLoginTests {

}
