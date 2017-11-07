package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserMainPageHelper extends HelperBase{

    public UserMainPageHelper(WebDriver driver) {
        super(driver);
    }

    public void clickGroupsOnToolbar() {
        click(By.xpath(".//*[@class='mctc_navMenuSec' and contains(@href,'groups')]"));
    }
}
