package tests;

import core.GroupsMainPage;
import core.LoginMainPage;
import core.PlaceCompanyInstitutionCreationPage;
import core.UserMainPage;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import static java.lang.System.currentTimeMillis;
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
        groupsMainPage.clickCreateGroup();

        PlaceCompanyInstitutionCreationPage page = groupsMainPage.clickPlaceCompanyInstitution();
        final String name = "Theatre" + currentTimeMillis();
        userMainPage = page.typeName(name)
                .typeDescription("Description")
                .selectSubcategory("Культура и искусство")
                .clickCreateButton()
                /*Чтобы счетчик групп изменился, нужно перелогиниться
                  Долго не понимал, почему тест падает.
                  На мой взгляд, странно*/
                .doLogout()
                .doLogin(BOT);

        final int groupsAfterCreationExpected = groupsBeforeCreation + 1;
        final int groupsAfterCreation = userMainPage.groupsCounter();

        assertEquals(groupsAfterCreationExpected, groupsAfterCreation);
    }

    /**
     * Осуществляется попытка созать новое место.
     * При создании не указывается название, и место не создаётся.
     * Появляется сообщение об ошибке, проходит время и бросается
     * исключение TimeoutException в методе AbstractGroupPage#clickCreateButton().
     * Тест ожидает выброса данного исключения, и если оно случается, то
     * тест считается успешно выполненным.
     */
    @Test(expected = TimeoutException.class)
    public void testUnsuccessfulCreation() {
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(BOT);

        GroupsMainPage groupsMainPage = userMainPage.clickGroupsOnToolbar();
        groupsMainPage.clickCreateGroup();

        PlaceCompanyInstitutionCreationPage page = groupsMainPage.clickPlaceCompanyInstitution();
        page.typeDescription("Description")
                .selectSubcategory("Культура и искусство")
                .clickCreateButton();
    }

    /**
     * За бота, которому нет 18 лет осуществляется попытка создать
     * место с возрастными ограничениями 18+.
     * <p>
     * На мой взгляд, не должно быть возможным сделать такое.
     * Но реально -- можно.
     * Тест не выполняется.
     */
    @Test(expected = TimeoutException.class)
    public void test18plusPlaceCreationBy18MinusPerson() {
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(BOT);

        GroupsMainPage groupsMainPage = userMainPage.clickGroupsOnToolbar();
        groupsMainPage.clickCreateGroup();

        PlaceCompanyInstitutionCreationPage page = groupsMainPage.clickPlaceCompanyInstitution();
        final String name = "Place" + currentTimeMillis();
        page.typeName(name)
                .typeDescription("Description")
                .selectSubcategory("Культура и искусство")
                .selectAgeRestriction18plus()
                .clickCreateButton();
    }

}
