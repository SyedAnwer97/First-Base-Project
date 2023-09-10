package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserLoginPage {

	WebDriver driver;

	public UserLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='username']")
	@CacheLookup
	public WebElement Username;

	@FindBy(xpath = "//input[@id='password']")
	@CacheLookup
	public  WebElement Password;

	@FindBy(xpath = "//button[@id='login']")
	@CacheLookup
	public  WebElement Login;

	@FindBy(xpath = "//button[@id='logout']")
	@CacheLookup
	public  WebElement logout;

}
