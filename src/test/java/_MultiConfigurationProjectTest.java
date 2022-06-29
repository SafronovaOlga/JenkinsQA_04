import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;
import java.util.List;

public class _MultiConfigurationProjectTest extends BaseTest {

    private static final String NAME = "TestMultiConfigurationProject";
    private static final String PROJECT_NAME = "Mcproject";
    private static final String NAME_FOLDER = "DisabledFolder";
    private static final String NAME_TO_DELETE = "TestToDelete";
    public void clickRenameButton(){
        getDriver().findElement(By.id("yui-gen1-button")).click();
    }

    @Test
    public void testCreateMultiConfigFolder() {
        ProjectUtils.createProject(getDriver(), ProjectUtils.ProjectType.MultiConfigurationProject, NAME);
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        WebElement nameOnDashboard = getDriver().findElement(By.xpath("//tr[@id='job_" + NAME + "']//td[3]"));

        Assert.assertEquals(nameOnDashboard.getText(), NAME);
    }

    @Test (dependsOnMethods = "testCreateMultiConfigFolder")
    public void testBuildNow() {
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        ProjectUtils.openProject(getDriver(), NAME);
        ProjectUtils.Dashboard.Project.BuildNow.click(getDriver());
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("build-status-link"))).click();

        Assert.assertTrue(getDriver().findElement(
                By.xpath("//span/span/*[name()='svg' and (contains(@tooltip, 'Success'))]")).isDisplayed());
    }

    @Test (dependsOnMethods = "testCreateMultiConfigFolder")
    public void testCheckSubMenuConfigureAfterCreatingProject() {
        final String DiscardOldBuildsText = "This determines when, if ever, build records for this project should be discarded. " +
                "Build records include the console output, archived artifacts, and any other metadata related " +
                "to a particular build.\n" +
                "Keeping fewer builds means less disk space will be used in the Build Record Root Directory," +
                " which is specified on the Configure System screen.\n" +
                "Jenkins offers two options for determining when builds should be discarded:\n" +
                "Build age: discard builds when they reach a certain age; for example, seven days old.\n" +
                "Build count: discard the oldest build when a certain number of builds already exist.\n" +
                "These two options can be active at the same time, so you can keep builds for 14 days, " +
                "but only up to a limit of 50 builds, for example. If either limit is exceeded, then any " +
                "builds beyond that limit will be discarded.\n" +
                "You can also ensure that important builds are kept forever, regardless of the " +
                "setting here — click the Keep this build forever button on the build page.\n" +
                "The last stable and last successful build are also excluded from these rules.\n" +
                "In the Advanced section, the same options can be specified, but specifically for build " +
                "artifacts. If enabled, build artifacts will be discarded for any builds which exceed the " +
                "defined limits. The builds themselves will still be kept; only the associated artifacts, " +
                "if any, will be deleted.\n" +
                "For example, if a project builds some software and produces a large installer, which is " +
                "archived, you may wish to always keep the console log and information about which source " +
                "control commit was built, while for disk space reasons, you may want to keep only " +
                "the last three installers that were built.\n" +
                "This can make sense for projects where you can easily recreate the same artifacts later by building " +
                "the same source control commit again.\n" +
                "Note that Jenkins does not discard items immediately when this configuration is updated, " +
                "or as soon as any of the configured values are exceeded; these rules are evaluated " +
                "each time a build of this project completes.";

        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        getActions().moveToElement(getDriver().findElement(By.xpath
                ("//table[@id='projectstatus']//a[normalize-space(.)='" + NAME + "']"))).perform();
        getActions().moveToElement(getDriver().findElement(By.id("menuSelector"))).click().perform();
        getActions().moveToElement(getDriver().findElement(
                By.xpath("//a[@class='yuimenuitemlabel']//span[text()='Configure']"))).click().perform();

        WebElement helpTitle = getDriver().findElement(By.xpath("//a[contains(@tooltip, 'Discard old builds')]"));

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(helpTitle.getAttribute("title"), "Help for feature: Discard old builds");

        helpTitle.click();

        asserts.assertEquals(getDriver().findElement(By.xpath("//div[@class='help']/div")).getText(),
                DiscardOldBuildsText);

        WebElement checkBoxDiscardOldBuilds = getDriver().findElement(
                By.xpath("//label[text()='Discard old builds']/preceding-sibling::input"));
        if (!checkBoxDiscardOldBuilds.isSelected()) {
            checkBoxDiscardOldBuilds.click();
        }
        asserts.assertTrue(checkBoxDiscardOldBuilds.isSelected());

        getActions().moveToElement(getDriver().findElement(By.xpath("//span[@name='Apply']"))).click().build().perform();

        WebElement applyMessage = getDriver().findElement(By.xpath("//div[@id='notification-bar']/span"));

//        asserts.assertEquals(applyMessage.getText(), "Saved");

        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.id("notification-bar")));
        asserts.assertAll();
    }

    @Test
    public void testBuildNowInDisabledProject() {
        boolean isBuildNowDisplayed = false;

        ProjectUtils.createProject(getDriver(), ProjectUtils.ProjectType.MultiConfigurationProject, NAME_FOLDER);
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        ProjectUtils.openProject(getDriver(), NAME_FOLDER);
        ProjectUtils.clickDisableProject(getDriver());

        List<WebElement> jobMenu = getDriver().findElements(By.xpath("//div[@id='tasks']//span[2]"));
        for (WebElement menu : jobMenu) {
            if (menu.getText().contains("Build Now")) {
                isBuildNowDisplayed = true;
            }
        }
        Assert.assertFalse(isBuildNowDisplayed);
    }

    @Test
    public void testAddDescription() {
        ProjectUtils.createProject(getDriver(), ProjectUtils.ProjectType.MultiConfigurationProject, PROJECT_NAME);
        ProjectUtils.openProject(getDriver(),PROJECT_NAME);

        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//div/textarea[@name='description']")).sendKeys("test");
        getDriver().findElement(By.id("yui-gen2-button")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description']/div[1]")).getText(), "test");
    }

    @Test(dependsOnMethods = {"testAddDescription"})
    public void testRenameMCProject() {
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        ProjectUtils.openProject(getDriver(),PROJECT_NAME);
        ProjectUtils.Dashboard.Project.Rename.click(getDriver());

        TestUtils.clearAndSend(getDriver(), By.xpath("//input[@checkdependson='newName']"),
                "McprojectRename");
        clickRenameButton();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(),
                "Project McprojectRename");
    }

    @Test
    public void testRenameMCProjectErrorSameName() {
        ProjectUtils.createProject(getDriver(), ProjectUtils.ProjectType.MultiConfigurationProject, PROJECT_NAME);
        ProjectUtils.openProject(getDriver(), PROJECT_NAME);
        ProjectUtils.Dashboard.Project.Rename.click(getDriver());

        getActions().moveToElement(getDriver().findElement(By.xpath("//input[@checkdependson='newName']")))
                .click().perform();
        clickRenameButton();

        Assert.assertEquals(getDriver().findElement(By.id("main-panel")).getText(),
                "Error\nThe new name is the same as the current name.");
    }

    @Test (dependsOnMethods = "testRenameMCProjectErrorSameName")
    public void testRenameMCPErrorNoName(){
        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        ProjectUtils.openProject(getDriver(),PROJECT_NAME);
        ProjectUtils.Dashboard.Project.Rename.click(getDriver());

        TestUtils.clearAndSend(getDriver(), By.xpath("//input[@checkdependson='newName']"),
                "");
        clickRenameButton();
        Assert.assertEquals(getDriver().findElement(By.id("main-panel")).getText(),
                "Error\nNo name is specified");

    }
    @Test (dependsOnMethods = "testRenameMCProjectErrorSameName")
    public void testRenameMCProjectErrorInvalidName() {
        final String[] invalidName =
                new String[]{"!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "\\", "/", "|", "<", ">", "?", "", " "};

        ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
        ProjectUtils.openProject(getDriver(),PROJECT_NAME);
        ProjectUtils.Dashboard.Project.Rename.click(getDriver());

        for (String unsafeChar : invalidName) {
            TestUtils.clearAndSend(getDriver(), By.name("newName"), unsafeChar);
            clickRenameButton();
            String expectedResult = "‘" + unsafeChar + "’ is an unsafe character";
            if ("&" == unsafeChar) {
                expectedResult = "‘&amp;’ is an unsafe character";
            } else if (unsafeChar == "<") {
                expectedResult = "‘&lt;’ is an unsafe character";
            } else if (unsafeChar == ">") {
                expectedResult = "‘&gt;’ is an unsafe character";
            } else if (unsafeChar == "" || unsafeChar == " ") {
                expectedResult = "No name is specified";
            }
            Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText(),
                    expectedResult);

            getDriver().navigate().back();
        }
    }

    @Test
    public void testDeleteMultiConfigFolder() {
      ProjectUtils.createProject(getDriver(), ProjectUtils.ProjectType.MultiConfigurationProject, NAME_TO_DELETE);
      ProjectUtils.Dashboard.Header.Dashboard.click(getDriver());
      ProjectUtils.openProject(getDriver(),NAME_TO_DELETE);
      ProjectUtils.Dashboard.Project.DeleteMultiConfigurationProject.click(getDriver());
      getDriver().switchTo().alert().accept();

      boolean isPresent = false;
      List<WebElement> projectsOnDashboard = TestUtils.getList(getDriver(),
                By.xpath("//table[@id='projectstatus']//tbody//td[3]"));
        for (WebElement jobs : projectsOnDashboard) {
            if (jobs.getText().contains(NAME_TO_DELETE)) {
                    isPresent = true;
            }
        }
        Assert.assertFalse(isPresent);
    }

}

