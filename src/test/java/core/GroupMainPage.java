package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GroupMainPage extends HelperBase{

    private static final By CREATE_NEW_GROUP = By.xpath(".//*[contains(@href,'st.layer.cmd=PopLayerCreateAltGroup')]");

    public GroupMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //пример использования метода из HelperBase
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return isElementPresent(CREATE_NEW_GROUP);
            }
        });

        //пример использования класса ExpectedConditions
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(CREATE_NEW_GROUP));
    }

    public void clickCreateButton() {
        click(By.id("hook_FormButton_button_create"));
    }

    public void typeGroupName(String groupName) {
        type(groupName, By.id("field_name"));
    }

    public void clickInterestGroup() {
        click(By.xpath(".//*[contains(@class,'create-group-dialog_img __interest')]"));
    }

    public void clickCreateGroup() {
        Assert.assertTrue("Не найден элемент создания группы", isElementPresent(CREATE_NEW_GROUP));
        driver.findElement(CREATE_NEW_GROUP).click();
    }
}
