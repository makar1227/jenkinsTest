package test;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.PageHeader;
import page.ShowsPage;

public class MainPageTest {

    private static final Logger logger = LogManager.getLogger(MainPageTest.class);
    private PageHeader mainPage;
    private ShowsPage showsPage;

    @BeforeClass
    private void init() {
        mainPage = new PageHeader();
    }

    @Test
    public void checkPage() {
        logger.log(Level.INFO, "check page test");
        Assert.assertTrue(!mainPage.isPageOpened());
    }

    @Test(groups = {"mainPage"})
    public void getFullEpisodes() {
        logger.log(Level.INFO, "click full episodes");
        showsPage = mainPage.clickShowsLink().selectFullEpisodes();
        Assert.assertTrue(showsPage.isPageOpened());
    }

}
