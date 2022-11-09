package com.znsio.sample.e2e.screen.web.amazon;

import com.context.TestExecutionContext;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.amazon.IphoneDetailPageScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IphoneDetailPageScreenWeb extends IphoneDetailPageScreen {

    public static final By byAddToCartId = By.id("add-to-cart-button");
    public static final By validatingSidePannelById = By.id("attach-accessory-pane");
    private final Driver driver;
    private final Visual visually;
    private final WebDriver innerDriver;
    private static final String SCREEN_NAME = AmazonHomePageScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final TestExecutionContext context;



    public IphoneDetailPageScreenWeb (Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        this.innerDriver = this.driver.getInnerDriver();
        long threadId = Thread.currentThread()
                .getId();
        context = Runner.getTestExecutionContext(threadId);
    }

    @Override
    public IphoneDetailPageScreen addIphoneToCart() {
        driver.findElement(byAddToCartId).click();
        driver.waitTillElementIsPresent(validatingSidePannelById);
        boolean isSidePannelVisible = driver.findElement(validatingSidePannelById).isDisplayed();
        if(isSidePannelVisible) {
            LOGGER.info("Iphone added to cart");
        } else {
            LOGGER.error("Iphone is not added to cart");
        }
        return IphoneDetailPageScreen.get();
    }
}
