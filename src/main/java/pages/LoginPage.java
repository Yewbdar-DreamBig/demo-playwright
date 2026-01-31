package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginPage {
    private final Page page;

    // Locators
    private static final String USERNAME_INPUT = "input[name='username']";
    private static final String PASSWORD_INPUT = "input[name='password']";
    private static final String LOGIN_BUTTON = "button[type='submit']";
    private static final String ERROR_MESSAGE = ".oxd-alert-content";
    private static final String USER_DROPDOWN = ".oxd-userdropdown-name";
    private static final String DASHBOARD_LOGO = ".oxd-brand-banner img";

    public LoginPage(Page page) {
        this.page = page;
    }

    // Actions
    public void enterUsername(String username) {
        page.waitForSelector(USERNAME_INPUT);
        page.fill(USERNAME_INPUT, username);
    }

    public void enterPassword(String password) {
        page.waitForSelector(PASSWORD_INPUT);
        page.fill(PASSWORD_INPUT, password);
    }

    public void clickLogin() {
        page.click(LOGIN_BUTTON);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Verifications
    public boolean isLoginPageDisplayed() {
        try {
            page.waitForSelector(LOGIN_BUTTON,
                new Page.WaitForSelectorOptions().setTimeout(5000));
            return page.isVisible(LOGIN_BUTTON);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            page.waitForSelector(ERROR_MESSAGE,
                new Page.WaitForSelectorOptions().setTimeout(5000));
            return page.isVisible(ERROR_MESSAGE);
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        page.waitForSelector(ERROR_MESSAGE);
        return page.textContent(ERROR_MESSAGE);
    }

    public boolean isDashboardDisplayed() {
        try {
            page.waitForSelector(DASHBOARD_LOGO,
                new Page.WaitForSelectorOptions().setTimeout(10000));
            return page.isVisible(DASHBOARD_LOGO);
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoggedInUserName() {
        page.waitForSelector(USER_DROPDOWN);
        return page.textContent(USER_DROPDOWN);
    }
}
