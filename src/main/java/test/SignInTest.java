package test;

import entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.EpisodePage;
import page.SignInPopUpPage;
import parser.ProjectProperties;

public class SignInTest {
    private static final Logger logger = LogManager.getLogger(SignInTest.class);
    private EpisodePage episodePage;
    private SignInPopUpPage signInPopUpPage;
    private User user;

    @BeforeClass
    private void init() {
        episodePage = new EpisodePage();
        user = new User(ProjectProperties.getProperties().getProperty("email"), ProjectProperties.getProperties().getProperty("password"));
    }

    @Test(dependsOnGroups = {"showsPage"})
    public void startClick() {
        logger.log(Level.INFO, "start watching");
        signInPopUpPage = episodePage.clickStartNowLink();
        Assert.assertTrue(signInPopUpPage.isPageOpened());
    }

    @Test(dependsOnMethods = {"startClick"})
    public void checkFirstHint() {
        logger.log(Level.INFO, "first hint");
        Assert.assertTrue(signInPopUpPage.checkFirstSignInHint());
    }

    @Test(dependsOnMethods = {"checkFirstHint"})
    public void signInFirstStep() {
        logger.log(Level.INFO, "second hint");
        signInPopUpPage.clickFirstSignInLink();
        Assert.assertTrue(signInPopUpPage.checkSecondSignInHint());
    }

    @Test(dependsOnMethods = {"signInFirstStep"})
    public void signInSecondStep() {
        logger.log(Level.INFO, "sign in ");
        signInPopUpPage.clickSecondSignInLink();
        signInPopUpPage.setEmail(user).setPassword(user);
        signInPopUpPage.clickSignInButton();
        Assert.assertTrue(signInPopUpPage.isErrorAppeared());
    }

    @Test(dependsOnMethods = {"signInSecondStep"})
    public void checkErrorReason() {
        logger.log(Level.INFO, "check error reason");
        Assert.assertTrue(signInPopUpPage.isPreviewPeriodExpired());
    }
}
