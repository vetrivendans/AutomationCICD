package SeleniumFrameworkDesign.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++; // Increment the retry counter
            return true;  // Indicate a retry
        }
        // Mark the test as failed after all retries
        result.setStatus(ITestResult.FAILURE);
        return false;  // Stop retrying
    }

    public int getRetryCount() {
        return retryCount; // Provide retry count for tracking
    }
}
