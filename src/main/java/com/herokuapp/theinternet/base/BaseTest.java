package com.herokuapp.theinternet.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;
    protected Logger log;

    @Parameters({"browser", "description"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String browser, ITestContext ctx, String description){
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);


        driver = factory.createDriver();
        driver.manage().window().maximize();
        log.info(description);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        log.info("Driver tear down");
        driver.quit();
    }
}
