package object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LogoutPage {

    private final WebDriver webDriver;
    @FindBy(css = ".h4.mb-4")
    private WebElement signInText;


    public LogoutPage(WebDriver driver) {

        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }


    public void verifyThatUserIsSuccessfulLogouted(String expectedSignInText, String messageOnFailure) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        String actualProfileText = signInText.getText();
        Assert.assertTrue(actualProfileText.contains(expectedSignInText), messageOnFailure);
    }
}
