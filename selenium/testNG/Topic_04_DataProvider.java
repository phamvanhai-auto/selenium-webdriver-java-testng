package testNG;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_04_DataProvider {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
	}
	
	@Test(dataProvider = "register")
	public void TC_01_Register_System(String emailAddr, String passWord) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@class='button']//span[text()='Create an Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Auto");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Class 22");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddr);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(passWord);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(passWord);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Log Out']")).click();

	}
	@Test(description="View User")
	public void User_02_View_User() {
		
	}
	
	@DataProvider(name="register")
	public String[][] UserAndPass_Data(){
		
		return new String[][] {
			{"auto" + getRandomNum() + "@mail.com", "123456"},
			{"auto" + getRandomNum() + "@mail.com", "123456"},
			{"auto" + getRandomNum() + "@mail.com", "123456"}
		};
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public int getRandomNum() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
}
