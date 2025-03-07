package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductPageObject;

public class ProductTest extends BaseTest{

    ProductPageObject productPageObject;
    LoginPageObject loginPageObject;
    private static final Logger logger = LogManager.getLogger(ProductTest.class);

    @Test(priority = 2)
    public void testProduct() throws InterruptedException {
//        System.out.println("running product test...");
        logger.info("running product test...");
        loginPageObject = new LoginPageObject(driver);
        productPageObject = loginPageObject.userLogin("standard_user","secret_sauce");
        System.out.println("Title : " +productPageObject.getTitleOfPage());
        Assert.assertEquals(productPageObject.getTitleOfPage(),"Products");
        Thread.sleep(10000);
        System.out.println("First Product text : " +productPageObject.getFirstProductText());
        Assert.assertEquals(productPageObject.getFirstProductText(),"Sauce Labs Backpack");


    }
}
