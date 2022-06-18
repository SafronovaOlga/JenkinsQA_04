import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;
import java.util.stream.Collectors;

public class CreateMultiConfigurationProjectTest extends BaseTest {
    private final String NAME_FOLDER = "Neeew Multi configuration project";
    private final By PROJECT_ON_DAHBOARD = By.xpath("//table[@id='projectstatus']//a[normalize-space(.)='" + NAME_FOLDER + "']");

    private void createMultiConfigFolder(String name) {
        getDriver().findElement(By.linkText("New Item")).click();
        WebElement itemName = getDriver().findElement(By.id("name"));
        itemName.sendKeys(name);
        getDriver().findElement(By.xpath("//div[@id='j-add-item-type-standalone-projects']//li[3]")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
    }

    private void returnToMainPage() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    private List<String> getListProjects() {

        return getDriver().findElements(
                        By.xpath("//tbody/tr/td/a[@class='jenkins-table__link model-link inside']"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    protected void deleteFolder(String name) {
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(
                By.xpath("//a[@href='job/" + name + "/']"))).click().build().perform();
        getDriver().findElement(By.xpath("//span[text()='Delete Multi-configuration project']")).click();
        getDriver().switchTo().alert().accept();
    }

//    @Test
//    public void testCreateMultiConfigurationProject() {
//        createMultiConfigFolder(NAME_FOLDER);
//        returnToMainPage();
//
//        Assert.assertTrue(getListProjects().contains(NAME_FOLDER));
//    }

    @Test
    public void testCheckSubMenuConfigureAfterCreatingProject() {
        String expectedResultDiscardOldBuilds = "Help for feature: Discard old builds";
        String expectedResult1 = "This determines when, if ever, build records for this project should be discarded. " +
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
        String expectedResultMessage = "Saved";

        createMultiConfigFolder(NAME_FOLDER);
        returnToMainPage();

        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(PROJECT_ON_DAHBOARD)).perform();
        WebElement subMenuButton = getDriver().findElement(By.id("menuSelector"));
        actions.moveToElement(subMenuButton).click().build().perform();
        actions.moveToElement(getDriver().findElement(
                By.xpath("//a[@class='yuimenuitemlabel']//span[text()='Configure']"))).click().build().perform();

        WebElement helpTitle = getDriver().findElement(By.xpath("//a[contains(@tooltip, 'Discard old builds')]"));

        Assert.assertEquals(helpTitle.getAttribute("title"), expectedResultDiscardOldBuilds);

        helpTitle.click();

        String textHelp = getDriver().findElement(By.xpath("//div[@class='help']/div")).getText();

        Assert.assertEquals(textHelp, expectedResult1);

        WebElement checkBoxDiscardOldBuilds = getDriver().findElement(
                By.xpath("//label[text()='Discard old builds']/preceding-sibling::input"));

        if (checkBoxDiscardOldBuilds.isSelected()) {
        } else {
            checkBoxDiscardOldBuilds.click();
        }

        checkBoxDiscardOldBuilds = getDriver().findElement(
                By.xpath("//label[text()='Discard old builds']/preceding-sibling::input"));

        Assert.assertTrue(checkBoxDiscardOldBuilds.isSelected());

        actions.moveToElement(getDriver().findElement(By.xpath("//span[@name='Apply']"))).click().build().perform();

        WebElement applyMessage = getDriver().findElement(By.xpath("//div[@id='notification-bar']/span"));

        Assert.assertEquals(applyMessage.getText(), expectedResultMessage);

        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.linkText("notification-bar")));

        getDriver().findElement(By.linkText("Dashboard")).click();

 //       returnToMainPage();

        deleteFolder(NAME_FOLDER);
    }

    @Test
    public void testMultiConfigurationProjectRenameUsingInvalidName() {
        String[] invalidName =
                new String[]{"!", "@", "#", "$", "%", "^", "&", "*", ":", ";", "\\", "/", "|", "<", ">", "?", "", " "};

        createMultiConfigFolder(NAME_FOLDER);
        returnToMainPage();

        getDriver().findElement(PROJECT_ON_DAHBOARD).click();
        getDriver().findElement(By.linkText("Rename")).click();

        for (String unsafeChar : invalidName) {
            getDriver().findElement(By.name("newName")).clear();
            getDriver().findElement(By.name("newName")).sendKeys(unsafeChar);
            getDriver().findElement(By.id("yui-gen1-button")).click();
            String expectedResult = "‘" + unsafeChar + "’ is an unsafe character";
            if ("&".equals(unsafeChar)) {
                expectedResult = "‘&amp;’ is an unsafe character";
            } else if (unsafeChar.equals("<")) {
                expectedResult = "‘&lt;’ is an unsafe character";
            } else if (unsafeChar.equals(">")) {
                expectedResult = "‘&gt;’ is an unsafe character";
            } else if (unsafeChar.equals("") || unsafeChar.equals(" ")) {
                expectedResult = "No name is specified";
            }

            String actualResult = getDriver().findElement(By.xpath("//div[@id='main-panel']/p")).getText();

            Assert.assertEquals(actualResult, expectedResult);

            getDriver().navigate().back();

            deleteFolder(NAME_FOLDER);
        }
    }

    @Test
    public void testMultiConfigurationProjectStatusDisableProject () {
        final By STATUS_TOOLTIP_DAHBOARD = By.xpath("//tr[@id='job_".concat(NAME_FOLDER).concat("']//span/span/node()"));

        final String tooltipEnable = "Not built";
        final String tooltipDisable = "Disabled";

        String status = getDriver().findElement(STATUS_TOOLTIP_DAHBOARD).getAttribute("tooltip");
        Assert.assertEquals(status, tooltipEnable);

        getDriver().findElement(PROJECT_ON_DAHBOARD).click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        WebElement warning = getDriver().findElement(By.xpath("//div[@class='warning']/form[@id='enable-project']"));
        System.out.println(warning.getText());

        Assert.assertTrue(getDriver().findElement(
                By.xpath("//div[@class='warning']/form[contains(text(), 'This project is currently disabled')]")).isDisplayed());

        returnToMainPage();

        status = getDriver().findElement(STATUS_TOOLTIP_DAHBOARD).getAttribute("tooltip");;

        Assert.assertEquals(status, tooltipDisable);
    }

    @Test
    public void TC_041_004_testDeleteMultiConfigurationProject() {

        createMultiConfigFolder(NAME_FOLDER);
        returnToMainPage();

        Assert.assertTrue(getListProjects().contains(NAME_FOLDER));
        deleteFolder(NAME_FOLDER);
        getDriver().findElement(By.xpath("//a[@id='jenkins-home-link']")).click();

        Assert.assertFalse(getListProjects().contains(NAME_FOLDER));
    }
}
