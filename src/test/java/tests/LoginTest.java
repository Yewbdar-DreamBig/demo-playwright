package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    // Test credentials (provided on the demo site)
    private static final String VALID_USERNAME = "Admin";
    private static final String VALID_PASSWORD = "admin123";

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(page);
    }

    @Test(priority = 1, description = "Verify login page is displayed")
    public void testLoginPageDisplayed() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
            "Login page should be displayed");
    }

    @Test(priority = 2, description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertTrue(loginPage.isDashboardDisplayed(),
            "Dashboard should be displayed after successful login");
    }

    @Test(priority = 3, description = "Verify error message with invalid username")
    public void testInvalidUsername() {
        loginPage.login("InvalidUser", VALID_PASSWORD);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
            "Error message should be displayed for invalid username");
    }

    @Test(priority = 4, description = "Verify error message with invalid password")
    public void testInvalidPassword() {
        loginPage.login(VALID_USERNAME, "wrongpassword");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
            "Error message should be displayed for invalid password");
    }

    @Test(priority = 5, description = "Verify error message with empty credentials")
    public void testEmptyCredentials() {
        loginPage.clickLogin();
        // The form has HTML5 validation, so we check if we're still on login page
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
            "Should remain on login page with empty credentials");
    }

     @Test(priority = 6, description = "Copy of test 5 Verify error message with empty credentials")
    public void testEmptyCredentials6() {
        loginPage.clickLogin();
        // The form has HTML5 validation, so we check if we're still on login page
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
            "Should remain on login page with empty credentials");
    }

         @Test(priority = 7, description = "Copy of test 7th Verify error message with empty credentials")
    public void testEmptyCredentials7() {
        loginPage.clickLogin();
        // The form has HTML5 validation, so we check if we're still on login page
        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
            "Should remain on login page with empty credentials");
    
    }
}
