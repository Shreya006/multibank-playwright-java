package com.multibank.automation.tests;

import com.multibank.automation.base.BaseTest;
import com.multibank.automation.pages.CompanyPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyPageTest extends BaseTest {

    private CompanyPage companyPage;

    @BeforeEach
    void openCompanyPage() {
        companyPage = new CompanyPage(page);
        companyPage.open();
    }

    @Test
    void verifyWhyMultiBankPageContent() {

        assertTrue(
                companyPage.isMainHeadingVisible(),
                "Why MultiBank Group heading should be visible"
        );

        assertTrue(
                companyPage.isIntroductionVisible(),
                "Introduction text should be visible"
        );

        assertTrue(
                companyPage.isTraditionSectionVisible(),
                "A tradition of global leadership section should be visible"
        );

        assertTrue(
                companyPage.isInnovationSectionVisible(),
                "Innovation with purpose section should be visible"
        );

        assertTrue(
                companyPage.isIntegritySectionVisible(),
                "Integrity built into every decision section should be visible"
        );

        assertTrue(
                companyPage.isStrengthSectionVisible(),
                "The strength behind MultiBank Group section should be visible"
        );

        assertTrue(
                companyPage.isCommunitySectionVisible(),
                "Community & Media section should be visible"
        );
    }
}