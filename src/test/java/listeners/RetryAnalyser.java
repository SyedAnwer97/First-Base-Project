package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {
	
	int failedCount = 0;
	int Limit = 1; 

	public boolean retry(ITestResult result) {
	if(failedCount<Limit) {
		failedCount++;
		return true;
	}
		return false;
	}

}
