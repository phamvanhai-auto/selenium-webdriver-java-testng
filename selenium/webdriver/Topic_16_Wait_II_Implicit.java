package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_Wait_II_Implicit {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_03_Implicit_2s() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//set implicit 2s -> sẽ fail 
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
				
	}
	
	@Test
	public void TC_03_Implicit_6s() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//set implicit 6s -> sẽ pass - vì đủ thời gian để loading 5s -> mà implicit 6s thì thoải mái :))
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		
	}
	
	@Test
	public void TC_02() {
		
	}	
	
	@Test
	public void TC_03() {
		
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