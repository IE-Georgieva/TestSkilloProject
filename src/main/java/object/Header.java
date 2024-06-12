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

public class Header {

    private final WebDriver webDriver;

    @FindBy(id = "nav-link-login")
    private WebElement loginLink;
    @FindBy(id = "nav-link-profile")
    private WebElement profilePageLink;
    @FindBy(css = ".fas.fa-sign-out-alt.fa-lg")
    private WebElement logoutButton;


    public Header(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Navigate to Login page
     */
    public void clickLogin() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLink.click();
    }

    /**
     * Navigate to Profile page
     */
    public void clickProfile() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(profilePageLink));
        profilePageLink.click();
    }

    /**
     * Method which log out user from the site
     */
    public void clickOnLogoutButton() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
    }


}
