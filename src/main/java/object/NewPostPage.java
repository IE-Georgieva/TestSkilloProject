package object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.File;


public class NewPostPage extends BasePage {
    public static final String NEWPOST_URL = "http://training.skillo-bg.com:4200/posts/create";


    @FindBy(css = "input.form-control.input-lg")
    private WebElement uploadPictureText;
    @FindBy(name = "caption")
    private WebElement postCaption;
    @FindBy(id = "create-post")
    private WebElement submitButton;
    @FindBy(css = "label[for=\"customSwitch2\"]")
    private WebElement toggle;
    @FindBy(css = ".toast-container")
    private WebElement failureMessage;

    public NewPostPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void navigateTo() {
        this.webDriver.get(NEWPOST_URL);
    }


    public void uploadPicture(File file) {

        WebElement uploadFile = webDriver.findElement(By.cssSelector(".form-group > input"));
        uploadFile.sendKeys(file.getAbsolutePath());
    }

    public boolean isImageUploaded(String fileName) {
        String actualText = uploadPictureText.getAttribute("placeholder");
        if (actualText.equals(fileName)) {
            return true;
        }
        return false;
    }

    public String uploadedImageText() {
        return uploadPictureText.getAttribute("placeholder");
    }

    public void typePostCaption(String text) {
        postCaption.sendKeys(text);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void changeToggleToPrivate() {
        toggle.click();
    }

    /**
     * Method which checks that public post is created successfully
     *
     * @param expectedPublicPostText - Expected message which should be displayed after public post is created
     * @param messageOnFailure       - Message which is displayed when test fail
     */
    public void verifyPublicPostIsSuccessful(String expectedPublicPostText, String messageOnFailure) {
        performWait();
        try {
            String actualPublicPostText = "";
            while (actualPublicPostText.isBlank())
                actualPublicPostText = failureMessage.getText();
            Assert.assertEquals(actualPublicPostText, expectedPublicPostText, messageOnFailure);
        } catch (Exception e) {

        }
    }

    /**
     * Method which checks that private post is created successfully
     *
     * @param expectedPrivatePostText - Expected message which should be displayed after private post is created
     * @param messageOnFailure        - Message which is displayed when test fail
     */
    public void verifyPrivatePostIsSuccessful(String expectedPrivatePostText, String messageOnFailure) {
        performWait();
        try {
            String actualPrivatePostText = "";
            while (actualPrivatePostText.isBlank())
                actualPrivatePostText = failureMessage.getText();
            Assert.assertEquals(actualPrivatePostText, expectedPrivatePostText, messageOnFailure);
        } catch (Exception e) {

        }
    }


}
