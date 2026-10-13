# Software Requirements — SauceDemo UI Testing

## 1. Document Information

| Field                  | Description                              |
| ---------------------- | ---------------------------------------- |
| Project                | Java QA Automation Framework             |
| Application Under Test | [SauceDemo](https://www.saucedemo.com/)  |
| Testing Scope          | UI Functional Testing                    |
| Automation Stack       | Java, Selenium WebDriver, JUnit 5, Maven |
| Document Status        | Initial Draft                            |

## 2. Purpose

This document defines the functional requirements used to design and execute automated UI tests for SauceDemo. The requirements establish the expected application behavior and provide traceability between application functionality and automated test cases.

## 3. Scope

The current automation scope covers:

* User authentication
* Product inventory display
* Product sorting
* Shopping cart operations
* Checkout validation
* Successful order completion

API testing, performance testing, security testing, and cross-browser compatibility testing are outside the current scope.

## 4. Functional Requirements

### FR-001 — User Login

The application shall allow users to log in using valid credentials.

**Expected behavior:** A successful login redirects the user to the inventory page.

**Related test cases:** TC-LOGIN-001

### FR-002 — Login Validation

The application shall reject invalid or incomplete login credentials and display an appropriate error message.

**Related test cases:** TC-LOGIN-002, TC-LOGIN-003

### FR-003 — Product Inventory

The application shall display available products and their relevant information after successful login.

**Related test cases:** TC-INVENTORY-001

### FR-004 — Product Sorting

The application shall allow users to sort products by price from lowest to highest.

**Related test cases:** TC-INVENTORY-002

### FR-005 — Shopping Cart Addition

The application shall allow users to add products to the shopping cart and reflect the cart contents in the UI.

**Related test cases:** TC-CART-001

### FR-006 — Shopping Cart Removal

The application shall allow users to remove products from the shopping cart.

**Related test cases:** TC-CART-002

### FR-007 — Checkout Information

The application shall provide a checkout form for users to enter their first name, last name, and postal code.

**Related test cases:** TC-CHECKOUT-001, TC-CHECKOUT-002

### FR-008 — Checkout Validation

The application shall prevent users from proceeding with checkout when the required first name is missing and display an appropriate validation message.

**Related test cases:** TC-CHECKOUT-002

### FR-009 — Order Completion

The application shall allow users to complete checkout when the required information is valid and display an order confirmation.

**Related test cases:** TC-CHECKOUT-001

## 5. Non-Functional Considerations

The following considerations are relevant to the automation framework, but are not yet verified as formal non-functional requirements:

* **Maintainability:** Page Object Model separates page interactions from test logic.
* **Reliability:** Explicit waits reduce dependence on fixed delays.
* **Repeatability:** Each test initializes its own browser and application state.
* **Diagnostics:** Test failures should provide useful error messages to support debugging.

Performance, accessibility, and security requirements have not been evaluated in this project.

## 6. Requirements Traceability

| Requirement | Related Test Cases               |
| ----------- | -------------------------------- |
| FR-001      | TC-LOGIN-001                     |
| FR-002      | TC-LOGIN-002, TC-LOGIN-003       |
| FR-003      | TC-INVENTORY-001                 |
| FR-004      | TC-INVENTORY-002                 |
| FR-005      | TC-CART-001                      |
| FR-006      | TC-CART-002                      |
| FR-007      | TC-CHECKOUT-001, TC-CHECKOUT-002 |
| FR-008      | TC-CHECKOUT-002                  |
| FR-009      | TC-CHECKOUT-001                  |

## 7. Limitations and Assumptions

* Tests depend on the availability and behavior of the public SauceDemo website.
* The current suite uses the `standard_user` test account.
* The requirements are based on the functionality selected for this portfolio project, not on an official SauceDemo specification.
* Test results may be affected by browser versions, network conditions, or changes to the application UI.
