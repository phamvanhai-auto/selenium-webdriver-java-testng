package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_06_Web_Element_Excercise {
	WebDriver driver;
///
	
	By emailbox = By.id("mail");
	By agecheckbox = By.id("under_18");
	By edubox = By.id("edu");
	By javacheckbox = By.id("java");
	
	By pwdbox = By.id("password");
	By biocheckbox = By.id("bio");
	By buttondisable = By.id("button-disabled");
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
	}
	
	@Test
	public void TC_01_Element_isDisplay() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if(isElementDisplay(emailbox))
			sendKeytoElement(emailbox, "Helloooo");
		
		if(isElementDisplay(agecheckbox))
			clicktoElement(agecheckbox);
		
		if(isElementDisplay(edubox))
			sendKeytoElement(edubox, "Hellllllooo");
		
//		
//		//Email
//		if(driver.findElement(By.id("mail")).isDisplayed()) {
//			driver.findElement(By.id("mail")).sendKeys("Hello Hai");
//			System.out.println("Mail Texbox is displayed");
//		}
//		else
//			System.out.println("Mail Texbox is not displayed");
//		
//		//Age
//		if(driver.findElement(By.id("under_18")).isDisplayed()) {
//			driver.findElement(By.id("under_18")).click();
//			System.out.println("Age under 18 is displayed");
//		}
//		else
//			System.out.println("Age under 18 is not displayed");
//		
//		//Edu
//		if(driver.findElement(By.id("edu")).isDisplayed()) {
//			driver.findElement(By.id("edu")).sendKeys("Hello Haiiii");
//			System.out.println("Edu is displayed");
//		}
//		else
//			System.out.println("Edu is not displayed");
	}

	@Test
	public void TC_02_Element_isSelected() {
		
		clicktoElement(agecheckbox);
		clicktoElement(javacheckbox);
		
		//verify
		Assert.assertTrue(isElementSelected(agecheckbox));
		Assert.assertTrue(isElementSelected(javacheckbox));
		
		clicktoElement(javacheckbox);
		
		Assert.assertFalse(isElementSelected(javacheckbox));
		Assert.assertTrue(isElementSelected(agecheckbox));
		
	}

	@Test
	public void TC_03_isEnable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//expected enable
		Assert.assertTrue(isElementEnable(emailbox));
		Assert.assertTrue(isElementEnable(edubox));
		Assert.assertTrue(isElementEnable(agecheckbox));
		
		//exxpected disable
		Assert.assertFalse(isElementEnable(pwdbox));
		Assert.assertFalse(isElementEnable(biocheckbox));
		Assert.assertFalse(isElementEnable(buttondisable));		
	}
	
	@Test
	public void TC_04_Register_Valid() {
		driver.get("https://login.mailchimp.com/signup/");
		
		By pwdbox = By.id("new_password");
		By signUpbutoon = By.id("create-account");
		By newslettercheckbox = By.id("marketing_newsletter");
		By uppercaseOK = By.cssSelector(".uppercase-char.completed");
		By lowercaseOK = By.cssSelector(".lowercase-char.completed");
		By numberOK = By.cssSelector(".number-char.completed");
		By specialchar = By.cssSelector(".special-char.completed");
		By greater8char = By.cssSelector("li[class='8-char completed']");
		
		
		driver.findElement(By.id("email")).sendKeys("hai124321@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("haieagle");
		
		//uppercase
		driver.findElement(pwdbox).sendKeys("HELL");
		Assert.assertTrue(isElementDisplay(uppercaseOK));
		Assert.assertFalse(isElementEnable(signUpbutoon));
		
		//lowercase
		driver.findElement(pwdbox).clear();
		driver.findElement(pwdbox).sendKeys("dsfs");
		Assert.assertTrue(isElementDisplay(lowercaseOK));
		Assert.assertFalse(isElementEnable(signUpbutoon));
		
		//number
		driver.findElement(pwdbox).clear();
		driver.findElement(pwdbox).sendKeys("2213");
		Assert.assertTrue(isElementDisplay(numberOK));
		Assert.assertFalse(isElementEnable(signUpbutoon));
		
		//special
		driver.findElement(pwdbox).clear();
		driver.findElement(pwdbox).sendKeys("#$@");
		Assert.assertTrue(isElementDisplay(specialchar));
		Assert.assertFalse(isElementEnable(signUpbutoon));
		
		//>8 char
		driver.findElement(pwdbox).clear();
		driver.findElement(pwdbox).sendKeys("safdkfskjle");
		Assert.assertTrue(isElementDisplay(lowercaseOK));
		Assert.assertTrue(isElementDisplay(greater8char));
		Assert.assertFalse(isElementEnable(signUpbutoon));
		
		//All criteria
		driver.findElement(pwdbox).clear();
		driver.findElement(pwdbox).sendKeys("Abc@1223435");
		
		Assert.assertFalse(isElementDisplay(uppercaseOK));
		Assert.assertFalse(isElementDisplay(lowercaseOK));
		Assert.assertFalse(isElementDisplay(numberOK));
		Assert.assertFalse(isElementDisplay(greater8char));
		Assert.assertFalse(isElementDisplay(specialchar));
		
		Assert.assertTrue(isElementEnable(signUpbutoon));
		
		//tick newsletter
		clicktoElement(newslettercheckbox);
		Assert.assertTrue(isElementSelected(newslettercheckbox));
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isElementDisplay(By by) {
		
		if(driver.findElement(by).isDisplayed()) {
			System.out.println(by + "is dissplayed");
			return true;
		}
		else
			System.out.println(by + "is not dissplayed");
			return false;
		
	}
	
	//isSelected
	public boolean isElementSelected(By by) {
		
		if(driver.findElement(by).isSelected()) {
			System.out.println(by + "is selected");
			return true;
		}
		else
			System.out.println(by + "is not selected");
			return false;
	
	}
	//isEnable
	public boolean isElementEnable(By by) {
		
		if(driver.findElement(by).isEnabled()) {
			System.out.println(by + "is enabled");
			return true;
		}
		else
			System.out.println(by + "is not enabled");
			return false;
	
	}
	public void sendKeytoElement(By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}
	public void clicktoElement(By by) {
		driver.findElement(by).click();
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