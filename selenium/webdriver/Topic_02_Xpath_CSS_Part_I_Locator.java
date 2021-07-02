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


public class Topic_02_Xpath_CSS_Part_I_Locator {
	WebDriver driver;
///
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}
	
	@Test
	public void TC_01_ID() {
		//input value to textbox
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Hai");
	}

	@Test
	public void TC_02_Class() {
		
		driver.navigate().refresh();
		driver.findElement(By.className("search-box-text")).sendKeys("Macbook");
		SleepInSecond(3);
		driver.findElement(By.className("search-box-button")).click();
		SleepInSecond(5);
	}

	@Test
	public void TC_03_Name() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.findElement(By.name("Email")).sendKeys("test@gmail.com");
		SleepInSecond(3);
		driver.findElement(By.name("Newsletter")).click();
	}

	@Test
	public void TC_04_TagName() {
		System.out.println("Sum input= " + driver.findElements(By.tagName("input")).size());
		driver.findElements(By.tagName("a")).size();
	}
	@Test
	public void TC_05_LinkText() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		//khi muốn lấy tuyệt đối đường link
		driver.findElement(By.linkText("Log in")).click();
	}
	@Test
	public void TC_06_Partial_Linktext() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.findElement(By.partialLinkText("Apply for vendor account")).click();
		driver.findElement(By.partialLinkText("vendor account")).click();
	}
	
	@Test
	public void TC_07_Css() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("Hai");
		SleepInSecond(3);
		driver.findElement(By.cssSelector("input[class='search-box-text ui-autocomplete-input']")).sendKeys("Hai");
		SleepInSecond(3);
		driver.findElement(By.cssSelector("a[href*='login']")).click();
	}
	
	@Test
	public void TC_08_Xpath() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Hai");
		SleepInSecond(3);
		driver.findElement(By.xpath("//a[contains(@href,'login')]")).click();
		SleepInSecond(3);
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