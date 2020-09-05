import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
public class Day03_isDisplayed {

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
    public void isDisplayed() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        //*[.="Dynamic Controls"]
        //h4
        WebElement baslik = driver.findElement(By.xpath("//*[.='Dynamic Controls']"));
        boolean gorunuyorMu = baslik.isDisplayed(); // true false
        System.out.println("Gorunuyor mu : " + gorunuyorMu);
    }

    @Test
    public void isSelected() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        WebElement secimElementi = driver.findElement(By.xpath("//input[@type='checkbox']"));
        // eğer bir webelement seçili ise, TRUE değilse FALSE
        boolean seciliMi = secimElementi.isSelected();
        System.out.println("Seçili mi 1: " + seciliMi);
        secimElementi.click();
        boolean seciliMi2 = secimElementi.isSelected();
        System.out.println("Seçili mi 2: " + seciliMi2);
        if (secimElementi.isSelected()) {// true false
            // eğer seçili ise burayı
        } else {
            // değilse burayı kullanırsınız.
        }
    }

    @Test
    public void isEnabled() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        WebElement inputKutusu = driver.findElement(By.xpath("//input[@type='text']"));
        boolean aktifMi = inputKutusu.isEnabled(); // true false
        System.out.println("Aktif Mi 1: " + aktifMi);
        WebElement enableButonu = driver.findElement(By.xpath("//*[.='Enable']"));
        enableButonu.click();

        // Java kodlarini calistirirken, istedigimiz saniye kadar bekletebiliriz.

        try {
            Thread.sleep(5000);  // 5 saniye bekliyor
        } catch (InterruptedException e) {
        }
        boolean aktifMi2 = inputKutusu.isEnabled(); // true false
        System.out.println("Aktif Mi 2: " + aktifMi2);
    }
}