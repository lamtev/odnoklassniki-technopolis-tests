package core;

import com.google.common.base.Preconditions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("WeakerAccess")
public abstract class HelperBase {

    private static final By USER_CARD = By.xpath("//*[contains(@class, 'ucard-mini') and contains(@class, 'toolbar_ucard')]");
    private static final By LOGOUT_BUTTON = By.xpath("//*[contains(@data-l, 't,logoutCurrentUser')]");
    private static final By LOGOUT_CONFIRM = By.xpath("//*[contains(@data-l, 't,confirm') and contains(@value, 'Выйти')]");

    protected final WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
        check();
    }

    public static boolean explicitWait(final WebDriver driver, final ExpectedCondition<?> condition, long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        Preconditions.checkNotNull(condition, "Condition must be not null");
        Preconditions.checkArgument(TimeUnit.MINUTES.toSeconds(3) > maxCheckTimeInSeconds, "Max check time in seconds should be less than 3 minutes");
        checkConditionTimeouts(maxCheckTimeInSeconds, millisecondsBetweenChecks);
        try {
            // сбрасываем ожидания в 0
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            // создаем эксплицитное ожидание
            WebDriverWait explicitWait = new WebDriverWait(driver, maxCheckTimeInSeconds, millisecondsBetweenChecks);
            // проверяем его
            explicitWait.until(condition);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            // при любом результате восстанавливаем значение имплицитного ожидания по умолчанию
            if (driver != null) {
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            } else {
                throw new IllegalArgumentException("Driver shouldn't be null");
            }
        }
    }

    /**
     * Проверяет таймаут провекри условия и интервал между проверками: таймаут
     * должен быть больше нуля, интервал проверки должен быть больше нуля
     * интервал между проверками умноженный на 1000 должен быть меньше таймаута
     * проверки
     *
     * @param maxCheckTimeInSeconds     максимальное время проверки в секундах
     * @param millisecondsBetweenChecks интервал между проверками в милисекундах
     */
    private static void checkConditionTimeouts(long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        Preconditions.checkState(maxCheckTimeInSeconds > 0, "maximum check time in seconds must be not 0");
        Preconditions.checkState(millisecondsBetweenChecks > 0, "milliseconds count between checks must be not 0");
        Preconditions.checkState(millisecondsBetweenChecks < (maxCheckTimeInSeconds * 1000),
                "Millis between checks must be less than max seconds to wait");
    }

    protected abstract void check();

    protected void type(String text, By locator) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected void checkAndType(String text, By locator) {
        assertTrue(isElementVisible(locator));
        type(text, locator);
    }

    protected void selectOptionByVisibleText(By locator, String visibleText) {
        final Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(visibleText);
    }

    public LoginMainPage doLogout() {
        Assert.assertTrue("Не найдена иконка пользователя в тулбаре", isElementVisible(USER_CARD));
        click(USER_CARD);
        Assert.assertTrue("Не найдена кнопка выхода", isElementVisible(LOGOUT_BUTTON));
        click(LOGOUT_BUTTON);
        Assert.assertTrue("Не найдена кнопка подтверждения выхода", isElementVisible(LOGOUT_CONFIRM));
        click(LOGOUT_CONFIRM);
        return new LoginMainPage(driver);
    }

    protected boolean isElementVisible(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean explicitWait(final ExpectedCondition<?> condition, long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        return explicitWait(driver, condition, maxCheckTimeInSeconds, millisecondsBetweenChecks);
    }

}