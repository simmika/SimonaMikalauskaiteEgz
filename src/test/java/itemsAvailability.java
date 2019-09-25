import org.junit.AfterClass;

import org.junit.Assert;
import org.junit.BeforeClass;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class itemsAvailability {

    private static WebDriver driver = new ChromeDriver();

    @BeforeClass
    public static void OpenBrowser() {
        driver.get("http://192.168.1.178/opencartone");
    }

    @AfterClass
    public static void CloseBrowser() {
        driver.close();
    }

    static boolean findElement(By by) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    static List<String> getTestData(String testFilePath) throws IOException {
        List<String> records = new ArrayList<String>();
        String record;

        BufferedReader file = new BufferedReader(new FileReader(testFilePath));

        while ((record = file.readLine()) != null) {
            records.add(record);
        }

        file.close();
        return records;
    }

    @Test
    public void ATest() throws IOException {
        String filePath = "src/test/AData";
        List<String> items = getTestData(filePath);

        String[] tabs = {"Laptops", "Tablets", "Phones"};
        List<Item> notAvailableItems = new ArrayList<Item>();

        for (String item : items) {
            for (String tab : tabs) {
                driver.findElement(By.partialLinkText(tab)).click();
                boolean elementExists = findElement(By.partialLinkText(item));
                if (elementExists) {
                    driver.findElement(By.partialLinkText(item)).click();

                    boolean outOfStock = findElement(By.xpath("//li[contains(text(),'Availability: Out Of Stock')]"));
                    boolean preOrder = findElement(By.xpath("//li[contains(text(),'Availability: Pre-Order')]"));

                    if (preOrder) {
                        notAvailableItems.add(new Item(item, tab, "Pre-Order"));
                    }
                    if (outOfStock) {
                        notAvailableItems.add(new Item(item, tab, "Out Of Stock"));
                    }
                }
            }
        }

        if (notAvailableItems.size() > 0) {
            for (Item item : notAvailableItems) {
                System.out.print("skiltyje " + item.tab + " prekes " + item.name + " paskiekamumas yra " + item.status + ", ");
            }
            System.out.print("nors visu prekiu stastuso tikimasi \"In Stock\"");
        }

        Assert.assertTrue("Egzistuoja bent viena preke, kuri yra nepasiekiama", notAvailableItems.size() == 0);

    }

    @Test
    public void BTest() throws IOException {
        String filePath = "src/test/BData";
        List<String> items = getTestData(filePath);

        String[] tabs = {"Laptops", "Tablets", "Phones"};
        List<Item> notAvailableItems = new ArrayList<Item>();

        for (String item : items) {
            for (String tab : tabs) {
                driver.findElement(By.partialLinkText(tab)).click();
                boolean elementExists = findElement(By.partialLinkText(item));
                if (elementExists) {
                    driver.findElement(By.partialLinkText(item)).click();

                    boolean outOfStock = findElement(By.xpath("//li[contains(text(),'Availability: Out Of Stock')]"));
                    boolean preOrder = findElement(By.xpath("//li[contains(text(),'Availability: Pre-Order')]"));

                    if (preOrder) {
                        notAvailableItems.add(new Item(item, tab, "Pre-Order"));
                    }
                    if (outOfStock) {
                        notAvailableItems.add(new Item(item, tab, "Out Of Stock"));
                    }
                }
            }
        }

        if (notAvailableItems.size() > 0) {
            for (Item item : notAvailableItems) {
                System.out.print("skiltyje " + item.tab + " prekes " + item.name + " paskiekamumas yra " + item.status + ", ");
            }
            System.out.print("nors visu prekiu stastuso tikimasi \"In Stock\"");
        }

        Assert.assertTrue("Egzistuoja bent viena preke, kuri yra nepasiekiama", notAvailableItems.size() == 0);

    }


    public class Item {
        String name;
        String tab;
        String status;

        Item(String name, String tab, String status) {
            this.name = name;
            this.tab = tab;
            this.status = status;
        }
    }

}


