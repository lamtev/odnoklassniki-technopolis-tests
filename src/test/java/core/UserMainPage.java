package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.xpath;

@SuppressWarnings("WeakerAccess")
public class UserMainPage extends HelperBase {

    private static final By GROUPS_TOOLBAR = xpath(".//*[@class='mctc_navMenuSec' and contains(@href,'groups')]");
    private static final By GROUPS_COUNTER = By.xpath("//*[contains(@href,'groups')]//*[contains(@class,'navMenuCount')]");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        assertTrue("Не дождались тулбара с группами", explicitWait(ExpectedConditions.visibilityOfElementLocated(GROUPS_TOOLBAR), 10, 500));
    }

    public GroupsMainPage clickGroupsOnToolbar() {
        click(GROUPS_TOOLBAR);
        return new GroupsMainPage(driver);
    }

    public int groupsCounter() {
        assertTrue("Счётчика групп нет", isElementPresent(GROUPS_COUNTER));
        final WebElement counter = driver.findElement(GROUPS_COUNTER);
        return Integer.parseInt(counter.getText());
    }

}
