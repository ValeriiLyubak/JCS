import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.InterviewPage;
import pages.LoginPage;
import pages.MainPage;

import static org.testng.Assert.assertTrue;

public class InterviewTest extends BaseTest {
    private LoginPage loginPage;
    private MainPage mainPage;
    private InterviewPage interviewPage;


    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();
        interviewPage = new InterviewPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

    }

    @Test
    public void testInterviewTextInTable() {
        logger.info("=== Начинаем тест: Проверка создания интервью ===");

        logger.info("Вводим логин");
        loginPage.inputLoginField();

        logger.info("Вводим пароль");
        loginPage.inputPasswordField();

        logger.info("Кликаем по кнопке авторизации");
        loginPage.clickLoginButton();

        logger.info("Кликаем по вкладке Интервью");
        mainPage.clickInterviewsButton();

        logger.info("Кликаем по кнопке добавления интервью");
        interviewPage.clickButtonAdd();

        String interviewName = "MyTestInterview";
        logger.info("Вводим имя интервью: " + interviewName);
        interviewPage.inputNameInterview(interviewName);

        logger.info("Кликаем по кнопке создания интервью");
        interviewPage.clickButtonCreate();

        logger.info("Проверяем, что текст появился в таблице");
        boolean isTextPresent = interviewPage.isTextPresentInTable(interviewName);
        assertTrue(isTextPresent, "Текст '" + interviewName + "' не найден в таблице!");

        logger.info("=== Тест: Проверка создания интервью ===");
    }
}
