package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(linkText = "New Item")
    private WebElement newItem;

    @FindBy(linkText = "Manage Jenkins")
    private WebElement manageJenkins;

    @FindBy(xpath = "//a[@href='/user/admin']")
    private WebElement userAdmin;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public NewItemPage clickNewItem() {
        newItem.click();

        return new NewItemPage(getDriver());
    }

    public ManageJenkinsPage clickManageJenkins() {
        manageJenkins.click();

        return new ManageJenkinsPage(getDriver());
    }

    public UserAdminPage clickAdminLink() {
        userAdmin.click();

        return new UserAdminPage(getDriver());
    }
}
