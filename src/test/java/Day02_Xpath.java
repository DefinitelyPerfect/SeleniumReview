import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Day02_Xpath {
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        // driver nesnesi oluşturduk.
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test1() {
        driver.get("http://a.testaddressbook.com/");
        //<a id="sign-in" class="nav-item nav-link" data-test="sign-in"
        // href="/sign_in">Sign in</a>
        // id               EVET, className        HAYIR, tagName          EVET, name             HAYIR, xpath            EVET, cssSelector      EVET, linkText         EVET, partialLinkText  EVET
        WebElement signInLinki = driver.findElement(By.linkText("Sign in"));
        signInLinki.click();
        //  ipucu : findElements kullanabilirsiniz.
        //  ipucu : tagName'i a olan webelementler linktir.
        //  ipucu : bir sayfadaki tüm webelementleri bulmak istiyorsanız, findElements
        //          kullanabilirsiniz.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        List<WebElement> tumLinkler = driver.findElements(By.tagName("a"));
        //By.xpath("//a")
        for (WebElement w : tumLinkler) {
            System.out.println(w.getText());
        }
        //<input type="email" class="form-control" placeholder="Email"
        // data-test="email" name="session[email]" id="session_email">
        WebElement kullaniciAdi = driver.findElement(By.cssSelector(".form-control"));
        kullaniciAdi.sendKeys("testtechproed@gmail.com");
        // <input class="form-control" placeholder="Password"
        // data-test="password" type="password" name="session[password]" id="session_password">
        WebElement sifre = driver.findElement(By.cssSelector("#session_password"));
        sifre.sendKeys("Test1234!");
        // <input type="submit" name="commit" value="Sign in" class="btn btn-primary"
        // data-test="submit" data-disable-with="Sign in">
        WebElement girisYap = driver.findElement(By.cssSelector(".btn.btn-primary"));
        girisYap.click();
        // findElements By.xpath("//*") // o sayfadaki tüm webelementlerini bulur.
        /*List<WebElement> tumElementler = driver.findElements(By.xpath("//*"));
        for(WebElement w : tumElementler){
            System.out.println(w.getText());
        }*/
        WebElement tumYazilar = driver.findElement(By.tagName("body"));
        System.out.println(tumYazilar.getText());
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}