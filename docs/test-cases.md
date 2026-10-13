# UI Automation Test Cases

## Project Information

* **Project:** Java QA Automation Framework
* **Application Under Test:** [SauceDemo](https://www.saucedemo.com/)
* **Automation Tools:** Java, Selenium WebDriver, JUnit 5, Maven
* **Test Type:** UI Functional Testing

## Test Cases

### TC-LOGIN-001 — Successful Login

* **Objective:** Verify that a valid user can log in.
* **Precondition:** The login page is accessible.
* **Steps:**

    1. Open the login page.
    2. Enter username `standard_user`.
    3. Enter password `secret_sauce`.
    4. Click Login.
* **Expected Result:** The user is redirected to the inventory page.
* **Automation:** `LoginTest`
* **Priority:** High

### TC-LOGIN-002 — Invalid Login Credentials

* **Objective:** Verify that invalid credentials are rejected.
* **Steps:**

    1. Open the login page.
    2. Enter invalid credentials.
    3. Click Login.
* **Expected Result:** An error message is displayed and login is unsuccessful.
* **Automation:** `LoginTest`
* **Priority:** High

### TC-LOGIN-003 — Empty Login Fields

* **Objective:** Verify validation when required login fields are empty.
* **Steps:**

    1. Open the login page.
    2. Leave the required fields empty.
    3. Click Login.
* **Expected Result:** The application displays an appropriate validation message.
* **Automation:** `LoginTest`
* **Priority:** Medium

### TC-INVENTORY-001 — Inventory Products Are Displayed

* **Objective:** Verify that products are displayed after login.
* **Precondition:** The user is logged in.
* **Steps:**

    1. Open the inventory page.
    2. Inspect the product list.
* **Expected Result:** The inventory and product information are displayed.
* **Automation:** `InventoryTest`
* **Priority:** High

### TC-INVENTORY-002 — Sort Products by Price

* **Objective:** Verify ascending price sorting.
* **Precondition:** The inventory page is displayed.
* **Steps:**

    1. Select the price low-to-high sorting option.
    2. Inspect the product prices.
* **Expected Result:** Products are ordered from the lowest to the highest price.
* **Automation:** `InventoryTest`
* **Priority:** Medium

### TC-CART-001 — Add Product to Cart

* **Objective:** Verify that a product can be added to the cart.
* **Precondition:** The user is logged in and the inventory is displayed.
* **Steps:**

    1. Add a product to the cart.
    2. Inspect the cart badge.
    3. Open the cart.
* **Expected Result:** The selected product appears in the cart.
* **Automation:** `CartTest`
* **Priority:** High

### TC-CART-002 — Remove Product from Cart

* **Objective:** Verify that a product can be removed from the cart.
* **Precondition:** The cart contains a product.
* **Steps:**

    1. Open the cart.
    2. Remove the selected product.
    3. Inspect the cart contents.
* **Expected Result:** The removed product no longer appears in the cart.
* **Automation:** `CartTest`
* **Priority:** High

### TC-CHECKOUT-001 — Successful Checkout

* **Objective:** Verify that a user can complete an order.
* **Precondition:** The user is logged in and the cart contains a product.
* **Steps:**

    1. Open the cart.
    2. Click Checkout.
    3. Enter first name, last name, and postal code.
    4. Continue to the checkout overview.
    5. Finish the order.
* **Expected Result:** The order confirmation message `Thank you for your order!` is displayed.
* **Automation:** `CheckoutTest`
* **Priority:** High

### TC-CHECKOUT-002 — Checkout Requires First Name

* **Objective:** Verify validation when the first name is missing.
* **Precondition:** The user is logged in and the cart contains a product.
* **Steps:**

    1. Open the cart and click Checkout.
    2. Leave the first name empty.
    3. Enter the last name and postal code.
    4. Click Continue.
* **Expected Result:** The application displays `Error: First Name is required`.
* **Automation:** `CheckoutTest`
* **Priority:** High

## API Automation Test Cases

### TC-API-001 — Retrieve Post by ID

* **Objective:** Verify that the API returns a post for a valid ID.
* **Endpoint:** `GET /posts/1`
* **Expected Status:** `200 OK`
* **Expected Result:** The response contains the requested post ID, user ID, and a non-empty title.
* **Automation:** `PostApiTest`
* **Priority:** High

### TC-API-002 — Filter Posts by User ID

* **Objective:** Verify that the API filters posts by user ID.
* **Endpoint:** `GET /posts?userId=1`
* **Expected Status:** `200 OK`
* **Expected Result:** The response contains at least one post, and every returned post has `userId` equal to `1`.
* **Automation:** `PostApiTest`
* **Priority:** Medium

### TC-API-003 — Create Post

* **Objective:** Verify the response when submitting a new post.

* **Endpoint:** `POST /posts`

* **Expected Status:** `201 Created`

* **Expected Result:** The response contains the submitted title, body, user ID, and an ID.

* **Automation:** `PostApiTest`

* **Priority:** High

* **Note:** JSONPlaceholder simulates resource creation. A successful POST response does not imply permanent data persistence.

## Framework Feature Verification

### TC-FW-001 — Capture Screenshot on UI Test Failure

- **Objective:** Verify that a screenshot is captured when a UI test method fails.
- **Precondition:** The browser session is active and the screenshot extension is registered.
- **Steps:**
  1. Execute a UI test with a temporary failing assertion.
  2. Allow the test to finish its failure handling.
  3. Check the `target/screenshots/` directory.
  4. Verify that a PNG screenshot corresponding to the test method exists.
  5. Verify that JUnit still reports the test as failed.
- **Expected Result:** A screenshot is saved, and the original test failure is preserved.
- **Execution Status:** Pass — update only after verification.

## Execution Notes

Test cases document the intended behavior of the application. Record the actual execution status only after running the corresponding automated tests. If a test fails, investigate whether the cause is an application defect, an automation defect, or an environment issue.
