package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class ApplicationManager {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    protected void init() {
        driver = new ChromeDriver();
        baseUrl = "https://ok.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/");
        doLogin(new TestBot("technopolisbot", "technopolis16"));
    }

    private void doLogin(TestBot testBot) {
        driver.findElement(By.id("field_email")).clear();
        driver.findElement(By.id("field_email")).sendKeys(testBot.getLogin());
        driver.findElement(By.id("field_password")).clear();
        driver.findElement(By.id("field_password")).sendKeys(testBot.getPassword());
        driver.findElement(By.cssSelector("div.form-actions > div > input.button-pro.form-actions_yes")).click();
    }

    protected void clickCreateButton() {
        driver.findElement(By.id("hook_FormButton_button_create")).click();
    }

    protected void typeGroupName(String groupName) {
        driver.findElement(By.id("field_name")).clear();
        driver.findElement(By.id("field_name")).sendKeys(groupName);
    }

    protected void clickInterestGroup() {
        driver.findElement(By.xpath("//*[contains(@class,'create-group-dialog_img __interest')]")).click();
    }

    protected void clickCreateGroup() {
        driver.findElement(By.xpath(".//*[contains(@href,'st.layer.cmd=PopLayerCreateAltGroup')]")).click();
    }

    protected void clickGroupsOnToolbar() {
        driver.findElement(By.xpath(".//*[@class='mctc_navMenuSec' and contains(@href,'groups')]")).click();
    }

    protected void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
