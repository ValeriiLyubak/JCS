import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

import static org.testng.Assert.assertEquals;

public class AuthorizationTest extends BaseTest {
    private LoginPage loginPage;
    private MainPage mainPage;

    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @Test
    public void testLogin() {
        logger.info("=== Начинаем тест: Авторизация ===");

        logger.info("Вводим логин");
        loginPage.inputLoginField();

        logger.info("Вводим пароль");
        loginPage.inputPasswordField();

        logger.info("Кликаем по кнопке авторизации");
        loginPage.clickLoginButton();

        logger.info("Проверяем, что пользователь успешно авторизовался");
        String actualUserName = mainPage.getNameUserProfile();
        String expectedUserName = "Валерий Л";
        assertEquals(actualUserName, expectedUserName, "Имя пользователя не соответствует ожидаемому!");

        logger.info("=== Тест: Авторизация завершен успешно ===");
    }
}

