package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@SuppressWarnings("WeakerAccess")
public final class EventCreationPage extends AbstractGroupCreationPage {

    public static final String DEFAULT_DESCRIPTION = "Description";
    public static final String DEFAULT_CATEGORY = "Кино";
    public static final String DEFAULT_ADDRESS = "Tverskaya 10";
    public static final String DEFAULT_PHONE = "1234567890";
    public static final String DEFAULT_WEBSITE = "www.x.com";
    private static final String FIELD_START_DATE_STR = "field_startDate";
    private static final By FIELD_START_DATE = id(FIELD_START_DATE_STR);
    private static final String FIELD_END_DATE_STR = "field_endDate";
    private static final By FIELD_END_DATE = id(FIELD_END_DATE_STR);
    private static final String FIELD_CITY_STR = "field_city";
    private static final By FIELD_CITY = id(FIELD_CITY_STR);
    private static final String FIELD_ADDRESS_STR = "field_address";
    private static final By FIELD_ADDRESS = id(FIELD_ADDRESS_STR);
    private static final String FIELD_PHONE_STR = "field_phone";
    private static final By FIELD_PHONE = id(FIELD_PHONE_STR);
    private static final String FIELD_WEBSITE_STR = "field_website";
    private static final By FIELD_WEBSITE = id(FIELD_WEBSITE_STR);
    private static final String FIELD_CATEGORY_STR = "field_category";
    private static final By FIELD_CATEGORY = id(FIELD_CATEGORY_STR);
    private static final Map<By, String> CHECK_MAP = new HashMap<>(AbstractGroupCreationPage.CHECK_MAP);

    static {
        CHECK_MAP.put(FIELD_START_DATE, FIELD_START_DATE_STR);
        CHECK_MAP.put(FIELD_CITY, FIELD_CITY_STR);
        CHECK_MAP.put(FIELD_ADDRESS, FIELD_ADDRESS_STR);
        CHECK_MAP.put(FIELD_PHONE, FIELD_PHONE_STR);
        CHECK_MAP.put(FIELD_WEBSITE, FIELD_WEBSITE_STR);
        CHECK_MAP.put(FIELD_CATEGORY, FIELD_CATEGORY_STR);
    }

    public EventCreationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        CHECK_MAP.forEach((locator, text) -> assertTrue(
                "Не дождались появления поля" + text + " в объекте " + toString(),
                explicitWait(visibilityOfElementLocated(locator), 5, 500)
        ));
    }

    @Override
    public EventCreationPage typeName(String name) {
        super.typeName(name);
        return this;
    }

    @Override
    public EventCreationPage typeRandomName() {
        return typeName("Event" + currentTimeMillis());
    }

    @Override
    public EventCreationPage typeDescription(String description) {
        super.typeDescription(description);
        return this;
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

}
