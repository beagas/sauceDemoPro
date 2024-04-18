import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

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
    public void testFullCart(){
       List <WebElement> itemNames = _driver.findElement(By.className("inventory_list")).findElements(By.className("inventory_item_name"));
       for (WebElement itemName : itemNames) {
            System.out.print(itemName.getText());
            System.out.println();
        }
       List <WebElement> addToCarts = _driver.findElement(By.className("inventory_list")).findElements(By.xpath("//*[text()='Add to cart']"));
       for (WebElement addToCart : addToCarts) {
          addToCart.click();
       }
    }
}
