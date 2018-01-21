package tests;

import core.GroupsMainPage;
import core.LoginMainPage;
import core.PlaceCompanyInstitutionCreationPage;
import core.UserMainPage;
import org.junit.Test;

import static core.PlaceCompanyInstitutionCreationPage.DEFAULT_DESCRIPTION;
import static core.PlaceCompanyInstitutionCreationPage.DEFAULT_SUBCATEGORY;
import static org.testng.AssertJUnit.assertEquals;
import static tests.Utils.BOT;

public class PlaceCompanyInstitutionTest extends TestBase {

    /**
     * Осуществляется попытка создать новое место.
     * Перед созданием смотрим сколько у пользователя создано групп.
     * Все необходимые для ввода поля заполняются и место успешно создаётся.
     * Затем происходит перелогин и снова смотрим сколько у пользователя групп.
     * Тест проверяет, что число групп до создания на 1 меньше, чем число после создания.
     */
    @Test
    public void testSuccessfulCreation() {

        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(BOT);
        final int groupsBeforeCreation = userMainPage.groupsCounter();

        GroupsMainPage groupsMainPage = userMainPage.clickGroupsOnToolbar();
        final PlaceCompanyInstitutionCreationPage page = groupsMainPage.clickCreateGroup().clickPlaceCompanyInstitution();

        userMainPage = page.typeRandomName()
                .typeDescription(DEFAULT_DESCRIPTION)
                .selectSubcategory(DEFAULT_SUBCATEGORY)
                .clickCreateButton()
                .andGroupWasCreatedSuccessfully()
                .doLogout()
                .doLogin(BOT);

        final int groupsAfterCreationExpected = groupsBeforeCreation + 1;
        final int groupsAfterCreation = userMainPage.groupsCounter();

        assertEquals(groupsAfterCreationExpected, groupsAfterCreation);
    }

    /**
     * Осуществляется попытка созать новое место.
     * При создании не указывается название, и место не создаётся.
     * Тест проверяет, что место не создалось и
     * появилось сообщение об ошибке.
     */
    @Test
    public void testUnsuccessfulCreation() {
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(BOT);
        GroupsMainPage groupsMainPage = userMainPage.clickGroupsOnToolbar();
        PlaceCompanyInstitutionCreationPage page = groupsMainPage.clickCreateGroup().clickPlaceCompanyInstitution();
        page.typeDescription(DEFAULT_DESCRIPTION)
                .selectSubcategory(DEFAULT_SUBCATEGORY)
                .clickCreateButton()
                .andGroupWasNotCreatedForNSeconds(5);
    }

}
