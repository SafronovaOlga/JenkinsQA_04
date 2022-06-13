import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class FabrianaCreateNewUserOnManageJenkinsTest extends BaseTest {

    private static String username = RandomStringUtils.randomAlphabetic(10);

    private String password = RandomStringUtils.randomAlphanumeric(10);

    private String trTableUsers = "//table[@id='people']/tbody/tr";

    private static String randomEmail() {
        String email = "";
        StringBuilder stringBuilder = new StringBuilder();
        email = email + stringBuilder.append(username).append("@").append(RandomStringUtils.randomAlphabetic(5)).append(".com");
        return email;
    }

    private WebElement dashboardMyViews() {
        return getDriver().findElement(By.xpath("//a[contains(@href, '/manage')]"));
    }

    private WebElement buttonManageUsers() {
        return getDriver().findElement(By.xpath("//a[contains(@href, 'securityRealm/')]"));
    }

    private WebElement buttonCreateUser() {
        return getDriver().findElement(By.xpath("//a[contains(@href, 'addUser')]"));
    }

    private WebElement fieldUsername() {
        return getDriver().findElement(By.xpath("//input[contains(@name, 'username')]"));
    }

    private WebElement fieldPassword() {
        return getDriver().findElement(By.xpath("//input[contains(@name, 'password1')]"));
    }

    private WebElement fieldConfirmPassword() {
        return getDriver().findElement(By.xpath("//input[contains(@name, 'password2')]"));
    }

    private WebElement fieldEmail() {
        return getDriver().findElement(By.xpath("//input[contains(@name, 'email')]"));
    }

    private WebElement buttonCreateUserDatabase() {
        return getDriver().findElement(By.xpath("//span[@class='first-child']/button[text()='Create User']"));
    }

    private WebElement titleTableUsers() {
        return getDriver().findElement(By.xpath("//div[@class='jenkins-app-bar']//h1[text()='Users']"));
    }

    @Test
    public void testCreateNewUserWithValidData_TC_107_001() {
        dashboardMyViews().click();

        buttonManageUsers().click();

        buttonCreateUser().click();

        fieldUsername().sendKeys(username);
        fieldPassword().sendKeys(password);
        fieldConfirmPassword().sendKeys(password);
        fieldEmail().sendKeys(randomEmail());
        buttonCreateUserDatabase().click();

        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        wait.until(ExpectedConditions.visibilityOf(titleTableUsers()));

        List<WebElement> users = getDriver().findElements(By.xpath(trTableUsers));
        List<String> actualResult = new ArrayList<>();
        for (WebElement user : users) {
            if (user.getText().contains(username)) {
                actualResult.add(user.getText());
            }
        }

        StringBuilder expectedResult = new StringBuilder();
        expectedResult.append(username).append("\n").append(username);

        Assert.assertEquals(actualResult.get(0), expectedResult.toString());
    }
}
