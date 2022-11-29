package com.znsio.sample.e2e.screen.android.Indigo;

import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.Indigo.IndigoPromoCodeScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class IndigoPromoCodeScreenAndroid extends IndigoPromoCodeScreen {


    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = IndigoPromoCodeScreenAndroid.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public IndigoPromoCodeScreenAndroid(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public IndigoPromoCodeScreen enterInvalidPromoCode() {
        return null;
    }

    @Override
    public String getErrorMessage() {
        return null;
    }

    @Override
    public String getFinalAmount() {
        return null;
    }

    @Override
    public IndigoPromoCodeScreen enterReceiverDetail(String name, String lastName, String email, String phone) {
        return null;
    }
}