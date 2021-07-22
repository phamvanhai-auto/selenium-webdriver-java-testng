package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_Wait_I_Element_Status {
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
	
//	@Test
	public void TC_01_Visible_Display() {
		
		driver.get("https://www.facebook.com/");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
		
	}
	
//	@Test
	public void TC_02_Invisible() {
		driver.get("https://www.facebook.com/");
		
		//wait cho button tao tk xuat hien
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")));
		//Action: click ok
		driver.findElement(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")).click();
	
		//k co UI, co DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
		
		//k cos UI, k co DOM
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='reg']")));

	}	
	
//	@Test
	public void TC_03_Presence() {
		driver.get("https://www.facebook.com/");
		
		//co UI & DOM - visible
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")));
		//Action: click ok
		driver.findElement(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")).click();
	
		//k co UI & co DOM - 
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("reg_email_confirmation__")));
		
		//k co UI & k co DOM > Failed
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		
		//explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//form[@id='reg']")));
	}
	
	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
		
		//wait cho button tao tk xuat hien
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")));
		//Action: click ok
		driver.findElement(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")).click();
		
		//hien thi va co trong DOM - visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='reg']")));
		WebElement registerForm = driver.findElement(By.xpath("//form[@id='reg']"));
		
		//k hthi va co trong DOM - invisible
		WebElement confirmEmail = driver.findElement(By.name("reg_email_confirmation__"));

		//tat form di - k co UI & k co DOM
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		
		//thi tai thoi diem nay element se k co trong DOM nua
		explicitWait.until(ExpectedConditions.stalenessOf(registerForm));
		explicitWait.until(ExpectedConditions.stalenessOf(confirmEmail));
		
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