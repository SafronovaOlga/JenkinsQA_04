import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class CreateNewViewWithSelectLabelMyViewTest extends BaseTest {
    final String NAME_OF_VIEW = "My new view";

    @Test
    public void testCreateNewView() {
        getDriver().findElement(By.xpath("//a[@title='New View']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME_OF_VIEW);
        getDriver().findElement(By.xpath("//label[text()='My View']")).click();
        getDriver().findElement(By.id("ok")).click();

        Assert.assertEquals(
                NAME_OF_VIEW,
                getDriver()
                        .findElement(By.xpath("//ul[@id='breadcrumbs']//a[contains(@href, '/view')]"))
                        .getText()
        );

        deleteCreatedView();
    }

    public void deleteCreatedView() {
        getDriver().findElement(By.xpath("//li/a[text()='Dashboard']")).click();
        getDriver().findElement(
                        By.xpath("//div[@class='tab']/a[contains(@href, '/view/')]"))
                .click();
        getDriver().findElement(By.xpath("//a[@href='delete']")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }
}
