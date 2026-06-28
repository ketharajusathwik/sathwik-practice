package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class CheckoutPage {

	WebDriver driver;
	private WaitUtils wait;

	By firstName =
			By.id("first-name");

	By lastName =
			By.id("last-name");

	By zipCode =
			By.id("postal-code");

	By continueBtn =
			By.id("continue");

	By finishBtn = By.id("finish");

	public CheckoutPage(WebDriver driver) {

		this.driver = driver;
		this.wait = new WaitUtils(driver);
	}

	public void enterCheckoutInfo(
			String fname,
			String lname,
			String zip) {

		wait.waitForVisibility(firstName)
		.sendKeys(fname);

		wait.waitForVisibility(lastName)
		.sendKeys(lname);

		wait.waitForVisibility(zipCode)
		.sendKeys(zip);
	}

	public void clickContinue() {
		wait.waitForClickable(continueBtn).click();
	}
	public void clickFinish() {
		wait.waitForClickable(finishBtn).click();
	}
}