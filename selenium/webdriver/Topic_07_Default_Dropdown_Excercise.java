package webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown_Excercise {
	WebDriver driver;
	Select select;
	String firstName, lastName, day, month, year, emailAddr, companyName, Pwd;
	JavascriptExecutor jsExecutor;
	String Job2[] = { "Automation", "Mobile", "Desktop" };

///
	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.gecko.driver",
		// ".\\browserDrivers\\geckodriver.exe");
		// System.setProperty("webdriver.chrome.driver",
		// ".\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		jsExecutor = (JavascriptExecutor) driver;
		
		firstName = "Automation";
		lastName = "FC";
		day = "1";
		month = "May";
		year = "1980";
		emailAddr = "autofc" + genEmail();
		companyName = "VN FFC";
		Pwd = "1232345";

	}

//	@Test
	public void TC_01_NoCommerce() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.className("ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
		driver.findElement(By.id("LastName")).sendKeys("FC");

		select = new Select(driver.findElement(By.name("DateOfBirthDay")));

		// 3 cach de chon dropdown
		// select.selectByIndex(0);
		// select.selectByValue("");
		// chọn 1 item trong droplist
		select.selectByVisibleText("10");

		// lấy ra số lượng option
		Assert.assertEquals(select.getOptions().size(), 32);

	}

//	@Test
	public void TC_02_Dropdown_01() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		select = new Select(driver.findElement(By.id("job1")));
		Assert.assertFalse(select.isMultiple());

		select.selectByVisibleText("Manual Testing");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");

		select.selectByIndex(9);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");

		Assert.assertEquals(select.getOptions().size(), 10);

		select = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(select.isMultiple());

		// select 3 items trong job2
		for (String value : Job2) {
			select.selectByVisibleText(value);
			Thread.sleep(500);
		}

		List<WebElement> selectedOption = select.getAllSelectedOptions();
		Assert.assertEquals(selectedOption.size(), 3);

		// tao ra list de add items dc chon den mang nay
		List<String> actualValues = new ArrayList<>();

		for (WebElement options : selectedOption) {
			actualValues.add(options.getText());
		}

		// so sanh ket qua
		List<String> expectedValues = Arrays.asList(Job2);
		Assert.assertEquals(actualValues, expectedValues);

		// bo chon het & kiem tra
		select.deselectAll();
		Assert.assertEquals(select.getAllSelectedOptions().size(), 0);

	}

	@Test
	public void TC_03_Dropdown_02() {
		driver.get("https://demo.nopcommerce.com/register");

		driver.findElement(By.xpath("//a[@class='ico-register']"));
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		Assert.assertEquals(select.getOptions().size(), 13);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(select.getOptions().size(), 112);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddr);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(Pwd);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(Pwd);
		
		//su dung JS de click
		ClickbyJS(By.id("register-button"));
		//driver.findElement(By.id("register-button")).click();		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		SleepInSecond(3);
		
		//kiem tra file dropdown
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddr);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String genEmail() {

		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.com";

	}
	
	public void ClickbyJS(By by) {
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}
	public void SleepInSecond(long timeoutSecond) {
		try {
			Thread.sleep(timeoutSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}