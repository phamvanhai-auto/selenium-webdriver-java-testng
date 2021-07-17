package webdriver;

import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_14_JavascriptExecutor {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 10);
		
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
//	@Test
	public void TC_01_Guru99() {
		//01-open url
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		//02-get domain
		String Guru99domain = (String) jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(Guru99domain, "live.demoguru99.com");
		
		//03-get url
		String Guru99URL = (String) jsExecutor.executeScript("return document.URL");
		Assert.assertEquals(Guru99URL, "http://live.demoguru99.com/");
		
		//04-Mobile 
		clickToElementByJS("//a[text()='Mobile']");
		//05-Add to cart
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button[@title='Add to Cart']");
		
		//06-verify texxt
		Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		//07-Customer service
		clickToElementByJS("//a[text()='Customer Service']");
		
		//08-verify title page
		String pageTitle = (String) jsExecutor.executeScript("return document.title");
		Assert.assertEquals(pageTitle, "Customer Service");
		
		//09-keo xuong input mail
		scrollToElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", genEmail());
		
		//10-click
		clickToElementByJS("//button[@title='Subscribe']");
		Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));
		
		//11-navigate to other url
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		//wait den khi open url
		explicitWait.until(ExpectedConditions.urlToBe("http://demo.guru99.com/v4/"));
		
		String demo = (String) jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(demo, "demo.guru99.com");
		
	}
	
//	@Test
	public void TC_02_Validation_Msg() {
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
		
		//02-verify display
		clickToElementByJS("//input[@value='SUBMIT']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");
		
		//03-input data vao Name field > click submit
		sendkeyToElementByJS("//input[@id='fname']", "Auto FC");
		clickToElementByJS("//input[@value='SUBMIT']");
		//so sanh 
		Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");
		
		//04-input data vao Pass field > click submit
		sendkeyToElementByJS("//input[@id='pass']", "Auto FC");
		clickToElementByJS("//input[@value='SUBMIT']");
		SleepInSecond(2);
		//verify Email field
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");
		
		//05-input invalid vao Emai field > click submit
		sendkeyToElementByJS("//input[@id='em']", "123");
		clickToElementByJS("//input[@value='SUBMIT']");
		SleepInSecond(2);
		
		//Verify
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please enter an email address.");
		
		//send key khác
		sendkeyToElementByJS("//input[@id='em']", "123@34");
		clickToElementByJS("//input[@value='SUBMIT']");
		SleepInSecond(2);
		
		//Verify
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please match the requested format.");
		
		//send key khác
		sendkeyToElementByJS("//input[@id='em']", "123@#!&^*");
		clickToElementByJS("//input[@value='SUBMIT']");
		SleepInSecond(2);
		
		//Verify
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please enter an email address.");
		
		//send key hợp lệ
		sendkeyToElementByJS("//input[@id='em']", "123@mai.com");
		clickToElementByJS("//input[@value='SUBMIT']");
		SleepInSecond(2);
		
		//Verify address
		Assert.assertEquals(getElementValidationMessage("//select"), "Please select an item in the list.");

	}	
	
//	@Test
	public void TC_03_Ubuntu() {
		
			navigateToUrlByJS("https://login.ubuntu.com/");
			clickToElementByJS("//button[@type='submit']");
			Assert.assertEquals(getElementValidationMessage("//input[@id='id_email']"),"Please fill out this field.");
			
			sendkeyToElementByJS("//input[@id='id_email']", "aa1");
			clickToElementByJS("//button[@type='submit']");
			Assert.assertEquals(getElementValidationMessage("//input[@id='id_email']"),"Please include an '@' in the email address. 'aa1' is missing an '@'.");

			sendkeyToElementByJS("//input[@id='id_email']", "aa1@%$");
			clickToElementByJS("//button[@type='submit']");
			Assert.assertEquals(getElementValidationMessage("//input[@id='id_email']"),"A part following '@' should not contain the symbol '%'.");

			sendkeyToElementByJS("//input[@id='id_email']", "a21234@12.com");
			clickToElementByJS("//button[@type='submit']");
			
			//verify pwd field
			Assert.assertEquals(getElementValidationMessage("//input[@type='password']"),"Please fill out this field.");

			//open other url
			navigateToUrlByJS("https://sieuthimaymocthietbi.com/account/register");
			clickToElementByJS("//button[@type='submit']");
			Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"),"Please fill out this field.");
			
			//same tren

	}
	
//	@Test
	public void TC_04_Remove_Attribute() {
		driver.get("http://demo.guru99.com/v4/");
		
		// data test (New Customer)
		String name, dob, addr, city, state, pin, mobile, email, pwd;
		String EmailAddr;
		String loginUrlPage;
		String UserID, Pwd;
		String CustomerID;
		
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
		
		// Initial New Cus
		EmailAddr = "Obama" + genEmail();
		name = "Black Obama";
		dob = "1992-03-05";
		addr = "123 Trung Kinh Xuong dong";
		city = "Ha noi";
		state = "Cau giay";
		pin = "132545";
		mobile = "0343538695";
		
		loginUrlPage = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(EmailAddr);

		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		UserID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Pwd = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		driver.get(loginUrlPage);

		driver.findElement(By.name("uid")).sendKeys(UserID);
		driver.findElement(By.name("password")).sendKeys(Pwd);

		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextbox).sendKeys(name);
		
		//remove Date Picker
		removeAttributeInDOM("//input[@id='dob']", "type");
		SleepInSecond(3);
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

	}
	
//	@Test
	public void TC_05_Create_ACC() {
		
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
		SleepInSecond(2);
		clickToElementByJS("//a[@title='Create an Account']");
		SleepInSecond(2);
		
		sendkeyToElementByJS("//input[@id='firstname']", "Auto FC");
		sendkeyToElementByJS("//input[@id='lastname']", "Auto FC");
		sendkeyToElementByJS("//input[@id='email_address']", genEmail());
		sendkeyToElementByJS("//input[@id='password']", "1234325");
		sendkeyToElementByJS("//input[@id='confirmation']", "1234325");
		
		clickToElementByJS("//button[@title='Register']");
		
		Assert.assertTrue(isExpectedTextInInnerText("Thank you for registering with Main Website Store."));
		
		clickToElementByJS("//a[text()='Log Out']");
		
		explicitWait.until(ExpectedConditions.titleContains("Home page"));
		String Homepage = (String) jsExecutor.executeScript("return document.title");
		Assert.assertEquals(Homepage, "Home page");
		
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

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		SleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = false;
		status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public String genEmail() {
		
		Random rand = new Random();
		return "autofc" + rand.nextInt(9999) + "@gmail.com";
		
	}
}
