package com.multibank.automation.tests;
import com.microsoft.playwright.Locator;
import com.multibank.automation.base.BaseTest;
import com.multibank.automation.pages.SpotMarketPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class SpotMarketTest extends BaseTest {

    private SpotMarketPage spotMarketPage;

    @BeforeEach
    void openExplorePage() {
        spotMarketPage = new SpotMarketPage(page);
        spotMarketPage.open();
    }

    @Test
    void verifySpotMarketSectionIsDisplayed() {

        assertTrue(
                spotMarketPage.isSpotMarketHeadingVisible(),
                "Spot Market heading should be visible"
        );

        assertTrue(
                spotMarketPage.isSpotMarketDescriptionVisible(),
                "Spot Market description should be visible"
        );
    }

    @Test
    void verifyTradingAssetsAreDisplayed() {

        int assetCount = spotMarketPage.getAssetCount();

        assertTrue(
                assetCount > 0,
                "At least one trading asset should be displayed"
        );

        assertFalse(
                spotMarketPage.getAssetNames().isEmpty(),
                "Trading asset list should not be empty"
        );
    }

    @Test
    void verifyTradingAssetDestinations() {

        List<String> expectedSymbols = List.of(
                "MBG",
                "BTC",
                "ETH",
                "XRP",
                "SOL",
                "DOGE",
                "UNI",
                "BCH",
                "ADA",
                "AVAX",
                "LTC",
                "LINK",
                "AAVE",
                "TRX",
                "XLM"
        );

        assertTrue(
                spotMarketPage.getAssetCount() > 0,
                "Trading assets should be loaded"
        );

        for (String symbol : expectedSymbols) {

            String selector = "a[href=\"/explore/" + symbol + "\"]";

            Locator asset = page.locator(selector).first();

            if (page.locator(selector).count() > 0) {

                assertEquals(
                        "/explore/" + symbol,
                        asset.getAttribute("href"),
                        "Incorrect destination for: " + symbol
                );
            }
        }
    }

    @Test
    void verifyTradingPairDataFields() {

        List<String> expectedSymbols = List.of(
                "MBG",
                "BTC",
                "ETH",
                "XRP",
                "SOL",
                "DOGE",
                "UNI",
                "BCH",
                "ADA",
                "AVAX",
                "LTC",
                "LINK",
                "AAVE",
                "TRX",
                "XLM"
        );

        Pattern pricePattern = Pattern.compile(
                "^\\$[0-9,]+(?:\\.\\d{1,2})?$"
        );

        Pattern changePattern = Pattern.compile(
                "^-?\\d+(?:\\.\\d+)?%$"
        );

        for (String symbol : expectedSymbols) {

            String selector = "a[href=\"/explore/" + symbol + "\"]";

            if (page.locator(selector).count() == 0) {
                continue;
            }

            assertEquals(
                    symbol,
                    spotMarketPage.getAssetSymbol(symbol),
                    "Incorrect symbol for: " + symbol
            );

            assertFalse(
                    spotMarketPage.getAssetDisplayName(symbol).isBlank(),
                    "Asset name should not be empty for: " + symbol
            );

            String price = spotMarketPage.getAssetPrice(symbol);

            assertTrue(
                    pricePattern.matcher(price).matches(),
                    "Invalid price format for " + symbol + ": " + price
            );

            String change = spotMarketPage.getAssetChange(symbol);

            assertTrue(
                    changePattern.matcher(change).matches(),
                    "Invalid change format for " + symbol + ": " + change
            );
        }
    }
}