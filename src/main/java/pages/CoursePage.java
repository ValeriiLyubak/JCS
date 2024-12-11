package pages;

import configuration.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CoursePage extends BasePage {

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement addButton;

    @FindBy(xpath = "//input[@placeholder='Название'][1]")
    private WebElement courseNameField;

    @FindBy(xpath = "//button[text()='Create']")
    private WebElement createCourseButton;

    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public String getCourseInTableXPath(String courseName) {
        return "//tbody/tr/td/span[text()='" + courseName + "']";
    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void inputCourseNameField() {
        wait.until(ExpectedConditions.elementToBeClickable(courseNameField)).sendKeys(ConfigManager.getCourseName());
    }

    public void clickCreateCourseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createCourseButton)).click();
    }

    public void waitForCourseToAppearWithDelay(String courseName) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getCourseInTableXPath(courseName))));
    }

    public boolean isCoursePresent(String courseName) {
        return !driver.findElements(By.xpath(getCourseInTableXPath(courseName))).isEmpty();
    }
}



