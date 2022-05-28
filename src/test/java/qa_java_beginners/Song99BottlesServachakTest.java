package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Song99BottlesServachakTest extends BaseTest {

    @Test
    public void testLyricsText() {
        String expectedResult = "99 bottles of beer on the wall, 99 bottles of beer.\n" +
                "Take one down and pass it around, 98 bottles of beer on the wall." +
                "98 bottles of beer on the wall, 98 bottles of beer.\n" +
                "Take one down and pass it around, 97 bottles of beer on the wall." +
                "97 bottles of beer on the wall, 97 bottles of beer.\n" +
                "Take one down and pass it around, 96 bottles of beer on the wall." +
                "96 bottles of beer on the wall, 96 bottles of beer.\n" +
                "Take one down and pass it around, 95 bottles of beer on the wall." +
                "95 bottles of beer on the wall, 95 bottles of beer.\n" +
                "Take one down and pass it around, 94 bottles of beer on the wall." +
                "94 bottles of beer on the wall, 94 bottles of beer.\n" +
                "Take one down and pass it around, 93 bottles of beer on the wall." +
                "93 bottles of beer on the wall, 93 bottles of beer.\n" +
                "Take one down and pass it around, 92 bottles of beer on the wall." +
                "92 bottles of beer on the wall, 92 bottles of beer.\n" +
                "Take one down and pass it around, 91 bottles of beer on the wall." +
                "91 bottles of beer on the wall, 91 bottles of beer.\n" +
                "Take one down and pass it around, 90 bottles of beer on the wall." +
                "90 bottles of beer on the wall, 90 bottles of beer.\n" +
                "Take one down and pass it around, 89 bottles of beer on the wall." +
                "89 bottles of beer on the wall, 89 bottles of beer.\n" +
                "Take one down and pass it around, 88 bottles of beer on the wall." +
                "88 bottles of beer on the wall, 88 bottles of beer.\n" +
                "Take one down and pass it around, 87 bottles of beer on the wall." +
                "87 bottles of beer on the wall, 87 bottles of beer.\n" +
                "Take one down and pass it around, 86 bottles of beer on the wall." +
                "86 bottles of beer on the wall, 86 bottles of beer.\n" +
                "Take one down and pass it around, 85 bottles of beer on the wall." +
                "85 bottles of beer on the wall, 85 bottles of beer.\n" +
                "Take one down and pass it around, 84 bottles of beer on the wall." +
                "84 bottles of beer on the wall, 84 bottles of beer.\n" +
                "Take one down and pass it around, 83 bottles of beer on the wall." +
                "83 bottles of beer on the wall, 83 bottles of beer.\n" +
                "Take one down and pass it around, 82 bottles of beer on the wall." +
                "82 bottles of beer on the wall, 82 bottles of beer.\n" +
                "Take one down and pass it around, 81 bottles of beer on the wall." +
                "81 bottles of beer on the wall, 81 bottles of beer.\n" +
                "Take one down and pass it around, 80 bottles of beer on the wall." +
                "80 bottles of beer on the wall, 80 bottles of beer.\n" +
                "Take one down and pass it around, 79 bottles of beer on the wall." +
                "79 bottles of beer on the wall, 79 bottles of beer.\n" +
                "Take one down and pass it around, 78 bottles of beer on the wall." +
                "78 bottles of beer on the wall, 78 bottles of beer.\n" +
                "Take one down and pass it around, 77 bottles of beer on the wall." +
                "77 bottles of beer on the wall, 77 bottles of beer.\n" +
                "Take one down and pass it around, 76 bottles of beer on the wall." +
                "76 bottles of beer on the wall, 76 bottles of beer.\n" +
                "Take one down and pass it around, 75 bottles of beer on the wall." +
                "75 bottles of beer on the wall, 75 bottles of beer.\n" +
                "Take one down and pass it around, 74 bottles of beer on the wall." +
                "74 bottles of beer on the wall, 74 bottles of beer.\n" +
                "Take one down and pass it around, 73 bottles of beer on the wall." +
                "73 bottles of beer on the wall, 73 bottles of beer.\n" +
                "Take one down and pass it around, 72 bottles of beer on the wall." +
                "72 bottles of beer on the wall, 72 bottles of beer.\n" +
                "Take one down and pass it around, 71 bottles of beer on the wall." +
                "71 bottles of beer on the wall, 71 bottles of beer.\n" +
                "Take one down and pass it around, 70 bottles of beer on the wall." +
                "70 bottles of beer on the wall, 70 bottles of beer.\n" +
                "Take one down and pass it around, 69 bottles of beer on the wall." +
                "69 bottles of beer on the wall, 69 bottles of beer.\n" +
                "Take one down and pass it around, 68 bottles of beer on the wall." +
                "68 bottles of beer on the wall, 68 bottles of beer.\n" +
                "Take one down and pass it around, 67 bottles of beer on the wall." +
                "67 bottles of beer on the wall, 67 bottles of beer.\n" +
                "Take one down and pass it around, 66 bottles of beer on the wall." +
                "66 bottles of beer on the wall, 66 bottles of beer.\n" +
                "Take one down and pass it around, 65 bottles of beer on the wall." +
                "65 bottles of beer on the wall, 65 bottles of beer.\n" +
                "Take one down and pass it around, 64 bottles of beer on the wall." +
                "64 bottles of beer on the wall, 64 bottles of beer.\n" +
                "Take one down and pass it around, 63 bottles of beer on the wall." +
                "63 bottles of beer on the wall, 63 bottles of beer.\n" +
                "Take one down and pass it around, 62 bottles of beer on the wall." +
                "62 bottles of beer on the wall, 62 bottles of beer.\n" +
                "Take one down and pass it around, 61 bottles of beer on the wall." +
                "61 bottles of beer on the wall, 61 bottles of beer.\n" +
                "Take one down and pass it around, 60 bottles of beer on the wall." +
                "60 bottles of beer on the wall, 60 bottles of beer.\n" +
                "Take one down and pass it around, 59 bottles of beer on the wall." +
                "59 bottles of beer on the wall, 59 bottles of beer.\n" +
                "Take one down and pass it around, 58 bottles of beer on the wall." +
                "58 bottles of beer on the wall, 58 bottles of beer.\n" +
                "Take one down and pass it around, 57 bottles of beer on the wall." +
                "57 bottles of beer on the wall, 57 bottles of beer.\n" +
                "Take one down and pass it around, 56 bottles of beer on the wall." +
                "56 bottles of beer on the wall, 56 bottles of beer.\n" +
                "Take one down and pass it around, 55 bottles of beer on the wall." +
                "55 bottles of beer on the wall, 55 bottles of beer.\n" +
                "Take one down and pass it around, 54 bottles of beer on the wall." +
                "54 bottles of beer on the wall, 54 bottles of beer.\n" +
                "Take one down and pass it around, 53 bottles of beer on the wall." +
                "53 bottles of beer on the wall, 53 bottles of beer.\n" +
                "Take one down and pass it around, 52 bottles of beer on the wall." +
                "52 bottles of beer on the wall, 52 bottles of beer.\n" +
                "Take one down and pass it around, 51 bottles of beer on the wall." +
                "51 bottles of beer on the wall, 51 bottles of beer.\n" +
                "Take one down and pass it around, 50 bottles of beer on the wall." +
                "50 bottles of beer on the wall, 50 bottles of beer.\n" +
                "Take one down and pass it around, 49 bottles of beer on the wall." +
                "49 bottles of beer on the wall, 49 bottles of beer.\n" +
                "Take one down and pass it around, 48 bottles of beer on the wall." +
                "48 bottles of beer on the wall, 48 bottles of beer.\n" +
                "Take one down and pass it around, 47 bottles of beer on the wall." +
                "47 bottles of beer on the wall, 47 bottles of beer.\n" +
                "Take one down and pass it around, 46 bottles of beer on the wall." +
                "46 bottles of beer on the wall, 46 bottles of beer.\n" +
                "Take one down and pass it around, 45 bottles of beer on the wall." +
                "45 bottles of beer on the wall, 45 bottles of beer.\n" +
                "Take one down and pass it around, 44 bottles of beer on the wall." +
                "44 bottles of beer on the wall, 44 bottles of beer.\n" +
                "Take one down and pass it around, 43 bottles of beer on the wall." +
                "43 bottles of beer on the wall, 43 bottles of beer.\n" +
                "Take one down and pass it around, 42 bottles of beer on the wall." +
                "42 bottles of beer on the wall, 42 bottles of beer.\n" +
                "Take one down and pass it around, 41 bottles of beer on the wall." +
                "41 bottles of beer on the wall, 41 bottles of beer.\n" +
                "Take one down and pass it around, 40 bottles of beer on the wall." +
                "40 bottles of beer on the wall, 40 bottles of beer.\n" +
                "Take one down and pass it around, 39 bottles of beer on the wall." +
                "39 bottles of beer on the wall, 39 bottles of beer.\n" +
                "Take one down and pass it around, 38 bottles of beer on the wall." +
                "38 bottles of beer on the wall, 38 bottles of beer.\n" +
                "Take one down and pass it around, 37 bottles of beer on the wall." +
                "37 bottles of beer on the wall, 37 bottles of beer.\n" +
                "Take one down and pass it around, 36 bottles of beer on the wall." +
                "36 bottles of beer on the wall, 36 bottles of beer.\n" +
                "Take one down and pass it around, 35 bottles of beer on the wall." +
                "35 bottles of beer on the wall, 35 bottles of beer.\n" +
                "Take one down and pass it around, 34 bottles of beer on the wall." +
                "34 bottles of beer on the wall, 34 bottles of beer.\n" +
                "Take one down and pass it around, 33 bottles of beer on the wall." +
                "33 bottles of beer on the wall, 33 bottles of beer.\n" +
                "Take one down and pass it around, 32 bottles of beer on the wall." +
                "32 bottles of beer on the wall, 32 bottles of beer.\n" +
                "Take one down and pass it around, 31 bottles of beer on the wall." +
                "31 bottles of beer on the wall, 31 bottles of beer.\n" +
                "Take one down and pass it around, 30 bottles of beer on the wall." +
                "30 bottles of beer on the wall, 30 bottles of beer.\n" +
                "Take one down and pass it around, 29 bottles of beer on the wall." +
                "29 bottles of beer on the wall, 29 bottles of beer.\n" +
                "Take one down and pass it around, 28 bottles of beer on the wall." +
                "28 bottles of beer on the wall, 28 bottles of beer.\n" +
                "Take one down and pass it around, 27 bottles of beer on the wall." +
                "27 bottles of beer on the wall, 27 bottles of beer.\n" +
                "Take one down and pass it around, 26 bottles of beer on the wall." +
                "26 bottles of beer on the wall, 26 bottles of beer.\n" +
                "Take one down and pass it around, 25 bottles of beer on the wall." +
                "25 bottles of beer on the wall, 25 bottles of beer.\n" +
                "Take one down and pass it around, 24 bottles of beer on the wall." +
                "24 bottles of beer on the wall, 24 bottles of beer.\n" +
                "Take one down and pass it around, 23 bottles of beer on the wall." +
                "23 bottles of beer on the wall, 23 bottles of beer.\n" +
                "Take one down and pass it around, 22 bottles of beer on the wall." +
                "22 bottles of beer on the wall, 22 bottles of beer.\n" +
                "Take one down and pass it around, 21 bottles of beer on the wall." +
                "21 bottles of beer on the wall, 21 bottles of beer.\n" +
                "Take one down and pass it around, 20 bottles of beer on the wall." +
                "20 bottles of beer on the wall, 20 bottles of beer.\n" +
                "Take one down and pass it around, 19 bottles of beer on the wall." +
                "19 bottles of beer on the wall, 19 bottles of beer.\n" +
                "Take one down and pass it around, 18 bottles of beer on the wall." +
                "18 bottles of beer on the wall, 18 bottles of beer.\n" +
                "Take one down and pass it around, 17 bottles of beer on the wall." +
                "17 bottles of beer on the wall, 17 bottles of beer.\n" +
                "Take one down and pass it around, 16 bottles of beer on the wall." +
                "16 bottles of beer on the wall, 16 bottles of beer.\n" +
                "Take one down and pass it around, 15 bottles of beer on the wall." +
                "15 bottles of beer on the wall, 15 bottles of beer.\n" +
                "Take one down and pass it around, 14 bottles of beer on the wall." +
                "14 bottles of beer on the wall, 14 bottles of beer.\n" +
                "Take one down and pass it around, 13 bottles of beer on the wall." +
                "13 bottles of beer on the wall, 13 bottles of beer.\n" +
                "Take one down and pass it around, 12 bottles of beer on the wall." +
                "12 bottles of beer on the wall, 12 bottles of beer.\n" +
                "Take one down and pass it around, 11 bottles of beer on the wall." +
                "11 bottles of beer on the wall, 11 bottles of beer.\n" +
                "Take one down and pass it around, 10 bottles of beer on the wall." +
                "10 bottles of beer on the wall, 10 bottles of beer.\n" +
                "Take one down and pass it around, 9 bottles of beer on the wall." +
                "9 bottles of beer on the wall, 9 bottles of beer.\n" +
                "Take one down and pass it around, 8 bottles of beer on the wall." +
                "8 bottles of beer on the wall, 8 bottles of beer.\n" +
                "Take one down and pass it around, 7 bottles of beer on the wall." +
                "7 bottles of beer on the wall, 7 bottles of beer.\n" +
                "Take one down and pass it around, 6 bottles of beer on the wall." +
                "6 bottles of beer on the wall, 6 bottles of beer.\n" +
                "Take one down and pass it around, 5 bottles of beer on the wall." +
                "5 bottles of beer on the wall, 5 bottles of beer.\n" +
                "Take one down and pass it around, 4 bottles of beer on the wall." +
                "4 bottles of beer on the wall, 4 bottles of beer.\n" +
                "Take one down and pass it around, 3 bottles of beer on the wall." +
                "3 bottles of beer on the wall, 3 bottles of beer.\n" +
                "Take one down and pass it around, 2 bottles of beer on the wall." +
                "2 bottles of beer on the wall, 2 bottles of beer.\n" +
                "Take one down and pass it around, 1 bottle of beer on the wall." +
                "1 bottle of beer on the wall, 1 bottle of beer.\n" +
                "Take one down and pass it around, no more bottles of beer on the wall." +
                "No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, 99 bottles of beer on the wall.";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver()
                .findElement(
                        By.xpath("//ul[@id='submenu']/li/a[@href='lyrics.html']"))
                .click();

        String[] pText = new String[100];
        for (int i = 0; i < pText.length; i++) {
            int index = i + 1;
            pText[i] = getDriver().findElement(
                            By.xpath("//div[@id='main']/p[" + index + "]"))
                    .getText();
        }

        String actualResult = "";

        for (int i = 0; i < pText.length; i++) {
            actualResult += pText[i];
        }

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testAreAllLanguagesStartingWithTheLetterJInMenuBrowseLanguages() {

        String expectedResult = "All languages starting with the " +
                "letter J are shown, sorted by Language.";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(
                By.xpath("//ul[@id='menu']//a[@href='/abc.html']")).click();

        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();

        String actualResult = getDriver().findElement(
                By.xpath("//div[@id='main']/p[1]")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testLastLanguageInMenuBrowseLanguageLetterM() {

        String expectedResult = "MySQL";
        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(
                By.xpath("//ul[@id='menu']//a[@href='/abc.html']")).click();

        getDriver().findElement(By.xpath("//a[@href = 'm.html']")).click();

        String actualResult = getDriver().findElement(
                By.xpath("//a[@href='language-mysql-1252.html']")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTableInBrowseLanguages() {
        String expectedResult = "Language Author Date Comments Rate";

        getDriver().get("http://www.99-bottles-of-beer.net/");

        getDriver().findElement(
                By.xpath("//ul[@id='menu']//a[@href='/abc.html']")).click();

        String actualResult = getDriver().findElement(
                By.xpath("//table[@id='category']/tbody/tr[1]")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testLanguageMathematica() {
        String expectedResult = "Mathematica Brenton Bostick 03/16/06 1";

        getDriver().get("http://www.99-bottles-of-beer.net/");
        getDriver().findElement(
                By.xpath("//ul[@id='menu']//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();

        String actualResult = getDriver()
                .findElement(
                        By.xpath("//table[@id='category']//td[normalize-space()='Brenton Bostick']/..")
                ).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testLanguageNameStartsWithNumber() {
        int expectedResult = 10;

        getDriver().get("http://www.99-bottles-of-beer.net/");
        getDriver().findElement(
                By.xpath("//ul[@id='menu']//a[@href='/abc.html']")).click();
        getDriver().findElement(By.xpath("//a[@href='0.html']")).click();

        int actualResult = getDriver()
                .findElements(By.xpath("//table[@id='category']/tbody/tr"))
                .size() - 1;

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testSignGuestbookError() {
        String expectedResult = "Error: Error: Invalid security code.";

        getDriver().get("http://www.99-bottles-of-beer.net/signv2.html");
        getDriver().findElement(
                        By.xpath("//form[@id='addentry']//input[@name='name']"))
                .sendKeys("Servachak Maria");
        getDriver().findElement(
                        By.xpath("//form[@id='addentry']//input[@name='location']"))
                .sendKeys("Ukraine");
        getDriver().findElement(
                        By.xpath("//form[@id='addentry']//input[@name='email']"))
                .sendKeys("servachak.m.u@gmail.com");
        getDriver().findElement(
                        By.xpath("//form[@id='addentry']//input[@name='homepage']"))
                .sendKeys("http://www.99-bottles-of-beer.net/");
        getDriver().findElement(
                        By.xpath("//form[@id='addentry']/p/textarea[@name='comment']"))
                .sendKeys("Hello");
        getDriver().findElement(
                        By.xpath("//form[@id='addentry']//input[@name='captcha']"))
                .sendKeys(Integer.toString((int) (Math.random() * 900) + 100));
        getDriver().findElement(
                        By.xpath("//form[@id='addentry']//input[@type='submit']"))
                .click();

        String actualResult = getDriver().findElement(
                By.xpath("//div[@id='main']/p[text()]")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testBookmarkRedditInBrowseLanguageCPlusPlus7() {
        String expectedResult = "reddit.com: Log in";

        getDriver().get("http://www.99-bottles-of-beer.net/");
        getDriver().findElement(
                By.xpath("//ul[@id='menu']//a[@href='/abc.html']")).click();
        getDriver().findElement(
                By.xpath("//ul[@id='submenu']//a[@href='c.html']")).click();
        getDriver().findElement(
                By.xpath("//table[@id='category']//a[@href='language-c++-111.html']")
        ).click();
        getDriver().findElement(
                By.xpath("//table[@id='category']//a[@href='language-c++-109.html']")
        ).click();
        getDriver().findElement(
                By.xpath("//div[@id='voting']//a[@title='reddit']")).click();

        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testIsShakespeareInTop20Rated() {
        getDriver().get("http://www.99-bottles-of-beer.net/");
        getDriver().findElement(
                        By.xpath("//div[@id='navigation']//a[@href='/toplist.html']"))
                .click();
        getDriver().findElement(By.xpath("//a[@href='./toplist.html']")).click();

        int actualResult = Integer.parseInt(
                getDriver().findElement(
                        By.xpath("//table[@id='category']//a[text()='Shakespeare']/../..")
                ).getText().substring(0, 2));

        Assert.assertTrue(actualResult < 21);
    }

    @Test
    public void testIsShakespeareInTop10EsotericRated() {
        getDriver().get("http://www.99-bottles-of-beer.net/");
        getDriver().findElement(
                        By.xpath("//div[@id='navigation']//a[@href='/toplist.html']"))
                .click();
        getDriver().findElement(By.xpath("//a[@href='./toplist_esoteric.html']"))
                .click();

        int actualResult = Integer.parseInt(
                getDriver().findElement(
                        By.xpath("//table[@id='category']//a[text()='Shakespeare']/../..")
                ).getText().substring(0, 2).replace(".", ""));

        Assert.assertTrue(actualResult < 11);
    }

    @Test
    public void testIsShakespeareInTop6Hits() {
        getDriver().get("http://www.99-bottles-of-beer.net/");
        getDriver().findElement(
                        By.xpath("//div[@id='navigation']//a[@href='/toplist.html']"))
                .click();
        getDriver().findElement(By.xpath("//a[@href='./tophits.html']")).click();

        int actualResult = Integer.parseInt(
                getDriver().findElement(
                        By.xpath("//table[@id='category']//a[text()='Shakespeare']/../..")
                ).getText().substring(0, 2).replace(".", ""));

        Assert.assertTrue(actualResult < 7);
    }

    @Test
    public void testIsShakespeareInTopRealLanguage() {
        getDriver().get("http://www.99-bottles-of-beer.net/");
        getDriver().findElement(
                        By.xpath("//div[@id='navigation']//a[@href='/toplist.html']"))
                .click();
        getDriver().findElement(By.xpath("//a[@href='./toplist_real.html']"))
                .click();

        Assert.assertFalse(getDriver().findElement(
                By.xpath("//table[@id='category']")).getText().contains("Shakespeare"));
    }

    @Test
    public void testNumberOfJavaVersions(){
        int expectedResult = 6;

        getDriver().get("http://www.99-bottles-of-beer.net/");
        getDriver().findElement(By.xpath("//ul[@id='menu']//a[@href='/abc.html']"))
                .click();
        getDriver().findElement(By.xpath("//ul[@id='submenu']//a[@href='j.html']"))
                .click();
        getDriver().findElement(By.xpath("//table[@id='category']//a[text()='Java']"))
                .click();
        int javaVersion = getDriver().findElements(
                By.xpath("//table[@id='category']//tr[@onmouseover]")).size();

        int actualResult = javaVersion + 1;

        Assert.assertEquals(actualResult, expectedResult);
    }
}