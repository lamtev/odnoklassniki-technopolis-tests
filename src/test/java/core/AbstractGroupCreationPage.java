package core;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public abstract class AbstractGroupCreationPage extends HelperBase {

    static final By FIELD_NAME = id("field_name");
    static final By FIELD_DESCRIPTION = id("field_description");
    static final By HOOK_FORM_BUTTON_BUTTON_CREATE = id("hook_FormButton_button_create");
    private static final By ANY_ERROR_MESSAGE = xpath("//*[contains(@class, 'input-e')]");


    AbstractGroupCreationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        asList(FIELD_NAME, FIELD_DESCRIPTION, HOOK_FORM_BUTTON_BUTTON_CREATE).forEach(
                it -> assertTrue(
                        "Не дождались появления поля" + it.toString(),
                        new WebDriverWait(driver, 10)
                                .until((ExpectedCondition<Boolean>) d -> isElementVisible(it))
                )
        );
    }

    public AbstractGroupCreationPage typeName(String name) {
        checkAndType(name, FIELD_NAME);
        return this;
    }

    public AbstractGroupCreationPage typeDescription(String description) {
        checkAndType(description, FIELD_DESCRIPTION);
        return this;
    }

    public CreatedGroupPage clickCreateButton() {
        assertTrue(isElementPresent(HOOK_FORM_BUTTON_BUTTON_CREATE));
        click(HOOK_FORM_BUTTON_BUTTON_CREATE);
        return new CreatedGroupPage(driver);
    }

    public boolean hasErrorMessage() {
        try {
            driver.findElement(ANY_ERROR_MESSAGE);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
