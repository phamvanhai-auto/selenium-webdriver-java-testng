package webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_User_Interaction {
	WebDriver driver;
	Actions action;
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		action = new Actions(driver);
		driver.manage().window().maximize();
		
	}
	
//	@Test
	public void TC_01_Hover_Mouse_01() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		action.moveToElement(driver.findElement(By.cssSelector("#age"))).perform();
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");

	}
	
//	@Test
	public void TC_02_Hover_Mouse_02() {
		driver.get("http://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		SleepInSecond(3);
		
		driver.findElement(By.xpath("//a[normalize-space()='Home & Bath']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		
	}
	
//	@Test
	public void TC_03_Hover_Mouse_03() {
		driver.get("https://hn.telio.vn/");
		
		action.moveToElement(driver.findElement(By.xpath("(//a[@class='menu-link']/span[text()='Đồ ăn vặt'])[2]"))).perform();
		SleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("(//a[text()='Bắp rang bơ'])[2]")).isDisplayed());
	}
	
//	@Test
	public void TC_04_Click_Hold_Mouse() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> rectagNumber = driver.findElements(By.cssSelector("#selectable>li"));
		
		action.clickAndHold(rectagNumber.get(0)).moveToElement(rectagNumber.get(3)).release().perform();
		SleepInSecond(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);
		
	}
	
//	@Test
	public void TC_04_Click_Hold_Mouse_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> rectagNumber = driver.findElements(By.cssSelector("#selectable>li"));
		
		//giữ phím ctrl
		action.keyDown(Keys.CONTROL).perform();
		action.click(rectagNumber.get(0))
		.click(rectagNumber.get(5))
		.click(rectagNumber.get(7))
		.click(rectagNumber.get(11)).perform();
		
		//nhả phím ctrl
		action.keyUp(Keys.CONTROL).perform();
		SleepInSecond(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);
		
	}

//	@Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		action.doubleClick(driver.findElement(By.xpath("//button[normalize-space()='Double click me']"))).perform();
		SleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo']")).isDisplayed());
		
	}
	
//	@Test
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		//click right mouse
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		SleepInSecond(2);
		
		//hover to Copy 
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-copy"))).perform();
		SleepInSecond(2);
		
		action.click(driver.findElement(By.cssSelector(".context-menu-icon-copy"))).perform();
		SleepInSecond(2);
		
		//Alert
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: copy");
		driver.switchTo().alert().accept();
	}

//	@Test
	public void TC_08_Drag_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement bigDrop = driver.findElement(By.xpath("//div[@id='droptarget']"));
		WebElement smallDrag = driver.findElement(By.xpath("//div[@id='draggable']"));
		
		action.dragAndDrop(smallDrag, bigDrop).perform();
		SleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='droptarget']")).getText(), "You did great!");
		//verify background by convert to Hex
		Assert.assertEquals(Color.fromString(bigDrop.getCssValue("background-color")).asHex(), "#03a9f4");
	}
	
//	@Test
	public void TC_09_Drag_Drop_HTML5() {
		//Phức tạp để xử lý
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