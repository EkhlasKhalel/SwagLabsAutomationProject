package Listeners;

import Utilities.LogUtilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomITestResult implements ITestListener {

    public void onTestStart(ITestResult result) {
        LogUtilities.info("test case" + result.getName() + "started");
    }

    public void onTestSuccess(ITestResult result) {
        LogUtilities.info("test case" + result.getName() + "success");
    }
    public void onTestSkipped(ITestResult result) {
        LogUtilities.info("test case" + result.getName() +" skipped");
    }

}
