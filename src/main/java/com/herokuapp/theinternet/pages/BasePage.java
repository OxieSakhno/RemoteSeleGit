package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    Logger log;

    public BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /** Open page with given URL */
    protected void openPage(String pageUrl) {
        driver.get(pageUrl);
    }

    /** Get Title of the page */
    protected String getPageTitle() {
        return driver.getTitle();
    }

    /** Get current URL */
    protected String getPageURL(){
        return driver.getCurrentUrl();
    }

    /** Find webElement by given locator */
    protected WebElement find(By locator) {
        waitForVisibilityOf(locator, 5);
        return driver.findElement(locator);
    }

    /** Type text into given element */
    protected void type(String text, By locator) {
        find(locator).sendKeys(text);
    }

    /** Click on given element */
    protected void click(By locator) {
        find(locator).click();
    }

    /** Wait for a specific condition for given amount of time */
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

    /** Wait for visibility of element by given locator */
    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                        break;
            } catch (StaleElementReferenceException e) {
                log.info("Exception caught: " + e.getMessage());
            }
            attempts++;
        }
    }
}
