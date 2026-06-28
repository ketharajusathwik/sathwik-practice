package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String captureScreenshot(
            WebDriver driver,
            String testName) {

        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss_SSS")
                        .format(new Date());

        String screenshotsFolder =
                System.getProperty("user.dir")
                        + File.separator
                        + "Screenshots";

        new File(screenshotsFolder).mkdirs();

        String screenshotPath =
                screenshotsFolder
                        + File.separator
                        + testName
                        + "_"
                        + timestamp
                        + ".png";

        try {
            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(
                    source,
                    new File(screenshotPath));

            return screenshotPath;

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to capture screenshot for: "
                            + testName,
                    e);
        }
    }
}