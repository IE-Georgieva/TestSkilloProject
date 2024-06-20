package object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends BaseComponent {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/users/login";

    @FindBy(css = "[name=username]")
    private WebElement usernameTextFieldRegisterForm;
    @FindBy(css = "input[type=\"email\"]")
    private WebElement emailTextField;
    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordTextFieldRegisterForm;
    @FindBy(id = "defaultRegisterPhonePassword")
    private WebElement confirmPasswordTextField;
    @FindBy(css = "button#sign-in-button")
    private WebElement signInButton;
    @FindBy(css = "a[href='/users/register'] ")
    private WebElement registerButton;
    @FindBy(id = "nav-link-home")
    private WebElement homePage;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameTextFieldSignInForm;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordTextFieldSignInForm;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    private Header header = super.header;

    public boolean isLoaded() {
        performWait();
        return webDriverWait.until(ExpectedConditions.urlToBe(PAGE_URL));

    }

    public void navigateTo() {
        this.webDriver.get(PAGE_URL);
    }

    public void populateRegistrationForm(String username, String email, String password, String confirmPassword) {
        performWait();
        webDriverWait.until(ExpectedConditions.visibilityOf(usernameTextFieldRegisterForm));
        usernameTextFieldRegisterForm.sendKeys(username);
        webDriverWait.until(ExpectedConditions.visibilityOf(emailTextField));
        emailTextField.sendKeys(email);
        webDriverWait.until(ExpectedConditions.visibilityOf(passwordTextFieldRegisterForm));
        passwordTextFieldRegisterForm.sendKeys(password);
        webDriverWait.until(ExpectedConditions.visibilityOf(confirmPasswordTextField));
        confirmPasswordTextField.sendKeys(confirmPassword);
        signInButton.click();

    }

    public void populateLoginForm(String username, String password) {
        performWait();
        webDriverWait.until(ExpectedConditions.visibilityOf(usernameTextFieldSignInForm));
        usernameTextFieldSignInForm.sendKeys(username);
        webDriverWait.until(ExpectedConditions.visibilityOf(passwordTextFieldSignInForm));
        passwordTextFieldSignInForm.sendKeys(password);
        signInButton.click();

    }

    public void verifyRegistrationIsSuccessful() {
        performWait();
        String expectedUrl = "http://training.skillo-bg.com:4200/posts/all";
        String actualUrl = homePage.getAttribute("href");
        Assert.assertEquals(actualUrl, expectedUrl, "Incorrect link is displayed!");
    }

    public void clickRegisterButton() {
        performWait();
        registerButton.click();
    }
}
