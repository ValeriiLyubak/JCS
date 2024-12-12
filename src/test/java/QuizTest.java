import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.QuestionPage;
import pages.QuizPage;

import static org.testng.Assert.assertTrue;

public class QuizTest extends BaseTest {
    private LoginPage loginPage;
    private MainPage mainPage;
    private QuizPage quizPage;


    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();
        quizPage = new QuizPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);

    }

    @Test
    public void testInterviewTextInTable() {
        logger.info("=== Начинаем тест: Проверка создания Квизов ===");

        logger.info("Вводим логин");
        loginPage.inputLoginField();

        logger.info("Вводим пароль");
        loginPage.inputPasswordField();

        logger.info("Кликаем по кнопке авторизации");
        loginPage.clickLoginButton();

        logger.info("Кликаем по вкладке Квизы");
        mainPage.clickQuizButton();

        logger.info("Кликаем по кнопке добавления квиза");
        quizPage.clickButtonAdd();

        String questionName = "MyTestQuiz";
        logger.info("Вводим вопрос: " + questionName);
        quizPage.inputInputQuiz(questionName);

        logger.info("Кликаем по кнопке создания квиза");
        quizPage.clickButtonCreate();

        logger.info("Проверяем, что текст появился в таблице");
        boolean isTextPresent = quizPage.isTextPresentInTable(questionName);
        assertTrue(isTextPresent, "Текст '" + questionName + "' не найден в таблице!");

        logger.info("=== Тест: Проверка создания квиза ===");
    }
}

