package core;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public final class LoginMainPage extends HelperBase {

    private static final String LOGIN_FIELD_STR = "field_email";
    private static final By LOGIN_FIELD = id(LOGIN_FIELD_STR);
    private static final String PASSWORD_FIELD_STR = "field_password";
    private static final By PASSWORD_FIELD = id(PASSWORD_FIELD_STR);
    private static final String LOGIN_BUTTON_STR = ".//*[contains(@data-l,'loginButton')]";
    private static final By LOGIN_BUTTON = xpath(LOGIN_BUTTON_STR);
    private static final Map<By, String> CHECK_MAP = new HashMap<>();

    static {
        CHECK_MAP.put(LOGIN_FIELD, LOGIN_FIELD_STR);
        CHECK_MAP.put(PASSWORD_FIELD, PASSWORD_FIELD_STR);
        CHECK_MAP.put(LOGIN_BUTTON, LOGIN_BUTTON_STR);
    }

    public LoginMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        CHECK_MAP.forEach((locator, text) -> assertTrue(
                "Не дождались появления поля" + text + " в объекте " + toString(),
                explicitWait(visibilityOfElementLocated(locator), 5, 500)
        ));
    }

    public UserMainPage doLogin(User bot) {
        type(bot.getLogin(), LOGIN_FIELD);
        type(bot.getPassword(), PASSWORD_FIELD);
        click(LOGIN_BUTTON);
        return new UserMainPage(driver);
    }
}
