package page;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import parser.TitleParser;
import util.Waiter;

import java.util.List;

public class EpisodePage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(EpisodePage.class);
    private static final int PROVIDERS = 12;

    @FindBy(xpath = ".//*[@id='player_slate_content']")
    private WebElement page;

    @FindBy(css = ".tveapp_24h")
    private WebElement startNowLink;

    @FindAll(@FindBy(css = ".tveapp_ePL-link"))
    private List<WebElement> providersLogos;

    public EpisodePage() {
        super();
    }

    public boolean checkLogos() {
        logger.log(Level.INFO, "checkLogos");
        Waiter.waitForElementVisible(providersLogos);
        return providersLogos.size() == PROVIDERS;
    }

    @Override
    public boolean isPageOpened() {
        return page.isDisplayed();
    }

    public String getShowTitle() {
        Waiter.waitForElementVisible(startNowLink);
        String title = TitleParser.getShowNameFromTitle(this.getTitle());
        logger.log(Level.INFO, "show title is "+title);
        return title;
    }

    public SignInPopUpPage clickStartNowLink() {
        startNowLink.click();
        return new SignInPopUpPage();
    }

}
