import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TC05_PositivePerformanceTest {
     WebDriver driver;

    @BeforeTest
    public void setup() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        
        // Maximize the browser window
        driver.manage().window().maximize();
        
        // Navigate to the login page
        driver.get("https://demo.opencart.com");
    }

    @AfterClass
    public void teardown() {
        // Close the browser
        if (driver != null) {
            //driver.quit();
        }
    }

   
    @Test(priority=1)
    public void testValidLogin() throws InterruptedException {
       
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	WebElement loginLink = driver.findElement(By.xpath("//a[@href='https://demo.opencart.com/en-gb?route=account/login']"));
    	js.executeScript("arguments[0].click();", loginLink);

        
        Thread.sleep(5000);
    }
        @Test(priority=2)
        public void loginInput() throws InterruptedException{
        	Thread.sleep(7000);
        	driver.findElement(By.xpath("//input[@name='email' and @placeholder='E-Mail Address']")).sendKeys("user11@example.com");
        	driver.findElement(By.xpath("//input[@name='password' and @placeholder='Password']")).sendKeys("password123");
        }
        @Test(priority=3)
        public void Login() throws InterruptedException{
        	Thread.sleep(2000);
        	driver.findElement(By.xpath("//button[@type='submit']")).click();
    	
        System.out.println("Test case is running...");
    }
        @Test(priority = 4)
        public void performance() throws InterruptedException {
            long startTime = System.currentTimeMillis();
            
            // Click the login button
            //Login.click();
            
            // Wait for the page to load (if necessary)
            Thread.sleep(1000); // Replace with appropriate wait if needed, like WebDriverWait

            long endTime = System.currentTimeMillis();
            
            // Assert that the current URL contains "dashboard"
            assertTrue(driver.getCurrentUrl().contains("customer"), "Expected URL to contain 'dashboard'"); // Replace with actual success URL

            // Calculate the duration and assert that the login completed in under 3 seconds
            long duration = endTime - startTime;
            assertTrue(duration <= 3000, "Login took too long: " + duration + "ms"); // Check duration
        }


}
