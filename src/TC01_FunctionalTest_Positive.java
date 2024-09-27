import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class TC01_FunctionalTest_Positive {

    private static WebDriver driver;

    @BeforeTest
    public static void setup() throws Exception {
        // Use WebDriverManager to automatically manage the ChromeDriver
        WebDriverManager.chromedriver().setup();
        
        // Initialize the ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Navigate to the desired URL
        driver.get("https://demo.opencart.com");
    }

    @AfterClass
    public void teardown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLogin() {
        // Your test code here
        System.out.println("Test case is running...");
    }
}
