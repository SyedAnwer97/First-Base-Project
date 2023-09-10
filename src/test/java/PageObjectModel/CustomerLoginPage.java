package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerLoginPage {

	WebDriver driver;

	public CustomerLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//select[@id='mySelect']")
	@CacheLookup
	public WebElement 日本語;
	@FindBy(xpath = "//input[@id='username']")
	@CacheLookup
	public WebElement username;
	@FindBy(xpath = "//input[@id='password']")
	@CacheLookup
	public WebElement password;
	@FindBy(xpath = "//button[@id='logmein']")
	@CacheLookup
	public WebElement login;

}
