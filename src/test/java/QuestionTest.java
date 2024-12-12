import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.QuestionPage;

import static org.testng.Assert.assertTrue;

public class QuestionTest extends BaseTest {
    private LoginPage loginPage;
    private MainPage mainPage;
    private QuestionPage questionPage;


    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();
        questionPage = new QuestionPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

    }

    @Test
    public void testInterviewTextInTable() {
        logger.info("=== Начинаем тест: Проверка создания вопросов ===");

        logger.info("Вводим логин");
        loginPage.inputLoginField();

        logger.info("Вводим пароль");
        loginPage.inputPasswordField();

        logger.info("Кликаем по кнопке авторизации");
        loginPage.clickLoginButton();

        logger.info("Кликаем по вкладке Вопросы");
        mainPage.clickQuestionButton();

        logger.info("Кликаем по кнопке добавления вопроса");
        questionPage.clickButtonAdd();

        String questionName = "MyTestQuestion";
        logger.info("Вводим вопрос: " + questionName);
        questionPage.inputInputQuestion(questionName);

        logger.info("Кликаем по кнопке создания интервью");
        questionPage.clickButtonCreate();

        logger.info("Проверяем, что текст появился в таблице");
        boolean isTextPresent = questionPage.isTextPresentInTable(questionName);
        assertTrue(isTextPresent, "Текст '" + questionName + "' не найден в таблице!");

        logger.info("=== Тест: Проверка создания вопроса ===");
    }
}
