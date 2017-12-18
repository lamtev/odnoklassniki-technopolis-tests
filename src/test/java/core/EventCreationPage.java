package core;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

@SuppressWarnings("WeakerAccess")
public class EventCreationPage extends AbstractGroupCreationPage {

    private static final By FIELD_START_DATE = id("field_startDate");
    private static final By FIELD_END_DATE = id("field_endDate");
    private static final By FIELD_CITY = id("field_city");
    private static final By FIELD_ADDRESS = id("field_address");
    private static final By FIELD_PHONE = id("field_phone");
    private static final By FIELD_WEBSITE = id("field_website");
    private static final By FIELD_CATEGORY = id("field_category");

    private static final By ANY_ERROR_MESSAGE = xpath("//*[contains(@class, 'input-e')]");

    private static final List<By> X_PATHS = Arrays.asList(
            FIELD_NAME, FIELD_START_DATE,
            FIELD_CITY, FIELD_ADDRESS,
            FIELD_PHONE, FIELD_WEBSITE,
            FIELD_DESCRIPTION, HOOK_FORM_BUTTON_BUTTON_CREATE,
            FIELD_CATEGORY
    );

    public EventCreationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        X_PATHS.forEach(it -> assertTrue(
                "Не дождались появления поля" + it.toString(),
                new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) d -> isElementVisible(it))
        ));
    }

    @Override
    public EventCreationPage typeName(String name) {
        return (EventCreationPage) super.typeName(name);
    }

    @Override
    public EventCreationPage typeDescription(String description) {
        return (EventCreationPage) super.typeDescription(description);
    }

    public EventCreationPage typeStartDate(String date) {
        checkAndType(date, FIELD_START_DATE);
        return this;
    }

    public EventCreationPage typeEndDate(String date) {
        checkAndType(date, FIELD_END_DATE);
        return this;
    }

    public EventCreationPage selectCategory(String categoryVisibleText) {
        assertTrue(isElementPresent(FIELD_CATEGORY));
        selectOptionByVisibleText(FIELD_CATEGORY, categoryVisibleText);
        return this;
    }

    //FIXME
    public EventCreationPage typeCity(String city) {
        checkAndType(city, FIELD_CITY);
//        click(xpath(".//*[contains(@id, 'gwt-uid-') and contains(@class, 'sug_it-div')]"));
        return this;
    }

    public EventCreationPage typeAddress(String address) {
        checkAndType(address, FIELD_ADDRESS);
        return this;
    }

    public EventCreationPage typePhone(String phone) {
        checkAndType(phone, FIELD_PHONE);
        return this;
    }

    public EventCreationPage typeWebsite(String website) {
        checkAndType(website, FIELD_WEBSITE);
        return this;
    }

    @Override
    public CreatedGroupPage clickCreateButton() {
        assertTrue(isElementPresent(HOOK_FORM_BUTTON_BUTTON_CREATE));
        click(HOOK_FORM_BUTTON_BUTTON_CREATE);
        return null;
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
