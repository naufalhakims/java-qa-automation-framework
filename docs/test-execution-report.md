# Test Execution Report

## 1. Execution Summary

| Field                 | Details                               |
| --------------------- | ------------------------------------- |
| Project               | Java QA Automation Framework          |
| Application           | SauceDemo                             |
| Automation Framework  | Selenium WebDriver + JUnit 5          |
| Build Tool            | Maven                                 |
| Browser               | Google Chrome                         |
| Test Scope            | Login, Inventory, Cart, Checkout      |
| Latest Execution Date | To be updated after execution         |
| Execution Status      | Pending final full-suite verification |

## 2. Test Coverage

| Module        | Planned Test Cases |
| ------------- | -----------------: |
| Login         |                  3 |
| Inventory     |                  2 |
| Shopping Cart |                  2 |
| Checkout      |                  2 |
| **Total**     |              **9** |

## 3. Execution Results

The test suite is designed to cover nine UI functional test cases.

Actual pass, fail, error, and skipped counts must be copied from the latest Maven test execution. No final pass rate is claimed until the complete suite has been executed and its results verified.

| Metric    | Result  |
| --------- | ------- |
| Tests Run | 12 |
| Passed    | 12 |
| Failed    | 0 |
| Errors    | 0 |
| Skipped   | 0 |
| Pass Rate | 100% |

Pass rate calculation:

`Pass Rate = Passed Tests / Tests Run × 100%`

Proof of work:
<img width="960" height="222" alt="image" src="https://github.com/user-attachments/assets/cfa46c78-253b-4a34-add8-0c62aa5cff87" />

| Feature | Verification | Result |
|---|---|---|
| Automatic failure screenshot | Temporarily fail a UI test and verify screenshot output | Pass |
| Screenshot file format | Verify the generated file is a PNG | Pass |
| Original test failure preserved | Verify the test remains failed after screenshot handling | Pass |

**Screenshot output directory:** `target/screenshots/`

**Note:** Framework feature verification is reported separately from the application's UI and API test counts.

## 4. Execution Command

Run the full test suite from the project root:

```bash
mvn clean test
```

The `clean` goal removes previous build output before the tests execute.

## 5. Result Evidence

Maven Surefire reports are generated under:

`target/surefire-reports/`

Review the generated text and XML reports for the final test counts, individual test results, and failure details.

## 6. Known Considerations

* The test suite depends on the public SauceDemo website.
* Browser and ChromeDriver compatibility may affect execution.
* Tests should be rerun after changes to page objects, locators, or browser configuration.
* A successful individual test class does not establish that the complete suite passes.

## 7. Final Assessment

Complete this section after the latest full-suite run.

Document the final execution counts, investigate any failures, and record the evidence used to determine whether the framework is stable.
