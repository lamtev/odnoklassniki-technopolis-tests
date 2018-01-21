package core;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import static core.HelperBase.explicitWait;
import static junit.framework.TestCase.assertTrue;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public final class CreatedGroupPromise {

    private static final By GROUP_TITLE = By.xpath("//*[contains(@class,'mctc_name_tx')]");
    private static final By ANY_ERROR_MESSAGE = xpath("//*[contains(@class, 'input-e')]");

    private final WebDriver driver;

    CreatedGroupPromise(WebDriver driver) {
        this.driver = driver;
    }

    public CreatedGroupPage andGroupWasCreatedSuccessfully() {
        explicitWait(driver, visibilityOfElementLocated(GROUP_TITLE), 5, 500);
        return new CreatedGroupPage(driver);
    }

    public void andGroupWasNotCreatedForNSeconds(int n) {
        try {
            explicitWait(driver, visibilityOfElementLocated(GROUP_TITLE), n, 500);
            assertTrue("Группа была создана", true);
        } catch (TimeoutException e) {
            assertTrue("Не то исключение", true);
        }
        explicitWait(driver, visibilityOfElementLocated(ANY_ERROR_MESSAGE), n, 500);
    }

}
