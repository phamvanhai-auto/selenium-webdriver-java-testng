package webdriver;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_12_Frame_iFrame {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	Select select;
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		
	}
	
//	@Test
	public void TC_01_iFrame() {
		driver.get("https://kyna.vn/");
		
		//2 - verify iframe hiển thị hay k
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='face-content']")).isDisplayed());
		
		//switch to iframe fb
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		
		//3- lấy ra số lượng like
		String likePage = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println(likePage);
		
		//switch về main
		driver.switchTo().defaultContent();
		
		//4- click chat
		driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")).click();
		SleepInSecond(5);
		
		//switch to iframe chat
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
		
		//input data để send msg
		driver.findElement(By.xpath("//input[@ng-model='login.username']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//input[@ng-model='login.phone']")).sendKeys("02830234594");
		
		//Dropdown to chọn option > có thẻ select/option nên đc hỗ trợ bởi selenium
		select = new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']")));
		select.selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		SleepInSecond(3);
		
		driver.findElement(By.name("message")).sendKeys("Hello guys");
		driver.findElement(By.xpath("//input[@value='Gửi tin nhắn']")).click();
		SleepInSecond(3);
		
		//verify text
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='floater_inner']//label[@class='logged_in_name ng-binding']")).getText(), "Selenium");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='floater_inner']//label[@class='logged_in_phone ng-binding']")).getText(), "02830234594");
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='message']")).getText(), "Hello guys");
		
		//switch về main
		driver.switchTo().defaultContent();
		
		//7- search keyword 'Excel'
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
		driver.findElement(By.cssSelector(".search-button"));
		SleepInSecond(3);
		
		//8- Verify ds tìm kiếm gồm từ khóa Excel
		List<WebElement> courses = driver.findElements(By.cssSelector(".content h4"));
		for (WebElement course : courses) {
			Assert.assertEquals(course.getText(), "Excel");
		}
		
	}
	
	@Test
	public void TC_02_Frame() {
		driver.get("https://v1.hdfcbank.com/assets/popuppages/netbanking.htm");
		
		//2- click vào link
		driver.findElement(By.xpath("(//div[@class='container']/div/a)[2]")).click();
		SleepInSecond(3);
		
		//Switch to frame login
		driver.switchTo().frame("login_page");
		
		//3- input Custom ID
		driver.findElement(By.cssSelector(".input_password")).sendKeys("Automation");
		driver.findElement(By.xpath("//a[@onclick = 'return fLogon();']")).click();
		SleepInSecond(2);
		
		//4- verify textbox pwd
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='fldPassword']")).isDisplayed());
		
		//switch back main page
		driver.switchTo().defaultContent();
		
		//switch to frame footer
		driver.switchTo().frame("footer");
		
		driver.findElement(By.xpath("//a[text()='Terms and Conditions']")).click();
		SleepInSecond(3);
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