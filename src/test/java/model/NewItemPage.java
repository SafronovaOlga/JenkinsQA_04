package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public class NewItemPage extends BasePage {

    @FindBy(id = "name")
    private WebElement nameText;

    @FindBy(id = "ok-button")
    private WebElement okButton;

    public NewItemPage(WebDriver driver) {
        super(driver);
    }

    public NewItemPage setProjectName(String text) {
        nameText.sendKeys(text);

        return this;
    }

    public NewItemPage setProjectType(ProjectUtils.ProjectType projectType) {
        projectType.click(getDriver());

        return this;
    }

    public ItemConfigPage createAndGoToConfig() {
        okButton.click();

        return new ItemConfigPage(getDriver());
    }

    public PipelineConfigPage createAndGoToPipelineConfigure() {
        okButton.click();

        return new PipelineConfigPage(getDriver());
    }
}
