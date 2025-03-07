package page_objects;

import generic_keywords.WebElementInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends WebElementInteractions {

    private final By user_name = By.cssSelector("#user-name");
    private final By pwd = By.cssSelector("#password");
    private final By login_btn = By.cssSelector("#login-button");
    private final By menu_btn = By.cssSelector("button#react-burger-menu-btn");
    private final By logout_link = By.cssSelector("a#logout_sidebar_link");

    public LoginPageObject(WebDriver driver) {
        super(driver);
    }

    public ProductPageObject userLogin(String username, String password){
        goToApplication("http://saucedemo.com");
        enterText(user_name,username);
        enterText(pwd,password);
        clickElement(login_btn);
        return new ProductPageObject(driver);
    }

    public boolean isUserLoggedIn(){
        clickElement(menu_btn);
        return isElementDisplayed(logout_link);
    }
}
