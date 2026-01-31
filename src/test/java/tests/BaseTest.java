package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeClass
    public void setUpClass() {
        playwright = Playwright.create();

        // Check if running in CI environment or headless mode requested
        boolean headless = System.getenv("CI") != null ||
                          System.getProperty("headless", "false").equals("true");

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
            .setHeadless(headless)
            .setSlowMo(headless ? 0 : 50);

        browser = playwright.chromium().launch(launchOptions);
    }

    @BeforeMethod
    public void setUp() {
        context = browser.newContext(new Browser.NewContextOptions()
            .setViewportSize(1920, 1080));
        page = context.newPage();
        page.setDefaultTimeout(30000);
        page.navigate(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (context != null) {
            context.close();
        }
    }

    @AfterClass
    public void tearDownClass() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
