import org.junit.AfterClass;

import org.junit.BeforeClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class itemsAvailability {

    private static WebDriver driver = new ChromeDriver();

    @BeforeClass
    public static void OpenBrowser() {
        driver.get("https://demo.opencart.com/");
    }

    @AfterClass
    public static void CloseBrowser() {
        driver.close();
    }



}
