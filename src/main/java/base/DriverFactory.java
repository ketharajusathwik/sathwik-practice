package base;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.ConfigReader;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    public static void initDriver() {

        String browser =
                ConfigReader.getProperty("browser");

        if (browser == null || browser.isBlank()) {

            throw new RuntimeException(
                    "Browser value is missing in config.properties");
        }

        switch (browser.toLowerCase()) {

            case "chrome":

                ChromeOptions options =
                        new ChromeOptions();

                Map<String, Object> prefs =
                        new HashMap<>();

                // Disable Chrome password save popup
                prefs.put(
                        "credentials_enable_service",
                        false);

                // Disable Chrome password manager popup
                prefs.put(
                        "profile.password_manager_enabled",
                        false);

                // Disable password breach / leak warning popup
                prefs.put(
                        "profile.password_manager_leak_detection",
                        false);

                options.setExperimentalOption(
                        "prefs",
                        prefs);

                driver.set(
                        new ChromeDriver(options));

                break;

            case "firefox":

                driver.set(
                        new FirefoxDriver());

                break;

            case "edge":

                driver.set(
                        new EdgeDriver());

                break;

            default:

                throw new RuntimeException(
                        "Unsupported browser: "
                                + browser);
        }

        getDriver()
                .manage()
                .window()
                .maximize();

        getDriver()
                .manage()
                .timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(0));
    }

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void quitDriver() {

        if (getDriver() != null) {

            getDriver()
                    .quit();

            driver.remove();
        }
    }
}