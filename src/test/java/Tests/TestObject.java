package Tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import object.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TestObject {
    protected Homepage homepage;
    protected Header header;
    protected LoginPage loginPage;
    protected ProfilePage profilePage;
    protected NewPostPage newPostPage;
    protected Search search;
    protected LogoutPage logoutPage;

    public static final String SCREENSHOTS_DIR = ("src\\test\\resources\\screenshots\\");
    protected WebDriver webDriver;


    @BeforeSuite
    protected final void setupTestSuite() throws IOException {
        cleanDirectory(SCREENSHOTS_DIR);
        WebDriverManager.chromedriver().setup();


    }

    @BeforeMethod()
    protected final void setupDriver() {

        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homepage = new Homepage(webDriver);
        loginPage = new LoginPage(webDriver);
        profilePage = new ProfilePage(webDriver);
        header = new Header(webDriver);
        newPostPage = new NewPostPage(webDriver);
        search = new Search(webDriver);
        logoutPage = new LogoutPage(webDriver);
    }

    @AfterMethod
    protected void tearDownTest(ITestResult testResult) {
        takeScreenshot(testResult);
        quitDriver();
    }


    private void quitDriver() {
        if (this.webDriver != null) {
            this.webDriver.quit();
        }
    }

    private WebDriver getWebDriver() {
        return webDriver;
    }

    private void cleanDirectory(String directoryPath) throws IOException {

        File directory = new File(directoryPath);
        Assert.assertTrue(directory.isDirectory(), "Invalid directory!");

        try {
            FileUtils.cleanDirectory(directory);
            System.out.printf("All files are deleted in Directory: %s%n", directoryPath);
        } catch (IOException exception) {
            System.out.printf("Unable to delete the files in Directory: %s%n", directoryPath);
        }

    }

    private void takeScreenshot(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
                String testName = testResult.getName();
                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpeg")));
            } catch (IOException e) {
                System.out.println("Unable to create a screenshot file: " + e.getMessage());
            }
        }
    }
}