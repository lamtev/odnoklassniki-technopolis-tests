package tests;

import core.GroupMainPage;
import core.LoginMainPage;
import core.TestBase;
import core.UserMainPage;
import model.TestBot;
import org.junit.Test;

public class SecondTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        new LoginMainPage(driver).doLogin(new TestBot("technopolisbot", "technopolis16"));
        new UserMainPage(driver).clickGroupsOnToolbar();
        GroupMainPage groupMainPage = new GroupMainPage(driver);
        groupMainPage.clickCreateGroup();
        groupMainPage.clickInterestGroup();
        groupMainPage.typeGroupName("Group");
        groupMainPage.clickCreateButton();
        //todo не хватает проверки
    }
}