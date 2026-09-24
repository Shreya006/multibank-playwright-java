package com.multibank.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class NavigationPage {

    private final Page page;

    public NavigationPage(Page page) {
        this.page = page;
    }

    public Locator getNavigationLink(String linkText) {
        return page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions()
                        .setName(linkText)
                        .setExact(true)
        );
    }

    public boolean isNavigationLinkVisible(String linkText) {
        return getNavigationLink(linkText).isVisible();
    }

    public String getNavigationLinkHref(String linkText) {
        String href = getNavigationLink(linkText).getAttribute("href");

        if (href == null) {
            return null;
        }

        // Convert relative URLs into absolute URLs
        return java.net.URI.create(page.url()).resolve(href).toString();
    }

    public void clickNavigationLink(String linkText) {
        getNavigationLink(linkText).click();
    }
}