package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePage{
    private By successMessage = By.id("flash");
    private By pageName = By.xpath("//h2[contains(text(), 'Secure Area')]");
    private By logoutButton = By.xpath("//i[contains(text(), 'Logout')]/..");

    public SecureAreaPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    public String getPageName() {
        return find(pageName).getText();
    }

    public String getSuccessMessage(){
        return find(successMessage).getText();
    }

    public void logout() {
        click(logoutButton);
    }
}
