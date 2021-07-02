package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_02_Xpath_CSS_Part_II_Technical {
	WebDriver driver;
///
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("http://live.demoguru99.com/");
	}
	
	@Test
	public void TC_01_Login_Empty_Email_Pwd() {
		driver.get("http://live.demoguru99.com/index.php");
		
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.xpath("//button[@name='send']")).click();
		SleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");
		
	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.get("http://live.demoguru99.com/index.php");
		
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("12334@4343.1232");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@name='send']")).click();
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
					
	}

	@Test
	public void TC_03_Invalid_Pwd() {
		driver.get("http://live.demoguru99.com/index.php");
		
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hai@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@name='send']")).click();
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
			
	}

	@Test
	public void TC_04_Incorrect_Email_Pwd() {
		driver.get("http://live.demoguru99.com/index.php");
		
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hai@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@name='send']")).click();
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
					
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void SleepInSecond(long timeoutSecond) {
		try {
			Thread.sleep(timeoutSecond*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}