
package com.naufal.qa.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotOnFailureExtension
        implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(
            ExtensionContext context,
            Throwable throwable) throws Throwable {

        try {
            Object testInstance = context.getRequiredTestInstance();

            if (testInstance instanceof BaseUiTest) {
                BaseUiTest baseTest = (BaseUiTest) testInstance;
                WebDriver driver = baseTest.driver;

                if (driver != null) {
                    saveScreenshot(driver, context);
                }
            }
        } catch (Exception screenshotException) {
            // Screenshot failure must not hide the original test failure.
            throwable.addSuppressed(screenshotException);
        }

        // Preserve the original test failure so JUnit reports it correctly.
        throw throwable;
    }

    private void saveScreenshot(
            WebDriver driver,
            ExtensionContext context) throws IOException {

        Path screenshotDirectory = Path.of(
                "target", "screenshots"
        );

        Files.createDirectories(screenshotDirectory);

        String testName = context.getRequiredTestMethod().getName()
                .replaceAll("[^a-zA-Z0-9._-]", "_");

        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS")
        );

        Path screenshotPath = screenshotDirectory.resolve(
                testName + "-" + timestamp + ".png"
        );

        byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);

        Files.write(screenshotPath, screenshot);

        System.out.println(
                "Failure screenshot saved: "
                        + screenshotPath.toAbsolutePath()
        );
    }
}