package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;

import pages.LoginPage;
import pages.InventoryPage;
import pages.CartPage;
import pages.CheckoutPage;
import utils.ConfigReader;
import utils.JsonReader;
import utils.RetryAnalyzer;


public class CheckoutTest
        extends BaseTest {

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void verifyCheckout() {

        LoginPage login =
                new LoginPage(
                        DriverFactory.getDriver());

        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password"));

        InventoryPage inventory =
                new InventoryPage(
                        DriverFactory.getDriver());

        inventory.addProductToCart();

        inventory.openCart();

        CartPage cart =
                new CartPage(
                        DriverFactory.getDriver());

        cart.clickCheckout();

        CheckoutPage checkout =
                new CheckoutPage(
                        DriverFactory.getDriver());

        checkout.enterCheckoutInfo(

                JsonReader.getValue(
                        "firstName"),

                JsonReader.getValue(
                        "lastName"),

                JsonReader.getValue(
                        "zipCode")
        );
        System.out.println(
                JsonReader.getValue(
                        "firstName"));

        checkout.clickContinue();

        Assert.assertTrue(

                DriverFactory
                        .getDriver()
                        .getCurrentUrl()
                        .contains(
                                "checkout-step-two.html"));
        checkout.clickFinish();
        
    }
}