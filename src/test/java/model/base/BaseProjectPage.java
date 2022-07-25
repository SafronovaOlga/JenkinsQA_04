package model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BaseProjectPage extends BaseDashboardPage{

    public BaseProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1")
    private WebElement projectName;

    @FindBy(linkText = "Configure")
    private WebElement configure;

    @FindBy(css = ".icon-edit-delete")
    private WebElement delete;

    @FindBy(linkText = "Rename")
    private WebElement rename;

    @FindBy(id = "description-link")
    private WebElement addOrEditDescriptionButton;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement textFieldDescription;

    @FindBy(id = "yui-gen2-button")
    private WebElement saveDescriptionButton;

    @FindBy(id = "yui-gen1-button")
    private WebElement disableOrEnableButton;

    public String getProjectName() {
        return projectName.getText();
    }

    public String getDisableOrEnableButton() {
        return disableOrEnableButton.getText();
    }

    public void clickConfigure() {
        configure.click();
    }

    public void clickDelete() {
        delete.click();
    }

    public void clickRename() {
        rename.click();
    }

    public void clickAddOrEditDescriptionButton() {
        addOrEditDescriptionButton.click();
    }

    public void clickTextFieldDescription() {
        textFieldDescription.click();
    }

    public void clickSaveDescriptionButton() {
        saveDescriptionButton.click();
    }

    public void clickDisableOrEnableButton() {
        disableOrEnableButton.click();
    }

    public void addTextDescriptionAndSave(String textDescription) {
        clickAddOrEditDescriptionButton();
        textFieldDescription.sendKeys(textDescription);
        clickSaveDescriptionButton();
    }

    public void clearTextDescription() {
        clickAddOrEditDescriptionButton();
        textFieldDescription.clear();
        clickSaveDescriptionButton();
    }
}
