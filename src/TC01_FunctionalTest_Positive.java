import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class TC01_FunctionalTest_Positive {

    private static WebDriver driver;

    @BeforeTest
    public static void setup() throws Exception {
        // Use WebDriverManager to automatically manage the ChromeDriver
        
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        WebDriverManager.chromedriver().setup();
        // Initialize the ChromeDriver
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        // Navigate to the desired URL
        driver.get("https://demo.opencart.com");
        Thread.sleep(1000);
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
        	Thread.sleep(5000);
        	driver.findElement(By.xpath("//input[@name='email' and @placeholder='E-Mail Address']")).sendKeys("user11@example.com");
        	driver.findElement(By.xpath("//input[@name='password' and @placeholder='Password']")).sendKeys("password123");
        }
        @Test(priority=3)
        public void Login() throws InterruptedException{
        	Thread.sleep(2000);
        	driver.findElement(By.xpath("//button[@type='submit']")).click();
    	


        System.out.println("Test case is running...");
    }

}
