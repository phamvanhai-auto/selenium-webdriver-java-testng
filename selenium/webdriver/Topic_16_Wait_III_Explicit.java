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


public class Topic_16_Wait_III_Explicit {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	
	String projectPath = System.getProperty("user.dir");
	
	String abcName = "abc.jpg";
	String avatarName = "avatar.jpg";
	
	String abcFilePath = projectPath + "\\uploadFiles\\" + abcName;
	String avatarFilePath = projectPath + "\\uploadFiles\\" + avatarName;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		//driver.manage().window().maximize();
		
	}
	
//	@Test
	public void TC_04_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//chờ khi nào text xuất hiện
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
				
	}
	
//	@Test
	public void TC_05_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		//chờ khi nào loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		
	}
	
//	@Test
	public void TC_06_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//chờ cho Data Picker đc hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#ctl00_ContentPlaceholder1_Panel1")));
		
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		//click vào today
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td/a[text()='18']")));
		driver.findElement(By.xpath("//td/a[text()='18']")).click();
		
		//chờ cho loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		//verify date đc chọn
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='18']")));
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='18']")).isDisplayed());
		
		//ktra text hien thi
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Sunday, July 18, 2021");

	}	
	
	@Test
	public void TC_07_Upload_File() {
		driver.get("https://gofile.io/?t=uploadFiles");
		
		//wait cho upload button xuất hiện & upload files
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.uploadButton")));
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(abcFilePath + "\n" + avatarFilePath);
		
		//wait cho Select server biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#rowUploadProgress-selectServer")));
		
		//wait cho progress bar/tên file tải lên biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'text-truncate') and text()='" + abcName + "']")));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'text-truncate') and text()='" + avatarName + "']")));
		
		//wait mesg đc hiển thị thành công
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		//wait Show file button & click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles")));
		driver.findElement(By.cssSelector("button#rowUploadSuccess-showFiles")).click();
		
		//verify file upload ok
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='contentName' and text()='" + abcName + "']")));
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + abcName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + avatarName + "']")).isDisplayed());

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