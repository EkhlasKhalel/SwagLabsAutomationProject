package Tests;

import Listeners.CustomITestResult;
import Listeners.IInvokedMethod;
import Pages.P01_LoginPage;
import Utilities.DataUtilities;
import Utilities.LogUtilities;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtilities.getPropertyValue;

@Listeners({IInvokedMethod.class, CustomITestResult.class})
public class T02_LoginPageWithInvalidData {

    @BeforeMethod
    public void setup() throws IOException {
        setDriver(getPropertyValue("environment", "browser"));
        LogUtilities.info("chrome driver is opened");
        getDriver().get(getPropertyValue("environment", "Base_Url"));
        LogUtilities.info("page isnt redirected to url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void validLoginTc() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUsername(DataUtilities.getJsonData("validLogin", "invalid username"))
                .enterPassword(DataUtilities.getJsonData("validLogin", "password"))
                .clickOnLoginButton();
        // إنشاء كائن من صفحة تسجيل الدخول
        // P01_LoginPage loginPage = new P01_LoginPage(getDriver());

        // تنفيذ Assertion للتحقق من رسالة الخطأ
        Assert.assertTrue(new P01_LoginPage(getDriver()).verifyErrorMessage("Epic sadface: Username and password do not match any user in this service",
                By.xpath("//h3[@data-test='error']")));
    }


    @AfterMethod
    public void quit() {
        quitDriver();
    }

}