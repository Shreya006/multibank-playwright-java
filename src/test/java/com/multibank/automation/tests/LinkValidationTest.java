package com.multibank.automation.tests;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LinkValidationTest {

    private static Playwright playwright;
    private static APIRequestContext request;

    @BeforeAll
    static void setUp() {
        playwright = Playwright.create();

        request = playwright.request().newContext(
                new APIRequest.NewContextOptions()
        );
    }

    @AfterAll
    static void tearDown() {
        if (request != null) {
            request.dispose();
        }

        if (playwright != null) {
            playwright.close();
        }
    }

    @Test
    void verifyImportantInternalLinksAreNotBroken() {

        Map<String, String> links = new LinkedHashMap<>();

        links.put("Explore", "https://mb.io/en/explore");
        links.put("Features", "https://mb.io/en/features");
        links.put("OTC Desk", "https://mb.io/en/features/otc-desk");
        links.put("Company", "https://mb.io/en/company");
        links.put("Support", "https://mb.io/en/support");
        links.put("Blog", "https://mb.io/en/blog");

        for (Map.Entry<String, String> link : links.entrySet()) {

            APIResponse response = request.get(link.getValue());

            assertTrue(
                    response.status() >= 200 && response.status() < 400,
                    link.getKey()
                            + " link is broken. HTTP status: "
                            + response.status()
            );
        }
    }
}