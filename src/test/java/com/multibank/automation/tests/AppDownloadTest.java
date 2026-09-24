package com.multibank.automation.tests;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.multibank.automation.base.BaseTest;
import com.multibank.automation.pages.AppDownloadPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppDownloadTest extends BaseTest {

    private AppDownloadPage appDownloadPage;

    @BeforeEach
    void openExplorePage() {
        appDownloadPage = new AppDownloadPage(page);
        appDownloadPage.open();
    }

    @Test
    void verifyDownloadAppLinkConfiguration() {

        assertTrue(
                appDownloadPage.isDownloadLinkVisible(),
                "Download the app link should be visible"
        );

        assertEquals(
                "https://mbio.go.link/6OW91",
                appDownloadPage.getDownloadLinkHref(),
                "Incorrect download app link"
        );

        assertEquals(
                "_blank",
                appDownloadPage.getDownloadLinkTarget(),
                "Download app link should open in a new tab"
        );
    }

    @Test
    void verifyDownloadAppLinkResolves() {

        String downloadUrl = appDownloadPage.getDownloadLinkHref();

        assertNotNull(
                downloadUrl,
                "Download app URL should not be null"
        );

        APIRequestContext request =
                playwright.request().newContext(
                        new APIRequest.NewContextOptions()
                );

        try {
            APIResponse response = request.get(downloadUrl);

            assertTrue(
                    response.status() >= 200
                            && response.status() < 400,
                    "Download app link should resolve successfully, but returned HTTP status: "
                            + response.status()
            );

        } finally {
            request.dispose();
        }
    }
}