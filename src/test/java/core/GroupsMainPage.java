package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@SuppressWarnings("WeakerAccess")
public final class GroupsMainPage extends HelperBase {

    private static final String CREATE_NEW_GROUP_XPATH_STR = ".//*[contains(@href,'st.layer.cmd=PopLayerCreateAltGroup')]";
    private static final By CREATE_NEW_GROUP_XPATH = xpath(CREATE_NEW_GROUP_XPATH_STR);
    private static final Map<By, String> CHECK_MAP =
            Collections.singletonMap(CREATE_NEW_GROUP_XPATH, CREATE_NEW_GROUP_XPATH_STR);

    public GroupsMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        CHECK_MAP.forEach((locator, text) -> assertTrue(
                "Не дождались появления поля" + text + " в объекте " + toString(),
                explicitWait(visibilityOfElementLocated(locator), 5, 500)
        ));
    }

    public GroupTypeLayer clickCreateGroup() {
        assertTrue("Не найден элемент создания группы", isElementVisible(CREATE_NEW_GROUP_XPATH));
        driver.findElement(CREATE_NEW_GROUP_XPATH).click();
        return new GroupTypeLayer(driver);
    }

}
