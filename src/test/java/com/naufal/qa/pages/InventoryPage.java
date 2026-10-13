
package com.naufal.qa.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By inventoryItems =
            By.cssSelector(".inventory_item");

    private final By productNames =
            By.cssSelector(".inventory_item_name");

    private final By productPrices =
            By.cssSelector(".inventory_item_price");

    private final By sortDropdown =
            By.cssSelector("[data-test='product-sort-container']");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));
    }

    public boolean isInventoryDisplayed() {
        return wait.until(
                d -> d.findElements(inventoryItems).size() > 0);
    }

    public List<String> getProductNames() {
        return driver.findElements(productNames)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();

        for (WebElement element : driver.findElements(productPrices)) {
            String priceText = element.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }

        return prices;
    }

    public void sortByPriceLowToHigh() {
        Select select = new Select(
                wait.until(d -> d.findElement(sortDropdown)));

        select.selectByValue("lohi");
    }

    public void addProductToCart(String productName) {
        List<WebElement> items = driver.findElements(
                By.cssSelector(".inventory_item"));

        for (WebElement item : items) {
            String name = item.findElement(
                    By.cssSelector(".inventory_item_name")).getText();

            if (name.equals(productName)) {
                item.findElement(By.tagName("button")).click();
                return;
            }
        }

        throw new IllegalArgumentException(
                "Product not found: " + productName);
    }

    public String getCartBadgeCount() {
        return driver.findElement(
                        By.cssSelector("[data-test='shopping-cart-badge']"))
                .getText();
    }

    public void openCart() {
        driver.findElement(
                By.cssSelector(".shopping_cart_link")).click();
    }
}

