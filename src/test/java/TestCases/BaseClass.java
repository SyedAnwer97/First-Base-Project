package TestCases;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjectModel.CustomerLoginPage;
import PageObjectModel.UserLoginPage;

public class BaseClass extends BeforeClassDemo {

	@Test(groups = { "Smoke" })
	public void openingBrowserChrome() throws InterruptedException {
		//extentTest.info(MarkupHelper.createLabel("The Test is started", ExtentColor.CYAN));

		contextJava.getDriver().get(contextJava.getConfigFile().webURL());
		CustomerLoginPage customerLoginPage = new CustomerLoginPage(contextJava.getDriver());
		Select select = new Select(customerLoginPage.日本語);
		select.selectByVisibleText(contextJava.getConfigFile().langauge());
		customerLoginPage.username.sendKeys(contextJava.getConfigFile().Customer());
		customerLoginPage.password.sendKeys(contextJava.getConfigFile().CustomerPassword());
		customerLoginPage.login.click();
		Thread.sleep(2000);

		//extentTest.info(MarkupHelper.createLabel("The Test is Mid", ExtentColor.CYAN));

		UserLoginPage loginPage = new UserLoginPage(contextJava.getDriver());
		loginPage.Username.sendKeys(contextJava.getConfigFile().user());
		loginPage.Password.sendKeys(contextJava.getConfigFile().userPassword());
		loginPage.Login.click();
		Thread.sleep(5000);
		Assert.assertEquals(contextJava.getDriver().getTitle(), "alfaDOCK");

		//extentTest.info(MarkupHelper.createLabel("The Test is end", ExtentColor.CYAN));

	}

}
