package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserPage extends BasePage {

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement addUserButton;

    @FindBy(xpath = "//small[text() = 'Имя']/following-sibling::input")
    private WebElement nameField;

    @FindBy(xpath = "//small[text() = 'Фамилия']/following-sibling::input")
    private WebElement surnameField;

    @FindBy(xpath = "//small[text() = 'Эл. почта']/following-sibling::input")
    private WebElement emailField;

    @FindBy(xpath = "//small[text() = 'Логин']/following-sibling::input")
    private WebElement loginField;

    @FindBy(xpath = "//small[text() = 'Пароль']/following-sibling::input")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@placeholder='Роли']")
    private WebElement roleField;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement careerTrackCheckbox;

    @FindBy(xpath = "//div[@class='react-datepicker__input-container']/input")
    private WebElement dateField;

    @FindBy(xpath = "//button[@class='btn-primary btn ']")
    private WebElement createButton;

    private By searchStatusButtons = By.xpath("//small[text() = 'Статус поиска']/ancestor::div//button");


    public UserPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddUserButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addUserButton)).click();
    }

    public void clickCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }

    public void addUser(String name, String surname, String email, String login, String password, String role,
                        Boolean careerTrack, String date, String searchStatus) {
        clickAddUserButton();

        if (name != null && !name.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);
        }
        if (surname != null && !surname.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOf(surnameField)).sendKeys(surname);
        }
        if (email != null && !email.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        }
        if (login != null && !login.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOf(loginField)).sendKeys(login);
        }
        if (password != null && !password.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        }
        if (role != null && !role.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOf(roleField)).sendKeys(role);
        }

        if (careerTrack != null) {
            if (careerTrack && !careerTrackCheckbox.isSelected()) {
                careerTrackCheckbox.click();
            } else if (!careerTrack && careerTrackCheckbox.isSelected()) {
                careerTrackCheckbox.click();
            }
        }

        if (date != null && !date.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOf(dateField)).clear();
            dateField.sendKeys(date);
        }

        if (searchStatus != null && !searchStatus.isEmpty()) {
            selectSearchStatus(searchStatus);
        }

        clickCreateButton();
    }

    private void selectSearchStatus(String searchStatus) {
        if (searchStatus != null && !searchStatus.trim().isEmpty() && !searchStatus.equals("-")) {
            for (WebElement button : driver.findElements(searchStatusButtons)) {
                String buttonText = button.getText().trim();
                if (buttonText.equalsIgnoreCase(searchStatus)) {
                    wait.until(ExpectedConditions.elementToBeClickable(button)).click();
                    break;
                }
            }
        }
    }

    public void addUsersFromCSV(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length < 10) {
                    System.err.println("Некорректная строка: " + line);
                    continue;
                }

                String name = getValueOrEmpty(userData, 1);
                String surname = getValueOrEmpty(userData, 2);
                String email = getValueOrEmpty(userData, 3);
                String login = getValueOrEmpty(userData, 4);
                String password = getValueOrEmpty(userData, 5);
                String role = getValueOrEmpty(userData, 6);

                Boolean careerTrack = !userData[7].trim().isEmpty() ? Boolean.parseBoolean(userData[7].trim()) : null;
                String date = getValueOrEmpty(userData, 8);
                String searchStatus = getValueOrEmpty(userData, 9);

                addUser(name, surname, email, login, password, role, careerTrack, date, searchStatus);
            }
        }
    }

    private String getValueOrEmpty(String[] data, int index) {
        return (index < data.length && data[index] != null && !data[index].trim().isEmpty()) ?
                data[index].trim() : "";
    }

    public boolean isUserPresent(String login) {
        By userLocator = By.xpath("//td[text()='" + login + "']");
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(userLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

