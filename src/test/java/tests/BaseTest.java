package tests;

import base.AppConstants;
import base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import static utils.ExtentReportHelper.getReporterObject;

public class BaseTest {

    protected WebDriver driver;
    protected static ThreadLocal<ExtentTest> testLogger = new ThreadLocal<>();
    private static final ExtentReports reports = getReporterObject();
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @Parameters("browserName")
    @BeforeMethod
    public void setUp(@Optional String browserName, ITestResult iTestResult) throws MalformedURLException {


        String browser;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-ssl-error=yes");
        options.addArguments("--ignore-certificate-errors");


        if (browserName != null) {
            browser = browserName;
        } else {
            browser = AppConstants.browserName;
        }
        logger.info("browser : " +browser);
        String platform = AppConstants.platformName;
        logger.info("platform : " +AppConstants.platformName);

        if (browser.equalsIgnoreCase("chrome")) {
            if (platform.equalsIgnoreCase("local")) {
                driver = new ChromeDriver();
                logger.info("Chrome driver launched..");
            } else if (platform.equalsIgnoreCase("remote")) {
//                chrome standalone url
                driver = new RemoteWebDriver(new URL("http://localhost:4441"), options);
            }
        } else if (browser.equalsIgnoreCase("Safari")) {
            if (AppConstants.platformName.equalsIgnoreCase("local")) {

                logger.info("launching safari browser..");
                driver = new SafariDriver();
            }
        }  else if (browser.equalsIgnoreCase("firefox")) {
            if (AppConstants.platformName.equalsIgnoreCase("local")) {
                logger.info("launching firefox browser..");
                driver = new FirefoxDriver();
            } else if (platform.equalsIgnoreCase("remote")) {
                driver = new RemoteWebDriver(new URL("http://localhost:4442"), options);
            }
        }
        else {
            throw new RuntimeException("Not supported browser..");
        }

        ExtentTest test = reports.createTest(iTestResult.getMethod().getMethodName());
        testLogger.set(test);
        testLogger.get().log(Status.INFO, "Driver start time : " + LocalDateTime.now());
    }

    @AfterMethod
    public void tearDownMethod(ITestResult iTestResult) {
        if (iTestResult.isSuccess()) {
            testLogger.get().log(Status.PASS, MarkupHelper.createLabel(iTestResult.getMethod().getMethodName() + " is successful!!", ExtentColor.GREEN));
        } else {
            testLogger.get().log(Status.FAIL, "Test failed due to: " + iTestResult.getThrowable());
            String screenshot = BasePage.getScreenshot(iTestResult.getMethod().getMethodName() + ".jpg", driver);
            testLogger.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(BasePage.convertImg_Base64(screenshot)).build());
        }

        testLogger.get().log(Status.INFO, "Driver End Time: " + LocalDateTime.now());

        driver.quit();
    }


    @AfterClass
    public void flushTestReport() {
        reports.flush();
    }
}

