package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@SuppressWarnings("WeakerAccess")
public class PlaceCompanyInstitutionCreationPage extends AbstractGroupCreationPage {

    private static final String FIELD_SUBCATEGORY_STR = "field_pageMixedCategory";
    private static final By FIELD_SUBCATEGORY = id(FIELD_SUBCATEGORY_STR);
    private static final String FIELD_AGE_RESTRICTION_STR = "field_ageRestriction";
    private static final By FIELD_AGE_RESTRICTION = id(FIELD_AGE_RESTRICTION_STR);
    private static final Map<By, String> CHECK_MAP = new HashMap<>(AbstractGroupCreationPage.CHECK_MAP);

    static {
        CHECK_MAP.put(FIELD_SUBCATEGORY, FIELD_SUBCATEGORY_STR);
        CHECK_MAP.put(FIELD_AGE_RESTRICTION, FIELD_AGE_RESTRICTION_STR);
    }

    public PlaceCompanyInstitutionCreationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void check() {
        CHECK_MAP.forEach((locator, text) -> assertTrue(
                "Не дождались появления поля " + text + " в объекте " + toString(),
                explicitWait(visibilityOfElementLocated(locator), 5, 500)
        ));
    }

    @Override
    public PlaceCompanyInstitutionCreationPage typeName(String name) {
        super.typeName(name);
        return this;
    }

    @Override
    public PlaceCompanyInstitutionCreationPage typeDescription(String description) {
        super.typeDescription(description);
        return this;
    }

    public PlaceCompanyInstitutionCreationPage selectSubcategory(String subcategoryVisibleText) {
        assertTrue(isElementPresent(FIELD_SUBCATEGORY));
        selectOptionByVisibleText(FIELD_SUBCATEGORY, subcategoryVisibleText);
        return this;
    }

    public PlaceCompanyInstitutionCreationPage selectAgeRestriction18plus() {
        assertTrue(isElementPresent(FIELD_AGE_RESTRICTION));
        selectOptionByVisibleText(FIELD_AGE_RESTRICTION, "От 18 и старше");
        return this;
    }

}
