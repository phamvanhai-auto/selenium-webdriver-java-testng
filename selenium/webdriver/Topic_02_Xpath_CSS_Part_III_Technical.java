package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_CSS_Part_III_Technical {

	WebDriver driver;
	String firstName, lastName, emailAdrr, pwd, fullname;

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.gecko.driver",
		// ".\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		// driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		// driver.get("http://live.demoguru99.com/");

		firstName = "Black";
		lastName = "Obama";
		fullname = firstName + " " + lastName;
		emailAdrr = "Obama" + genEmail();
		pwd = "123456";

	}

	// Create a new account
	@Test
	public void TC_05_Create_New_Acc() {
		driver.get("http://live.demoguru99.com/index.php");

		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		driver.findElement(By.id("firstname")).sendKeys(firstName);
		//driver.findElement(By.id("middlename")).sendKeys("Van");
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath(".//*[@id='email_address']")).sendKeys(emailAdrr);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.id("confirmation")).sendKeys(pwd);
		SleepInSecond(3);
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='my-account']//span")).getText(),
				"Thank you for registering with Main Website Store.");
		
		//dung isDislay() 
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'" + fullname +"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'" + emailAdrr +"')]")).isDisplayed());
	
		//dung getText va verify contains
		String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInfo);
		
		Assert.assertTrue(contactInfo.contains(fullname));
		Assert.assertTrue(contactInfo.contains(emailAdrr));
		
		driver.findElement(By.cssSelector(".skip-account")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
	}
	@Test
	public void TC_06_valid_Login() {

		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();

		driver.findElement(By.cssSelector("#email")).sendKeys(emailAdrr);
		driver.findElement(By.cssSelector("#pass")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.xpath(" //div[@class='welcome-msg']//strong")).getText(),
				"Hello, " + fullname + "!");

		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String genEmail() {
		
		Random rand = new Random();
		return rand.nextInt(9999) + "@gmail.com";
		
	}
//	public int genPwd() {
//		
//		Random rand = new Random();
//		return rand.nextInt(9999999);
//		
//	}
	public void SleepInSecond(long timeoutSecond) {
		try {
			Thread.sleep(timeoutSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}