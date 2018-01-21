package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public abstract class AbstractGroupCreationPage extends HelperBase {

    static final Map<By, String> CHECK_MAP = new HashMap<>();
    private static final String FIELD_NAME_STR = "field_name";
    private static final By FIELD_NAME = id(FIELD_NAME_STR);
    private static final String FIELD_DESCRIPTION_STR = "field_description";
    private static final By FIELD_DESCRIPTION = id(FIELD_DESCRIPTION_STR);
    private static final String BUTTON_CREATE_STR = "hook_FormButton_button_create";
    private static final By BUTTON_CREATE = id(BUTTON_CREATE_STR);

    static {
        CHECK_MAP.put(FIELD_NAME, FIELD_NAME_STR);
        CHECK_MAP.put(FIELD_DESCRIPTION, FIELD_DESCRIPTION_STR);
        CHECK_MAP.put(BUTTON_CREATE, BUTTON_CREATE_STR);
    }

    AbstractGroupCreationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        CHECK_MAP.forEach((locator, text) -> assertTrue(
                "Не дождались появления поля" + text + " в объекте " + toString(),
                explicitWait(visibilityOfElementLocated(locator), 5, 500)
        ));
    }

    public AbstractGroupCreationPage typeName(String name) {
        checkAndType(name, FIELD_NAME);
        return this;
    }

    public abstract AbstractGroupCreationPage typeRandomName();

    public AbstractGroupCreationPage typeDescription(String description) {
        checkAndType(description, FIELD_DESCRIPTION);
        return this;
    }

    public CreatedGroupPromise clickCreateButton() {
        assertTrue(isElementPresent(BUTTON_CREATE));
        click(BUTTON_CREATE);
        return new CreatedGroupPromise(driver);
    }

}
