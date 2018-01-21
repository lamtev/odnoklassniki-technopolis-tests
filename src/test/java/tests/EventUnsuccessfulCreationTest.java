package tests;

import core.EventCreationPage;
import core.GroupsMainPage;
import core.LoginMainPage;
import core.UserMainPage;
import org.junit.Test;

import static core.EventCreationPage.*;
import static tests.Utils.BOT;

public class EventUnsuccessfulCreationTest extends TestBase {

    /**
     * Осуществляется попытка созать мероприятие без ввода города.
     * В итоге на странице создания появляется сообщение об ошибке.
     * Тест проверяет, что мероприятие не создалось и
     * сообщение об ошибке появилось.
     */
    @Test
    public void testMissedCity() {
        final UserMainPage userMainPage = new LoginMainPage(driver).doLogin(BOT);
        GroupsMainPage groupsMainPage = userMainPage.clickGroupsOnToolbar();
        EventCreationPage eventCreationPage = groupsMainPage.clickCreateGroup().clickEvent();

        eventCreationPage.typeRandomName()
                .typeDescription(DEFAULT_DESCRIPTION)
                .selectCategory(DEFAULT_CATEGORY)
                .typeAddress(DEFAULT_ADDRESS)
                .typePhone(DEFAULT_PHONE)
                .typeWebsite(DEFAULT_WEBSITE)
                .clickCreateButton()
                .andGroupWasNotCreatedForNSeconds(5);
    }

}
