package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class GroupTypeLayer extends HelperBase {

    private static final String EVENT_XPATH_STR = ".//*[contains(@class,'create-group-dialog_img __event')]";
    private static final By EVENT_XPATH = xpath(EVENT_XPATH_STR);
    private static final String PLACE_COMPANY_INSTITUTION_XPATH_STR = ".//*[contains(@class,'create-group-dialog_img __local')]";
    private static final By PLACE_COMPANY_INSTITUTION_XPATH = xpath(PLACE_COMPANY_INSTITUTION_XPATH_STR);
    private static final Map<By, String> CHECK_MAP = new HashMap<>();

    static {
        CHECK_MAP.put(EVENT_XPATH, EVENT_XPATH_STR);
        CHECK_MAP.put(PLACE_COMPANY_INSTITUTION_XPATH, PLACE_COMPANY_INSTITUTION_XPATH_STR);
    }

    GroupTypeLayer(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        CHECK_MAP.forEach((locator, text) -> assertTrue(
                "Не дождались появления типа группы по локатору" + text + " в объекте " + toString(),
                explicitWait(visibilityOfElementLocated(locator), 5, 500)
        ));
    }

    public EventCreationPage clickEvent() {
        assertTrue(isElementPresent(EVENT_XPATH));
        click(EVENT_XPATH);
        return new EventCreationPage(driver);
    }

    public PlaceCompanyInstitutionCreationPage clickPlaceCompanyInstitution() {
        assertTrue(isElementPresent(PLACE_COMPANY_INSTITUTION_XPATH));
        click(PLACE_COMPANY_INSTITUTION_XPATH);
        return new PlaceCompanyInstitutionCreationPage(driver);
    }

}
