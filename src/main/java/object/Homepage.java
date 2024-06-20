package object;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


import java.util.List;


public class Homepage extends BasePage {
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


    /**
     * Navigate to Skillo homepage
     */
    public void navigateTo() {
        this.webDriver.get(HOME_URL);
    }

    /**
     * Check that if Homepage is loaded
     */
    public boolean isLoaded() {

        performWait();
        return webDriverWait.until(ExpectedConditions.urlToBe(HOME_URL));
    }


    /**
     * Method which selects first element of all displayed on the page
     */

    public void selectFirstElement() throws InterruptedException {

        performWait();
        List<WebElement> postsElements = webDriver.findElements(By.cssSelector("div.post-feed-container"));
        if (!postsElements.isEmpty()) {
            WebElement firstElement = postsElements.get(0);
            Thread.sleep(2000);
            firstElement.click();
        }


    }

    /**
     * Method which checks that post is liked
     *
     * @return - Returns true or false
     */
    public boolean isPostLiked() {
        WebElement heartElement = webDriver.findElement(By.cssSelector("i.like"));
        String text = heartElement.getAttribute("class");
        if (text.contains("liked")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method which clicks on like button
     */
    public void likePost() {
        if (isPostLiked() == false) {
            likeButton.click();
        }

    }

    /**
     * Method which clicks on like button and after that on dislike
     */
    public void dislikePost() {
        dislikeButton.click();
    }

    /**
     * Method which post comment on first post
     */
    public void writeCommentOnFirstPost() {
        comment.sendKeys("test comment");
        comment.sendKeys(Keys.ENTER);
    }

    /**
     * Method which checks that first post is disliked
     *
     * @param expectedDislikePostText - Expected message which should be displayed after post is deleted
     * @param messageOnFailure        - Message which is displayed when test fail
     */
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

    /**
     * Method which checks that first post is liked
     *
     * @param expectedLikePostText - Expected message which should be displayed after post is liked
     * @param messageOnFailure     - Message which is displayed when test fail
     */

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

    /**
     * @param expectedCommentPostText - Expected comment which should posted
     * @param messageOnFailure        - Message which is displayed when test fail
     */

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

