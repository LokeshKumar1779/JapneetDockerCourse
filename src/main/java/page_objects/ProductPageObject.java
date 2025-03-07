package page_objects;

import generic_keywords.WebElementInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPageObject extends WebElementInteractions {

    private final By getTitleOfProductPage = By.cssSelector(".title");
    private final By getFirstProduct = By.cssSelector("a#item_4_title_link div");

    public ProductPageObject(WebDriver driver) {
        super(driver);
    }

    public String getTitleOfPage(){

        return retrieveTextData(getTitleOfProductPage);
    }

    public String getFirstProductText(){
        return retrieveTextData(getFirstProduct);
    }
}
