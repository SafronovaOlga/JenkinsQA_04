import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _FreestyleTest extends BaseTest {

    private void createProject() {
        getDriver().findElement(By.className("task-link-text")).click();
        getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys("FirstProject");

        getDriver().findElement(By.className("label")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(
                By.xpath("//html/body/div[5]/div/div/div/div/form/div[1]/div[12]/div/div[2]/div[2]/span[1]/span/button")
        ).click();
    }

    private void deleteProject() {
        getDriver().findElement(By.linkText("Delete Project")).click();
        getDriver().switchTo().alert().accept();
    }

    @Test
    public void testCreateFreestyleProject() {
        String expectedResult = "FirstProject";
        createProject();

        String actualResult = getDriver().findElement(By.xpath("//ul/li/a[@href='/job/FirstProject/']")).getText();
        Assert.assertEquals(actualResult, expectedResult);

        deleteProject();
    }
}
