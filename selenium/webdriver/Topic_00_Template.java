package webdriver;

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


public class Topic_00_Template {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01_Accept_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		SleepInSecond(3);
		
		//wait for alert display + switch to alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Verify text
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		
		//verify result
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
		
	}
	
	@Test
	public void TC_02_Confirm_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		SleepInSecond(3);
		
		//wait for alert display + switch to alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Verify text
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		alert.dismiss();
		
		//verify result
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
		
	}
	
	@Test
	public void TC_03_Prompt_Alert() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		SleepInSecond(3);
		
		//wait for alert display + switch to alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Verify text
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		alert.sendKeys("Selenium FC");
		SleepInSecond(3);
		
		alert.accept();
		
		//verify result
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: Selenium FC");
		
	}
	

	@Test
	public void TC_04_Authen_Alert() {
		//Cách 1: thêm cú pháp vào url: http://user:pass@domain
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(),"Congratulations! You must have the proper credentials.");
		
		//Cách 2: lấy ra url rồi insert user/pass
		driver.get("http://the-internet.herokuapp.com");
		
		String urlAuthen = driver.findElement(By.xpath("//a[@href='/basic_auth']")).getAttribute("href");
		System.out.println(urlAuthen);
		
		insertValuetoURL(urlAuthen, "admin", "admin");
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(),"Congratulations! You must have the proper credentials.");
		
	}

	@Test
	public void TC_03() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void insertValuetoURL(String url, String username, String password) {
		
		String[] urlSplit = url.split("//");
		url = urlSplit[0] + "//" + username + ":" + password + "@" + urlSplit[1];
		
		driver.get(url);
		
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