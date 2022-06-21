import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;

import java.util.*;

public class _ManageUsersTest extends BaseTest {

    private static final String USER_NAME = "viktorp";
    private static final String PASSWORD = "123456ABC";
    private static final String FULL_NAME = "Viktor P";
    private static final String NEW_USER_FULL_NAME = "Michael";
    private static final String EMAIL = "testemail.@gmail.com";
    private static final By BUTTON_SUBMIT_TYPE = By.id("yui-gen1-button");
    private static final String USER_NAME_XPATH = "username";

    private void goOnCreateUserPage() {
        getManageJenkinsClick();
        getManageUsersClick();
        getCreateUserClick();
    }

    public void getManageJenkinsClick() {
        getDriver().findElement(By.xpath("//span[text()='Manage Jenkins']")).click();
    }

    public void getManageUsersClick() {
        getDriver().findElement(By.xpath("//dt[text()='Manage Users']")).click();
    }

    public void getCreateUserClick() {
        getDriver().findElement(By.xpath("//span[text()='Create User']")).click();
    }

    public WebElement userName() {
        return getDriver().findElement(By.id(USER_NAME_XPATH));
    }

    public WebElement passwordOne() {
        return getDriver().findElement(By.name("password1"));
    }

    public WebElement passwordConfirm() {
        return getDriver().findElement(By.name("password2"));
    }

    public WebElement fullName() {
        return getDriver().findElement(By.name("fullname"));
    }

    public WebElement emailAddress() {
        return getDriver().findElement(By.name("email"));
    }

    public WebElement buttonCreateUser() {
        return getDriver().findElement(BUTTON_SUBMIT_TYPE);
    }

    public WebElement fullNameConfigure() {
        return getDriver().findElement(By.name("_.fullName"));
    }

    public void fillOutFieldsCreateUser(String userName, String password, String fullName, String email) {

        userName().sendKeys(userName);
        passwordOne().sendKeys(password);
        passwordConfirm().sendKeys(password);
        fullName().sendKeys(fullName);
        emailAddress().sendKeys(email);
    }

    public List<WebElement> createListWithErrorMessages() {

        return getDriver().findElements(By.className("error"));
    }

    public List<WebElement> getListWithAllUsers() {

        return getDriver().findElements(By.xpath("//table[@id='people']/tbody/tr"));
    }

    public boolean displayedWebElement(String webElement) {

        return getDriver().getPageSource().contains(webElement);
    }


    @Test
    public void testUserCanCreateNewUser() {

        goOnCreateUserPage();
        fillOutFieldsCreateUser(USER_NAME, PASSWORD, FULL_NAME, EMAIL);
        buttonCreateUser().click();

        for (WebElement user : getListWithAllUsers()) {
            if (user.getText().contains(USER_NAME) && user.getText().contains(FULL_NAME)) {

                Assert.assertTrue(user.isDisplayed());
            }
        }
    }

    @Test(dependsOnMethods = {"testUserCanCreateNewUser"})
    public void testEditUserFullName() {

        getManageJenkinsClick();
        getManageUsersClick();
        getDriver().findElement(By.xpath("//a[@href='user/".concat(USER_NAME.toLowerCase()).concat("/configure']")))
                .click();
        fullNameConfigure().clear();
        fullNameConfigure().sendKeys(NEW_USER_FULL_NAME);
        getDriver().findElement(By.id("yui-gen2-button")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText(),
                NEW_USER_FULL_NAME);
    }

    @Test(dependsOnMethods = {"testEditUserFullName"})
    public void testUserCanDeleteUser() {

        getManageJenkinsClick();
        getManageUsersClick();
        getDriver().findElement(By.xpath(String.format("//a[contains(@href, '%s/delete')]", USER_NAME))).click();
        getDriver().findElement(BUTTON_SUBMIT_TYPE).click();

        for (WebElement user : getListWithAllUsers()) {

            Assert.assertFalse(user.getText().contains(USER_NAME));
        }
    }

    @Test(dependsOnMethods = {"testUserCanCreateNewUser"})
    public void testUsernameFieldDoesNotAcceptSpecialCharacters() {

        SoftAssert asserts = new SoftAssert();

        final String expectedResult = "User name must only contain alphanumeric characters, underscore and dash";

        goOnCreateUserPage();
        fillOutFieldsCreateUser("", PASSWORD, FULL_NAME, EMAIL);

        List<String> specialCharacters = new ArrayList<>(Arrays.asList(
                "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "+", ";", ":", "?", "=",
                "~", "`", "[", "{", "]", "}", "|", "/", "'", ",", ".", "*", "\"", "\\", " "));

        for (String specialCharacter : specialCharacters) {
            List<String> namesWithSpecialCharacter = new ArrayList<>(Arrays.asList(
                    specialCharacter + USER_NAME, USER_NAME + specialCharacter,
                    "vik".concat(specialCharacter).concat("torp")));

            for (String nameWithSpecialCharacter : namesWithSpecialCharacter) {
                if (!displayedWebElement(USER_NAME_XPATH)) {
                    getDriver().navigate().back();
                    getDriver().navigate().refresh();
                    List<WebElement> createUserFields = getDriver().findElements(
                            By.xpath("//input[@id='username']/ancestor::tbody//input"));
                    for (WebElement createUserField : createUserFields) {
                        createUserField.clear();
                    }
                    fillOutFieldsCreateUser("", PASSWORD, FULL_NAME, EMAIL);
                }
                userName().clear();
                userName().sendKeys(nameWithSpecialCharacter);
                buttonCreateUser().click();

                if (displayedWebElement(USER_NAME_XPATH)) {
                    for (WebElement errorMessage : createListWithErrorMessages()) {

                        asserts.assertEquals(errorMessage.getText(), expectedResult);
                    }
                } else {
                    for (WebElement user : getListWithAllUsers()) {
                        if (user.getText().contains(nameWithSpecialCharacter)) {

                            asserts.assertEquals(nameWithSpecialCharacter, expectedResult);

                            break;
                        }
                    }
                }
            }
        }
        asserts.assertAll();
    }

    @Test(dependsOnMethods = {"testUsernameFieldDoesNotAcceptSpecialCharacters"})
    public void testErrorMessagesHaveValidCssValues() {

        SoftAssert asserts = new SoftAssert();

        goOnCreateUserPage();
        fillOutFieldsCreateUser(USER_NAME.concat("*"), PASSWORD, FULL_NAME, EMAIL);
        buttonCreateUser().click();

        List<String> cssValues = new ArrayList<>(Arrays.asList(
                "color", "font-weight", "padding-left", "min-height", "line-height",
                "background-image",
                "background-position", "background-repeat", "background-size"));

        List<String> expectedResults = new ArrayList<>(Arrays.asList(
                "rgba(204, 0, 0, 1)", "700", "20px", "16px", "16px",
                "error.svg",
                "0% 0%", "no-repeat", "16px 16px"));

        for (WebElement errorMessageElement : createListWithErrorMessages()) {
            int index = 0;

            for (String expectedResult : expectedResults) {
                String actualResult = errorMessageElement.getCssValue(cssValues.get(index));
                if (actualResult.contains("url")) {

                    asserts.assertTrue(actualResult.contains(expectedResults.get(index)));
                } else {

                    asserts.assertEquals(actualResult, expectedResult);
                }
                index++;
            }
        }
        asserts.assertAll();
    }

    @Test
    public void testCreateUserEmptyFields() {
        final List<String> expectedErrorsText = List.of("Password is required",
                "\"\" is prohibited as a full name for security reasons.",
                "Invalid e-mail address",
                "\"\" is prohibited as a username for security reasons.");

        goOnCreateUserPage();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(BUTTON_SUBMIT_TYPE)).click();

        List<WebElement> actualErrors = createListWithErrorMessages();
        List<String> actualErrorsText = new ArrayList<>();
        for (WebElement error : actualErrors) {
            actualErrorsText.add(error.getText());
        }

        Assert.assertEquals(actualErrorsText.size(), 4);
        Assert.assertEquals(actualErrorsText, expectedErrorsText);
    }

    @Test
    public void testCheckValueInUsernameEqualValueFromFullName() {
        goOnCreateUserPage();
        fillOutFieldsCreateUser("Balthazarrr", "", "", "");
        getDriver().findElement(BUTTON_SUBMIT_TYPE).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("fullname")));

        String actualResultFullName = fullName().getAttribute("value");
        Assert.assertEquals(actualResultFullName, "Balthazarrr");

        final List<String> expectedErrorsText = List.of("Password is required", "Invalid e-mail address");
        List<WebElement> actualErrors = createListWithErrorMessages();
        List<String> actualErrorsText = new ArrayList<>();
        for (WebElement error : actualErrors) {
            actualErrorsText.add(error.getText());
        }

        Assert.assertEquals(actualErrorsText.size(), 2);
        Assert.assertEquals(actualErrorsText, expectedErrorsText);
    }
}