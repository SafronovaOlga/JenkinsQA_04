import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;
import java.util.stream.Collectors;

public class CreateMultiConfigurationProjectXbrookxTest extends BaseTest {

    private void createMultiConfigurationProject(String projectName) {
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//button[normalize-space()='Save']")).click();
    }

    private void returnHomePage() {
        getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    private List<String> getListProjects() {

        return getDriver().findElements(
                        By.xpath("//tbody/tr/td/a[@class='jenkins-table__link model-link inside']"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Test
    public void TC_041_003_testCreateMultiConfigurationProject() {
        final String PROJECT_NAME = "Neeew Multi configuration project";
        createMultiConfigurationProject(PROJECT_NAME);
        returnHomePage();

        Assert.assertTrue(getListProjects().contains(PROJECT_NAME));
    }
}