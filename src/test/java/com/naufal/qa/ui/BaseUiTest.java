
package com.naufal.qa.ui;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@ExtendWith(ScreenshotOnFailureExtension.class)
public abstract class BaseUiTest {

    protected WebDriver driver;

    protected void startBrowser() {
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> preferences = new HashMap<>();
        preferences.put("credentials_enable_service", false);
        preferences.put("profile.password_manager_enabled", false);
        preferences.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", preferences);

        if ("true".equalsIgnoreCase(System.getenv("CI"))) {
            options.addArguments(
                    "--headless=new",
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--window-size=1920,1080"
            );
        } else {
            options.addArguments("--start-maximized");
        }

        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}