import model.HomePage;
import model.LastBuildPage;
import model.ProjectPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class _BuildHistoryTest extends BaseTest {

    private static final String PROJECT_NAME = "BuildHistoryPageProject";
    private static final String BUILD_PROJECT_NAME = "NewFreestyleProject";

    private String buildNumber;

    @Test
    public void testBuildIsOnProjectPage() {
        ProjectPage projectPage = new HomePage(getDriver())
                .clickNewItem()
                .setProjectTypeFreestyle()
                .setProjectName(PROJECT_NAME)
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickBuildButton();

        buildNumber = projectPage.getBuildNumber();

        Assert.assertTrue(projectPage.buildNumberIsDisplayed());
    }

    @Test(dependsOnMethods = "testBuildIsOnProjectPage")
    public void testBuildIsOnBuildHistoryPage() {
        boolean result = new HomePage(getDriver())
                .clickBuildHistory()
                .checkProjectIsOnBoard(PROJECT_NAME);

        Assert.assertTrue(result);
    }

    @Test(dependsOnMethods = "testBuildIsOnBuildHistoryPage")
    public void testBuildHistoryChanges() {
        String changesHeader = new HomePage(getDriver())
                .clickBuildHistory()
                .clickBuildSpanMenu(PROJECT_NAME, buildNumber)
                .clickChangesAndGoToChangesPage()
                .getPageHeader();

        Assert.assertEquals(changesHeader, "Changes");
    }

    @Test (dependsOnMethods = "testBuildHistoryChanges")
    public void testBuildHistoryConsole() {
        String consoleHeader = new HomePage(getDriver())
                .clickBuildHistory()
                .clickBuildSpanMenu(PROJECT_NAME, buildNumber)
                .clickConsoleAndGoToConsolePage()
                .getPageHeader();

        Assert.assertEquals(consoleHeader, "Console Output");
    }

    @Test
    public void testVerifyChangeOnBuildStatusPage() {
        String buildName = new HomePage(getDriver())
                .clickNewItem()
                .setProjectName(BUILD_PROJECT_NAME)
                .setProjectTypeFreestyle()
                .clickOkAndGoToConfig()
                .saveConfigAndGoToProject()
                .clickBuildButton()
                .waitForBuildToComplete()
                .clickDashboardButton()
                .clickProjectName(BUILD_PROJECT_NAME)
                .selectLastBuild()
                .clickEditBuildInfoButton()
                .enterBuildName("New build 123")
                .enterBuildDescription("Build 123 description test")
                .clickSaveButton()
                .getBuildName();


        String buildDescription = new LastBuildPage(getDriver())
                .getBuildDescription();

        Assert.assertEquals(buildName, "New build 123");
        Assert.assertEquals(buildDescription, "Build 123 description test");
    }

    @Test(dependsOnMethods = {"testVerifyChangeOnBuildStatusPage"})
    public void testVerifyChangeOnProjectStatusPage() {
        String buildName = new HomePage(getDriver())
                .clickProjectName(BUILD_PROJECT_NAME)
                .selectLastBuild()
                .clickBackToProjectButton()
                .getBuildName();

        String descriptionName = new ProjectPage(getDriver())
                .getBuildDescription();

        Assert.assertEquals(buildName,"New build 123");
        Assert.assertEquals(descriptionName,"Build 123 description test");
    }

    @Test(dependsOnMethods = {"testVerifyChangeOnProjectStatusPage"})
    public void testVerifyChangeOnBuildHistoryPage() {
        getDriver().findElement(By.xpath("//a[@href='job/NewFreestyleProject/']")).click();
        getDriver().findElement(By.xpath("//a[@href='lastBuild/']")).click();
        getDriver().findElement(By.xpath("//span[text()='Back to Project']")).click();
        getDriver().findElement(By.xpath("//span[text()='Back to Dashboard']")).click();
        getDriver().findElement(By.xpath("//span[text()='Build History']")).click();

        String buildName = getDriver().findElement(By.xpath("//a[@href='/job/NewFreestyleProject/1/']")).getText();

        Assert.assertTrue(buildName.contains("New build 123"));
    }
}