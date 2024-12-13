package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CoursePage extends BasePage {

    public CoursePage(WebDriver driver) {
        super(driver);
        waitForPageToLoad();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement addButton;

    @FindBy(xpath = "//input[@placeholder='Название']")
    private WebElement inputCourse;

    @FindBy(xpath = "//button[@class='btn-primary btn ']")
    private WebElement createButton;

    @FindBy(xpath = "//div[@class='table-responsive']")
    private WebElement courseTable;


    public void clickAddCourseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void inputInputCourse(String interviewName) {
        wait.until(ExpectedConditions.elementToBeClickable(inputCourse)).sendKeys(interviewName);
    }

    public void clickCourseCreate() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createButton);
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }

    public boolean isTextPresentInTable(String expectedText) {
        wait.until(ExpectedConditions.visibilityOf(courseTable));
        List<WebElement> rows = courseTable.findElements(By.xpath(".//tr"));

        for (WebElement row : rows) {
            if (row.getText().contains(expectedText)) {
                return true;
            }
        }
        return false;
    }
}



