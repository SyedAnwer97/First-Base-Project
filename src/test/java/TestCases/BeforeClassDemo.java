package TestCases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utility.ConfigFile;
import context.java.contextJava;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BeforeClassDemo {

	contextJava contextJava = new contextJava();
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	public static String screenshotsSubFolderName;
	public static String All_ReportName;
	public static String Fail_ReportName;

	@BeforeSuite
	@Parameters({ "TesterName", "URL" })
	public void initialseExtentReports(String Testername, String URL) {
		String timestamp = new SimpleDateFormat("-yyyy-MM-dd-hh-mm-ss").format(new Date());
		String All_ReportName = "AllTestReport" + timestamp + ".html";
		ExtentSparkReporter Sparkreporter_All = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/Extend Reports/" + All_ReportName);
		Sparkreporter_All.config().setReportName("All Test Report");
		Sparkreporter_All.config().setDocumentTitle("All Test");
		Sparkreporter_All.config().setTheme(Theme.DARK);
		Sparkreporter_All.config().setJs("document.getElementsByClassName('logo')[0].style.display='none';");

		String Fail_ReportName = "FailTestReport" + timestamp + ".html";
		ExtentSparkReporter SparkReporter_Failed = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/Extend Reports/" + Fail_ReportName);
		SparkReporter_Failed.filter().statusFilter().as(new Status[] { Status.FAIL }).apply();
		SparkReporter_Failed.config().setReportName("Failure Report");
		SparkReporter_Failed.config().setReportName("All Test Report");
		SparkReporter_Failed.config().setDocumentTitle("All Test");
		SparkReporter_Failed.config().setTheme(Theme.DARK);
		SparkReporter_Failed.config().setJs("document.getElementsByClassName('logo')[0].style.display='none';");

		extentReports = new ExtentReports();
		extentReports.attachReporter(Sparkreporter_All, SparkReporter_Failed);
		extentReports.setSystemInfo("Tester Name", Testername);
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("Host", URL);
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
	}

	@BeforeTest
	@Parameters({ "browser" })
	public void createTestInReport(ITestContext context, String string) {

		if (string.equalsIgnoreCase("chrome")) {
			ConfigFile configFile = new ConfigFile();
			contextJava.setConfigFile(configFile);
			WebDriver driver = new ChromeDriver();
			contextJava.setDriver(driver);
			WebDriverManager.chromedriver().setup();
			contextJava.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			contextJava.setWait(wait);
			contextJava.getDriver().manage().window().maximize();
		}

		if (string.equalsIgnoreCase("edge")) {
			WebDriver driver = new EdgeDriver();
			ConfigFile configFile = new ConfigFile();
			contextJava.setConfigFile(configFile);
			contextJava.setDriver(driver);
			WebDriverManager.edgedriver().setup();
			contextJava.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			contextJava.setWait(wait);
			contextJava.getDriver().manage().window().maximize();
		}

		if (string.equalsIgnoreCase("firefox")) {
			WebDriver driver = new FirefoxDriver();
			ConfigFile configFile = new ConfigFile();
			contextJava.setConfigFile(configFile);
			contextJava.setDriver(driver);
			WebDriverManager.firefoxdriver().setup();
			contextJava.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			contextJava.setWait(wait);
			//contextJava.getDriver().manage().window().maximize();
		}

		Capabilities capabilities = ((RemoteWebDriver) contextJava.getDriver()).getCapabilities();
		String device = capabilities.getBrowserName() + " "
				+ capabilities.getBrowserVersion().substring(0, capabilities.getBrowserVersion().indexOf("."));
		String author = context.getCurrentXmlTest().getParameter("author");

		ExtentTest extentTest = extentReports.createTest(context.getName());
		extentTest.assignAuthor(author);
		extentTest.assignDevice(device);
	}

	@AfterTest
	public void teardown() {
		contextJava.getDriver().quit();
	}

	@AfterMethod
	public void checkStatus(Method m, ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = null;
			screenshotPath = Screencapture(
					result.getTestContext().getName() + "_" + result.getMethod().getMethodName() + ".jpg");
			extentTest.addScreenCaptureFromPath(screenshotPath);
			extentTest.fail(MarkupHelper.createLabel(m.getName() + " is Failed", ExtentColor.RED));
			extentTest.fail(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromBase64String(Screencapture64()).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(MarkupHelper.createLabel(m.getName() + " is Passed", ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.skip(MarkupHelper.createLabel(m.getName() + " is skipped", ExtentColor.ORANGE));
		}

		extentTest.assignCategory(m.getAnnotation(Test.class).groups());
	}

	@AfterSuite
	public void generateExtentReports() throws IOException {

		extentReports.flush();
		Desktop.getDesktop()
				.browse(new File(System.getProperty("user.dir") + "/Extend Reports/" + All_ReportName).toURI());
		Desktop.getDesktop()
				.browse(new File(System.getProperty("user.dir") + "/Extend Reports/" + Fail_ReportName).toURI());
	}

	public String Screencapture(String FileName) {
		if (screenshotsSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss");
			screenshotsSubFolderName = myDateObj.format(myFormatObj);
		}

		TakesScreenshot takesScreenshot = (TakesScreenshot) contextJava.getDriver();
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/" + screenshotsSubFolderName + "/" + FileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
		return destFile.getAbsolutePath();

	}

	public String Screencapture64() {
		if (screenshotsSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss");
			screenshotsSubFolderName = myDateObj.format(myFormatObj);
		}

		TakesScreenshot takesScreenshot = (TakesScreenshot) contextJava.getDriver();
		String BaseString = takesScreenshot.getScreenshotAs(OutputType.BASE64);
		System.out.println("ScreenShot is taken");
		return BaseString;
	}
}
