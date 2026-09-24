package com.multibank.automation.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.multibank.automation.base.BaseTest;
import com.multibank.automation.pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test
    void verifyMultiBankHomepageLoads() {

        HomePage homePage = new HomePage(page);

        homePage.open();

        assertFalse(
                homePage.getTitle().isEmpty(),
                "Page title should not be empty"
        );

        assertTrue(
                homePage.isHeaderVisible(),
                "Header should be visible"
        );
    }
}