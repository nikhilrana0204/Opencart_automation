import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC04_SecurityTest {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        
        // Maximize the browser window
        driver.manage().window().maximize();
        
        // Navigate to the login page
        driver.get("https://demo.opencart.com/index.php?route=account/login");
    }

    @Test
    public void testSQLInjection() {
        try {
            // Find the email input field and insert SQL Injection string
        	WebElement emailField = driver.findElement(By.xpath("//input[@name='email' and @placeholder='E-Mail Address']"));
        	emailField.sendKeys("user' or '1'='1");
        	Thread.sleep(6000);

            // Find the password input field and insert any dummy password
            WebElement passwordField = driver.findElement(By.xpath("//input[@type='password' and @placeholder='Password']"));
            passwordField.sendKeys("password123");

            // Find and click the login button
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
            loginButton.click();

            // Wait for the error message (you can use an explicit wait here if needed)
            Thread.sleep(2000); // temporary wait for demo purposes

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait time

            // Wait for the alert message to appear
            WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
            	    By.xpath("//div[@id='alert' and contains(@class, 'toast-container')]")));

            
            // Fetch the alert message
            String alertText = alertMessage.getText();
            System.out.println("Alert message text: " + alertText);

            // Optionally, get its XPath if you need to use it dynamically
            //String xpathOfAlert = "//*[contains(text(), '" + alertText + "')]";
        
            
            // Assert that the error message is displayed, ensuring SQL Injection failed
            Assert.assertTrue(alertMessage.isDisplayed(), "Test Passed: SQL Injection was blocked.");
            
            // Print the error message for verification
           System.out.println("Error message displayed: " + alertMessage.getText());
        } catch (Exception e) {
            // If an exception occurs during the test
            Assert.fail("Test Failed: SQL Injection test encouAntered an issue.");
        
    }
    }
    

    @AfterTest
    public void teardown() {
        // Close the browser after test
        if (driver != null) {
           // driver.quit();
        }
    }
}
