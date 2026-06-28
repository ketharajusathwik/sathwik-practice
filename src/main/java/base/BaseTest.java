package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import listeners.*;

import utils.ConfigReader;

@Listeners(TestListener.class)
public class BaseTest {

	@BeforeMethod
	public void setup() {							
		
		DriverFactory.initDriver();

		DriverFactory.getDriver().get(
				ConfigReader.getProperty("url"));
	}

	@AfterMethod
	public void tearDown() {

		DriverFactory.quitDriver();
	}
}