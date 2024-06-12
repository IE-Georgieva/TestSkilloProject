package object;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;


public class LoginPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/users/login";
    private final WebDriver webDriver;

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

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }


    public boolean isLoaded() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(PAGE_URL));

    }

    /**
     * Navigate to Login page
     */
    public void navigateTo() {
        this.webDriver.get(PAGE_URL);
    }


    /**
     * Method which populates username and password and clicks on "Sign in" button
     *
     * @param username - Text field for username
     * @param password - Text field for password
     */
    public void populateRegistrationForm(String username, String email, String password, String confirmPassword) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(usernameTextFieldRegisterForm));
        usernameTextFieldRegisterForm.sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(emailTextField));
        emailTextField.sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordTextFieldRegisterForm));
        passwordTextFieldRegisterForm.sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordTextField));
        confirmPasswordTextField.sendKeys(confirmPassword);
        signInButton.click();

    }

    /**
     * Method which populates Login form
     *
     * @param username - Text field for username
     * @param password - Text field for password
     */
    public void populateLoginForm(String username, String password) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(usernameTextFieldSignInForm));
        usernameTextFieldSignInForm.sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(passwordTextFieldSignInForm));
        passwordTextFieldSignInForm.sendKeys(password);
        signInButton.click();

    }

    /**
     * Method which checks that registration is successful
     */
    public void verifyRegistrationIsSuccessful() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        String expectedUrl = "http://training.skillo-bg.com:4200/posts/all";
        String actualUrl = homePage.getAttribute("href");
        Assert.assertEquals(actualUrl, expectedUrl, "Incorrect link is displayed!");
    }


    /**
     * Method which clicks on register button
     */
    public void clickRegisterButton() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        registerButton.click();
    }


}
