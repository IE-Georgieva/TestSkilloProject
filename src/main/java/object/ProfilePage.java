package object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;


public class ProfilePage {
    public static final String PROFILE_PAGE_URL = "http://training.skillo-bg.com:4200/users/";
    private final WebDriver webDriver;
    @FindBy(css = "app-profile-posts-section label:first-of-type")
    private WebElement allButton;
    @FindBy(css = "label.delete-ask")
    private WebElement deleteButton;
    @FindBy(css = "button:first-child")
    private WebElement yesButton;
    @FindBy(css = ".toast-container")
    private WebElement failureMessage;

    public ProfilePage(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlContains(PROFILE_PAGE_URL));
    }

    public boolean isUrlLoaded(String userId) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(PROFILE_PAGE_URL + userId));
    }

    public void navigateTo() {
        this.webDriver.get(PROFILE_PAGE_URL);
    }

    /**
     * Method which clicks on "ALL" tab
     */
    public void clickAllTab() {
        allButton.click();
    }

    /**
     * Method which selects first element form profile
     */
    public void selectFirstElementFromProfile() throws InterruptedException {


        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        List<WebElement> postsElements = webDriver.findElements(By.cssSelector("div.row.no-gutters"));
        if (!postsElements.isEmpty()) {
            WebElement firstElement = postsElements.get(0);
            Thread.sleep(2000);
            firstElement.click();
        }

    }

    /**
     * Method which clicks on "Delete" button
     */
    public void deletePost() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        deleteButton.click();
        yesButton.click();
    }

    /**
     * Method which checks that post is deleted successfully
     */
    public void verifyThatPostIsDeleted(String expectedDeletedPostText, String messageOnFailure) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        try {
            String actualDeletedPostText = "";
            while (actualDeletedPostText.isBlank())
                actualDeletedPostText = failureMessage.getText();
            Assert.assertEquals(actualDeletedPostText, expectedDeletedPostText, messageOnFailure);
        } catch (Exception e) {

        }

    }
}
