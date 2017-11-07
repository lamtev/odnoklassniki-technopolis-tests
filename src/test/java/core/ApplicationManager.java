package core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import model.TestBot;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class ApplicationManager {
    private UserMainPageHelper userMainPageHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();
    protected WebDriver driver;


    public void init() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1600,1200));
        baseUrl = "https://ok.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/");
        groupHelper = new GroupHelper(driver);
        userMainPageHelper = new UserMainPageHelper(driver);
        sessionHelper = new SessionHelper(driver);
        sessionHelper.doLogin(new TestBot("technopolisbot", "technopolis16"));
    }

    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public UserMainPageHelper getUserMainPageHelper() {
        return userMainPageHelper;
    }
}
