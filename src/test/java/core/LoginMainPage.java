package core;

import model.TestBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginMainPage extends HelperBase{

    public LoginMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //todo
    }

    public void doLogin(TestBot testBot) {
        type(testBot.getLogin(), By.id("field_email"));
        type(testBot.getPassword(), By.id("field_password"));
        click(By.xpath(".//*[contains(@data-l,'loginButton')]"));
    }
}
