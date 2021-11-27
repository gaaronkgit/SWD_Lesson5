package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SiteTest
{
    @Test
    @DisplayName("Тестирование входа на сайт")
    void LoginTest()
    {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("http://automationpractice.com/index.php/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("gaaronk8325@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("qwerty123456");

        driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]")).click();

        List<WebElement> lstElm = driver.findElements(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"));

        Assertions.assertEquals("Stepan Ivanov",lstElm.get(0).getText(),"Тест пройден");

        driver.quit();
    }

    @Test
    @DisplayName("Тестирование добавления товара в корзину")
    void AddToCartTest()
    {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //Иногда возвращает ошибку 508
        driver.get("http://automationpractice.com/index.php/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("gaaronk8325@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("qwerty123456");

        driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]")).click();

        List<WebElement> lstElm = driver.findElements(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"));

        if(lstElm.size() != 1 || !lstElm.get(0).getText().equals("Stepan Ivanov"))
        {
            System.out.println("Авторизация на сайте не выполнена! Тест AddToCartTest провален");
            driver.quit();
            return;
        }

        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[6]/div/div[1]/div/a[1]/img")).click();

        driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span")).click();
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/span")).click();

        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a")).click();

        //По другому не смог найти :-( только такой длинный XPath находит верно
        Assertions.assertEquals("Printed Summer Dress",
                driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[2]/p/a")).getText(),
                "Товар найден в корзине! Тест AddToCartTest проден");

        driver.quit();
    }

    @Test
    @DisplayName("Тестирование перехода к футболкам")
    void NavigateToTShirtTest()
    {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //Иногда возвращает ошибку 508
        driver.get("http://automationpractice.com/index.php/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a")).click();

        Assertions.assertEquals("T-SHIRTS ", driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[1]")).getText(),"Тест пройден");
        driver.quit();
    }

    @Test
    @DisplayName("Тестирование поиска")
    void SearchBlouseTest()
    {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("http://automationpractice.com/index.php/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id=\"search_query_top\"]")).sendKeys("Blouse");
        driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button")).click();

        Assertions.assertEquals("Blouse",
                driver.findElement(By.cssSelector("#center_column > ul > li > div > div.right-block > h5 > a")).getText()
                ,"Тест пройден");

        driver.quit();



    }
}
