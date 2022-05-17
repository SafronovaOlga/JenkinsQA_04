import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LetsBeTestersTest extends BaseTest {

    WebDriverWait wait;

    @BeforeMethod
    private void before() {
        wait = new WebDriverWait(getDriver(), 10);
    }

    private void openWebSite(String url) {
        getDriver().get(url);
    }

    @Test
    public void testCountOfSectionButtons() {

        openWebSite("https://www.nokia.com/");

        getDriver().findElement(By.id("modalAcceptAllBtn")).click();

        List<WebElement> carousel = getDriver().findElements(By.xpath("//div[contains(@id, 'tns1-item')]//h2"));

        Assert.assertEquals(carousel.size(), 9);
    }

    @Test
    public void testCheckTablet() {

        openWebSite("https://www.nokia.com/");

        getDriver().findElement(By.id("modalAcceptAllBtn")).click();

        Actions action = new Actions(getDriver());

        List<WebElement> menu = getDriver().findElements(By.xpath("//li[contains(@class, 'dropdown-menu')]/a"));
        WebElement forConsumers = menu.stream().filter(el2 -> el2.getText().equals("For consumers")).findFirst().orElse(null);

        action.moveToElement(forConsumers).build().perform();

        getDriver().findElement(By.xpath("//li[@class='dropdown-submenu-item']/a[text()='Phones']")).click();
        getDriver().findElement(By.xpath("//a[@data-gtm-cta='tablets']")).click();
        String actualResult = getDriver().findElement(By.xpath("//li[contains(@class, 'h5')]")).getText();

        Assert.assertEquals(actualResult, "Nokia T20");
    }

    @Test
    public void testKICheckAddress() {

        openWebSite("https://davinagaz.by/");

        getDriver().findElement(By.xpath("//b[text()= ' Полесская 14']")).click();

        String address = "Полесская 14";

        WebElement actualResult = getDriver().findElement(By.cssSelector(".name-office"));

        Assert.assertTrue(actualResult.getText().contains(address));
    }

    @Test
    public void testKICheckHeader() throws InterruptedException {

        openWebSite("https://davinagaz.by/");

        getDriver().findElement(By.xpath("//b[text()= ' Полесская 14']")).click();

        String expectedResult = "Аккумулятор";

        WebElement menu = getDriver().findElement(By.xpath("//a[text()='ТО и фильтра']"));

        Actions action = new Actions(getDriver());
        action.moveToElement(menu).perform();
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//a[text()='Аккумулятор']")).click();
        Thread.sleep(1000);

        WebElement actualResult = getDriver().findElement(By.tagName("h1"));

        Assert.assertEquals(actualResult.getText(), expectedResult.toUpperCase());
    }

    @Test
    public void testElementsTextBox() {

        openWebSite("https://demoqa.com/");

        getDriver().findElement(By.xpath("//h5[text()='Elements']")).click();

        String[] testData = {"Maksim", "test@test.com", "Sankt-Peterburg"};

        getDriver().findElement(By.id("item-0")).click();

        getDriver().findElement(By.id("userName")).sendKeys(testData[0]);
        getDriver().findElement(By.id("userEmail")).sendKeys(testData[1]);
        getDriver().findElement(By.id("currentAddress")).sendKeys(testData[2]);

        getDriver().findElement(By.id("submit")).click();

        String[] actualData = new String[3];
        actualData[0] = getDriver().findElement(By.id("name")).getText().replace("Name:", "");
        actualData[1] = getDriver().findElement(By.id("email")).getText().replace("Email:", "");
        actualData[2] = getDriver().findElement(By.xpath("//*[@class='mb-1'][@id='currentAddress']"))
                .getText().replace("Current Address :", "");

        System.out.println(getDriver().findElement(By.id("currentAddress")).getText());

        Assert.assertEquals(actualData, testData);
    }

    @Test
    public void testElementsRadioButton() {

        openWebSite("https://demoqa.com/");

        getDriver().findElement(By.xpath("//h5[text()='Elements']")).click();

        getDriver().findElement(By.id("item-2")).click();

        getDriver().findElement(By.xpath("//*[@for='impressiveRadio']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@class='text-success']")).getText(), "Impressive");
    }

    @Test
    public void testFlagmaMainPageOpening() {

        openWebSite("https://flagma.si/");

        getDriver().findElement(By.xpath("//a[@id='uc-lang-ru']")).click();

        WebElement goodAndServicesTitle = getDriver().findElement(By.xpath("//h1"));

        String actualResult = goodAndServicesTitle.getText();
        String expectedResult = "Товары и услуги в Словении";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testOfNavigationToCoffeeSection() {

        openWebSite("https://flagma.si/");

        getDriver().findElement(By.xpath("//a[@id='uc-lang-ru']")).click();

        Actions actions = new Actions(getDriver());

        WebElement sideBarMenu = getDriver().findElement(By.xpath("//div[@class='toggle-cats']"));
        sideBarMenu.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='cat-746']")));
        WebElement foodMenuItem = getDriver().findElement(By.xpath("//a[@class='cat-746']"));
        actions.moveToElement(foodMenuItem).perform();

        WebElement groupOfDrinksItem = getDriver().findElement(By.xpath("//span[text()='Вода, напитки, соки']"));
        actions.moveToElement(groupOfDrinksItem).perform();

        WebElement coffeeTeaCacaoItem = getDriver().findElement(By.xpath("//span[text()='Чай, кофе, какао']"));
        actions.moveToElement(coffeeTeaCacaoItem).perform();

        WebElement coffeeItem = getDriver().findElement(By.xpath("//a[@href='https://flagma.si/ru/products/kofe/']/span[text()='Кофе']"));
        actions.moveToElement(coffeeItem).click().perform();

        WebElement titleOfCoffeeSection = getDriver().findElement(By.xpath("//h1"));

        String actualResult = titleOfCoffeeSection.getText();
        String expectedResult = "Кофе в Словении";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testCheckSmartphoneIphoneSelection() {

        openWebSite("https://flagma.si/");

        getDriver().findElement(By.xpath("//a[@id='uc-lang-ru']")).click();

        WebElement inputSearch = getDriver().findElement(By.xpath("//div[@id='search-input']//input[@name='q']"));
        inputSearch.sendKeys("iphone\n");

        WebElement searchResultTitle = getDriver().findElement(By.xpath("//div[@class='title']"));

        String actualResult = searchResultTitle.getText();
        String expectedResult = "Найдено по запросу iphone в Словении";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testRegistration() {

        openWebSite("https://cloud.swivl.com/register/");

        WebElement NamePlaceholderElement = getDriver().findElement(By.id("swivl_registration_firstName"));
        WebElement LastNamePlaceholderElement = getDriver().findElement(By.id("swivl_registration_lastName"));
        WebElement EmailPlaceholderElement = getDriver().findElement(By.id("swivl_registration_email"));
        WebElement PasswordPlaceholderElement = getDriver().findElement(By.id("swivl_registration_plainPassword_first"));
        WebElement ConfirmPasswordPlaceholderElement = getDriver().findElement(By.id("swivl_registration_plainPassword_second"));
        Select AgeDropDownPlaceholder = new Select(getDriver().findElement(By.id("swivl_registration_age")));
        Select CountryDropDownPlaceholder = new Select(getDriver().findElement(By.id("swivl_registration_country")));
        Select RoleDropDownPlaceholder = new Select(getDriver().findElement(By.id("swivl_registration_role_rolePreset")));

        NamePlaceholderElement.sendKeys("John");
        LastNamePlaceholderElement.sendKeys("Smith");
        EmailPlaceholderElement.sendKeys("fmvmug@midiharmonica.com");
        PasswordPlaceholderElement.sendKeys("fmvmug@midiharmonica.com");
        ConfirmPasswordPlaceholderElement.sendKeys("fmvmug@midiharmonica.com");
        AgeDropDownPlaceholder.selectByIndex(2);
        CountryDropDownPlaceholder.selectByVisibleText("Japan");
        RoleDropDownPlaceholder.selectByVisibleText("IT");

        getDriver().findElement(By.xpath("//button[@id = 'formSubmit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class='control__error']/a")).getText(), "Sign in");
    }

    @Test
    public void testEbayFindProduct() {

        openWebSite("https://www.ebay.com/");

        getDriver().findElement(By.xpath("//input[@class = 'gh-tb ui-autocomplete-input']")).sendKeys("Ipad");
        getDriver().findElement(By.id("gh-btn")).click();
        WebElement actualResult = getDriver().findElement(By.xpath("//h1[@class = 'srp-controls__count-heading']/span[@class = 'BOLD'][2]"));

        Assert.assertEquals(actualResult.getText(), "ipad");
    }

    @Ignore
    @Test
    public void testCheckTopMenuCategory() {
        openWebSite("http://automationpractice.com/index.php");
        List<WebElement> GeneralPAgeTopMenuCategory = getDriver().findElements(By.cssSelector("[class*='sf-menu clearfix menu-content']>li"));
        for (int i = 0; i < GeneralPAgeTopMenuCategory.size(); i++) {
            GeneralPAgeTopMenuCategory = getDriver().findElements(By.cssSelector("[class*='sf-menu clearfix menu-content']>li"));
            String generalCategoryName = GeneralPAgeTopMenuCategory.get(i).getText().trim();
            GeneralPAgeTopMenuCategory.get(i).click();
            String subCategoryName = getDriver().findElement(By.className("cat-name")).getText().trim();
            Assert.assertEquals(generalCategoryName, subCategoryName);
            getDriver().navigate().back();
        }
    }

    @Ignore
    @Test
    public void testEbayFindProductByCategory() {

        openWebSite("https://www.ebay.com/");

        getDriver().findElement(By.id("gh-cat")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[.='eBay Motors']"))).click();

        getDriver().findElement(By.id("gh-btn")).click();
        getDriver().findElement(By.xpath("//li/button/span[contains(text(), 'Cars & Trucks')]")).click();
        getDriver().findElement(By.xpath("//a[contains(text(), 'Cadillac')]")).click();
        getDriver().findElement(By.xpath("//p[.='DeVille']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[.='Cadillac DeVille Cars']")).getText(), "Cadillac DeVille Cars");
    }

    @Test
    public void testEbayCheckFooterMenu() {

        openWebSite("https://www.ebay.com/");

        List<WebElement> footerElements = getDriver().findElements(By.xpath("//h3[@class = 'gf-bttl']"));

        List<String> expectedResult = Arrays.asList("Buy", "Sell", "Tools & apps", "Stay connected", "About eBay", "Help & Contact", "Community", "eBay Sites");

        for (int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(footerElements.get(i).getText(), expectedResult.get(i));
        }
    }

    @Test
    public void testMallCheckSearch() {

        openWebSite("https://www.mall.sk/");

        getDriver().findElement(By.id("site-search-input")).sendKeys("ipad");
        getDriver().findElement(By.id("search-button")).click();
        List<WebElement> models = getDriver().findElements(By.xpath("//a[@data-testid = 'category-menu-item']"));

        List<String> expectedResult = Arrays.asList("Ipad 2021", "Ipad air", "Apple ipad", "Ipad pro", "Ipad mini", "Ipad 10.2", "Ipad 8", "Ipad 2019", "Ipad 9.7");
        for (int i = 0; i < models.size(); i++) {
            Assert.assertEquals(models.get(i).getText(), expectedResult.get(i));
        }
    }

    @Test
    public void testMallCheckCountIcons() {

        openWebSite("https://www.mall.sk/");

        getDriver().findElement(By.id("site-search-input")).sendKeys("ipad");
        getDriver().findElement(By.id("search-button")).click();
        List<WebElement> icons = getDriver().findElements(By.xpath("//h3[contains(text(), 'iPad')]"));
        wait.until(ExpectedConditions.visibilityOfAllElements(icons));

        Assert.assertEquals(icons.size(), 24);
    }

    @Test
    public void testMallCheckSortingPricesDown(){

        openWebSite("https://www.mall.sk/");

        getDriver().findElement(By.id("site-search-input")).sendKeys("ipad");
        getDriver().findElement(By.id("search-button")).click();
        getDriver().findElement(By.xpath("//a[@data-sel = 'cta_sort_by_lowest_price']")).click();

        List<WebElement> elements = getDriver().findElements(By.xpath("//span[@class = 'product-price__price']"));

        Double[] price = new Double[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            price[i] = Double.parseDouble(elements.get(i).getText().replace(" €", "").replace(",", "."));
        }

        for (int i = 0; i < elements.size() - 1; i++) {
            Assert.assertTrue(price[i] <= price[i + 1]);
        }
    }
}
