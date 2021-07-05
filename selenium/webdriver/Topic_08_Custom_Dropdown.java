package webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	//inject 1 js code
	JavascriptExecutor jsExecutor;
	
	String[] firstMonth = {"February", "June", "December"};
	String[] secondMonth = {"February", "June", "September", "December"};
	String[] thirdMonth ={};	
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	//@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//goi toi ham de select option trong dropdown
		selectItemInCusDropdown("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]", "//ul[@id='number-menu']//div", "3");
		SleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='3']")).isDisplayed());
				
		selectItemInCusDropdown("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]", "//ul[@id='number-menu']//div", "9");
		SleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='9']")).isDisplayed());
		
		selectItemInCusDropdown("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]", "//ul[@id='number-menu']//div", "19");
		SleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
	}

//	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCusDropdown("//div[@role='listbox']/i[contains(@class,'dropdown')]", "//div[@role='option']/span", "Elliot Fu");
		SleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Elliot Fu']")).isDisplayed());
		
		selectItemInCusDropdown("//div[@role='listbox']/i[contains(@class,'dropdown')]", "//div[@role='option']/span", "Christian");
		SleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Christian']")).isDisplayed());
		
		selectItemInCusDropdown("//div[@role='listbox']/i[contains(@class,'dropdown')]", "//div[@role='option']/span", "Justen Kitsune");
		SleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Justen Kitsune']")).isDisplayed());
	
	}

//	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInCusDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		SleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		
		selectItemInCusDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		SleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
		
		selectItemInCusDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		SleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());
	
	}
	
//	@Test
	public void TC_04_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		
		//Football
		selectItemInCusDropdown("//span[@aria-owns='games_options']", "//li[@class='e-list-item ']", "Football");
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@aria-label='Football']")).getAttribute("aria-label"), "Football");
		
		//Tennis
		selectItemInCusDropdown("//span[@aria-owns='games_options']", "//li[@class='e-list-item ']", "Tennis");
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@aria-label='Tennis']")).getAttribute("aria-label"), "Tennis");	

	}
	
//	@Test
	public void TC_05_Editable_Click() {
		driver.get("https://valor-software.com/ng2-select/");
		
		enterAndSelectItemInCusDropdown("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//input", "//tab[@heading='Single']//a[@class='dropdown-item']/div", "Amsterdam");
		SleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//tab[@heading='Single']//span[@class='ui-select-match-text pull-left ui-select-allow-clear']")).isDisplayed());
		
		enterAndSelectItemInCusDropdown("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//input", "//tab[@heading='Single']//a[@class='dropdown-item']/div", "Łódź");
		SleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//tab[@heading='Single']//span[@class='ui-select-match-text pull-left ui-select-allow-clear']")).isDisplayed());

		
	}
	
//	@Test
	public void TC_05_Editable_Tab() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterAndTabItemInCusDropdown("//input[@class='search']", "Albania");
		SleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text']/i")).isDisplayed());
		
		enterAndTabItemInCusDropdown("//input[@class='search']", "Belgium");
		SleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text']/i")).isDisplayed());
		
	}
	
	@Test
	public void TC_06_Multiple() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		
		MultipleItemInCusDropdown("(//button[@class='ms-choice'])[1]", "//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//li//span", firstMonth);
		SleepInSecond(1);
		Assert.assertTrue(areOptionSelected(firstMonth));
		
		driver.navigate().refresh();
		
		MultipleItemInCusDropdown("(//button[@class='ms-choice'])[1]", "//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//li//span", secondMonth);
		SleepInSecond(1);
		Assert.assertTrue(areOptionSelected(secondMonth));
		
		driver.navigate().refresh();
		
		MultipleItemInCusDropdown("(//button[@class='ms-choice'])[1]", "//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//li//span", thirdMonth);
		SleepInSecond(1);
		Assert.assertFalse(areOptionSelected(thirdMonth));

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void selectItemInCusDropdown(String parentXpath, String childXpath, String expectedOption) {
		//Click vao de xo het cac option trong dropdown ra -> parentXpath
		driver.findElement(By.xpath(parentXpath)).click(); 
		
		//kiem tra cac option co visiable het khong -> childXpath
		//lay het cac option vao list
		List<WebElement> allOption = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//Duyet qua tung option
		for (WebElement option : allOption) {
			if (option.getText().trim().equals(expectedOption)) {
				//option can chon hien thi click vao
				if(!option.isDisplayed()) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", option);
					SleepInSecond(1);
				}
				option.click();
				break;
			}
		}
	}
	
	public void enterAndSelectItemInCusDropdown(String parentXpath, String textboxXpath, String childXpath, String expectedOption) {
		//Click vao de xo het cac option trong dropdown ra -> parentXpath
		driver.findElement(By.xpath(parentXpath)).click(); //click để ra textbox 
		
		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectedOption); 
		SleepInSecond(1);
		
		List<WebElement> allOption = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//Duyet qua tung option
		for (WebElement option : allOption) {
			if (option.getText().trim().equals(expectedOption)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", option);
				SleepInSecond(1);
				option.click();
				break;
			}
		}
	}

	public void enterAndTabItemInCusDropdown(String textboxXpath, String expectedOption) {
		//send string can tim vao texxtbox luon, k can click vi site này hien textboc luon
		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectedOption); 
		SleepInSecond(1);
		
		driver.findElement(By.xpath(textboxXpath)).sendKeys(Keys.TAB);
		
	}
	
	public void MultipleItemInCusDropdown(String parentXpath, String childXpath, String[] expectedOption) {
		//1. click vao dropdown de xo het ra option
		driver.findElement(By.xpath(parentXpath)).click(); 
		
		//2. kiem tra cac option co visiable het khong -> childXpath
		//lay het cac option vao list
		List<WebElement> allOption = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//Duyet qua het các phần tử cho đến khi thỏa mãn dkien trong list chọn
		for (WebElement childElement : allOption) {
			//mảng firstMonth là các month đc chọn
			for (String option : expectedOption) {
				if(childElement.getText().equals(option)) {
					//3. scroll đến option cần chọn (nếu option đã show hết rồi thì k cần scroll)
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					SleepInSecond(1);	
					
					//4. click vào option cần chọn
					childElement.click();
					SleepInSecond(1);
					
					//cho option đc chọn vào 1 list
					List<WebElement> optionSelected = driver.findElements(By.xpath(" //li[@class='selected']//input"));
					System.out.println("Option selected = " + optionSelected.size());
					if(expectedOption.length == optionSelected.size())
						break;
					
				}
			}
		}
		
	}

	public boolean areOptionSelected(String[] months) {
		
		List<WebElement> optionSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberSelected = optionSelected.size(); //có bn option đc chọn
		
		String allOptionSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Hien thi text da chon: " + allOptionSelectedText); //các tháng trong mảng firstmonth/secondmonths
		
		//kiểm tra số months dc chọn
		if(numberSelected <=3 && numberSelected > 0) {
			boolean status = true;
			for (String option : months) {
				if(!allOptionSelectedText.contains(option)) {
					status = false;
					return status;
				}
			}
			return status;
		}
		else if (numberSelected==12)
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
		else if (numberSelected > 3 && numberSelected < 12)
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberSelected + " of 12 selected']")).isDisplayed();
		else
			return false;
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