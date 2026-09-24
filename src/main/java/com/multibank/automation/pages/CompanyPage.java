package com.multibank.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CompanyPage {

    private final Page page;

    private final Locator mainHeading;
    private final Locator introductionText;
    private final Locator traditionHeading;
    private final Locator innovationHeading;
    private final Locator integrityHeading;
    private final Locator strengthHeading;
    private final Locator communityHeading;

    public CompanyPage(Page page) {
        this.page = page;

        mainHeading = page.getByRole(
                com.microsoft.playwright.options.AriaRole.HEADING,
                new Page.GetByRoleOptions()
                        .setName("Why MultiBank Group?")
                        .setExact(true)
        );

        introductionText = page.getByText(
                "For nearly two decades, MultiBank has built a reputation as one of the world’s most trusted financial institutions. With a foundation rooted in regulation, transparency, and technological excellence, we continue to serve millions of clients across the globe with integrity and ambition.",
                new Page.GetByTextOptions().setExact(true)
        );

        traditionHeading = page.getByText(
                "A tradition of global leadership",
                new Page.GetByTextOptions().setExact(true)
        );

        innovationHeading = page.getByText(
                "Innovation with purpose",
                new Page.GetByTextOptions().setExact(true)
        );

        integrityHeading = page.getByText(
                "Integrity built into every decision",
                new Page.GetByTextOptions().setExact(true)
        );

        strengthHeading = page.getByText(
                "The strength behind MultiBank Group",
                new Page.GetByTextOptions().setExact(true)
        );

        communityHeading = page.getByText(
                "Community & Media",
                new Page.GetByTextOptions().setExact(true)
        );
    }

    public void open() {
        page.navigate(
                "https://mb.io/en/company",
                new Page.NavigateOptions()
                        .setWaitUntil(
                                com.microsoft.playwright.options.WaitUntilState.DOMCONTENTLOADED
                        )
                        .setTimeout(30000)
        );
    }

    public boolean isMainHeadingVisible() {
        return mainHeading.isVisible();
    }

    public boolean isIntroductionVisible() {
        return introductionText.isVisible();
    }

    public boolean isTraditionSectionVisible() {
        return traditionHeading.isVisible();
    }

    public boolean isInnovationSectionVisible() {
        return innovationHeading.isVisible();
    }

    public boolean isIntegritySectionVisible() {
        return integrityHeading.isVisible();
    }

    public boolean isStrengthSectionVisible() {
        return strengthHeading.isVisible();
    }

    public boolean isCommunitySectionVisible() {
        return communityHeading.isVisible();
    }
}