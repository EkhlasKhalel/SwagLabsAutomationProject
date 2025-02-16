package Tests;

import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Utilities.DataUtilities;
import Utilities.LogUtilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtilities.getPropertyValue;

public class TC02_LandingPage {
    @BeforeMethod
    public void setup() throws IOException {
        setDriver(getPropertyValue("environment", "browser"));
        LogUtilities.info("chrome driver is opened");
        getDriver().get(getPropertyValue("environment", "Base_Url"));
        LogUtilities.info("page is redirected to url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkingNumberOfSelectedProductsTc() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUsername(DataUtilities.getJsonData("validLogin", "username"))
                .enterPassword(DataUtilities.getJsonData("validLogin", "password"))
                .clickOnLoginButton()
                .addAllProductsToCart();
        Assert.assertTrue(new P02_LandingPage(getDriver()).comparingNumberOfSelectedProductsWithCart());
    }

    @Test
    public void addingRandomProductsToCart() throws FileNotFoundException {
        new P01_LoginPage(getDriver())
                .enterUsername(DataUtilities.getJsonData("validLogin", "username"))
                .enterPassword(DataUtilities.getJsonData("validLogin", "password"))
                .clickOnLoginButton().addRandomProducts(3, 6);
        Assert.assertTrue(new P02_LandingPage(getDriver()).comparingNumberOfSelectedProductsWithCart());
    }

    @Test
    public void clickOnCartIcon() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUsername(DataUtilities.getJsonData("validLogin", "username"))
                .enterPassword(DataUtilities.getJsonData("validLogin", "password"))
                .clickOnLoginButton().clickOnCartIcon();
        Assert.assertTrue(new P02_LandingPage(getDriver()).verifyCartPageUrl(DataUtilities.getPropertyValue("environment", "Cart_Url")));
    }


    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
