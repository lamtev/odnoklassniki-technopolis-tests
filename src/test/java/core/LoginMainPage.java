package core;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public final class LoginMainPage extends HelperBase {

    private static final String LOGIN_FIELD = "field_email";
    private static final String PASSWORD_FIELD = "field_password";
    private static final By LOGIN_BUTTON = xpath(".//*[contains(@data-l,'loginButton')]");

    public LoginMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        assertTrue("Не дождались логина", explicitWait(visibilityOfElementLocated(LOGIN_BUTTON), 10, 500));
    }

    public UserMainPage doLogin(User bot) {
        type(bot.getLogin(), id(LOGIN_FIELD));
        type(bot.getPassword(), id(PASSWORD_FIELD));
        click(LOGIN_BUTTON);
        return new UserMainPage(driver);
    }
}
