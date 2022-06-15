import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.BaseUtils;
import runner.ProjectUtils;

public class CopyDataFromExistingItemIntoNewOneTest extends BaseTest {

    private final String DESCRIPTION_FIELD = "description";
    private final String GITHUB_URL = "_.projectUrlStr";
    private final String NAME = "NJ";
    private final String NAME2 = "NJ2";
    private final String DESCRIPTION_INPUT = "New Project created by TA";
    private final String URL_INPUT = "https://github.com/SergeiDemyanenko/JenkinsQA_04/";
    private Actions action;

    private Actions getAction() {
        if (action == null) {
            action = new Actions(getDriver());
        }
        return action;
    }

    public void homePage() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    public void startFreestyleProject(String name) {
        getDriver().findElement(By.xpath("//a[@title='New Item']")).click();
        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
    }

    public void okButton() {
        getAction().moveToElement(getDriver().findElement(By.id("ok-button"))).click().perform();
    }

    public void saveButton() {
        getDriver().findElement(By.xpath("//div[@id='bottom-sticker']//button[@type='submit']")).click();
    }

    public void copyFrom (String name){
        WebElement copFromButton = getDriver().findElement(By.id("from"));
        getAction().moveToElement(copFromButton).perform();
        copFromButton.sendKeys(name);
        okButton();
    }

    public void createBaseFreestyleProject() {
        startFreestyleProject(NAME);

        okButton();

        getDriver().findElement(By.name(DESCRIPTION_FIELD)).sendKeys(DESCRIPTION_INPUT);
        getDriver().findElement(By.name("githubProject")).click();
        getDriver().findElement(By.name(GITHUB_URL)).sendKeys(URL_INPUT);
        saveButton();

       homePage();
    }

    @Ignore
    @Test
    public void testCopyDataFromExistingItemNegative() {
        startFreestyleProject("A");
        okButton();
        saveButton();
        homePage();

        startFreestyleProject("NJ3");
        copyFrom("NJ4");

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(), "Error");
        getDriver().findElement(By.id("jenkins-home-link")).click();
        homePage();
    }

    @Test
    public void testCopyDataFromExistingItemPositive() {
        homePage();
        createBaseFreestyleProject();

        startFreestyleProject(NAME2);
        copyFrom(NAME);

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(getDriver().findElement(By.name(DESCRIPTION_FIELD)).getText(), DESCRIPTION_INPUT);
        asserts.assertEquals(getDriver().findElement(By.name(GITHUB_URL)).getAttribute("value"),URL_INPUT);
        asserts.assertAll();

        saveButton();
        homePage();
    }
}