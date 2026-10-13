
package com.naufal.qa.ui;

import com.naufal.qa.pages.InventoryPage;
import com.naufal.qa.pages.LoginPage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryTest extends BaseUiTest {

    private InventoryPage inventoryPage;

    @BeforeEach
    void setUp() {
        startBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        inventoryPage = new InventoryPage(driver);
    }

    @Test
    void inventoryDisplaysProducts() {
        assertTrue(
                inventoryPage.isInventoryDisplayed(),
                "Inventory should display products"
        );

        assertFalse(
                inventoryPage.getProductNames().isEmpty(),
                "Product list should not be empty"
        );
    }

    @Test
    void sortingProductsByPriceLowToHighWorks() {
        inventoryPage.sortByPriceLowToHigh();

        List<Double> actualPrices =
                inventoryPage.getProductPrices();

        List<Double> expectedPrices =
                new ArrayList<>(actualPrices);

        Collections.sort(expectedPrices);

        assertFalse(
                actualPrices.isEmpty(),
                "Product prices should be available"
        );

        assertTrue(
                actualPrices.equals(expectedPrices),
                "Products should be sorted by price from low to high"
        );
    }
}