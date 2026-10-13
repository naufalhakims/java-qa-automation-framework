
package com.naufal.qa.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartItems =
            By.cssSelector(".cart_item");

    private final By productNames =
            By.cssSelector(".cart_item .inventory_item_name");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));
    }

    public List<String> getProductNames() {
        wait.until(d -> d.getCurrentUrl().contains("cart.html"));

        wait.until(d -> !d.findElements(
                By.cssSelector(".cart_item")
        ).isEmpty());

        return wait.until(d -> {
            try {
                List<WebElement> elements = d.findElements(
                        By.cssSelector(".cart_item .inventory_item_name")
                );

                List<String> names = elements.stream()
                        .map(WebElement::getText)
                        .toList();

                return names.isEmpty() ? null : names;

            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    public void removeProduct(String productName) {
        wait.until(d -> d.getCurrentUrl().contains("cart.html"));

        String itemXpath =
                "//div[contains(@class,'cart_item')]"
                        + "[.//div[contains(@class,'inventory_item_name')"
                        + " and normalize-space()='" + productName + "']]";

        By removeButton = By.xpath(itemXpath + "//button");
        By productRow = By.xpath(itemXpath);

        wait.until(d -> !d.findElements(removeButton).isEmpty());

        wait.until(d -> {
            try {
                List<WebElement> buttons =
                        d.findElements(removeButton);

                if (buttons.isEmpty()) {
                    return false;
                }

                WebElement button = buttons.get(0);

                if (button.isDisplayed() && button.isEnabled()) {
                    button.click();
                    return true;
                }

                return false;

            } catch (StaleElementReferenceException e) {
                return false;
            }
        });

        wait.until(d -> d.findElements(productRow).isEmpty());
    }

    public boolean isCartEmpty() {
        return driver.findElements(cartItems).isEmpty();
    }
}