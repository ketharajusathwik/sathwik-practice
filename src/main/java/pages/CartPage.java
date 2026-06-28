package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class CartPage {

    WebDriver driver;
    private WaitUtils wait;

    By checkoutBtn =
            By.id("checkout");

    public CartPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void clickCheckout() {
    	
    	wait.waitForClickable(checkoutBtn).click();

    }
}