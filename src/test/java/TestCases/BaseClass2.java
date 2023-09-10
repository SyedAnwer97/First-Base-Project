package TestCases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import PageObjectModel.CustomerLoginPage;
import PageObjectModel.UserLoginPage;

public class BaseClass2 extends BeforeClassDemo {

	@Test(groups = {"Smoke","Sanity"})
	public void openingBrowserFirefox() throws InterruptedException {
		contextJava.getDriver().get(contextJava.getConfigFile().webURL());
		CustomerLoginPage customerLoginPage = new CustomerLoginPage(contextJava.getDriver());
		Select select = new Select(customerLoginPage.日本語);
		select.selectByVisibleText(contextJava.getConfigFile().langauge());
		customerLoginPage.username.sendKeys(contextJava.getConfigFile().Customer());
		customerLoginPage.password.sendKeys(contextJava.getConfigFile().CustomerPassword());
		customerLoginPage.login.click();
		Thread.sleep(2000);

		UserLoginPage loginPage = new UserLoginPage(contextJava.getDriver());
		loginPage.Username.sendKeys(contextJava.getConfigFile().user());
		loginPage.Password.sendKeys(contextJava.getConfigFile().userPassword());
		loginPage.Login.click();
		Thread.sleep(3000);
		assertEquals(false, true);
		//Assert.assertEquals(contextJava.getDriver().getTitle(), "UserLogin123");
	}

}
