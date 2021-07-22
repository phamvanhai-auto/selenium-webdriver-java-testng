package webdriver;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_Wait_V_Fluent {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	FluentWait<WebElement> fluentElement;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//explicitWait = new WebDriverWait(driver, 10);
		//driver.manage().window().maximize();
		
	}
	
//	@Test
	public void TC_08() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdownTimer = driver.findElement(By.id("javascript_countdown_time"));
		
		fluentElement = new FluentWait<WebElement>(countdownTimer);
		
		fluentElement.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement countdown) {
				boolean status = countdown.getText().endsWith("00");
				System.out.println("Text = " + countdown.getText() + "-----: " + status);
				return status;
			}
			
		});
		
	}
	
//	@Test
	public void TC_09() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		clickToElement(By.xpath("//div[@id='start']/button"));
		
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
	}
	
	@Test
	public void TC_10_Icon_Loading_success() {
		driver.get("https://opensource-demo.orangehrmlive.com");
		
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		//wait page ready (JQuery/Ajax loading)
		Assert.assertTrue(isJQueryLoadedSuccess(driver));
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='total']//span")).getText(), "3 month(s)");
		
		driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']")).click();
		
		Assert.assertTrue(isJQueryLoadedSuccess(driver));
		driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys("Linda Jane");
		driver.findElement(By.id("searchBtn")).click();
		
		Assert.assertTrue(isJQueryLoadedSuccess(driver));
		Assert.assertEquals(driver.findElement(By.xpath("//table[@id='resultTable']//tr/td[3]")).getText(), "Linda Jane");
		
	}	

	public WebElement getElement(By locator) {
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		
		wait.withTimeout(Duration.ofSeconds(10))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			
			@Override
			public WebElement apply(WebDriver driver) {
				
				return driver.findElement(locator);
			}
			
		});
		return element;
		
	}
	
	public void clickToElement(By locator) {
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		
		wait.withTimeout(Duration.ofSeconds(10))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			
			@Override
			public WebElement apply(WebDriver driver) {
				
				return driver.findElement(locator);
			}
			
		});
		element.click();;
		
	}
	
	public boolean isElementDisplayed(By locator) {
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		
		wait.withTimeout(Duration.ofSeconds(10))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class);
		
		Boolean status = wait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				
				return driver.findElement(locator).isDisplayed();
			}
			
		});
		return status;

	}
	
	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		
		explicitWait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				
				return (Boolean) jsExecutor.executeScript("return (window.jQuery !=null) && (jQuery.active === 0)");
			}
		};
		return explicitWait.until(jQueryLoad);
				
				
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