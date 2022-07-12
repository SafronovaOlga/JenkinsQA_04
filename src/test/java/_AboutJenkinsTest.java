import model.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;

import java.util.HashSet;
import java.util.Set;

public class _AboutJenkinsTest extends BaseTest {
    @Test
    public void testAmountLinksMavenizedDependenciesPOM() {
        int amountLinksInMavenizedDependencies = new HomePage(getDriver())
                .clickManageJenkins()
                .clickAboutJenkins()
                .countLinksInTab("Mavenized dependencies");

        Assert.assertEquals(amountLinksInMavenizedDependencies, 107);
    }

    @Test
    public void testAmountLinksStaticResourcesPOM() {
        int amountLinksInStaticResources = new HomePage(getDriver())
                .clickManageJenkins()
                .clickAboutJenkins()
                .clickStaticResources()
                .countLinksInTab("Static resources");

        Assert.assertEquals(amountLinksInStaticResources, 4);
    }

    @Test
    public void testAmountLinksLicenseAndDependencyInformationForPluginsPOM() {
        int amountLinksInStaticResources = new HomePage(getDriver())
                .clickManageJenkins()
                .clickAboutJenkins()
                .clickLicenseAndDependencyInformationForPlugins()
                .countLinksInTab("License and dependency information for plugins");

        Assert.assertEquals(amountLinksInStaticResources, 85);
    }

    @Test
    public void testLinkAntLRParserGeneratorPOM() {
        new HomePage(getDriver())
                .clickManageJenkins()
                .clickAboutJenkins()
                .clickLinkAntLRParserGenerator();

        Assert.assertTrue(TestUtils.getOpenTabTitles(getDriver()).contains("ANTLR"));
    }

}
