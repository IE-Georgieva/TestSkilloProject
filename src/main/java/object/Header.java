package object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Header extends BaseComponent {
    @FindBy(id = "nav-link-login")
    private WebElement loginLink;
    @FindBy(id = "nav-link-profile")
    private WebElement profilePageLink;
    @FindBy(css = ".fas.fa-sign-out-alt.fa-lg")
    private WebElement logoutButton;

    public Header(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickLogin() {
        performWait();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLink.click();
    }

    public void clickProfile() {
        performWait();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(profilePageLink));
        profilePageLink.click();
    }

    public void clickOnLogoutButton() {
        performWait();
        webDriverWait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
    }
}
