import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TC07_UsabilityTest {
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
    public void loginButton() throws InterruptedException{
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
    
        // Simulate feedback collection (e.g., through a form)
        String buttonLocation = loginButton.getLocation().toString();
        assertNotNull(buttonLocation); // Assert button is located
        System.out.println("Button location: " + buttonLocation);
    }
        // Additional usability checks could be added here
    

    }
    
    