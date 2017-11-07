package tests;

import org.junit.Test;

public class SecondTest extends TestBase{

    @Test
    public void testGroupCreation() throws Exception {
        app.getUserMainPageHelper().clickGroupsOnToolbar();
        app.getGroupHelper().clickCreateGroup();
        app.getGroupHelper().clickInterestGroup();
        app.getGroupHelper().typeGroupName("Group");
        app.getGroupHelper().clickCreateButton();
    }
}