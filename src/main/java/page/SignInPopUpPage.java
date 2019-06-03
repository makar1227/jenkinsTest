package page;

import entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import util.DriverFactory;
import util.DriverManager;
import util.Waiter;

public class SignInPopUpPage extends EpisodePage {
    private static final Logger logger = LogManager.getLogger(SignInPopUpPage.class);

    @FindBy(xpath = " .//*[@id='iui-component']")
    private WebElement page;

    @FindBy(xpath = ".//*[@id='iui-component']/div[@class='container iui-container iui-welcome']/div[@class='row']/div//a")
    private WebElement firstSignInLink;

    @FindBy(xpath = ".//*[@id='iui-component']/div[@class='container iui-container iui-welcome']/div[@class='row']/div//a/img")
    private WebElement firstSignInHint;

    @FindBy(xpath = ".//*[@id='iui-component']/div[@class='container iui-container iui-signup']/div[@class='row']/div/p/a")
    private WebElement secondSignInLink;

    @FindBy(xpath = ".//*[@id='iui-component']/div[@class='container iui-container iui-signup']/div[@class='row']/div/p/a/img")
    private WebElement secondSignInHint;

    @FindBy(xpath = ".//*[@id='iui-email-20']")
    private WebElement emailFieldForSignIn;

    @FindBy(xpath = ".//*[@id='iui-password-22']")
    private WebElement passwordFieldForSignIn;

    @FindBy(xpath = ".//*[@id='iui-email-37']")
    private WebElement emailFieldForSignUp;

    @FindBy(xpath = ".//*[@id='iui-password-39']")
    private WebElement passwordFieldForSignUp;

    @FindBy(xpath = ".//*[@id='iui-password-confirm-41']")
    private WebElement repeatPasswordFieldForSignUp;

    @FindBy(xpath = ".//*[@id='iui-component']//div[@class='col-xs-8 iui-form-field ']/p")
    private WebElement dateFormatMassage;

    @FindBy(css = ".iui-ape-signup-btn")
    private WebElement signUpButton;

    @FindBy(css = ".iui-ape-login-btn")
    private WebElement signInButton;

    @FindBy(css = ".page-overlay_headline")
    private WebElement errorMessage;

    @FindBy(css = " .page-overlay_description")
    private WebElement errorReason;

    @FindBy(xpath = ".//*[@id='tveapp_vipLogin']/div[@class='tveapp_header']/a[@class='tveapp_button tveapp_close']")
    private WebElement closePopUpButton;

    private static final String FIRST_SIGN_IN_HINT = "Or you can use your email";
    private static final String SECOND_SIGN_IN_HINT = "Have an account already? Sign in";
    private static final String ERROR_REASON = "The free preview has expired.";
    private static final String TITLE = "title";

    public SignInPopUpPage() {
        super();
    }

    public boolean checkFirstSignInHint() {
        String hint = firstSignInHint.getAttribute(TITLE);
        logger.log(Level.INFO, "first hint is " + hint);
        return hint.trim().equalsIgnoreCase(FIRST_SIGN_IN_HINT);
    }

    public boolean checkSecondSignInHint() {
        String hint = secondSignInHint.getAttribute(TITLE);
        logger.log(Level.INFO, "second hint is " + hint);
        return hint.trim().equalsIgnoreCase(SECOND_SIGN_IN_HINT);
    }

    public SignInPopUpPage clickFirstSignInLink() {
        Waiter.waitForPageLoad();
        firstSignInLink.click();
        return this;
    }

    public SignInPopUpPage clickSecondSignInLink() {
        Waiter.waitForPageLoad();
        secondSignInLink.click();
        return this;
    }

    public EpisodePage closePopUp() {
        new Actions(DriverManager.getInstance().getDriver()).click(closePopUpButton).build().perform();
        logger.log(Level.INFO, "pop up was closed");
        return new EpisodePage();
    }

    public SignInPopUpPage setEmail(User user) {
        emailFieldForSignIn.sendKeys(user.getEmail());
        return this;
    }

    public SignInPopUpPage setPassword(User user) {
        passwordFieldForSignIn.sendKeys(user.getPassword());
        return this;
    }

    public SignInPopUpPage clickSignInButton() {
        signInButton.click();
        return this;
    }

    public SignInPopUpPage setDataForSignUp(User user) {
        passwordFieldForSignUp.sendKeys(user.getPassword());
        emailFieldForSignUp.sendKeys(user.getEmail());
        repeatPasswordFieldForSignUp.sendKeys(user.getPassword());
        signUpButton.click();
        return this;
    }

    public boolean isDataFormatErrorMassageDisplayed() {
        return dateFormatMassage.isDisplayed();
    }

    public boolean isErrorAppeared() {
        return errorMessage.isDisplayed();
    }

    private String getErrorReason() {
        return errorReason.getText();
    }

    public boolean isPreviewPeriodExpired() {
        String error = getErrorReason().trim();
        logger.log(Level.INFO, "error is " + error);
        return error.equalsIgnoreCase(ERROR_REASON);
    }

    @Override
    public boolean isPageOpened() {
        Waiter.waitForElementVisible(page);
        return page.isDisplayed();
    }
}
