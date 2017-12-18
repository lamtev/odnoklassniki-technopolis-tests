package tests;

import core.*;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static tests.Utils.BOT;

public class EventUnsuccessfulCreationTest extends TestBase {

    /**
     * Осуществляется попытка созать мероприятие без ввода города.
     * В итоге на странице создания появляется сообщение об ошибке.
     * Тест проверяет, что сообщение об ошибке появилось.
     */
    @Test
    public void testMissedCity() {

        final UserMainPage userMainPage = new LoginMainPage(driver).doLogin(BOT);
        GroupsMainPage groupsMainPage = userMainPage.clickGroupsOnToolbar();
        groupsMainPage.clickCreateGroup();

        EventCreationPage eventCreationPage = groupsMainPage.clickEvent();
        eventCreationPage.typeName("Event")
                .typeDescription("Description")
                .selectCategory("Кино")
                .typeAddress("Tverskaya 10")
                .typePhone("1234567890")
                .typeWebsite("www.x.com")
                .clickCreateButton();

        assertTrue("Нет сообщения об ошибке", eventCreationPage.hasErrorMessage());
    }

}
