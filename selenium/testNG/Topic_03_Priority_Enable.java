package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_03_Priority_Enable {
	
	@Test (priority=1, enabled=true)
	public void User_01_Create_User() {
		
	}
	@Test(description="View User")
	public void User_02_View_User() {
		
	}
	@Test
	public void User_03_Modify() {
		
	}
  @Test
  public void User_04_Delete() {
	 
  }
}
