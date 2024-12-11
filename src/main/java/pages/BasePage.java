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

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Метод для ожидания завершения загрузки страницы
    protected void waitForPageToLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }

    // Метод для ожидания видимости элемента
    protected void waitForElementToBeVisible(WebElement element) {
        waitForPageToLoad();  // Ожидаем загрузки страницы перед ожиданием элемента
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Метод для ожидания, что элемент будет кликабельным
    protected void waitForElementToBeClickable(WebElement element) {
        waitForPageToLoad();
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}


