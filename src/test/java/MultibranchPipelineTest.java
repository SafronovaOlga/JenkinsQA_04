import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class MultibranchPipelineTest extends BaseTest{

    public void сreateMBPipelineProject(String projectName) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        WebElement mbPipeline = getDriver().findElement(By.xpath("//ul/li/label/span[text()='Multibranch Pipeline']"));
        JavascriptExecutor javascriptExecutor;
        javascriptExecutor = (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", mbPipeline);
        mbPipeline.click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']/li/a")).click();
    }

    @Test
    public void testDeleteMultibranchPipelineProject() throws InterruptedException {
        String nameProject = "MB Project 1";
        сreateMBPipelineProject(nameProject);

        WebElement createdJob = getDriver().findElement(By.xpath("//a[@href='job/MB%20Project%201/']"));
        Actions action = new Actions(getDriver());
        action.moveToElement(createdJob).perform();

        getDriver().findElement(By.id("menuSelector")).click();
        getDriver().findElement(By.xpath("//a/span[text()='Delete Multibranch Pipeline']")).click();
        getDriver().findElement(By.id("yui-gen1-button")).click();

//        String headerProjectPage = getDriver().findElement(By.tagName("h1")).getText();
//        Assert.assertEquals(headerProjectPage, "Project FirstProject");
//
//
//        String headerProjectPage = getDriver().findElement(By.tagName("h1")).getText();
//        Assert.assertEquals(headerProjectPage, "Project FirstProject");


        Thread.sleep(5000);




    }
}
