package rahulshettyacademy.Resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {


    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("Automation Regression By Abhinav");
        extentSparkReporter.config().setDocumentTitle("Purchase Order Testing");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(extentSparkReporter);
        extent.setSystemInfo("Tester", "Abhinav Chaurasia");
        return extent;
    }
}
