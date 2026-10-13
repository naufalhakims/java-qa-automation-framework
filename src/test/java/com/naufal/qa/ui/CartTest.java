
package com.naufal.qa.ui;

import com.naufal.qa.pages.CartPage;
import com.naufal.qa.pages.InventoryPage;
import com.naufal.qa.pages.LoginPage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseUiTest {

    private InventoryPage inventoryPage;
    private CartPage cartPage;

    private static final String PRODUCT_NAME =
            "Sauce Labs Backpack";

    @BeforeEach
    void setUp() {
        startBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    void addedProductAppearsInCart() {
        inventoryPage.addProductToCart(PRODUCT_NAME);

        assertEquals(
                "1",
                inventoryPage.getCartBadgeCount(),
                "Cart badge should show one item"
        );

        inventoryPage.openCart();

        assertTrue(
                cartPage.getProductNames().contains(PRODUCT_NAME),
                "The selected product should appear in the cart"
        );
    }

    @Test
    void removedProductNoLongerAppearsInCart() {
        inventoryPage.addProductToCart(PRODUCT_NAME);
        inventoryPage.openCart();

        assertTrue(
                cartPage.getProductNames().contains(PRODUCT_NAME),
                "Product should be present before removal"
        );

        cartPage.removeProduct(PRODUCT_NAME);

        assertTrue(
                cartPage.isCartEmpty(),
                "Cart should be empty after removing its only product"
        );
    }
}