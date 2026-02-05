package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentManager;
import utils.ScreenshotUtil;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    // Playwright objects
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    // ExtentReports objects
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setUpSuite() {
        // יצירת ExtentReports instance - פעם אחת לכל ה-suite
        extent = ExtentManager.getInstance();
    }

    @BeforeClass
    public void setUpClass() {
        // יצירת Playwright ודפדפן - פעם אחת לכל class
        playwright = Playwright.create();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(true)
                .setSlowMo(50);

        browser = playwright.chromium().launch(launchOptions);
    }

    @BeforeMethod
    public void setUp(java.lang.reflect.Method method) {
        // יצירת test חדש ב-ExtentReports
        test = extent.createTest(method.getName());
        test.assignCategory(this.getClass().getSimpleName());

        // הגדרות Context
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setLocale("he-IL")
                .setTimezoneId("Asia/Jerusalem");

        // יצירת context ועמוד חדש
        context = browser.newContext(contextOptions);
        page = context.newPage();

        // הגדרות timeout
        page.setDefaultTimeout(30000);
        page.setDefaultNavigationTimeout(30000);

        test.log(Status.INFO, "Browser opened successfully");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // צילום מסך כ-Base64
            String base64Screenshot = ScreenshotUtil.takeScreenshotAsBase64(page);

            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
            test.addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot");

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed Successfully");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        }

        // סגירת העמוד והקונטקסט
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
    }

    @AfterClass
    public void tearDownClass() {
        // הדפסה לקונסול
        System.out.println("All tests in class completed");
    }

    @AfterSuite
    public void tearDownSuite() {
        // סגירת Playwright
        if (playwright != null) {
            playwright.close();
        }

        // שמירת דוח ExtentReports - חשוב מאוד!
        if (extent != null) {
            extent.flush();
        }

        System.out.println("Test suite completed. Report saved.");
    }

    // Helper methods
    protected void navigateTo(String url) {
        page.navigate(url);
        test.log(Status.INFO, "Navigated to: " + url);
    }

    protected String takeScreenshot(String fileName) {
        try {
            String screenshotPath = "screenshots/" + fileName + ".png";
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(screenshotPath))
                    .setFullPage(true));
            return screenshotPath;
        } catch (Exception e) {
            test.log(Status.WARNING, "Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }

    // Helper method for logging
    protected void log(String message) {
        test.log(Status.INFO, message);
    }

    // Helper method for logging with status
    protected void log(Status status, String message) {
        test.log(status, message);
    }
}