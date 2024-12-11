

import pages.LoginPage;
import pages.MainPage;
import pages.CoursePage;
import configuration.ConfigManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class CourseTest extends BaseTest {

    private LoginPage loginPage;
    private MainPage mainPage;
    private CoursePage coursePage;

    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        coursePage = new CoursePage(driver);
    }

    @Test
    public void testCreateAndVerifyCourse() {
        logger.info("=== Начинаем тест: Создание курса ===");

        loginPage.inputLoginField();
        loginPage.inputPasswordField();
        loginPage.clickLoginButton();

        mainPage.clickCourseButton();

        coursePage.clickAddButton();
        coursePage.inputCourseNameField();
        coursePage.clickCreateCourseButton();

        coursePage.waitForCourseToAppearWithDelay(ConfigManager.getCourseName());

        String expectedCourseName = ConfigManager.getCourseName();
        Assert.assertTrue(coursePage.isCoursePresent(expectedCourseName),
                "Курс с названием '" + expectedCourseName + "' не найден в таблице!");

        logger.info("Курс с названием '" + expectedCourseName + "' был успешно добавлен и найден!");

        logger.info("=== Тест пройден: Создание курса ===");
    }
}