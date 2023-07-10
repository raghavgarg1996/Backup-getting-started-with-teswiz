package com.znsio.sample.e2e.screen.web.theapp;

import com.znsio.sample.e2e.screen.theapp.AppLaunchScreen;
import com.znsio.sample.e2e.screen.theapp.ClipboardDemoScreen;
import com.znsio.sample.e2e.screen.theapp.EchoScreen;
import com.znsio.sample.e2e.screen.theapp.LoginScreen;
import com.znsio.teswiz.runner.Driver;
import com.znsio.teswiz.runner.Visual;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class AppLaunchScreenWeb
        extends AppLaunchScreen {
    private static final String NOT_YET_IMPLEMENTED = "NOT_YET_IMPLEMENTED";
    private static final String SCREEN_NAME = AppLaunchScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private final Driver driver;
    private final Visual visually;
    private final By byLoginFormLinkText = By.linkText("Form Authentication");

    public AppLaunchScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
        visually.checkWindow(SCREEN_NAME, "Home screen");
    }

    @Override
    public LoginScreen selectLogin() {
        driver.findElement(byLoginFormLinkText).click();
        return LoginScreen.get();
    }

    @Override
    public AppLaunchScreen goBack() {
        LOGGER.info("Skipping this step for Web");
        return this;
    }

    @Override
    public EchoScreen selectEcho() {
        LOGGER.info("Skipping this step for Web");
        return EchoScreen.get();
    }

    @Override
    public ClipboardDemoScreen goToClipboardDemo() {
        return null;
    }
}
