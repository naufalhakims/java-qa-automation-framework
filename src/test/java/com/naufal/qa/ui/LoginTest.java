
package com.naufal.qa.ui;

import com.naufal.qa.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseUiTest {

    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        startBrowser();
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @Test
    void validUserCanLoginSuccessfully() {
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Successful login should open the inventory page"
        );
    }

    @Test
    void invalidPasswordShowsErrorMessage() {
        loginPage.login("standard_user", "wrong_password");

        assertEquals(
                "Epic sadface: Username and password do not match any user in this service",
                loginPage.getErrorMessage(),
                "An error message should appear when the password is incorrect"
        );
    }

    @Test
    void emptyCredentialsShowErrorMessage() {
        loginPage.login("", "");

        assertEquals(
                "Epic sadface: Username is required",
                loginPage.getErrorMessage(),
                "An error message should appear when the username is empty"
        );
    }
}