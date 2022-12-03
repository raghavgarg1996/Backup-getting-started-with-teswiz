package com.znsio.sample.e2e.businessLayer.Indigo;

import com.context.TestExecutionContext;
import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.sample.e2e.entities.INDIGO_TEST_CONTEXT;
import com.znsio.sample.e2e.screen.Indigo.IndigoLandingScreen;
import com.znsio.sample.e2e.screen.Indigo.IndigoVoucherScreen;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;


public class VoucherBL {
    private static final Logger LOGGER = Logger.getLogger(VoucherBL.class.getName());
    private final TestExecutionContext context;
    private final SoftAssertions softly;


    public VoucherBL(String userPersona, Platform forPlatform) {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
        Runner.setCurrentDriverForUser(userPersona, forPlatform, context);
    }

    public VoucherBL() {
        long threadId = Thread.currentThread()
                .getId();
        this.context = Runner.getTestExecutionContext(threadId);
        softly = Runner.getSoftAssertion(threadId);
    }

    public PromoCodeBL personaliseAndPreviewGiftVoucher() {
        IndigoVoucherScreen indigoVoucherScreen = IndigoLandingScreen.get()
                .selectGiftVoucher();
        int totalSum = indigoVoucherScreen
                .selectDenomination(1)
                .selectQuantity(1)
                .getTotalAmount();
        int denominationVal = Integer.parseInt(context.getTestState(INDIGO_TEST_CONTEXT.DENOMINATION).toString());
        int quantityVal = Integer.parseInt(context.getTestState(INDIGO_TEST_CONTEXT.QUANTITY).toString());
        int totalSumExpected = denominationVal * quantityVal;
        LOGGER.info("Total sum in Voucher Page " + totalSum);
        assertThat(totalSum).as("Total Sum visible on Voucher Screen is wrong").isEqualTo(totalSumExpected);
        context.addTestState(INDIGO_TEST_CONTEXT.TOTALAMOUNT, totalSum);
        String randomMessage = RandomStringUtils.randomAlphabetic(20);
        String voucherDetails = indigoVoucherScreen
                .personalizeVoucher(System.getProperty("user.name"), randomMessage)
                .getPreviewVoucherDetails();
        String messageExpected = context.getTestState(INDIGO_TEST_CONTEXT.DEAR).toString().trim() + ", " + context.getTestState(INDIGO_TEST_CONTEXT.MESSAGE).toString().trim();
        softly.assertThat(voucherDetails).as("Gift Voucher details are different on Preview Voucher screen").isEqualTo(messageExpected);
        return new PromoCodeBL();
    }
}
