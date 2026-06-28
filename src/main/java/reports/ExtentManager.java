package reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {

        if (extent == null) {

            String timestamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            String reportsFolder =
                    System.getProperty("user.dir")
                            + File.separator
                            + "Reports";

            new File(reportsFolder).mkdirs();

            String reportPath =
                    reportsFolder
                            + File.separator
                            + "ExtentReport_"
                            + timestamp
                            + ".html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config()
                    .setDocumentTitle(
                            "Automation Execution Report");

            spark.config()
                    .setReportName(
                            "SauceDemo Regression Suite");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo(
                    "Environment",
                    "QA");

            extent.setSystemInfo(
                    "Browser",
                    "Configured at runtime");
        }

        return extent;
    }
}