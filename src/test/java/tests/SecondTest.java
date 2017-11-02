package tests;

import org.junit.Test;

public class SecondTest extends TestBase{

    @Test
    public void testGroupCreation() throws Exception {
        app.clickGroupsOnToolbar();
        app.clickCreateGroup();
        app.clickInterestGroup();
        app.typeGroupName("Group");
        app.clickCreateButton();
    }
}