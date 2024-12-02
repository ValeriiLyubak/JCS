package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    private By courseBuotton = By.xpath("//a[@href='/course']");

    private By userBuotton = By.xpath("//a[@href='/users']");


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickcourseBuotton() {
        wait.until(ExpectedConditions.elementToBeClickable(courseBuotton)).click();
    }
    public void clickuserBuotton() {
        wait.until(ExpectedConditions.elementToBeClickable(userBuotton)).click();
    }

}
