package pages;

import configuration.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CoursePage extends BasePage {
    private By addButton = By.xpath("//button[@class='btn btn-default']");
    private By courseNameField = By.xpath("//input[@placeholder='Название'][1]");
    private By createCourseButton = By.xpath("//button[text()='Create']");

    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public By getCourseInTableLocator(String courseName) {
        return By.xpath("//tbody/tr/td/span[text()='" + courseName + "']");
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
        wait.until(ExpectedConditions.presenceOfElementLocated(getCourseInTableLocator(courseName)));
    }

    public boolean isCoursePresent(String courseName) {
        return !driver.findElements(getCourseInTableLocator(courseName)).isEmpty();
    }
}


