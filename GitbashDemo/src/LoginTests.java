

import java.io.IOException;
import java.util.HashMap;

import org.iitwforce.selenium.mmppluto.lib.AppLibrary;
import org.iitwforce.selenium.mmppluto.lib.BaseClass;
import org.iitwforce.selenium.mmppluto.pages.HomePage;
import org.iitwforce.selenium.mmppluto.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends BaseClass {
	HashMap<String,String> expectedHMap= new HashMap<String,String>();
	HashMap<String,String> actualHMap= new HashMap<String,String>();	
	@Parameters({"username","password"})
	@Test(description="TC_001 Login with valid creds",enabled=false,groups= {"Sanity","Regression"})
	public void validateBooking_TC001(String username,String password)
	{
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = loginPage.login(username,password);
		Assert.assertTrue(homePage.getcurrentTitle().contains("home"));
	}
	@Parameters({"username","invpassword"})
	@Test(description="TC_002 Login with invalid creds",enabled=false,groups= {"Sanity","Regression"})
	public void validateBooking_TC002_invalid_creds(String username,String invpassword)
	{
		LoginPage loginPage = new LoginPage(driver);
	    loginPage.login(username,invpassword);
	    String actual = loginPage.getLoginErrMessage();
		String expected="Wrong username and password.";
		Assert.assertEquals(actual,expected);
	}
	
	@DataProvider(name="DP")
	public String[][] feedDP() throws IOException
	{
		String filePath = System.getProperty("user.dir")+ "//inputData.xlsx";
		String data[][] = AppLibrary.readXlsx(filePath);
		return data;
	}
	
	@Test(dataProvider="DP",description="TC_003 Login with valid creds via xlsx",groups= {"Sanity","Regression"})
	public void validateBooking_TC003_xlsx(String username,String password,String expectedMsg)
	{
		launchBrowser(pro.getProperty("url"));
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = loginPage.login(username,password);
		String actualTitle = homePage.getcurrentTitle();
//		String actualTitle="home";
		String expectedTitle=expectedMsg;
		System.out.println("Login to MMP is successful");
		Assert.assertEquals(actualTitle, expectedTitle);
		 
	}

}
