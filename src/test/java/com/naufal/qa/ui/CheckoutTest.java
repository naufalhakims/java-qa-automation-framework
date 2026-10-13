
package com.naufal.qa.ui;

import com.naufal.qa.pages.CheckoutPage;
import com.naufal.qa.pages.InventoryPage;
import com.naufal.qa.pages.LoginPage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest extends BaseUiTest {

    private CheckoutPage checkoutPage;

    private static final String PRODUCT_NAME =
            "Sauce Labs Backpack";

    @BeforeEach
    void setUp() {
        startBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart(PRODUCT_NAME);
        inventoryPage.openCart();

        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    void userCanCompleteCheckoutSuccessfully() {
        checkoutPage.clickCheckout();

        assertTrue(
                checkoutPage.getCurrentUrl().contains(
                        "checkout-step-one.html"
                ),
                "Checkout information page should open"
        );

        checkoutPage.enterCustomerInformation(
                "Naufal",
                "Hakim",
                "60123"
        );

        checkoutPage.continueCheckout();

        assertTrue(
                checkoutPage.isOverviewDisplayed(),
                "Checkout overview should be displayed. Current URL: "
                        + checkoutPage.getCurrentUrl()
        );

        checkoutPage.finishOrder();

        assertEquals(
                "Thank you for your order!",
                checkoutPage.getCompleteHeader(),
                "Order confirmation should appear after checkout"
        );
    }

    @Test
    void checkoutRequiresFirstName() {
        checkoutPage.clickCheckout();

        checkoutPage.enterCustomerInformation(
                "",
                "Hakim",
                "60123"
        );

        checkoutPage.continueCheckout();

        assertEquals(
                "Error: First Name is required",
                checkoutPage.getErrorMessage(),
                "Checkout should reject a missing first name"
        );
    }
}