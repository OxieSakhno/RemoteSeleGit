package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    private String pageURL = "https://the-internet.herokuapp.com/login";
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By cofirmationMessage = By.id("flash");


    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        openPage(this.pageURL);
    }


    /** Return Login button element */
    public WebElement getLoginButton() {
        return find(loginButton);
    }


    public SecureAreaPage loginToSecureAreaPage(String username, String password) {
        type(username, usernameField);
        type(password, passwordField);
        find(loginButton).click();
        return new SecureAreaPage(driver, log);
    }

    public String getConfirmationMessageText() {
        return find(cofirmationMessage).getText();
    }
}
