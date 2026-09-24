package com.multibank.automation.tests;

import com.multibank.automation.base.BaseTest;
import com.multibank.automation.pages.NavigationPage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class NavigationTest extends BaseTest {

    private NavigationPage navigationPage;

    private static final Map<String, String> EXPECTED_NAVIGATION = Map.of(
            "Explore", "https://mb.io/en/explore",
            "Features", "https://mb.io/en/features",
            "OTC Desk", "https://mb.io/en/features/otc-desk",
            "Company", "https://mb.io/en/company",
            "Support", "https://mb.io/en/support",
            "Blog", "https://mb.io/en/blog",
            "$MBG", "https://token.multibankgroup.com/en"
    );

    @BeforeEach
    void openHomePage() {

        page.navigate(
                "https://mb.io/en",
                new com.microsoft.playwright.Page.NavigateOptions()
                        .setWaitUntil(
                                com.microsoft.playwright.options.WaitUntilState.DOMCONTENTLOADED
                        )
                        .setTimeout(30000)
        );

        navigationPage = new NavigationPage(page);
    }

    @Test
    void verifyNavigationItemsAreVisible() {

        for (String navigationItem : EXPECTED_NAVIGATION.keySet()) {

            assertTrue(
                    navigationPage.isNavigationLinkVisible(navigationItem),
                    "Navigation item should be visible: " + navigationItem
            );
        }
    }

    @Test
    void verifyNavigationDestinations() {

        for (Map.Entry<String, String> entry : EXPECTED_NAVIGATION.entrySet()) {

            String navigationItem = entry.getKey();
            String expectedUrl = entry.getValue();

            String actualHref =
                    navigationPage.getNavigationLinkHref(navigationItem);

            assertNotNull(
                    actualHref,
                    "Href should not be null for: " + navigationItem
            );

            assertEquals(
                    expectedUrl,
                    actualHref,
                    "Incorrect destination for: " + navigationItem
            );
        }
    }
}