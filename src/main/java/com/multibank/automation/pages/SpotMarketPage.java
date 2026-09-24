package com.multibank.automation.pages;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SpotMarketPage {

    private final Page page;

    private final Locator spotMarketHeading;
    private final Locator spotMarketDescription;
    private final Locator assetLinks;

    public SpotMarketPage(Page page) {
        this.page = page;

        spotMarketHeading = page.getByRole(
                com.microsoft.playwright.options.AriaRole.HEADING,
                new Page.GetByRoleOptions()
                        .setName("Spot market")
                        .setExact(true)
        );

        spotMarketDescription = page.getByText(
                "Discover our cryptocurrency spot market data page, featuring crypto market cap, trading volumes, and historical performance.",
                new Page.GetByTextOptions().setExact(true)
        );

        assetLinks = page.locator("a[href^=\"/explore/\"]");
    }

    public void open() {
        page.navigate(
                "https://mb.io/en/explore",
                new Page.NavigateOptions()
                        .setWaitUntil(
                                com.microsoft.playwright.options.WaitUntilState.DOMCONTENTLOADED
                        )
                        .setTimeout(30000)
        );
    }

    public boolean isSpotMarketHeadingVisible() {
        return spotMarketHeading.isVisible();
    }

    public boolean isSpotMarketDescriptionVisible() {
        return spotMarketDescription.isVisible();
    }

    public int getAssetCount() {
    page.waitForSelector(
            "a[href^='/explore/']",
            new Page.WaitForSelectorOptions().setTimeout(30000)
    );

    Locator lastAsset = assetLinks.last();

    lastAsset.scrollIntoViewIfNeeded();

    page.waitForTimeout(2000);

    return assetLinks.count();
}

    public List<String> getAssetNames() {
        return assetLinks.allInnerTexts();
    }

    public Locator getAsset(String symbol) {
        return page.locator(
                "a[href=\"/explore/" + symbol + "\"]:visible"
        );
    }

    public boolean isAssetVisible(String symbol) {
        return getAsset(symbol).isVisible();
    }

    public String getAssetHref(String symbol) {
        return getAsset(symbol).getAttribute("href");
    }

    public Locator getTradingRow(String symbol) {
        return page.locator(
                "a[href=\"/explore/" + symbol + "\"]"
        ).locator("xpath=ancestor::tr");
    }

    public String getAssetSymbol(String symbol) {
        return getTradingRow(symbol)
                .locator("td[id$='displayName-td'] span")
                .first()
                .innerText()
                .trim();
    }

    public String getAssetDisplayName(String symbol) {
        return getTradingRow(symbol)
                .locator("td[id$='displayName-td'] span")
                .nth(1)
                .innerText()
                .trim();
    }

    public String getAssetPrice(String symbol) {
        return getTradingRow(symbol)
                .locator("td[id$='price-td']")
                .innerText()
                .trim();
    }

    public String getAssetChange(String symbol) {
        return getTradingRow(symbol)
                .locator("td[id$='change-td']")
                .innerText()
                .trim();
    }

    public boolean hasWeeklyChart(String symbol) {
        return getTradingRow(symbol)
                .locator("td[id$='week-chart-td']")
                .locator("svg")
                .count() > 0;
    }
}