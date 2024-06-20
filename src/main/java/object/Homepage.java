package object;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class Homepage extends BaseComponent {
    public static final String HOME_URL = "http://training.skillo-bg.com:4200/posts/all";

    @FindBy(css = ".like.far.fa-heart.fa-2x")
    private WebElement likeButton;
    @FindBy(css = "app-post-modal i.ml-4.far.fa-thumbs-down.fa-2x")
    private WebElement dislikeButton;
    @FindBy(css = "fieldset input[formcontrolname=\"content\"]")
    private WebElement comment;
    @FindBy(css = ".toast-container")
    private WebElement failureMessage;
    @FindBy(css = ".col-12.comment-content")
    private WebElement commentText;

    public Homepage(WebDriver webDriver) {
        super(webDriver);
    }
    private Header header = super.header;

    public void navigateTo() {
        this.webDriver.get(HOME_URL);
    }

    public boolean isLoaded() {

        performWait();
        return webDriverWait.until(ExpectedConditions.urlToBe(HOME_URL));
    }

    public void selectFirstElement() throws InterruptedException {

        performWait();
        List<WebElement> postsElements = webDriver.findElements(By.cssSelector("div.post-feed-container"));
        if (!postsElements.isEmpty()) {
            WebElement firstElement = postsElements.get(0);
            Thread.sleep(2000);
            firstElement.click();
        }
    }

    public boolean isPostLiked() {
        WebElement heartElement = webDriver.findElement(By.cssSelector("i.like"));
        String text = heartElement.getAttribute("class");
        if (text.contains("liked")) {
            return true;
        } else {
            return false;
        }
    }

    public void likePost() {
        if (isPostLiked() == false) {
            likeButton.click();
        }

    }

    public void dislikePost() {

        dislikeButton.click();
    }

    public void writeCommentOnFirstPost() {
        comment.sendKeys("test comment");
        comment.sendKeys(Keys.ENTER);
    }

    public void verifyThatFirstPostIsDisliked(String expectedDislikePostText, String messageOnFailure) {
        performWait();
        try {
            String actualDislikePostText = "";
            while (actualDislikePostText.isBlank())
                actualDislikePostText = failureMessage.getText();
            Assert.assertEquals(actualDislikePostText, expectedDislikePostText, messageOnFailure);
        } catch (Exception e) {

        }
    }

    public void verifyThatFirstPostIsLiked(String expectedLikePostText, String messageOnFailure) {
        performWait();
        try {
            String actualLikePostText = "";
            while (actualLikePostText.isBlank())
                actualLikePostText = failureMessage.getText();
            Assert.assertEquals(actualLikePostText, expectedLikePostText, messageOnFailure);
        } catch (Exception e) {

        }
    }

    public void verifyThatCommentIsPostedSuccessful(String expectedCommentPostText, String messageOnFailure) {
        performWait();
        try {
            String actualDeletedPostText = "";
            while (actualDeletedPostText.isBlank())
                actualDeletedPostText = commentText.getText();
            Assert.assertEquals(actualDeletedPostText, expectedCommentPostText, messageOnFailure);
        } catch (Exception e) {

        }
    }
}

