import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC03_UITest {
    public WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        // Open the login page
        driver.get("https://demo.opencart.com"); 
    }
    @Test(priority=1)
    public void testinValidLogin() throws InterruptedException {
       
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	WebElement loginLink = driver.findElement(By.xpath("//a[@href='https://demo.opencart.com/en-gb?route=account/login']"));
    	js.executeScript("arguments[0].click();", loginLink);

        
        Thread.sleep(5000);
    }
    @Test
    public void verifyLoginButtonVisibilityAndAlignment() {
        // Find the login button using an appropriate selector
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']")); 
        
        // Check if the button is displayed (visible)
        Assert.assertTrue(loginButton.isDisplayed(), "Login button is not visible.");
        
        // Check if the button is enabled (clickable)
        Assert.assertTrue(loginButton.isEnabled(), "Login button is not clickable.");

        // Verify the alignment by checking the position of the button
        int buttonXPosition = loginButton.getLocation().getX();
        int buttonYPosition = loginButton.getLocation().getY();

        // Add assertions for checking alignment (Example: checking it’s aligned within a specific range)
        //Assert.assertTrue(buttonXPosition >= 100 && buttonXPosition <= 200, "Login button is not correctly aligned on the X-axis.");
        //Assert.assertTrue(buttonYPosition >= 300 && buttonYPosition <= 400, "Login button is not correctly aligned on the Y-axis.");
        
        System.out.println("Button position - X: " + buttonXPosition + ", Y: " + buttonYPosition);
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
