package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.SearchResultPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPageScreenWeb extends SearchResultPageScreen {
    public static final By getIphoneCountByXpath = By.xpath("//div[@class='a-section']");
    public static final By clickOnFirstIphone13 = By.xpath("(//span[contains(text(),'iPhone 13')])[4]");


    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = SearchResultPageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;



    public SearchResultPageScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }


    @Override
    public SearchResultPageScreen isIphoneListVisible() {
       int iphoneCountOnSRp =  driver.findElements(getIphoneCountByXpath).size();
       if (iphoneCountOnSRp>0) {
           LOGGER.info("List of Iphone on SRP page:-" +iphoneCountOnSRp);
       } else {
           LOGGER.error("Iphone list not visible");
       }
     return this;
    }

    @Override
    public boolean selectFirstIphone() {
        driver.findElement(clickOnFirstIphone13).click();
        driver.switchToNextTab();
        String validatePageTitle = driver.getInnerDriver().getTitle();
        if (validatePageTitle.contains("Apple-iPhone-13")) {
            LOGGER.info("Successfully opened the Iphone Detail Page");
            return true;
        } else {
            LOGGER.error("Iphone Detail page not opened");
            return false;
        }
    }
}
