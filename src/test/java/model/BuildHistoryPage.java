package model;

import model.base.BaseBuildPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class BuildHistoryPage extends BaseBuildPage {

    public BuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//td/a[contains(@href, 'job/')][1]")
    private List<WebElement> listBuildHistory;

    @FindBy(xpath = "//table[@id='projectStatus']/tbody")
    private WebElement tableOfProjects;

    @FindBy(linkText = "Changes")
    private WebElement changesButton;

    @FindBy(linkText = "Console Output")
    private WebElement consoleButton;

    @FindBy(xpath = "//a[@class='jenkins-table__link jenkins-table__badge model-link inside']")
    private WebElement buildName;

    public List<String> collectListBuildHistory() {
        List<String> collectList = new ArrayList<>();

        for (WebElement el : listBuildHistory) {
            collectList.add(el.getText());
        }
        getDriver().navigate().refresh();

        return collectList;
    }

    public boolean checkProjectIsOnBoard(String projectName) {
        return tableOfProjects.getText().contains(projectName);
    }

    public BuildHistoryPage clickBuildSpanMenu(String projectName, String buildName) {
        getActions().moveToElement(getDriver().findElement(
                By.xpath(String.format("//a[@href='/job/%s/%s/']", projectName, buildName)))).perform();
        getDriver().findElement(By.id("menuSelector")).click();

        return this;
    }

    public BuildChangesPage clickChangesAndGoToChangesPage() {
        changesButton.click();

        return new BuildChangesPage(getDriver());
    }

    public BuildConsolePage clickConsoleAndGoToConsolePage() {
        consoleButton.click();

        return new BuildConsolePage(getDriver());
    }

    public String getBuildName() {
        return buildName.getText();
    }
}