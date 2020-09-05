import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Day03_Dropdown {

    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        // driver nesnesi oluşturduk.
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void dropwdown1(){
        driver.get("http://amazon.com");
        WebElement dropDown = driver.findElement(By.id("searchDropdownBox"));
        Select select = new Select(dropDown);
        // seçili olan option(seçimi) alabiliriz.
        select.getFirstSelectedOption();
        // biz farklı bir kategori seçmek istersek ?
        // 3 farklı seçeneğimiz var
        // 1. selectByVisibleText
        // seçeneğin görünen yazısını kullanarak seçim yapmamızı sağlıyor.
        select.selectByVisibleText("Baby");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 2. selectByIndex
        // seçeneğin sıralamadaki konumuna göre seçim yapmamızı sağlıyor.
        select.selectByIndex(5);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 3. selectByValue
        // seçeneğin value attribute kullanılılarak seçim yapmamızı sağlıyor.
        select.selectByValue("search-alias=computers");

        // dropdown içerisindeki tüm seçenekleri almak istersek
        List<WebElement> tumSecenekler = select.getOptions();
        for(WebElement w : tumSecenekler){
            System.out.println(w.getText());
        }

        System.out.println("Toplam Kategori Sayisi: "+tumSecenekler.size());

        // sadece seçilen seçeneği bize return eder.
        WebElement secili = select.getFirstSelectedOption();
        System.out.println("Şuan Seçili Olan : " + secili.getText());

    }
}

