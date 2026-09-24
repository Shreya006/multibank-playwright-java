package com.multibank.automation.tests;

import com.microsoft.playwright.Response;
import com.multibank.automation.base.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NegativeTest extends BaseTest {

    @Test
    void verifyInvalidRouteReturnsErrorResponse() {

        Response response = page.navigate(
                "https://mb.io/en/this-page-does-not-exist",
                new com.microsoft.playwright.Page.NavigateOptions()
                        .setWaitUntil(
                                com.microsoft.playwright.options.WaitUntilState.DOMCONTENTLOADED
                        )
                        .setTimeout(30000)
        );

        assertNotNull(response, "Navigation response should not be null");

        int statusCode = response.status();

        assertTrue(
                statusCode >= 400,
                "Invalid route should return an HTTP error status, but received: "
                        + statusCode
        );
    }

    @Test
void verifyHomepageOnMobileViewport() {

    page.setViewportSize(390, 844);

    page.navigate(
            "https://mb.io/en",
            new com.microsoft.playwright.Page.NavigateOptions()
                    .setWaitUntil(
                            com.microsoft.playwright.options.WaitUntilState.DOMCONTENTLOADED
                    )
                    .setTimeout(30000)
    );

    assertTrue(
            page.locator("header").isVisible(),
            "Header should be visible on mobile viewport"
    );

    assertFalse(
            page.title().isBlank(),
            "Page title should not be empty on mobile viewport"
    );
}
}