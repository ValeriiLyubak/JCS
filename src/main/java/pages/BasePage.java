package pages;

import configuration.ConfigManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected String baseUrl;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.baseUrl = ConfigManager.getBaseUrl();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
    }

    protected void navigateToBaseUrl() {
        driver.get(baseUrl);
    }

    protected void waitForPageToLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }

    protected void waitForElementToBeVisible(WebElement element) {
        waitForPageToLoad();
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        waitForPageToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}


