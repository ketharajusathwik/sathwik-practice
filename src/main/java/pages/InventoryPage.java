package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

public class InventoryPage {

    WebDriver driver;
    private WaitUtils wait;

    By addToCart =
            By.id("add-to-cart-sauce-labs-backpack");

    By cart =
            By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void addProductToCart() {

    	wait.waitForClickable(addToCart).click();
    }

    public void openCart() {

    	wait.waitForClickable(cart).click();
    }
}