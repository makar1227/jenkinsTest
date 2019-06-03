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

public class SignUpTest {

    private static final Logger logger = LogManager.getLogger(SignUpTest.class);
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
        logger.log(Level.INFO, "registration");
        signInPopUpPage = episodePage.clickStartNowLink();
        signInPopUpPage.clickFirstSignInLink().setDataForSignUp(user);
        Assert.assertTrue(signInPopUpPage.isDataFormatErrorMassageDisplayed());
    }

    @Test(dependsOnMethods = {"startClick"})
    public void closePopUp() {
        logger.log(Level.INFO, "close pop up");
        episodePage = signInPopUpPage.closePopUp();
        Assert.assertTrue(episodePage.isPageOpened());
    }
}

