package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class AntonSadchikovTest extends BaseTest {

    private WebElement getSearchBox(){
        return getDriver().findElement(By.id("typeaheadInput"));
    }

    @Ignore
    @Test
    public void testDiceSelenium() throws InterruptedException {
        getDriver().get("https://dice.com");
        getSearchBox().sendKeys("test");
        getDriver().findElement(By.id("submitSearch-button")).click();

        Thread.sleep(1000);

        Assert.assertEquals(getSearchBox().getAttribute("value"), "test");
    }
}