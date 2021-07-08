package webdriver;

import java.util.List;

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


public class Topic_11_Popup {
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
	
//	@Test
	public void TC_01_ngoaingu24h() {
		driver.get("https://ngoaingu24h.vn/");
		
		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		
		//verify popup display
		Assert.assertTrue(driver.findElement(By.xpath("//h4[@class='modal-title' and text()='Đăng nhập']")).isDisplayed());
		driver.findElement(By.xpath("//h4[@class='modal-title' and text()='Đăng nhập']/preceding-sibling::button")).click();
		
	}
//	@Test
	public void TC_02_Popup_Random_InDOM() {
		driver.get("https://blog.testproject.io/");
		
		//wait for popup xuất hiện 
		SleepInSecond(15);
		if (driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed())
			driver.findElement(By.xpath("//div[@id=\"close-mailch\"]")).click();
		
		//input để tìm kiểm
		driver.findElement(By.xpath("//section[@id='search-2']//input[@placeholder='Search Articles']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		SleepInSecond(3);
		
		List<WebElement> articles = driver.findElements(By.xpath("//h3[@class='post-title']")); //ra số lượng 8
		
		for (WebElement article : articles) {
			
			Assert.assertTrue(article.getText().contains("Selenium"));
		}	
	}

	@Test
	public void TC_04_Popup_Random_NotInDOM() {
		driver.get("https://shopee.vn/");
		
		//wait for popup xuất hiện 
		SleepInSecond(3);
		if (driver.findElement(By.xpath("//img[@alt='home_popup_banner']")).isDisplayed())
			driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
		
		//input để tìm kiểm
		driver.findElement(By.cssSelector(".shopee-searchbar-input input")).sendKeys("Macbook Pro");
		driver.findElement(By.xpath("//button[@type='button']")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector(".shopee-search-item-result__item .yQmmFK"));
		
		String searchKey = "Macbook Pro";
		
		String[] searchChar = searchKey.split(" ");
		//searchChar[0].toLowerCase();
		
		for (WebElement product : products) {
			String productName = product.getText().toLowerCase();
			Assert.assertTrue(productName.contains(searchChar[0].toLowerCase()) || productName.contains(searchChar[1].toLowerCase()));
		}
	
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