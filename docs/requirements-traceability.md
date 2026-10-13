# Requirements Traceability Matrix

## 1. Purpose

This matrix links functional requirements to their associated test cases and automation modules.

## 2. Traceability Matrix

| Requirement ID | Requirement                    | Test Case IDs                    | Automation Class |
| -------------- | ------------------------------ | -------------------------------- | ---------------- |
| FR-001         | Successful login               | TC-LOGIN-001                     | LoginTest        |
| FR-002         | Login validation               | TC-LOGIN-002, TC-LOGIN-003       | LoginTest        |
| FR-003         | Product inventory display      | TC-INVENTORY-001                 | InventoryTest    |
| FR-004         | Price sorting                  | TC-INVENTORY-002                 | InventoryTest    |
| FR-005         | Add product to cart            | TC-CART-001                      | CartTest         |
| FR-006         | Remove product from cart       | TC-CART-002                      | CartTest         |
| FR-007         | Checkout information           | TC-CHECKOUT-001, TC-CHECKOUT-002 | CheckoutTest     |
| FR-008         | Required first-name validation | TC-CHECKOUT-002                  | CheckoutTest     |
| FR-009         | Successful order completion    | TC-CHECKOUT-001                  | CheckoutTest     |

## 3. Coverage Notes

* Each listed functional requirement maps to at least one planned test case.
* Checkout has both a positive test and a negative validation test.
* Login includes positive and negative scenarios.
* The matrix describes planned coverage, not proof that every test passed.
* Coverage should be reviewed whenever requirements or automated tests change.

## 4. Future Coverage

Potential future extensions include:

* Empty last-name and postal-code validation
* Cart quantity and multiple-product scenarios
* Checkout cancellation
* Logout behavior
* Locked-out user behavior
* API contract and negative API testing
* Cross-browser testing

These extensions are outside the current nine-test baseline.
