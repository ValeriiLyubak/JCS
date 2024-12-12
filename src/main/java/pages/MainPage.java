package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[@href='/interviews']")
    private WebElement interviewsButton;

    @FindBy(xpath = "//a[@href='/theme-question']")
    private WebElement questionButton;

    @FindBy(xpath = "//a[@href='/quizes']")
    private WebElement quizButton;

    @FindBy(xpath = "//a[@href='/course']")
    private WebElement courseButton;

    @FindBy(xpath = "//a[@href='/users']")
    private WebElement userButton;

    @FindBy(xpath = "//div[@class='menuProfile']")
    private WebElement nameProfile;

    public MainPage(WebDriver driver) {
        super(driver);
        waitForPageToLoad();
        PageFactory.initElements(driver, this);
    }

    public void clickInterviewsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(interviewsButton)).click();
    }

    public void clickQuestionButton() {
        wait.until(ExpectedConditions.elementToBeClickable(questionButton)).click();
    }

    public void clickCourseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(courseButton)).click();
    }

    public void clickQuizButton() {
        wait.until(ExpectedConditions.elementToBeClickable(quizButton)).click();
    }

    public void clickUserButton() {
        wait.until(ExpectedConditions.elementToBeClickable(userButton)).click();
    }

    public String getNameUserProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(nameProfile));
        return nameProfile.getText();
    }
}

