package generic_keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebElementInteractions {

    protected WebDriver driver;
    WebDriverWait wait;

    public WebElementInteractions(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(By locator){
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public void enterText(By locator, String text){
        driver.findElement(locator).sendKeys(text);
    }

    public String retrieveTextData(By locator){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public void goToApplication(String url){
        driver.get(url);
    }

    public boolean isElementDisplayed(By locator){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).isDisplayed();
    }




}
