package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserAdminPage extends BasePage {

    @FindBy(id = "description-link")
    private WebElement addDescriptionLink;

    @FindBy(name = "description")
    private WebElement descriptionTextarea;

    @FindBy(id = "yui-gen1-button")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@id='description']/div[1]")
    private WebElement descriptionText;

    public UserAdminPage(WebDriver driver) {
        super(driver);
    }

    public UserAdminPage clickDescriptionLink() {
        addDescriptionLink.click();

        return this;
    }

    public UserAdminPage setDescriptionTextarea(String text) {
        descriptionTextarea.sendKeys(text);

        return this;
    }

    public UserAdminPage clickSaveButton() {
        saveButton.click();

        return this;
    }

    public String getDescriptionText() {
        return descriptionText.getText();
    }
}
