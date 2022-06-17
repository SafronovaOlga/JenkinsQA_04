import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateNewViewWithSelectLabelMyViewTest extends BaseTest {
    final String NAME_OF_VIEW = "My new view";

    @Test
    public void testCreateNewView() throws InterruptedException{
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME_OF_VIEW);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();
        getDriver().findElement(By.id("ok")).click();

        Assert.assertEquals(
                NAME_OF_VIEW,
                getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[contains(@href, '/view')]")).getText()
        );

        deleteCreatedView();
    }

    private void deleteCreatedView() throws InterruptedException {
        getDriver().findElement(By.xpath("//li/a[text()='Dashboard']")).click();
        Thread.sleep(5000);
        getWait20().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("projectstatus-tabBar")));
        getDriver().findElement(By.xpath("//div[@class='tab']/a[contains(@href, '/view/')]")).click();
        getDriver().findElement(By.xpath("//a[@href='delete']")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }
}
