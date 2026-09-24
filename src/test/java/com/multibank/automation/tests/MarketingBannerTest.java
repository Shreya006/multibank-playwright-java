package com.multibank.automation.tests;

import com.multibank.automation.base.BaseTest;
import com.multibank.automation.pages.MarketingBannerPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MarketingBannerTest extends BaseTest {

    private MarketingBannerPage marketingBannerPage;

    @BeforeEach
    void openExplorePage() {
        marketingBannerPage = new MarketingBannerPage(page);
        marketingBannerPage.open();
    }

    @Test
    void verifyMarketingBannersAreDisplayed() {

        List<String> expectedBanners = List.of(
                "Earn rewards",
                "Instant buy crypto",
                "Deposit funds"
        );

        for (String banner : expectedBanners) {

            assertTrue(
                    marketingBannerPage.isBannerVisible(banner),
                    "Marketing banner should be visible: " + banner
            );

            assertNotNull(
                    marketingBannerPage.getBannerSource(banner),
                    "Banner image source should exist: " + banner
            );
        }
    }

    @Test
    void verifyMarketingBannerContent() {

        String earnRewardsText =
                marketingBannerPage.getBannerCardText("Earn rewards");

        assertTrue(
                earnRewardsText.contains("Earn interest on your assets"),
                "Earn Rewards banner should contain the expected heading"
        );

        assertTrue(
                earnRewardsText.contains("25%"),
                "Earn Rewards banner should contain the APY information"
        );
    }
}