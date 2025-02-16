package Listeners;

import Utilities.LogUtilities;
import Utilities.Utility;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.IOException;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokedMethod implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {

    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {//بقوله شوفلى كل ميسود تخلص شوفلى النتيجه بتاعتها ولو بقيت فاشله خدلى اسكرين ليها والاسم بتاعها هييقى نفس الاسم بتاع التست كيسز اللى شغاله
            LogUtilities.info("test case" + testResult.getName() + "failed");
            Utility.Shots(getDriver(), testResult.getName()); // output the screenshot validloginTc-2025-02-02-12-12-12pm
        }

    }
}
