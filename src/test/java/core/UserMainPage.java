package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@SuppressWarnings("WeakerAccess")
public final class UserMainPage extends HelperBase {

    private static final String GROUPS_TOOLBAR_STR = ".//*[@class='mctc_navMenuSec' and contains(@href,'groups')]";
    private static final By GROUPS_TOOLBAR = xpath(GROUPS_TOOLBAR_STR);
    private static final String GROUPS_COUNTER_STR = "//*[contains(@href,'groups')]//*[contains(@class,'navMenuCount')]";
    private static final By GROUPS_COUNTER = By.xpath(GROUPS_COUNTER_STR);
    private static final Map<By, String> CHECK_MAP = new HashMap<>();

    static {
        CHECK_MAP.put(GROUPS_TOOLBAR, GROUPS_TOOLBAR_STR);
        CHECK_MAP.put(GROUPS_COUNTER, GROUPS_COUNTER_STR);
    }

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        CHECK_MAP.forEach((locator, text) -> assertTrue(
                "Не дождались появления поля" + text + " в объекте " + toString(),
                explicitWait(visibilityOfElementLocated(locator), 5, 500)
        ));
    }

    public GroupsMainPage clickGroupsOnToolbar() {
        click(GROUPS_TOOLBAR);
        return new GroupsMainPage(driver);
    }

    public int groupsCounter() {
        assertTrue("Счётчика групп нет", isElementVisible(GROUPS_COUNTER));
        final WebElement counter = driver.findElement(GROUPS_COUNTER);
        return Integer.parseInt(counter.getText());
    }

}
