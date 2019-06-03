package page;

import org.openqa.selenium.support.PageFactory;
import util.DriverManager;

public abstract class AbstractPage {
    AbstractPage() {
        PageFactory.initElements(DriverManager.getInstance().getDriver(), this);
    }

    String getTitle() {
        return DriverManager.getInstance().getDriver().getTitle();
    }

    public abstract boolean isPageOpened();
}
