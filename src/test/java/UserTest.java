import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.UserPage;
import configuration.ConfigManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserTest extends BaseTest {

    private LoginPage loginPage;
    private MainPage mainPage;
    private UserPage userPage;

    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        userPage = new UserPage(driver);
    }

    @Test
    public void testAddUsersFromCSV() {
        logger.info("=== Начинаем тест: Добавление пользователей из CSV ===");

        // Авторизация
        loginPage.inputLoginField();
        loginPage.inputPasswordField();
        loginPage.clickLoginButton();

        // Переход на страницу пользователей
        mainPage.clickuserBuotton();

        String filePath = ConfigManager.getCsvFilePath();
        List<String> userLogins = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                // Проверяем точное количество полей
                if (userData.length >= 4) {
                    String login = userData[3].trim();
                    userLogins.add(login);
                }
            }
        } catch (IOException e) {
            logger.error("Ошибка при чтении файла CSV: " + e.getMessage());
            Assert.fail("Не удалось прочитать файл CSV: " + filePath);
        }

        // Добавление пользователей
        try {
            userPage.addUsersFromCSV(filePath);
        } catch (IOException e) {
            logger.error("Ошибка при добавлении пользователей: " + e.getMessage());
            Assert.fail("Не удалось добавить пользователей из CSV");
        }


        for (String login : userLogins) {
            boolean isUserPresent = userPage.isUserPresent(login);
            Assert.assertTrue(isUserPresent, "Пользователь с логином '" + login + "' не найден на странице!");
        }

        logger.info("=== Тест завершён: Пользователи из CSV добавлены и проверены ===");
    }
}