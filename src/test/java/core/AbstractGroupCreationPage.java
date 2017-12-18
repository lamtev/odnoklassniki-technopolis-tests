package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;

public abstract class AbstractGroupCreationPage extends HelperBase {

    static final By FIELD_NAME = id("field_name");
    static final By FIELD_DESCRIPTION = id("field_description");
    static final By HOOK_FORM_BUTTON_BUTTON_CREATE = id("hook_FormButton_button_create");


    AbstractGroupCreationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {

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

}
