package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class InterviewPage extends BasePage {

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement buttonAdd;

    @FindBy(xpath = "//input[@class='form-control ']")
    private WebElement inputNameInterview;

    @FindBy(xpath = "//button[@class='btn-primary btn ']")
    private WebElement buttonCreate;

    @FindBy(xpath = "//div[@class='table-responsive']")
    private WebElement interviewTable;

    public InterviewPage(WebDriver driver) {
        super(driver);
        waitForPageToLoad();
        PageFactory.initElements(driver, this);
    }

    public void clickButtonAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonAdd)).click();
    }

    public void inputNameInterview(String interviewName) {
        wait.until(ExpectedConditions.elementToBeClickable(inputNameInterview)).sendKeys(interviewName);
    }

    public void clickButtonCreate() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonCreate)).click();
    }

    public boolean isTextPresentInTable(String expectedText) {
        wait.until(ExpectedConditions.visibilityOf(interviewTable));
        List<WebElement> rows = interviewTable.findElements(By.xpath(".//tr"));

        for (WebElement row : rows) {
            if (row.getText().contains(expectedText)) {
                return true;
            }
        }
        return false;
    }
}


