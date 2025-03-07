package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.format.DateTimeFormatter;

public class ExtentReportHelper {

    public static ExtentReports reports;
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_hh_mm_ss");

    public static ExtentReports getReporterObject(){
        String reportPath = System.getProperty("user.dir")+"/Reports/extentReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Automation Results");
        sparkReporter.config().setDocumentTitle("Test Results");

        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Tester " , "Lokesh Kumar");
        return reports;

    }
}
