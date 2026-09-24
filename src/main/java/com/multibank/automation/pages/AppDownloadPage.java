package com.multibank.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AppDownloadPage {

    private final Page page;
    private final Locator downloadAppLink;

    public AppDownloadPage(Page page) {
        this.page = page;

        downloadAppLink = page.getByRole(
                com.microsoft.playwright.options.AriaRole.LINK,
                new Page.GetByRoleOptions()
                        .setName("Download the app")
                        .setExact(true)
        );
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

    public boolean isDownloadLinkVisible() {
        return downloadAppLink.isVisible();
    }

    public String getDownloadLinkHref() {
        return downloadAppLink.getAttribute("href");
    }

    public String getDownloadLinkTarget() {
        return downloadAppLink.getAttribute("target");
    }

   public Page openDownloadLink() {
    return page.waitForPopup(() -> downloadAppLink.click());
    }
}