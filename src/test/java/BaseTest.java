import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

public class BaseTest {
    protected WebDriver driver;
    protected BasePage basePage;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        driver.manage().window().maximize();
        logger.info("=== Настройка теста выполнена ===");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        logger.info("=== Завершено ===");
    }
}
