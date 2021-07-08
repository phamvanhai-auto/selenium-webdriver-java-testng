package webdriver;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_13_Window_Tab {
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
	public void TC_01_Window_2Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		SleepInSecond(2);
		
		//get parent Window/Tab ID (main page)
		String parentTabID = driver.getWindowHandle();
		
		//click to GG
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		
		//switch to GG tab
		switchToWindowbyID(parentTabID);
		
		//verify switch to GG thành công
		Assert.assertEquals(driver.getTitle(), "Google");	
	}
	
//	@Test
	public void TC_02_Window_Multi_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		SleepInSecond(5);
		
		//get parent Window/Tab ID (main page)
		String parentTabID = driver.getWindowHandle();
		
		//click to GG
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		SleepInSecond(3);
		
		//switch tới GG & verify đã tới gg tab
		switchToWindowTitle("Google");
		driver.findElement(By.name("q")).sendKeys("Automation");
		
		//Trở về parent
		switchToWindowTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		//05/06- click vào Fb & verify
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchToWindowTitle("Facebook - Đăng nhập hoặc đăng ký");
		
		//07- Trở về parent
		switchToWindowTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		//08/09- click vào Tiki
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToWindowTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
		//10- close other tabs (để lại parent tab)
		closeTabsexceptParrent(parentTabID);
		
		//verify switched to parent tab
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		
	}
	
//	@Test
	public void TC_08_Kyna() {
		driver.get("https://kyna.vn/");
		
		String parentID = driver.getWindowHandle();
		
		//click to fb icon at footer & switch to new tab
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		SleepInSecond(3);
		switchToWindowTitle("Đăng nhập Facebook");
		Assert.assertEquals(driver.getCurrentUrl(), "https://m.facebook.com/login.php?next=https%3A%2F%2Fm.facebook.com%2Fkyna.vn&refsrc=deprecated&_rdr");
		
		//switch to parrent
		switchToWindowTitle("Kyna.vn - Học online cùng chuyên gia");
		
		//click to Youtube icon at footer & switch to new tab
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();
		SleepInSecond(3);
		switchToWindowTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");
		
	}	
	
//	@Test
	public void TC_09_Guru99() {
		driver.get("http://live.demoguru99.com");
		
		String parentID = driver.getWindowHandle();
	
		//click vào Mobile
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		//add Iphone to giỏ
		driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		SleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product IPhone has been added to comparison list.']")).isDisplayed());
		
		//add Samsung to giỏ
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		SleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());

		
		//click Compare
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		//switch tới new tab
		switchToWindowbyID(parentID);
		System.out.println(driver.getCurrentUrl());
		SleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		driver.close();
		
		//switch to parent tab
		switchToWindowTitle("Mobile");
		//click Clear All
		SleepInSecond(2);
		driver.findElement(By.xpath("//div[@class='actions']//a[text()='Clear All']")).click();
		SleepInSecond(2);
		
		//switch to alert
		driver.switchTo().alert().accept();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());

	}
	@Test
	public void TC_03() {
		
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
	
	public void switchToWindowTitle(String titleTab) {
		//lấy ra ID của các Tabs
		Set<String> allTabsID = driver.getWindowHandles();
		
		//Duyệt từng tab, rồi switch luôn tới tab đó > xong kiểm tra title có đúng như truyền vào?
		for (String id : allTabsID) {
			driver.switchTo().window(id);
			String getTitle = driver.getTitle();
			if(getTitle.equals(titleTab))
				break;
		}
	}

	public void closeTabsexceptParrent(String parentTab) {
		
		Set<String> allTabIDs = driver.getWindowHandles();
		
		for (String id : allTabIDs) {
			if(!id.equals(parentTab)) {
				driver.switchTo().window(id);
				driver.close();
				
			}
		}
		driver.switchTo().window(parentTab);
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