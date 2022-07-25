import model.HomePage;
import model.ManageJenkinsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class _ManageJenkinsTest extends BaseTest {

    @Test
    public void testCheckSectionNames() {
        final List<String> expectedSectionNames = List.of(
                "System Configuration",
                "Security",
                "Status Information",
                "Troubleshooting",
                "Tools and Actions");

        List<String> actualManageJenkinsSectionNames = new HomePage(getDriver())
                .clickManageJenkins()
                .getActualManageJenkinsSectionNames();

        Assert.assertEquals(actualManageJenkinsSectionNames, expectedSectionNames);
    }

    @Test(dependsOnMethods = "testCheckSectionNames")
    public void testCheckSectionContentToolsAndActions() {
        final List<String> expectedContentSectionToolsAndActions = List.of(
                "Reload Configuration from Disk",
                "Jenkins CLI",
                "Script Console",
                "Prepare for Shutdown");

        List<String> actualContentSectionToolsAndActions = new HomePage(getDriver())
                .clickManageJenkins()
                .getActualManageJenkinsSectionContentToolsAndActions();

        Assert.assertEquals(actualContentSectionToolsAndActions, expectedContentSectionToolsAndActions);
    }

    @Test(dependsOnMethods = "testCheckSectionNames")
    public void testSectionSystemConfigurationContent() {
        final List<String> expectedSystemConfigurationContent = List.of(
                "Configure System",
                "Global Tool Configuration",
                "Manage Plugins",
                "Manage Nodes and Clouds");

        List<String> actualSystemConfigurationContent = new HomePage(getDriver())
                .clickManageJenkins()
                .getActualSystemConfigurationContent();

        Assert.assertEquals(actualSystemConfigurationContent, expectedSystemConfigurationContent);
    }

    @Test(dependsOnMethods = "testCheckSectionNames")
    public void testSectionSecurityContent() {
        final List<String> expectedSecurityContent = List.of(
                "Configure Global Security",
                "Manage Credentials",
                "Configure Credential Providers",
                "Manage Users");

        List<String> actualSecurityContent = new HomePage(getDriver())
                .clickManageJenkins()
                .getActualSecurityContent();

        Assert.assertEquals(actualSecurityContent, expectedSecurityContent);
    }

    @Test
    public void testCaptionsSystemConfiguration(){
       List<String> expectedCaptions = List.of(
               "Configure global settings and paths.",
               "Configure tools, their locations and automatic installers.",
               "Add, remove, disable or enable plugins that can extend the functionality of Jenkins.",
               "There are updates available",
               "Add, remove, control and monitor the various nodes that Jenkins runs jobs on.",
               "Installs Jenkins as a Windows service to this system, so that Jenkins starts automatically when the machine boots.");

       List<String> actualCaptions = new HomePage(getDriver())
               .clickManageJenkins()
               .getCaptionsSystemSysConf();

       Assert.assertEquals(expectedCaptions, actualCaptions);
       }

}
