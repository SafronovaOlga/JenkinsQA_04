package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class PipelineConfigPage extends BasePage {

    private JavascriptExecutor js = (JavascriptExecutor) getDriver();

    public PipelineConfigPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".samples select")
    private WebElement script;

    @FindBy(css = "[type='submit']")
    private WebElement saveButton;

    @FindBy(linkText = "Dashboard")
    private WebElement dashboardButton;

    @FindBy(css = ".samples")
    private WebElement dropDownMenuPipelineTab;

    @FindBy(xpath = "//div[1][@class='samples']//select/option")
    private List<WebElement> dropDownMenuInTabPipeline;

    @FindBy(xpath = "//button[@id='yui-gen4-button']")
    private WebElement advancedButton;

    @FindBy(css = "a[tooltip$='Display Name']")
    private WebElement displayNameInTabAdvancedProjectOptions;

    @FindBy(css = "[href='https://plugins.jenkins.io/workflow-job']")
    private WebElement urlAttribute;

    @FindBy(className = "config-section-activator")
    private List<WebElement> itemConfigurationMenu;

    @FindBy(xpath = "//div[@class='jenkins-form-item config_pipeline active']//select")
    private WebElement definitionDropDownMenuPipelineScript;

    @FindBy(xpath = "//div[@class='jenkins-form-item has-help']//select")
    private WebElement definitionDropDownMenuPipelineScriptScm;

    @FindBy(xpath = "//button[@id='yui-gen15-button']")
    private WebElement credentialsAddButton;

    @FindBy(id = "yui-gen17")
    private WebElement jenkinsProviderButton;

    @FindBy(tagName = "h2")
    private WebElement titleOfJenkinsCredentialsProviderWindow;

    public PipelineConfigPage selectScriptByValue(String name) {
        new Select(script).selectByValue(name);
        return this;
    }

    public ProjectPage saveConfigAndGoToProject() {
        saveButton.click();
        return new ProjectPage(getDriver());
    }

    public HomePage clickDashboardButton() {
        dashboardButton.click();
        return new HomePage(getDriver());
    }

    public PipelineConfigPage jsDropDownMenuPipelineTab() {
        js.executeScript("arguments[0].scrollIntoView();", dropDownMenuPipelineTab);
        return this;
    }

    public void collectAndAssertDropDownMenu(int number) {
        Assert.assertEquals(dropDownMenuInTabPipeline.size(), number);
    }

    public PipelineConfigPage scrollAndClickAdvancedButton() {
        js.executeScript("arguments[0].scrollIntoView();", advancedButton);
        advancedButton.click();
        return this;
    }

    public PipelineConfigPage clickHelpForFeatureDisplayName() {
        displayNameInTabAdvancedProjectOptions.click();
        return this;
    }

    public PipelineConfigPage selectConfigurationMenuDefinition(String name) {
        for (WebElement el : itemConfigurationMenu) {
            if (el.getText().equals(name)) {
                el.click();
            }
        }
        return new PipelineConfigPage(getDriver());
    }

    public PipelineConfigPage collectPipelineScriptDropDownMenu() {
        new Select(definitionDropDownMenuPipelineScript).selectByIndex(1);
        return this;
    }

    public PipelineConfigPage collectPipelineScriptScmDropDownMenu() {
        new Select(definitionDropDownMenuPipelineScriptScm).selectByIndex(1);
        return this;
    }

    public PipelineConfigPage clickCredentialsAddButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(credentialsAddButton)).click();
        return this;
    }

    public PipelineConfigPage clickJenkinsProviderButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(jenkinsProviderButton)).click();
        return this;
    }

    public void getH2TextAndAssert(String text) {
        Assert.assertEquals(titleOfJenkinsCredentialsProviderWindow.getText(), text);
        getDriver().navigate().back();
        getDriver().switchTo().alert().accept();
    }

}
