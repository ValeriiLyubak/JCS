package pages;

import configuration.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        navigateToBaseUrl();
        waitForPageToLoad();
        PageFactory.initElements(driver, this);
    }

    public void inputLoginField() {
        waitForElementToBeClickable(loginInput);
        loginInput.sendKeys(ConfigManager.getUsername());
    }

    public void inputPasswordField() {
        waitForElementToBeVisible(passwordInput);
        passwordInput.sendKeys(ConfigManager.getPassword());
    }

    public void clickLoginButton() {
        waitForElementToBeClickable(submitButton);
        submitButton.click();
    }
}