package com.herokuapp.theinternet.pages;

import com.herokuapp.theinternet.base.TestUtilities;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginPageTests extends TestUtilities {

    @Parameters({"expectedTitle"})
    @Test(priority = 1)
    public void loginPageTitleTest(String expectedTitle) {
        log.info("Starting LoginPageTitle Test");

        LoginPage loginPage = new LoginPage(driver, log);
        Assert.assertEquals(loginPage.getPageTitle(), expectedTitle);
    }


    @Test(dataProvider = "positiveLoginCredentials")
    public void positiveLoginPageTest(String username, String password, String confirmationMessage) {
        log.info("Starting Positive LoginPage test");

        LoginPage loginPage = new LoginPage(driver, log);
        SecureAreaPage secureAreaPage = loginPage.loginToSecureAreaPage(username, password);
        String actualConfirmationMessage = secureAreaPage.getSuccessMessage();

        Assert.assertTrue(actualConfirmationMessage.contains(confirmationMessage), "Confirmation message not match");
    }


    @Test(dataProvider = "positiveLogoutCredentials")
    public void logoutTest(String username, String password, String confirmationMessage) {
        log.info("Starting Logout test");

        LoginPage loginPage = new LoginPage(driver, log);
        SecureAreaPage secureAreaPage = loginPage.loginToSecureAreaPage(username, password);

        secureAreaPage.logout();

        String actualConfirmationMessage = loginPage.getConfirmationMessageText();
        Assert.assertTrue(actualConfirmationMessage.contains(confirmationMessage), "Confirmation message not match");
    }


    @Test(dataProvider = "negativeLoginCredentials")
    public void negativeLoginPageTest(String username, String password, String confirmationMessage) {
        log.info("Starting Negative LoginPage test");

        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.loginToSecureAreaPage(username, password);

        Assert.assertTrue(loginPage.getConfirmationMessageText().contains(confirmationMessage));
    }




}