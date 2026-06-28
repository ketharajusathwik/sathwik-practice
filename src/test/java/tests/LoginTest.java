package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverFactory;
import pages.InventoryPage;
import pages.LoginPage;
import utils.TestDataProvider;
import utils.RetryAnalyzer;

import org.testng.Assert;

public class LoginTest extends BaseTest {

	@Test(
		    dataProvider = "loginData",
		    dataProviderClass = TestDataProvider.class,
		    retryAnalyzer = RetryAnalyzer.class
		)
		public void verifyLogin(String username, String password) {
      
        LoginPage login =
                new LoginPage(
                        DriverFactory.getDriver());

        login.login(
                username,
                password);
                
        String currentUrl =
                DriverFactory
                .getDriver()
                .getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains("inventory"));
    }
}