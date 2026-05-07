package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReport() {

        if (extent == null) {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/ExtentReport.html");

            sparkReporter.config().setReportName("Simple Books API Automation Report");
            sparkReporter.config().setDocumentTitle("API Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Project Name", "SimpleBooks API");
            extent.setSystemInfo("Tester", "Thiyagu");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tool", "REST Assured + TestNG");
        }

        return extent;
    }
}