
package com.naufal.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By checkoutButton =
            By.id("checkout");

    private final By firstNameInput =
            By.id("first-name");

    private final By lastNameInput =
            By.id("last-name");

    private final By postalCodeInput =
            By.id("postal-code");

    private final By continueButton =
            By.id("continue");

    private final By finishButton =
            By.id("finish");

    private final By errorMessage =
            By.cssSelector("[data-test='error']");

    private final By completeHeader =
            By.cssSelector("[data-test='complete-header']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
        );
    }

    public void clickCheckout() {
        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        );

        button.click();

        // First, wait for the normal Selenium click to navigate.
        try {
            wait.until(
                    ExpectedConditions.urlContains(
                            "checkout-step-one.html"
                    )
            );
        } catch (TimeoutException firstAttempt) {
            // Retry with JavaScript only if the normal click did not navigate.
            WebElement checkout = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            checkoutButton
                    )
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", checkout
            );

            try {
                wait.until(
                        ExpectedConditions.urlContains(
                                "checkout-step-one.html"
                        )
                );
            } catch (TimeoutException secondAttempt) {
                throw new AssertionError(
                        "Checkout page did not open."
                                + " Current URL: " + driver.getCurrentUrl()
                                + ". Checkout button present: "
                                + !driver.findElements(checkoutButton).isEmpty(),
                        secondAttempt
                );
            }
        }

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameInput)
        );
    }

    public void enterCustomerInformation(
            String firstName,
            String lastName,
            String postalCode
    ) {
        WebElement firstNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameInput)
        );

        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = driver.findElement(lastNameInput);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        WebElement postalCodeField = driver.findElement(postalCodeInput);
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    public void continueCheckout() {
        wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        ).click();
    }

    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText();
    }

    public String getPageErrorIfPresent() {
        return driver.findElements(errorMessage)
                .stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .findFirst()
                .orElse("No error message found");
    }

    public boolean isOverviewDisplayed() {
        return wait.until(
                ExpectedConditions.urlContains("checkout-step-two.html")
        ) && wait.until(
                ExpectedConditions.visibilityOfElementLocated(finishButton)
        ).isDisplayed();
    }

    public void finishOrder() {
        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        ).click();
    }

    public String getCompleteHeader() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(completeHeader)
        ).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}