package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_09_Button_Raido_Checkbox {
	WebDriver driver;
	
	boolean status;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	
	}
	
//	@Test
	public void TC_01_Butoon() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		//disable
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Button status = " + status);
		Assert.assertFalse(status);
		
		driver.findElement(By.cssSelector("#login_username")).sendKeys("0983861683");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("12324345");
		
		//enable
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Button status = " + status);
		Assert.assertTrue(status);
		
		driver.navigate().refresh();
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		//remove ẩn nút đăng nhập đi
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(By.cssSelector(".fhs-btn-login")));
		SleepInSecond(2);
		
		driver.findElement(By.cssSelector(".fhs-btn-login")).click();
		SleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	
	}

//	@Test
	public void TC_02_Checkbox_Radio_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		By rearSideAir = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
		//click vào để chọn
		checktoCheckbox_Radio(rearSideAir);
		SleepInSecond(2);
		Assert.assertTrue(driver.findElement(rearSideAir).isSelected());
		
		//bỏ chọn
		unchecktoCheckbox(rearSideAir);
		Assert.assertFalse(driver.findElement(rearSideAir).isSelected());
		
		//Radio 
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		By oneDotPetro = By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input");
		checktoCheckbox_Radio(oneDotPetro);
		SleepInSecond(2);
		Assert.assertTrue(driver.findElement(oneDotPetro).isSelected());
		
		//chọn radio khác
		By twoDiesel = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");
		checktoCheckbox_Radio(twoDiesel);
		SleepInSecond(1);
		Assert.assertTrue(driver.findElement(twoDiesel).isSelected());

	}

//	@Test
	public void TC_03_Checkbox_Radio_Custom() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		//1 - Dùng span để click + input để verify
		By radioSum = By.xpath("//span[contains(text(),'Summer')]");
		driver.findElement(radioSum).click();
		
		By radioSumInput = By.xpath("//input[@value='Summer']");
		Assert.assertTrue(driver.findElement(radioSumInput).isSelected());
		
		driver.navigate().refresh();
		
		//2- theo cách trên cần 2 locator để thực hiện > phức tạp
		//sử dụng jsExcutor để click
		clicktoElementbyJS(radioSumInput);
		Assert.assertTrue(driver.findElement(radioSumInput).isSelected());

	}
	
	@Test
	public void TC_04_Checkbox_Radio_Custom() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		SleepInSecond(3);
		
		//kiem tra xem da dc chọn chưa
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Bình' and @aria-checked='false']/div[2]")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@aria-label='Quảng Bình']/div[2]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Bình' and @aria-checked='true']/div[2]")).isDisplayed());
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void checktoCheckbox_Radio(By by) {
		
		WebElement elmentBox = driver.findElement(by);
		if(!elmentBox.isSelected()) {
			elmentBox.click();
		}
	}
	
	public void unchecktoCheckbox(By by) {
		
		WebElement elmentBox = driver.findElement(by);
		if(elmentBox.isSelected())
			elmentBox.click();
	}
	
	public void clicktoElementbyJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click();", element);
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