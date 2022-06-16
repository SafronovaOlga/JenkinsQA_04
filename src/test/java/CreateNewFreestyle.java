import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateNewFreestyle extends BaseTest {

String nameItem = "My first project";

    public void deleteFreestyleProject() {
        //getDriver().findElement(By.xpath("//ul/li[1]/a[contains(@class, 'breadcrumbBarAnchor')]")).click();
        WebElement hiddenButton = getDriver().findElement(By.id("menuSelector"));
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(By.linkText(nameItem))).perform();
        hiddenButton.click();
        getDriver().findElement(By.xpath("//span[text() = 'Delete Project']")).click();
        getDriver().switchTo().alert().accept();



        }

@Test
public void createFreestyleProject (){



        getDriver().findElement(By.className("task-link-text")).click();
//        getDriver().findElement(By.xpath("//input[@class = 'input-validation-message']"));//.sendKeys("My first project");
    getDriver().findElement(By.id("name")).sendKeys(nameItem);
    getDriver().findElement(By.xpath("//li[@role = 'radio']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[text() = 'Save']")).click();
        getDriver().findElement(By.cssSelector("a.model-link.inside.breadcrumbBarAnchor")).click();
        getDriver().findElement(By.xpath("//ul/li[1]/a[contains(@class, 'breadcrumbBarAnchor')]")).click();
        Assert.assertTrue(getDriver().findElement(By.linkText(nameItem)).isDisplayed());
        deleteFreestyleProject();


        }
}
