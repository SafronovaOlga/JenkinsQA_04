import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FreestyleProjectBuildStepTest extends BaseTest {

    private static final String PROJECT_NAME = RandomStringUtils.randomAlphanumeric(3, 10);

    private void createFreestyleProjectWithBuildStep() {
        getDriver().findElement(
                By.xpath("//a[@class='model-link inside breadcrumbBarAnchor']")).click();
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(
                By.xpath("//span[contains(text(),'Freestyle project')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        WebElement addBuildStep = getDriver().findElement(
                By.xpath("//span[@class='yui-button yui-menu-button']//button[@type='button' and @suffix='builder']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", addBuildStep);
        new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.elementToBeClickable(addBuildStep));
        Actions action = new Actions(getDriver());
        action.moveToElement(addBuildStep);
        action.click().build().perform();
        getDriver().findElement(By.xpath("//a[text()='Execute shell']")).click();

        getDriver().findElement(By.xpath("//div[@class='CodeMirror']//div[@class='CodeMirror-scroll cm-s-default']")).click();

        /*getDriver().findElement(By.xpath("//div[@class='CodeMirror']//div[@class='CodeMirror-scroll cm-s-default']"))
                .sendKeys("JOB_NAME=${JOB_NAME}\n" +
                        "BUILD_NUMBER=${BUILD_NUMBER}");*/

        /*getDriver().findElement(By.xpath("//div[@class='CodeMirror-scroll cm-s-default Code-Mirror-focused']"))
                .sendKeys("JOB_NAME=${JOB_NAME}\n" +
                        "BUILD_NUMBER=${BUILD_NUMBER}");*/

        /*getDriver().findElement(By.xpath("//div[starts-with(@class,'CodeMirror-scroll cm-s-default')]"))
                .sendKeys("JOB_NAME=${JOB_NAME}\n" +
                        "BUILD_NUMBER=${BUILD_NUMBER}");*/

        /*getDriver().findElement(By.xpath("//div[contains(@class,'CodeMirror-scroll cm-s-default')]"))
                .sendKeys("JOB_NAME=${JOB_NAME}\n" +
                        "BUILD_NUMBER=${BUILD_NUMBER}");*/

        getDriver().findElement(
                By.xpath("//button[contains(text(),'Save')]")).click();
    }

    private List<WebElement> getSuccessfulBuilds() {
        return getDriver().findElements(
                By.xpath("//td[@class='build-row-cell']//span/span/*[name()='svg' " +
                        "and (contains(@tooltip, 'Success'))]"));
    }

    @Test
    public void testAddBuildStep_TC_007_001() {
        createFreestyleProjectWithBuildStep();
        assertEquals(getDriver().findElement(
                        By.xpath("//h1[@class='job-index-headline page-headline']")).getText(),
                "Project " + PROJECT_NAME);

        getDriver().findElement(By.linkText("Build Now")).click();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        List<WebElement> buildHistory = getDriver().findElements(
                By.xpath("//a[@class='tip model-link inside build-link display-name']"));

        Actions action = new Actions(getDriver());
        action.moveToElement(buildHistory.get(0)).click().build().perform();
        getDriver().findElement(By.xpath("//span[text()='Console Output']")).click();

        WebElement consoleOutput = getDriver().findElement(By.xpath("//pre[@class='console-output']"));
        assertTrue(consoleOutput.getText().contains("JOB_NAME"));
        assertTrue(consoleOutput.getText().contains("BUILD_NUMBER"));
        assertTrue(consoleOutput.getText().contains("Finished: SUCCESS"));
    }
}
