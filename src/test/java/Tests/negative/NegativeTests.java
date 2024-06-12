package Tests.negative;

import Tests.TestObject;
import object.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class NegativeTests extends TestObject {


    @Test(groups = "tests")
    public void unsuccessfulLogOut() {
        loginPage.navigateTo();
        loginPage.populateLoginForm("test8777", "878787");
        header.clickOnLogoutButton();
        logoutPage.verifyThatUserIsSuccessfulLogouted("Sign in1", "Different title is displayed!");
    }

    @Test(groups = "tests")
    public void unsuccessfulDeletePost() throws InterruptedException {
        loginPage.navigateTo();
        loginPage.populateLoginForm("test8777", "878787");
        header.clickProfile();
        profilePage.clickAllTab();
        profilePage.selectFirstElementFromProfile();
        profilePage.deletePost();
        profilePage.verifyThatPostIsDeleted("Post Deleted!23", "Different message is displayed!");
    }
    @Test(groups = "tests")
    public void successfulPostedComment() throws InterruptedException {
        loginPage.navigateTo();
        loginPage.populateLoginForm("test8777", "878787");
        homepage.selectFirstElement();
        homepage.writeCommentOnFirstPost();
        homepage.verifyThatCommentIsPostedSuccessful("test", "Different message is displayed!");
    }
}
