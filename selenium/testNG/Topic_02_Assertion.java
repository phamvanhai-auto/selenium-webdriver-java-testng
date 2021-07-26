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

public class Topic_02_Assertion {
  @Test
  public void TC_01() {
	  
	  String fullName = "Auto FC";
	  
	  //1 tham sô
	  Assert.assertEquals(fullName, "Ha Noi");
	  
	  Assert.assertTrue(fullName.equals("HCM"), "No matching!");

  }
}
