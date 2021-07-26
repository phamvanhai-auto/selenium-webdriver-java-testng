package testNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

@Listeners(ReportListener.class)
public class Topic_09_Dependency {
	
  @Test
  public void TC_01() {
	    
	  Assert.assertTrue(false);

  }
  
  @Test(dependsOnMethods = "TC_01")
  public void TC_02() {
	  
	  System.out.println("Run Ok after TC_01 Pass");
  }
}
