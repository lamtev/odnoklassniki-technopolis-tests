package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.xpath;

@SuppressWarnings("WeakerAccess")
public class GroupsMainPage extends HelperBase {

    private static final By CREATE_NEW_GROUP_XPATH = xpath(".//*[contains(@href,'st.layer.cmd=PopLayerCreateAltGroup')]");
    private static final By EVENT_XPATH = xpath(".//*[contains(@class,'create-group-dialog_img __event')]");
    private static final By PLACE_COMPANY_INSTITUTION_XPATH = xpath(".//*[contains(@class,'create-group-dialog_img __local')]");

    public GroupsMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //пример использования метода isElementVisible из HelperBase
        assertTrue("Не дождались кнопки созданиия новой группы",
                new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) d -> isElementVisible(CREATE_NEW_GROUP_XPATH)));
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

    public void clickCreateGroup() {
        assertTrue("Не найден элемент создания группы", isElementPresent(CREATE_NEW_GROUP_XPATH));
        driver.findElement(CREATE_NEW_GROUP_XPATH).click();
    }
}
