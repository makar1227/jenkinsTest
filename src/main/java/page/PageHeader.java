package page;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageHeader extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(PageHeader.class);

    @FindBy(xpath = ".//a[@class='has_arrow'][@data-link='shows']")
    private WebElement shows;

    @FindBy(xpath = ".//div[@class='menu_dropdown shows']//div[@class='column']/a[contains(@href,'episodes')]")
    private WebElement fullEpisodes;

    @FindBy(css = ".site_menu_wrapper")
    private WebElement page;

    public PageHeader() {
        super();
    }

    public PageHeader clickShowsLink() {
        logger.log(Level.INFO, "shows link");
        shows.click();
        return this;
    }

    @Override
    public boolean isPageOpened() {
        logger.log(Level.INFO, "isPageOpened");
        return page.isEnabled();
    }

    public ShowsPage selectFullEpisodes() {
        logger.log(Level.INFO, "full episodes");
        fullEpisodes.click();
        return new ShowsPage();
    }
}
