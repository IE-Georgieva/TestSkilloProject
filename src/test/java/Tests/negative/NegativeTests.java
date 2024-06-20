package Tests.negative;

import Tests.TestObject;
import org.testng.annotations.Test;


public class NegativeTests extends TestObject {

    @Test(groups = "tests")
    public void unsuccessfulLogOut() {
        loginPage.navigateTo();
        loginPage.populateLoginForm("test8777", "878787");
        header.clickOnLogoutButton();
        logoutPage.verifyThatUserIsSuccessfulLogout("Sign in1", "Different title is displayed!");
    }

    @Test(groups = "tests")
    public void unsuccessfulDeletePost() throws InterruptedException {
        loginPage.navigateTo();
        loginPage.populateLoginForm("test8777", "878787");
        header.clickProfile();
        profilePage.clickAllTab();
        profilePage.selectFirstElementFromProfile();
        profilePage.deletePost();
        profilePage.verifyThatPostIsDeleted();
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
