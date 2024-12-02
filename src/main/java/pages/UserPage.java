package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserPage extends BasePage {

    private By addUserButton = By.xpath("//button[@class='btn btn-default']");
    private By nameField = By.xpath("//small[text() = 'Имя']/following-sibling::input");
    private By surnameField = By.xpath("//small[text() = 'Фамилия']/following-sibling::input");
    private By emailField = By.xpath("//small[text() = 'Эл. почта']/following-sibling::input");
    private By loginField = By.xpath("//small[text() = 'Логин']/following-sibling::input");
    private By passwordField = By.xpath("//small[text() = 'Пароль']/following-sibling::input");
    private By roleField = By.xpath("//input[@placeholder='Роли']");
    private By careerTrackCheckbox = By.xpath("//input[@type='checkbox']");
    private By dateField = By.xpath("//div[@class='react-datepicker__input-container']/input");
    private By searchStatusButtons = By.xpath("//small[text() = 'Статус поиска']/ancestor::div//button");
    private By createButton = By.xpath("//button[@class='btn-primary btn ']");

    public UserPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddUserButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addUserButton)).click();
    }
    public void clickCreateButton() {
        WebElement createButtonElement = wait.until(ExpectedConditions.elementToBeClickable(createButton));
        scrollToElement(createButtonElement);
        createButtonElement.click();
    }

    public void addUser(String name, String surname, String email, String login, String password, String role,
                        Boolean careerTrack, String date, String searchStatus) {
        clickAddUserButton();

        if (name != null && !name.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        }
        if (surname != null && !surname.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(surnameField)).sendKeys(surname);
        }
        if (email != null && !email.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        }
        if (login != null && !login.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginField)).sendKeys(login);
        }
        if (password != null && !password.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        }
        if (role != null && !role.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(roleField)).sendKeys(role);
        }

        if (careerTrack != null) {
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(careerTrackCheckbox));

            if (careerTrack && !checkbox.isSelected()) {
                checkbox.click();
            }
            else if (!careerTrack && checkbox.isSelected()) {
                checkbox.click();
            }
        }

        if (date != null && !date.isEmpty() && !"null".equalsIgnoreCase(date)) {
            WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dateField));
            dateElement.clear();
            dateElement.sendKeys(date);
        }

        if (searchStatus != null && !searchStatus.isEmpty()) {
            selectSearchStatus(searchStatus);
        }

        clickCreateButton();

        clickAddUserButton();
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
                try {
                    String[] userData = line.split(",");

                    if (userData.length < 10) { // Изменил для учета первой колонки (порядковый номер)
                        System.err.println("Некорректное количество данных в строке: " + line);
                        continue;
                    }

                    String name = getValueOrEmpty(userData, 1); // C2
                    String surname = getValueOrEmpty(userData, 2); // C3
                    String email = getValueOrEmpty(userData, 3); // C4
                    String login = getValueOrEmpty(userData, 4); // C5
                    String password = getValueOrEmpty(userData, 5); // C6
                    String role = getValueOrEmpty(userData, 6); // C7

                    Boolean careerTrack = null;
                    if (!userData[7].trim().isEmpty()) { // C8
                        careerTrack = Boolean.parseBoolean(userData[7].trim());
                    }

                    String date = getValueOrEmpty(userData, 8); // C9
                    if ("null".equalsIgnoreCase(date)) {
                        date = null;
                    }

                    String searchStatus = getValueOrEmpty(userData, 9); // C10

                    addUser(name, surname, email, login, password, role, careerTrack, date, searchStatus);
                } catch (Exception e) {
                    System.err.println("Ошибка обработки строки: " + line + ". " + e.getMessage());
                }
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
            WebElement userElement = wait.until(ExpectedConditions.presenceOfElementLocated(userLocator));
            return userElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
