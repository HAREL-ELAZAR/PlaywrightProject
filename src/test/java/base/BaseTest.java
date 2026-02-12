package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.ExtentManager;
import utilities.ScreenshotUtil;

public class BaseTest {

    // Shared across suite (OK for parallel)
    protected static Playwright playwright;
    protected static Browser browser;
    protected static ExtentReports extent;

    // Per-thread (MUST for parallel)
    private static final ThreadLocal<BrowserContext> tlContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> tlPage = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();

    // ---------- Suite lifecycle ----------
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        extent = ExtentManager.getInstance();

        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(50);

        browser = playwright.chromium().launch(launchOptions);
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        try {
            if (browser != null) browser.close();
        } finally {
            browser = null;
            if (playwright != null) playwright.close();
            playwright = null;

            if (extent != null) extent.flush();
        }
    }

    // ---------- Test lifecycle ----------
    @BeforeMethod(alwaysRun = true)
    public void setUp(java.lang.reflect.Method method) {

        // ExtentTest per thread
        // (ExtentReports לא תמיד thread-safe, לכן נעטוף ב-synchronized)
        synchronized (BaseTest.class) {
            ExtentTest t = extent.createTest(method.getDeclaringClass().getSimpleName() + " :: " + method.getName());
            t.assignCategory(method.getDeclaringClass().getSimpleName());
            tlTest.set(t);
        }

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setLocale("he-IL")
                .setTimezoneId("Asia/Jerusalem");

        BrowserContext context = browser.newContext(contextOptions);
        Page page = context.newPage();

        page.setDefaultTimeout(30000);
        page.setDefaultNavigationTimeout(30000);

        tlContext.set(context);
        tlPage.set(page);

        log(Status.INFO, "Context + Page created successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                String base64Screenshot = ScreenshotUtil.takeScreenshotAsBase64(getPage());
                log(Status.FAIL, "Test Failed: " + result.getThrowable());
                getTest().addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot");
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                log(Status.PASS, "Test Passed Successfully");
            } else if (result.getStatus() == ITestResult.SKIP) {
                log(Status.SKIP, "Test Skipped: " + result.getThrowable());
            }
        } finally {
            // Close per-thread resources
            Page page = tlPage.get();
            BrowserContext context = tlContext.get();

            if (page != null) page.close();
            if (context != null) context.close();

            // IMPORTANT: clean ThreadLocal to prevent leaks
            tlPage.remove();
            tlContext.remove();
            tlTest.remove();
        }
    }

    // ---------- Getters for tests/pages ----------
    protected Page getPage() {
        Page page = tlPage.get();
        if (page == null) throw new IllegalStateException("Page is null - did setUp run?");
        return page;
    }

    protected BrowserContext getContext() {
        BrowserContext ctx = tlContext.get();
        if (ctx == null) throw new IllegalStateException("Context is null - did setUp run?");
        return ctx;
    }

    protected ExtentTest getTest() {
        ExtentTest t = tlTest.get();
        if (t == null) throw new IllegalStateException("ExtentTest is null - did setUp run?");
        return t;
    }

    // ---------- Helpers ----------
    protected void navigateTo(String url) {
        getPage().navigate(url);
        log(Status.INFO, "Navigated to: " + url);
    }

    protected void log(Status status, String message) {
        getTest().log(status, message);
    }

    protected void log(String message) {
        log(Status.INFO, message);
    }
}
