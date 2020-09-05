import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Day03_Ornek {
    // 1. DropDown'da Books kategorisini seçelim.
    // 2. Arama kutusuna JAVA yazalım.
    // 3. Toplam sonuç sayısını ekrana yazdıralım.
    // 4. İlk sıradaki ürüne tıklayalım.
    // 5. Back to results linki varsa, testimiz TRUE yoksa FALSE
    //      pass        fail

// Bu benim cozumum, calisiyor, dogru...

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
    public void amazonDropdownTest(){
        driver.get("http://amazon.com");

        WebElement dropdownButton=driver.findElement(By.cssSelector("#searchDropdownBox"));
        Select select = new Select(dropdownButton);
        select.selectByVisibleText("Books");

        WebElement searchBox=driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("JAVA"+ Keys.ENTER);

        WebElement result=driver.findElement(By.xpath("//*[contains(text(),'results')]"));
        System.out.println(result.getText());

        WebElement firstProduct=driver.findElement(By.partialLinkText("Effective Java"));
        firstProduct.click();

        WebElement backToResultsLink=driver.findElement(By.id("breadcrumb-back-link"));
        boolean varMi=backToResultsLink.isDisplayed();
        Assert.assertTrue(varMi); // True gelirse, testimiz basarili
    }
}
