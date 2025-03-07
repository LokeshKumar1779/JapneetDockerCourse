package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;

public class LoginTest extends BaseTest
{
    LoginPageObject loginPageObject;
    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @Test(priority = 1)
    public void testLogin() {
//        System.out.println("running login test...");
        logger.info("running login test...");
        String username = "standard_user";
        String password = "secret_sauce";
        loginPageObject = new LoginPageObject(driver);
        loginPageObject.userLogin("standard_user","secret_sauce");
        testLogger.get().info("username :" +username);
        testLogger.get().info("password :" +password);
        Assert.assertTrue(loginPageObject.isUserLoggedIn(),"user is not logged in successfully");
    }
}
