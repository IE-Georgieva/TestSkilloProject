package object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;


public class ProfilePage extends BasePage {
    public static final String PROFILE_PAGE_URL = "http://training.skillo-bg.com:4200/users/";

    @FindBy(css = "app-profile-posts-section label:first-of-type")
    private WebElement allButton;
    @FindBy(css = "label.delete-ask")
    private WebElement deleteButton;
    @FindBy(css = "button:first-child")
    private WebElement yesButton;
    @FindBy(css = ".toast-container")
    private WebElement failureMessage;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }


    public boolean isUrlLoaded() {
        performWait();
        return webDriverWait.until(ExpectedConditions.urlContains(PROFILE_PAGE_URL));
    }

    public boolean isUrlLoaded(String userId) {
        performWait();
        return webDriverWait.until(ExpectedConditions.urlToBe(PROFILE_PAGE_URL + userId));
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

        performWait();
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
        performWait();
        deleteButton.click();
        yesButton.click();
    }

    /**
     * Method which checks that post is deleted successfully
     */
    public void verifyThatPostIsDeleted(String expectedDeletedPostText, String messageOnFailure) throws InterruptedException {
        performWait();
        try {
            String actualDeletedPostText = "";
            while (actualDeletedPostText.isBlank())
                actualDeletedPostText = failureMessage.getText();
            Assert.assertEquals(actualDeletedPostText, expectedDeletedPostText, messageOnFailure);
        } catch (Exception e) {

        }

    }
}
