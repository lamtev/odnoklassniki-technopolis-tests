package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.id;

@SuppressWarnings("WeakerAccess")
public class PlaceCompanyInstitutionCreationPage extends AbstractGroupCreationPage {

    private static final By FIELD_SUBCATEGORY = id("field_pageMixedCategory");

    private static final List<By> X_PATHS = Arrays.asList(
            FIELD_NAME,
            FIELD_DESCRIPTION, HOOK_FORM_BUTTON_BUTTON_CREATE,
            FIELD_SUBCATEGORY
    );

    public PlaceCompanyInstitutionCreationPage(WebDriver driver) {
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
    public PlaceCompanyInstitutionCreationPage typeName(String name) {
        return (PlaceCompanyInstitutionCreationPage) super.typeName(name);
    }


    @Override
    public PlaceCompanyInstitutionCreationPage typeDescription(String description) {
        return (PlaceCompanyInstitutionCreationPage) super.typeDescription(description);
    }

    public PlaceCompanyInstitutionCreationPage selectSubcategory(String subcategoryVisibleText) {
        assertTrue(isElementPresent(FIELD_SUBCATEGORY));
        selectOptionByVisibleText(FIELD_SUBCATEGORY, subcategoryVisibleText);
        return this;
    }

}
