import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateNewItemEmptyNameTest extends BaseTest {

    @Test
    public void testCreateNewItemEmptyName () {

        String expectedResult = "» This field cannot be empty, please enter a valid name";

        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.id("name")).sendKeys("");
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        String actualResult = getDriver().findElement(By.id("itemname-required")).getText();;

        Assert.assertEquals(actualResult, expectedResult);

    }
}

