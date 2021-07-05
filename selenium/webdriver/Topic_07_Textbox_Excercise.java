package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.nio.sctp.SendFailedNotification;

public class Topic_07_Textbox_Excercise {
	WebDriver driver;
	String EmailAddr;
	String loginUrlPage;
	String UserID, Pwd;
	String CustomerID;

	// data test (New Customer)
	String name, dob, addr, city, state, pin, mobile, email, pwd;

	// data test (Edit Customer)
	String editAddr, editCity, editState, editPin, editMobile, editEmail;

	// UI new customer/edit customer
	By nameTextbox = By.name("name");
	By genderTextbox = By.name("gender");
	By dobTextbox = By.name("dob");
	By AddrTextArea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By mobileTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By pwdTextbox = By.name("password");

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.gecko.driver",
		// ".\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");

		// Initial New Cus
		EmailAddr = "Obama" + genEmail();
		name = "Black Obama";
		dob = "1992-03-05";
		addr = "123 Trung Kinh Xuong dong";
		city = "Ha noi";
		state = "Cau giay";
		pin = "132545";
		mobile = "0343538695";

		// Initial Edit Cus
		editAddr = "324 my dinh";
		editCity = "HCM";
		editState = "Binh Chanh";
		editPin = "123456";
		editMobile = "0912325435";
		editEmail = "Biden" + genEmail();
	}

	@Test
	public void TC_01_Register() {

		loginUrlPage = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(EmailAddr);

		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		UserID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Pwd = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {

		driver.get(loginUrlPage);

		driver.findElement(By.name("uid")).sendKeys(UserID);
		driver.findElement(By.name("password")).sendKeys(Pwd);

		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");

	}

	@Test
	public void TC_03_Create_New_Customer() {

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(dobTextbox).sendKeys(dob);
		driver.findElement(AddrTextArea).sendKeys(addr);
		SleepInSecond(3);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(mobileTextbox).sendKeys(mobile);
		driver.findElement(emailTextbox).sendKeys(EmailAddr);
		driver.findElement(pwdTextbox).sendKeys(Pwd);

		driver.findElement(By.name("sub")).click();

		//verify user data created
		Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(),"Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				mobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				EmailAddr);

		CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}

	@Test
	public void TC_04_Edit_Customer() {

		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(CustomerID);
		driver.findElement(By.name("AccSubmit")).click();

		// kiem tra disable
		Assert.assertFalse(driver.findElement(nameTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(dobTextbox).isEnabled());

		// verify sau khi chon edit
		Assert.assertEquals(driver.findElement(nameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(dobTextbox).getAttribute("value"), dob);
		Assert.assertEquals(driver.findElement(AddrTextArea).getText(), addr);
		Assert.assertEquals(driver.findElement(cityTextbox).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextbox).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextbox).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(mobileTextbox).getAttribute("value"), mobile);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), EmailAddr);

		// edit customer
		driver.findElement(AddrTextArea).clear();
		driver.findElement(AddrTextArea).sendKeys(editAddr);
		
		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(editCity);
		
		driver.findElement(stateTextbox).clear();
		driver.findElement(stateTextbox).sendKeys(editState);
		
		driver.findElement(pinTextbox).clear();
		driver.findElement(pinTextbox).sendKeys(editPin);
		SleepInSecond(3);
		
		driver.findElement(mobileTextbox).clear();
		driver.findElement(mobileTextbox).sendKeys(editMobile);
		
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(editEmail);
		
		driver.findElement(By.name("sub")).click();
		
		//verify sau khi submit edit
		Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(),
				"Customer details updated Successfully!!!");
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dob);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				editAddr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				editMobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				editEmail);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String genEmail() {

		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.com";

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