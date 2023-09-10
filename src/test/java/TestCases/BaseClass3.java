package TestCases;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class BaseClass3 extends BeforeClassDemo {

	@Test(groups = {"Smoke","Sanity","Regression"})
	public void openingBrowserFirefox() throws InterruptedException {
		/*
		 * contextJava.getDriver().get(contextJava.getConfigFile().webURL());
		 * CustomerLoginPage customerLoginPage = new
		 * CustomerLoginPage(contextJava.getDriver()); Select select = new
		 * Select(customerLoginPage.日本語);
		 * select.selectByVisibleText(contextJava.getConfigFile().langauge());
		 * customerLoginPage.username.sendKeys(contextJava.getConfigFile().Customer());
		 * customerLoginPage.password.sendKeys(contextJava.getConfigFile().
		 * CustomerPassword()); customerLoginPage.login.click(); Thread.sleep(2000);
		 * 
		 * UserLoginPage loginPage = new UserLoginPage(contextJava.getDriver());
		 * loginPage.Username.sendKeys(contextJava.getConfigFile().user());
		 * loginPage.Password.sendKeys(contextJava.getConfigFile().userPassword());
		 * loginPage.Login.click();
		 * Assert.assertEquals(contextJava.getDriver().getTitle(), "UserLogin");
		 */
		 boolean DataAvailable=false;
	      if(!DataAvailable)
	      throw new SkipException("Skipping this exception");
	      System.out.println("Executed Successfully");
		
	}

}
