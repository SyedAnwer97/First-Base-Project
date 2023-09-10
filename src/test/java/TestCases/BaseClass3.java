package TestCases;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class BaseClass3 extends BeforeClassDemo {

	@Test(groups = { "Smoke", "Sanity", "Regression" })
	public synchronized void openingBrowserFirefox() throws InterruptedException {

		boolean DataAvailable = false;
		if (!DataAvailable)
			throw new SkipException("Skipping this exception");

	}

}
