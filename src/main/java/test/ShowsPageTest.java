package test;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.EpisodePage;
import page.ShowsPage;

public class ShowsPageTest {

    private static final Logger logger = LogManager.getLogger(ShowsPageTest.class);
    private ShowsPage showsPage;
    private EpisodePage episodePage;

    @Test(groups = {"showsPage"}, dependsOnGroups = {"mainPage"})
    public void checkFirstEpisodeTitle() {
        logger.log(Level.INFO, "go to episode");
        showsPage = new ShowsPage();
        showsPage.setShowTitle();
        episodePage = showsPage.clickWatchEpisode();
        Assert.assertTrue(showsPage.getShowTitle().trim().equalsIgnoreCase(episodePage.getShowTitle()));
    }

    @Test(groups = {"showsPage"}, dependsOnMethods = {"checkFirstEpisodeTitle"})
    public void checkProviders() {
        logger.log(Level.INFO, "check providers");
        Assert.assertTrue(episodePage.checkLogos());
    }

}
