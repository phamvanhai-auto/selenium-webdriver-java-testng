package webdriver;

import java.util.Date;
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



public class Topic_16_Wait_IV_Mixing {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//explicitWait = new WebDriverWait(driver, 10);
		//driver.manage().window().maximize();
		
	}
	
//	@Test
	public void TC_01_Element_Found_Implicit_Explicit() {
		driver.get("https://vi-vn.facebook.com/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		
		showDateTimeNow("Start explicit: ");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		showDateTimeNow("End explicit: ");
		
		showDateTimeNow("Start implicit: ");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("Auto FC");
		showDateTimeNow("End implicit: ");
						
	}
	
//	@Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.get("https://vi-vn.facebook.com/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		showDateTimeNow("Start implicit: ");
		try {
			driver.findElement(By.xpath("//input[@id='lam gi co']")).sendKeys("Auto FC");
		} catch (Exception e) {
			e.printStackTrace();
		}
		showDateTimeNow("End implicit: ");
		
	}
	
//	@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		driver.get("https://vi-vn.facebook.com/");
		
		explicitWait = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		showDateTimeNow("Start explicit: ");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='lm gi co']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		showDateTimeNow("End explicit: ");
		
	}
	
	@Test
	public void TC_04_Element_Not_Found_Explicit_Para_By() {
		driver.get("https://vi-vn.facebook.com/");
		explicitWait = new WebDriverWait(driver, 5);
		
		showDateTimeNow("Start explicit: ");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='lm gi co']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		showDateTimeNow("End explicit: ");
		
	}
	
	@Test
	public void TC_05_Element_Not_Found_Explicit_Para_WebElement() {
		driver.get("https://vi-vn.facebook.com/");
		explicitWait = new WebDriverWait(driver, 5);
		
		showDateTimeNow("Start explicit: ");
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='lm gi co']"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		showDateTimeNow("End explicit: ");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void showDateTimeNow(String status) {
		Date date = new Date();
		System.out.println("-------------" + status + date.toString() + "-------------");
		
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