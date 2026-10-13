# Bug Reports and QA Findings

## 1. Overview

This document records verified application defects and relevant automation findings identified during testing of SauceDemo.

A failed automated test does not automatically indicate an application defect. Failures must be investigated to distinguish application defects from automation issues and environment-related problems.

## 2. Verified Application Defects

No application defects have been confirmed and documented at this stage.

This status does not guarantee that the application is defect-free. It means that no reproducible application defect has yet been established as a verified finding.

## 3. Automation and Environment Findings

### FINDING-001 — Browser Password Manager Warning

* **Category:** Test environment / browser configuration
* **Observation:** Chrome displayed a password breach warning during automated testing, requiring manual interaction.
* **Impact:** The warning could interrupt unattended test execution.
* **Resolution:** ChromeOptions preferences were configured in `BaseUiTest` to suppress the relevant password manager prompts in the Selenium browser session.
* **Status:** Mitigation implemented; verify during subsequent automated runs.
* **Classification:** Browser automation environment issue, not a confirmed SauceDemo application defect.

### FINDING-002 — Checkout Navigation Timeout

* **Category:** UI automation reliability
* **Observation:** Automated checkout tests timed out while waiting for checkout navigation.
* **Investigation:** The checkout page was confirmed to use the `/checkout-step-one.html` route.
* **Resolution:** The checkout page interaction was updated to wait for the expected route and retry the click using JavaScript if the normal Selenium click does not navigate.
* **Status:** Reported resolved by the developer; revalidate with a complete test run.
* **Classification:** Automation interaction issue unless further evidence establishes an application defect.

## 4. Bug Reporting Policy

A finding will be recorded as a verified application bug only when it is reproducible and supported by evidence, such as screenshots, test logs, or consistent manual reproduction.

Each confirmed bug should include:

* Bug ID
* Title and description
* Preconditions
* Steps to reproduce
* Expected result
* Actual result
* Severity and priority
* Environment
* Evidence
* Status

## 5. Current Conclusion

The current findings primarily concern the automation environment and test execution. No confirmed application defect is claimed in this report.

## Framework Improvements

### IMP-001 — Automatic UI Failure Screenshots

- **Description:** Added a JUnit 5 extension to capture screenshots when UI test methods throw exceptions.
- **Implementation:** `ScreenshotOnFailureExtension.java`, registered through `BaseUiTest`.
- **Output:** PNG screenshots are saved under `target/screenshots/`.
- **Purpose:** Improve debugging by preserving visual evidence of failed UI test executions.
- **Limitation:** Screenshot capture requires an active WebDriver session. Failures outside the handled test-method execution may not produce screenshots.