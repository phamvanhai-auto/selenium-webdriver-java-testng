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


public class Topic_06_Web_Browser_Exercise {
	WebDriver driver;
///
	@BeforeClass
	public void beforeClass() {
		
		//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		//driver.get("");
		
		driver.get("http://live.demoguru99.com");
	}

	@Test
	public void TC_01_Verify_URL() {
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String LoginPage = driver.getCurrentUrl();
		Assert.assertEquals(LoginPage,"http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String RegisterPage = driver.getCurrentUrl();
		Assert.assertEquals(RegisterPage,"http://live.demoguru99.com/index.php/customer/account/create/");		

	}
	
	@Test
	public void TC_02_Verify_Title() {
				
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(),"Customer Login");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

	}
	
	@Test
	public void TC_03_Navigate() {
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		SleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		
		driver.navigate().back();
		SleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.navigate().forward();
		SleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		
	}
	@Test
	public void TC_04_PageSource() {
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String pageSource = driver.getPageSource();
		Assert.assertTrue(pageSource.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
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