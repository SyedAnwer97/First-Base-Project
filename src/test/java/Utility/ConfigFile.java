package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigFile {

	Properties properties;

	public ConfigFile() {
		File file = new File("./Configuration/config.properties");
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInputStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String webURL() {
		String URL = properties.getProperty("url");
		return URL;
	}

	public String Customer() {
		String Customer = properties.getProperty("Customer");
		return Customer;
	}

	public String CustomerPassword() {
		String CustomerPassword = properties.getProperty("CustomerPassword");
		return CustomerPassword;
	}

	public String user() {
		String user = properties.getProperty("user");
		return user;
	}

	public String userPassword() {
		String userPassword = properties.getProperty("userPassword");
		return userPassword;
	}

	public String langauge() {
		String langauge = properties.getProperty("langauge");
		return langauge;
	}
	
	public String TestName() {
		String TesterName = properties.getProperty("Tester");
		return TesterName;
	}

}
