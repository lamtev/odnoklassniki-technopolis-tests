package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatedGroupPage extends HelperBase {

    private static final By GROUP_TITLE = By.xpath("//*[contains(@class,'mctc_name_tx')]");


    CreatedGroupPage(WebDriver driver) {
        super(driver);

    }

    @Override
    protected void check() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(GROUP_TITLE));
    }
}
