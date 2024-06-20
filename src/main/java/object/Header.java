package object;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class Header extends BasePage {


    @FindBy(id = "nav-link-login")
    private WebElement loginLink;
    @FindBy(id = "nav-link-profile")
    private WebElement profilePageLink;
    @FindBy(css = ".fas.fa-sign-out-alt.fa-lg")
    private WebElement logoutButton;

    public Header(WebDriver webDriver) {
        super(webDriver);
    }


    /**
     * Navigate to Login page
     */
    public void clickLogin() {
        performWait();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLink.click();
    }

    /**
     * Navigate to Profile page
     */
    public void clickProfile() {
        performWait();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(profilePageLink));
        profilePageLink.click();
    }

    /**
     * Method which log out user from the site
     */
    public void clickOnLogoutButton() {
        performWait();
        webDriverWait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
    }


}
