# Java QA Automation Framework

Automated UI and API testing framework built with Java, Selenium WebDriver, JUnit 5, REST Assured, and Maven. This project demonstrates functional test automation, reusable test architecture, API validation, and continuous integration using GitHub Actions.


![Java](https://img.shields.io/badge/Java-21%2B-orange)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-green)
![JUnit](https://img.shields.io/badge/JUnit-5-25A162)
![REST Assured](https://img.shields.io/badge/REST%20Assured-API%20Testing-blue)
![Maven](https://img.shields.io/badge/Build-Maven-C71A36)

## Overview

The framework automates selected functional scenarios for [SauceDemo](https://www.saucedemo.com/) and API scenarios for [JSONPlaceholder](https://jsonplaceholder.typicode.com/).

The goal is to practice maintainable automated testing, validate expected application behavior, and run tests locally or through a CI pipeline.

## Features

### UI Automation

* Login validation using positive and negative scenarios
* Inventory display and product price sorting
* Shopping cart addition and removal
* Checkout validation and order completion
* Page Object Model (POM) for reusable page interactions
* Explicit waits for synchronization with the UI

### API Automation

* GET request validation by resource ID
* Query parameter filtering
* POST request validation
* HTTP status code and JSON response assertions

### Continuous Integration

* GitHub Actions workflow triggered by pushes and pull requests
* Maven-based test execution
* Headless Chrome configuration for CI
* Test report artifacts uploaded after workflow execution

### Report Automation
- **Automatic Failure Screenshots:** Captures screenshots when UI test methods fail, helping identify interface and automation issues during debugging.
- **Screenshot Storage:** Saves captured screenshots as PNG files under `target/screenshots/`.
- **CI Screenshot Artifacts:** GitHub Actions can upload failure screenshots as workflow artifacts when the screenshot upload step is configured.

## Tech Stack

| Technology         | Purpose                                  |
| ------------------ | ---------------------------------------- |
| Java               | Programming language                     |
| Selenium WebDriver | Browser UI automation                    |
| JUnit 5            | Test organization and assertions         |
| REST Assured       | REST API automation                      |
| Maven              | Dependency management and test execution |
| GitHub Actions     | Continuous integration                   |
| Chrome             | Browser under test                       |

## Project Structure

```text
java-qa-automation-framework/
├── .github/
│   └── workflows/
│       └── ci.yml
├── docs/
│   ├── test-cases.md
│   ├── requirements.md
│   ├── bug-reports.md
│   ├── test-execution-report.md
│   └── requirements-traceability.md
├── src/
│   └── test/
│       └── java/
│           └── com/naufal/qa/
│               ├── api/
│               │   └── PostApiTest.java
│               ├── pages/
│               │   ├── LoginPage.java
│               │   ├── InventoryPage.java
│               │   ├── CartPage.java
│               │   └── CheckoutPage.java
│               └── ui/
│                   ├── BaseUiTest.java
│                   ├── LoginTest.java
│                   ├── InventoryTest.java
│                   ├── CartTest.java
│                   ├── CheckoutTest.java
│                   └── ScreenshotOnFailureExtension.java
├── pom.xml
└── README.md
```

## Prerequisites

Install the following tools:

* JDK 21 or a compatible JDK configuration
* Apache Maven
* Google Chrome
* Git

The CI workflow uses Java 21. Selenium Manager is used to help manage the browser driver.

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/naufalhakims/java-qa-automation-framework.git
cd java-qa-automation-framework
```

### 2. Run all tests

```bash
mvn clean test
```

### 3. Run UI tests only

```bash
mvn "-Dtest=LoginTest,InventoryTest,CartTest,CheckoutTest" test
```

### 4. Run API tests only

```bash
mvn "-Dtest=PostApiTest" test
```

### 5. Failure Screenshots

When a UI test method fails, the framework attempts to capture a screenshot before browser teardown.

Screenshots are saved to:

`target/screenshots/`

Each screenshot filename includes the test method name and a timestamp.

The original test failure is preserved even if screenshot capture fails. Screenshots are generated only when a failure is handled by the extension and a browser session is available.

If the GitHub Actions screenshot artifact step is configured, captured screenshots can also be downloaded from the workflow run's **Artifacts** section.

## Test Coverage

The current test suite is designed to cover the following scenarios.

| Module        | Planned Tests |
| ------------- | ------------: |
| Login         |             3 |
| Inventory     |             2 |
| Shopping Cart |             2 |
| Checkout      |             2 |
| API           |             3 |
| **Total**     |        **12** |

These figures describe the intended test suite. Actual pass/fail counts should be confirmed from the latest Maven execution.

## Test Reports

Maven Surefire generates test reports under:

```text
target/surefire-reports/
```

The GitHub Actions workflow is configured to upload these reports as an artifact after execution. Check the relevant workflow run to verify whether the job completed successfully and the artifact is available.

## Documentation

Additional project documentation is available in the `docs/` directory:

* [Test Cases](docs/test-cases.md)
* [Requirements](docs/requirements.md)
* [Bug Reports and QA Findings](docs/bug-reports.md)
* [Test Execution Report](docs/test-execution-report.md)
* [Requirements Traceability Matrix](docs/requirements-traceability.md)

## Notes and Limitations

* SauceDemo is a public demo application and may change over time.
* JSONPlaceholder is a simulated API; a successful POST response does not imply permanent resource persistence.
* The current suite focuses on selected functional scenarios, not exhaustive application coverage.
* Performance, security, accessibility, and cross-browser testing are outside the current scope.
* Test outcomes depend on the application, browser, network, and execution environment.

## Future Improvements

* Add more negative and boundary test scenarios
* Improve failure diagnostics and screenshot capture
* Generate richer test reports
* Expand API coverage
* Add cross-browser execution
* Improve CI reporting and test artifact management

## Author

**Naufal Syafi' Hakim**

* GitHub: [@naufalhakims](https://github.com/naufalhakims)
* LinkedIn: [Naufal Syafi Hakim](https://www.linkedin.com/in/naufal-syafi-hakim/)
