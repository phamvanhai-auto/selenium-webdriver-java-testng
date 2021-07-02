package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_05_Web_Element_Method {
	WebDriver driver;
///
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("");
	}
	
	@Test
	public void TC_01_Web_Element() {
		//Muon thao tac element thi pai tim element trc
		
		//tim 1 element
		driver.findElement(By.xpath(""));
		
		//tim list element
		driver.findElements(By.id(""));
		
		//Neu thao tac voi element 1 lan thi k can khai bao bien
		
		//Neu nhieu lan thi nen khai bao
		WebElement searchTextbox = driver.findElement(By.id(""));
		searchTextbox.clear();
		searchTextbox.sendKeys("abc");
		
		//De xem co bn element thoa man dkien, verify so luong element tra ve ntn
		//thao tac voi tat ca cac loai element giong nhau trong 1 page
		List<WebElement> checkboxs = driver.findElements(By.xpath("//div[@class='inputs']/input[not(@type='checkbox')]"));
		
		//lay ra so luong
		checkboxs.size();
		
		Assert.assertEquals(checkboxs.size(), 6);
		
		WebElement singleElement = driver.findElement(By.className(""));
		
		//textbox/TextArea/Editable dropdown
		singleElement.clear();
		singleElement.sendKeys("");
		
		//Button/Link/Radio/Checkbox/Custom Dropbox
		singleElement.click();
		
		//Cac ham có tiền tố bắt đầu bằng get luôn trả về dữ liệu
		//getTitle/getCurrentURL...
		singleElement.getAttribute("value");
		
		//lấy ra giá trị của các thuộc tính css - thường dùng để test GUI
		//Font/Size/Color/Background
		
		singleElement.getCssValue("background-color"); //return #4ab2f1
		
		//lấy ra tọa độ của element so với page hiện tại (get góc bên ngoài element)
		singleElement.getLocation();
		
		//lấy ra kích thước của element (rông x cao) > get góc bên trong element
		singleElement.getSize();
		
		//location+size
		singleElement.getRect();
		
		//chup hinh lỗi > dựa vào HTLM report
		singleElement.getScreenshotAs(OutputType.FILE);
		
		//get Tagname, nếu locator kp xpath mà là ID/Class/...
		//từ 1 element tagname > lấy ra đc cái tagname truyền vào cho locator khác
		String searchButtonTagname = singleElement.getTagName();
		searchTextbox = driver.findElement(By.xpath("//" + searchButtonTagname + "[@class='input']"));
	
		//lấy text của element (Header/Link/Message...)
		singleElement.getText();
		
		//các hàm có tiền tố isXXX thì trả về kiểu boolean (100%)  
		//true/false
		//kiểm tra xem 1 element là hiển thị cho ng dùng thao tác hay k
		
		singleElement.isDisplayed();
		singleElement.isEnabled();
		singleElement.isSelected();
		
		//no thay cho hành vi enter vào textbox/click vào button
		singleElement.submit();
		
		
		
		
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