import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestFullCart {

    WebDriver _driver;

    @BeforeClass
    public void SetupWebDriver() {
        ChromeOptions options = new ChromeOptions();
        _driver = new ChromeDriver();
        options.addArguments("--start-maximized");
        _driver.get("https://www.saucedemo.com/");
        _driver.findElement(By.id("user-name")).sendKeys("problem_user");
        _driver.findElement(By.id("password")).sendKeys("secret_sauce");
        _driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void testFullCart() {
        List<WebElement> addToCarts = _driver.findElement(By.className("inventory_list")).findElements(By.xpath("//*[text()='Add to cart']"));
        for (WebElement addToCart : addToCarts) {//all items are put in cart
            addToCart.click();
        }
        _driver.findElement(By.id("shopping_cart_container")).click();//open cart
        List<String> itemsCart = new ArrayList<>();
        List<WebElement> elements = _driver.findElement(By.className("cart_list")).findElements(By.className("inventory_item_name"));// all items that are in cart
        for (WebElement element : elements) {
            itemsCart.add(element.getText());
        }
        _driver.findElement(By.id("react-burger-menu-btn")).click();//open burger
        try {
            Thread.sleep(360);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _driver.findElement(By.id("inventory_sidebar_link")).click();//open all items
        List<String> itemNames = new ArrayList<>();
        List<WebElement> inventorys = _driver.findElement(By.className("inventory_list")).findElements(By.className("inventory_item_name"));
        List<String> succesfullyInCart = new ArrayList<>();
        for (WebElement inventory : inventorys) {
            itemNames.add(inventory.getText());
        }
        for (String itemName : itemNames) {
            for (String itemCart : itemsCart) {
                if (itemCart.equals(itemName)) {
                    succesfullyInCart.add(itemName);
                }
            }
        }
        itemNames.removeAll(succesfullyInCart);
            for (String item : itemNames) {
                System.out.println(item);
            }
    }
}
