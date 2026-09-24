package com.multibank.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MarketingBannerPage {

    private final Page page;

    public MarketingBannerPage(Page page) {
        this.page = page;
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

    public Locator getBanner(String altText) {
        return page.locator("img[alt=\"" + altText + "\"]");
    }

    public boolean isBannerVisible(String altText) {
        return getBanner(altText).isVisible();
    }

    public String getBannerSource(String altText) {
        return getBanner(altText).getAttribute("src");
    }

    public Locator getBannerCard(String altText) {
        return getBanner(altText).locator("xpath=..");
    }

    public String getBannerCardText(String altText) {
        return getBannerCard(altText).innerText().trim();
    }
}