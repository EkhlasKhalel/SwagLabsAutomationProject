package Tests;

import Listeners.CustomITestResult;
import Listeners.IInvokedMethod;
import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Pages.P03_CartPage;
import Utilities.DataUtilities;
import Utilities.LogUtilities;
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
public class T03_CartPage {

    @BeforeMethod
    public void setup() throws IOException {
        setDriver(getPropertyValue("environment", "browser"));
        LogUtilities.info("chrome driver is opened");
        getDriver().get(getPropertyValue("environment", "Base_Url"));
        LogUtilities.info("page is redirected to url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void comparingPricesTc() throws IOException {
        String totalPrice = new P01_LoginPage(getDriver())
                .enterUsername(DataUtilities.getJsonData("validLogin", "username"))
                .enterPassword(DataUtilities.getJsonData("validLogin", "password"))
                .clickOnLoginButton()
                .addRandomProducts(2, 6)
                .getTotalPriceOfSelectedProducts();
        new P02_LandingPage(getDriver()).clickOnCartIcon();
        Assert.assertTrue(new P03_CartPage(getDriver()).comparePrices(totalPrice));
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }

}
