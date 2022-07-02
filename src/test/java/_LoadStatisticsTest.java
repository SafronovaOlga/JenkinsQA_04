import model.HomePage;
import model.LoadStatisticsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _LoadStatisticsTest extends BaseTest {

    @Test
    public void testCheckToolTipForEachTimeSpan() {

        LoadStatisticsPage loadStatisticsPage = new HomePage(getDriver())
                .clickManageJenkins()
                .clickLoadStatistics();

        Assert.assertEquals(loadStatisticsPage.getShortTooltip(), "Every tick is 10 seconds");
        Assert.assertEquals(loadStatisticsPage.getMediumTooltip(), "Every tick is one minute");
        Assert.assertEquals(loadStatisticsPage.getLongTooltip(), "Every tick is one hour");
    }

    @Test(dependsOnMethods = "testCheckToolTipForEachTimeSpan")
    public void testCheckButtonsStatusForEachTimeSpan() {

        LoadStatisticsPage loadStatisticsPage = new HomePage(getDriver())
                .clickManageJenkins()
                .clickLoadStatistics();

            Assert.assertEquals(loadStatisticsPage.getShortTagName(), "a");
            Assert.assertEquals(loadStatisticsPage.getMediumTagName(), "span");
            Assert.assertEquals(loadStatisticsPage.getLongTagName(), "a");
    }
}
