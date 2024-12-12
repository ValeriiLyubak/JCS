package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class QuestionPage extends BasePage{

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement buttonAdd;

    @FindBy(xpath = "//textarea[@class='w-md-editor-text-input ']")
    private WebElement inputQuestion;

    @FindBy(xpath = "//button[@class='btn-primary btn ']")
    private WebElement buttonCreate;

    @FindBy(xpath = "//div[@class='table-responsive']")
    private WebElement questionTable;

    public QuestionPage(WebDriver driver) {
        super(driver);
        waitForPageToLoad();
        PageFactory.initElements(driver, this);
    }

    public void clickButtonAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonAdd)).click();
    }

    public void inputInputQuestion(String interviewName) {
        wait.until(ExpectedConditions.elementToBeClickable(inputQuestion)).sendKeys(interviewName);
    }

    public void clickButtonCreate() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonCreate)).click();
    }

    public boolean isTextPresentInTable(String expectedText) {
        wait.until(ExpectedConditions.visibilityOf(questionTable));
        List<WebElement> rows = questionTable.findElements(By.xpath(".//tr"));

        for (WebElement row : rows) {
            if (row.getText().contains(expectedText)) {
                return true;
            }
        }
        return false;
    }
}
