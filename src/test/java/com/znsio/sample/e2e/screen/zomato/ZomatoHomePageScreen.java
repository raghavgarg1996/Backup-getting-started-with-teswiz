package com.znsio.sample.e2e.screen.zomato;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.web.ZomatoHomePageScreenWeb;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class ZomatoHomePageScreen {

    private static final String SCREEN_NAME = ZomatoHomePageScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static ZomatoHomePageScreen get() {
        Driver driver = fetchDriver(Thread.currentThread()
                .getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread()
                .getId());

        switch(platform) {
            case web:
                return new ZomatoHomePageScreenWeb(driver, visually) {
                };
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract ZomatoHomePageScreen isHomepageVisible();

    public abstract ZomatoCityPageScreen selectLocationFromDropDown(String location);

    public abstract ZomatoCityPageScreen selectDetectLocation();

    public abstract String getLocationErrorMessage();

    public abstract boolean selectFromDetectLocation();
}
