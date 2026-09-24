package com.multibank.automation.base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeEach
    public void setUp() {

        playwright = Playwright.create();

        String browserName = System.getProperty("browser", "chromium");

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "true")
        );

        BrowserType browserType;

        switch (browserName.toLowerCase()) {

            case "firefox":
                browserType = playwright.firefox();
                break;

            case "webkit":
                browserType = playwright.webkit();
                break;

            case "chromium":
            default:
                browserType = playwright.chromium();
                break;
        }

        browser = browserType.launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(headless)
        );

        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1920, 1080)
        );

        page = context.newPage();

        page.setDefaultTimeout(15000);
    }

    @AfterEach
    public void tearDown() {

        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}