package page;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import util.Waiter;

import java.util.List;

public class ShowsPage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(ShowsPage.class);
    private String showTitle;

    @FindAll(@FindBy(xpath = ".//div[@class='image_holder']/img"))
    private List<WebElement> episodeLinks;

    @FindAll(@FindBy(css = ".label.item_sticker.link_wrapper"))
    private List<WebElement> episodeTitles;

    private static final String PAGE_TITLE = "MTV.com | Full Episodes";

    public ShowsPage() {
        super();
    }

    public SignInPopUpPage clickWatchEpisode() {
        Waiter.waitForPageLoad();
        episodeLinks.get(0).click();
        return new SignInPopUpPage();
    }

    @Override
    public boolean isPageOpened() {
        Waiter.waitForElementVisible(episodeTitles);
        String title=getTitle().trim();
        logger.log(Level.INFO, "episode title is " + title);
        return title.equalsIgnoreCase(PAGE_TITLE);
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle() {
        Waiter.waitForPageLoad();
        showTitle = episodeTitles.get(0).getText();
    }
}
