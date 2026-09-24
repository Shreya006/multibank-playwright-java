TASK 1: 

# MultiBank Playwright Java Automation

UI automation framework for the MultiBank website using **Java, Playwright, JUnit 5 and Maven**.

## Tech Stack

- Java 25
- Maven 3.9+
- Playwright for Java
- JUnit 5
- Page Object Model (POM)
- GitHub Actions

---

## Project Structure

```text
multibank-playwright-java
│
├── .github
│   └── workflows
│       └── maven-tests.yml
│
├── src
│   ├── main
│   │   └── java
│   │       └── com.multibank.automation
│   │           └── pages
│   │               ├── HomePage.java
│   │               ├── NavigationPage.java
│   │               ├── SpotMarketPage.java
│   │               ├── MarketingBannerPage.java
│   │               ├── AppDownloadPage.java
│   │               └── CompanyPage.java
│   │
│   └── test
│       └── java
│           └── com.multibank.automation
│               ├── base
│               │   └── BaseTest.java
│               └── tests
│                   ├── HomePageTest.java
│                   ├── NavigationTest.java
│                   ├── SpotMarketTest.java
│                   ├── MarketingBannerTest.java
│                   ├── AppDownloadTest.java
│                   ├── CompanyPageTest.java
│                   ├── NegativeTest.java
│                   └── LinkValidationTest.java
│
├── .gitignore
├── pom.xml
└── README.md

Prerequisites-

Java 17 or higher
Maven 3.9+
Internet connection

Verify:  java -version
mvn -version

Run Tests-
From the project root, run the complete test suite with a single command: mvn test
The framework runs in headless Chromium by default.

Browser Execution
Chromium -  Bash: mvn test

Firefox
mvn -Dbrowser=firefox test

Firefox
mvn -Dbrowser=firefox test

Headed execution
mvn -Dheadless=false test

Test Coverage
1. Navigation and Layout

Validates:

Homepage availability
Header visibility
Expected navigation items
Navigation destinations
Sign-in and sign-up links
App download link
2. Spot Market

Validates:

Spot Market heading
Spot Market description
Trading asset availability
Trading asset destinations
Asset symbols
Asset display names
Price format
Percentage change format
Dynamic Market Data Assumption

The Spot Market data is dynamically rendered and can vary depending on the current website/backend response.

Therefore, the automation validates the structure and integrity of the market data currently rendered by the page rather than treating an exact number of market rows as a permanent UI contract.

3. Marketing Content

Validates:

Marketing banner visibility
Banner image sources
Promotional content
4. App Download

Validates:

"Download the app" link visibility
Expected download URL
New-tab configuration
HTTP link resolution
5. Company / About Us

Validates important content on the Company page, including:

Why MultiBank Group?
A tradition of global leadership
Innovation with purpose
Integrity built into every decision
The strength behind MultiBank Group
Community & Media
6. Negative and Edge Cases

Validates:

Invalid route handling
Mobile viewport rendering
Basic page availability under a mobile viewport
7. Link Validation

Important internal links are checked using Playwright API requests to verify successful HTTP responses.

Framework:-

Page Object Model

Page-specific locators and actions are separated from test classes.

Benefits:

Better maintainability
Reduced locator duplication
Easier UI maintenance
More readable test cases

Browser Configuration:
Browser selection is controlled using:
-Dbrowser=chromium
-Dbrowser=firefox
-Dbrowser=webkit

Headless Execution:
Headless execution is enabled by default for CI compatibility.
It can be disabled using:
-Dheadless=false


API Validation: 
Playwright API requests are used for link validation where UI interaction is unnecessary.

Timeouts:
Explicit navigation and Playwright timeouts are used to handle network latency and dynamically rendered content.

Assumptions:
Market data is dynamic and may change independently of the automation suite.
External services and redirects are outside the direct control of the website.
The automation focuses on functional behaviour and structural validation rather than exact volatile market values.
Cross-browser execution is intended to identify major compatibility issues.
The website must be reachable from the execution environment.

Test Plan:

| Area          | Test Objective                           | Test Type      |
| ------------- | ---------------------------------------- | -------------- |
| Homepage      | Verify page loads and header is visible  | Functional     |
| Navigation    | Verify navigation items and destinations | Functional     |
| Spot Market   | Verify trading market structure and data | Functional     |
| Marketing     | Verify promotional banners               | Functional     |
| App Download  | Verify download link and HTTP resolution | Functional/API |
| Company       | Verify Why MultiBank content             | Functional     |
| Invalid Route | Verify invalid URL behaviour             | Negative       |
| Mobile        | Verify basic mobile rendering            | Regression     |
| Links         | Verify important links                   | API            |
| Browsers      | Verify compatibility                     | Cross-browser  |


Release Readiness Checklist:

Homepage coverage
 Navigation coverage
 Spot Market coverage
 Marketing banner coverage
 App download validation
 Company page coverage
 Negative test coverage
 Link validation
 Page Object Model
 Chromium execution
 Firefox execution
 WebKit execution
 CI workflow
 Test plan
 Risk matrix
 Framework assumptions
 README execution instructions

 Risk Matrix:

 | Risk                               | Probability | Impact | Mitigation                                         |
| ---------------------------------- | ----------- | ------ | -------------------------------------------------- |
| Dynamic market data changes        | Medium      | Medium | Validate rendered market structure                 |
| UI locator changes                 | Medium      | Medium | Use semantic and attribute-based locators          |
| External download redirect changes | Medium      | Medium | Validate HTTP resolution                           |
| Browser-specific behaviour         | Medium      | Medium | Run core tests across Chromium, Firefox and WebKit |
| Website/network availability       | Medium      | High   | Report CI failures separately from code failures   |
| Backend market-data failure        | Medium      | High   | Avoid assertions against volatile exact values     |
| Invalid route behaviour changes    | Low         | Medium | Maintain dedicated negative test                   |


Task 2 – Test Strategy
Testing Approach recommended:
A layered testing strategy that should be used/-
1) Smoke testing for critical pages and navigation.
2) Functional UI automation for important user journeys.
3) API/network validation for service and link-level checks.
4) Negative testing for invalid and edge scenarios.
5) Cross-browser testing for compatibility.
6) CI execution for repeatable regression testing.

What Should Not Be Automated-
Not every validation provides equal value through UI automation.
For eg:
1) Pure visual design judgement
2) One-off exploratory testing
3) Content requiring human interpretation
4) Third-party app-store behaviour
5) Highly volatile market values where exact values are not contractual
These areas can be covered through manual exploratory testing or API-level testing where appropriate.

Automation Prioritisation:

Automation priority should consider:

Business criticality
User frequency
Regression risk
Stability of the functionality
Cost of manual execution
Critical and repeatable user journeys should receive automation coverage first.

CI / GitHub Actions:

GitHub Actions is configured to run the Maven test suite on:

Push to main
Push to master
Pull requests
Manual workflow execution

The workflow:

Checks out the repository
Sets up Java
Installs Playwright browsers
Runs the Maven test suite
Uploads test reports when available

Main test command: mvn test

Sample Execution--

Local execution:mvn test

Output: BUILD SUCCESS

Cross-browser execution:
mvn test
mvn -Dbrowser=firefox test
mvn -Dbrowser=webkit test

Conclusion:-

This framework provides maintainable Java + Playwright automation using Page Object Model, JUnit 5, API validation, negative testing and cross-browser execution.

The suite focuses on meaningful functional coverage while documenting areas affected by dynamic website data and external dependencies.

