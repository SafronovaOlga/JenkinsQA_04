import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

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

import static org.testng.Assert.assertTrue;

public class FreestyleProjectBuildStepTest extends BaseTest {

    private static final String PROJECT_NAME = RandomStringUtils.randomAlphanumeric(3, 10);

    private void createFreestyleProjectWithBuildStep(String environmentVariable) throws InterruptedException, AWTException {
        Robot robot = new Robot();

        getDriver().findElement(
                By.className("breadcrumbBarAnchor")).click();
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

        Thread.sleep(2000);

        WebElement variableField = getDriver().findElement(By.cssSelector("div .CodeMirror-lines > div > div:nth-child(5) > pre"));
        variableField.click();

        StringSelection stringSelection = new StringSelection(environmentVariable);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        getDriver().findElement(
                By.xpath("//button[contains(text(),'Apply')]")).click();
        getDriver().findElement(
                By.xpath("//button[contains(text(),'Save')]")).click();
    }

    @Test
    public void testAddBuildStepToProject() throws InterruptedException, AWTException {
        createFreestyleProjectWithBuildStep("JOB_NAME=${JOB_NAME}");
        getDriver().findElement(By.linkText("Build Now")).click();

        List<WebElement> buildHistory = getDriver().findElements(
                By.className("display-name"));

        new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.visibilityOfAllElements(buildHistory));

        Actions action = new Actions(getDriver());
        action.moveToElement(buildHistory.get(0)).click().build().perform();

        getDriver().findElement(By.xpath("//span[text()='Console Output']")).click();
        WebElement consoleOutput = getDriver().findElement(By.className("console-output"));

        new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.visibilityOf(consoleOutput));

        assertTrue(consoleOutput.getText().contains("JOB_NAME=" + PROJECT_NAME));
        assertTrue(consoleOutput.getText().contains("SUCCESS"));
    }
}
