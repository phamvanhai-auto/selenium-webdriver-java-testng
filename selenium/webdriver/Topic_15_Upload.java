package webdriver;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_15_Upload {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	
	String projectPath = System.getProperty("user.dir");
	
	String abcFilePath = projectPath + "\\uploadFiles\\abc.jpg";
	String avatarFilePath = projectPath + "\\uploadFiles\\avatar.jpg";
	
	String firefoxUploadOneTime = projectPath + "\\AutoIT\\firefoxUploadOneTime.exe";
	String chromeUploadOneTime = projectPath + "\\AutoIT\\chromeUploadOneTime.exe";
	
	String firefoxUploadMultiple = projectPath + "\\AutoIT\\firefoxUploadMultiple.exe";
	String chromeUploadMultiple = projectPath + "\\AutoIT\\chromeUploadMultiple.exe";
	
	
	String abcName = "abc.jpg";
	String avatarName = "avatar.jpg";
	
	
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
	public void TC_01_SendKey_OneFile() {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//upload file theo path
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(abcFilePath);
		
		//verify đã đc load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + abcName + "']")).isDisplayed());
		
		//click upload
		driver.findElement(By.cssSelector("table .start")).click();
		
		//ktra file đã đc uploaded ok
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + abcName + "']")).isDisplayed());
	}
	
//	@Test
	public void TC_01_SendKey_MultiFile() {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(abcFilePath + "\n" + avatarFilePath);
		//driver.findElement(By.xpath("//input[@type='file']")).sendKeys(avatarFilePath);
		
		//verify đẩy file lên ok
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + abcName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + avatarName + "']")).isDisplayed());

		List<WebElement> startFileList = driver.findElements(By.cssSelector("table .start"));
		
		for (WebElement startFile : startFileList) {
			startFile.click();
			SleepInSecond(1);
		}
		//veriy đã upload lên ok
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + abcName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + avatarName + "']")).isDisplayed());
	}
	
//	@Test
	public void TC_02_AutoIT_OneFile() throws IOException {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector(".btn-success")).click();
		
		Runtime.getRuntime().exec(new String[] {firefoxUploadOneTime, abcFilePath});
		SleepInSecond(5);
		
		//verify đã đc load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + abcName + "']")).isDisplayed());
		
		//click upload
		driver.findElement(By.cssSelector("table .start")).click();
		SleepInSecond(2);
		
		//ktra file đã đc uploaded ok
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + abcName + "']")).isDisplayed());
	}
	
//	@Test
	public void TC_02_AutoIT_MultiFile() throws IOException {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector(".btn-success")).click();
		
		Runtime.getRuntime().exec(new String[] {firefoxUploadMultiple, abcFilePath, avatarFilePath});
		SleepInSecond(15);
		
		//verify đẩy file lên ok
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + abcName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + avatarName + "']")).isDisplayed());

		List<WebElement> startFileList = driver.findElements(By.cssSelector("table .start"));
		
		for (WebElement startFile : startFileList) {
			startFile.click();
			SleepInSecond(1);
		}
		//veriy đã upload lên ok
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + abcName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + avatarName + "']")).isDisplayed());
	}
	
	@Test
	public void TC_03_Java_Robot() {
		
	}
	
	@Test
	public void TC_04_Upload_Exercise() {
		
		driver.get("https://gofile.io/?t=uploadFiles");
		
		//get parent Window/Tab ID (main page)
		String parentTabID = driver.getWindowHandle();
		
		//load 2 file 
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(abcName + "\n" + avatarName);
		
		//Assert.assertEquals(driver.findElement(By.tagName("h5")).getText().trim(), "Your files have been successfully uploaded");
		
		driver.findElement(By.id("rowUploadSuccess-downloadPage")).click();
		
		//switch to other window
		switchToWindowbyID(parentTabID);
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void switchToWindowbyID(String parentID) {
		
		Set<String> allTabIDs = driver.getWindowHandles();
		
		for (String id : allTabIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
			}
		}		
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