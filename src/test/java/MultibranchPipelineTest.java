import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class MultibranchPipelineTest extends BaseTest{

    @Test
    public void testDeleteMultibranchPipelineProject() throws InterruptedException {
        getDriver().get("http://localhost:8080/");
        getDriver().findElement(By.id("j_username")).sendKeys("admin");
        getDriver().findElement(By.name("j_password")).sendKeys("925f305afc694751b31243eea4ce9429");
        getDriver().findElement(By.name("Submit")).click();

        Thread.sleep(1000);

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("FirstProject");
        getDriver().findElement(By.xpath("//ul/li/label/span[text()='Freestyle project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.id("yui-gen27")).click();

        String headerProjectPage = getDriver().findElement(By.tagName("h1")).getText();
        Assert.assertEquals(headerProjectPage, "Project FirstProject");


        Thread.sleep(5000);




    }
}
