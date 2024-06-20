package object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class LogoutPage extends BasePage {


    @FindBy(css = ".h4.mb-4")
    private WebElement signInText;

    public LogoutPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void verifyThatUserIsSuccessfulLogout(String expectedSignInText, String messageOnFailure) {
        performWait();
        String actualProfileText = signInText.getText();
        Assert.assertTrue(actualProfileText.contains(expectedSignInText), messageOnFailure);
    }
}
