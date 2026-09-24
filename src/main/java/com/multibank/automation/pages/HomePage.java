package com.multibank.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {

    private final Page page;

    private final Locator header;

    public HomePage(Page page) {
        this.page = page;
        this.header = page.locator("header");
    }

 public void open() {

    page.navigate(
            "https://mb.io/en",
            new Page.NavigateOptions()
                    .setWaitUntil(
                            com.microsoft.playwright.options.WaitUntilState.DOMCONTENTLOADED
                    )
                    .setTimeout(30000)
    );

}

    public String getTitle() {
        return page.title();
    }

    public boolean isHeaderVisible() {
        return header.isVisible();
    }

    public Page getPage() {
        return page;
    }
}