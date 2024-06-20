package Tests.positive;

import Tests.TestObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class SuccessfulTests extends TestObject {
    private static final Logger log = LoggerFactory.getLogger(SuccessfulTests.class);

    @DataProvider(name = "getPath")
    public Object[][] getPath() {
        File postPicture = new File("src\\test\\resources\\upload\\I3E4FcV.jpeg");
        String caption = "Test picture";
        return new Object[][]{

                {postPicture, caption},
        };
    }

    @Test(groups = "tests")
    public void successfulRegistration() {
        loginPage.navigateTo();
        loginPage.clickRegisterButton();
        loginPage.populateRegistrationForm("test94236", "test94236@abv.bg", "878787", "878787");
        loginPage.verifyRegistrationIsSuccessful();
    }

    @Test(dataProvider = "getPath", groups = "tests")
    public void successfulPrivatePost(File postPicture, String caption) {
        loginPage.navigateTo();
        loginPage.populateLoginForm("test8777", "878787");
        header.clickProfile();
        newPostPage.navigateTo();
        newPostPage.uploadPicture(postPicture);
        newPostPage.typePostCaption(caption);
        newPostPage.changeToggleToPrivate();
        newPostPage.clickSubmitButton();
        newPostPage.verifyPrivatePostIsSuccessful("Post created!", "Post is not created!");
    }

    @Test(dataProvider = "getPath", groups = "tests")
    public void successfulPublicPost(File postPicture, String caption) {
        loginPage.navigateTo();
        loginPage.populateLoginForm("test8777", "878787");
        header.clickProfile();
        newPostPage.navigateTo();
        newPostPage.uploadPicture(postPicture);
        newPostPage.typePostCaption(caption);
        newPostPage.clickSubmitButton();
        newPostPage.verifyPublicPostIsSuccessful("Post created!", "Different message is displayed!");
    }

    @Test(groups = "tests")
    public void successfulDeletePost() throws InterruptedException {
        loginPage.navigateTo();
        loginPage.populateLoginForm("test8777", "878787");
        header.clickProfile();
        profilePage.clickAllTab();
        profilePage.selectFirstElementFromProfile();
        profilePage.deletePost();
        profilePage.verifyThatPostIsDeleted();
    }

    @Test(groups = "tests")
    public void successfulDislikedPost() throws InterruptedException {
        loginPage.navigateTo();
        loginPage.populateLoginForm("test8777", "878787");
        homepage.selectFirstElement();
        homepage.dislikePost();
        homepage.verifyThatFirstPostIsDisliked("Post disliked", "Different message is displayed!");
    }

    @Test(groups = "tests")
    public void successfulLikedPost() throws InterruptedException {
        loginPage.navigateTo();
        loginPage.populateLoginForm("test8777", "878787");
        homepage.selectFirstElement();
        homepage.likePost();
        homepage.verifyThatFirstPostIsLiked("Post liked", "Different message is displayed!");
    }

    @Test(groups = "tests")
    public void successfulPostedComment() throws InterruptedException {
        loginPage.navigateTo();
        loginPage.populateLoginForm("test8777", "878787");
        homepage.selectFirstElement();
        homepage.writeCommentOnFirstPost();
        homepage.verifyThatCommentIsPostedSuccessful("test comment", "Different message is displayed!");
    }

    @Test(groups = "tests")
    public void successfulLogOut() {
        loginPage.navigateTo();
        loginPage.populateLoginForm("test8777", "878787");
        header.clickOnLogoutButton();
        logoutPage.verifyThatUserIsSuccessfulLogout("Sign in", "User is not logged out!");
    }
}



