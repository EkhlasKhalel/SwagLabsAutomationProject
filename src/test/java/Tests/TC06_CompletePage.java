package Tests;

import Pages.P01_LoginPage;
import Utilities.DataUtilities;
import Utilities.LogUtilities;
import Utilities.Utility;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtilities.getPropertyValue;

public class TC06_CompletePage {
    private final String FirstName = DataUtilities.getJsonData("information", "fName") + "-" + Utility.getTimeStamp();
    private final String LastName = DataUtilities.getJsonData("information", "lName") + "-" + Utility.getTimeStamp();
    private final String zipCode = new Faker().number().digits(5);

    public TC06_CompletePage() throws FileNotFoundException {
    }

    @BeforeMethod
    public void setup() throws IOException {
        setDriver(getPropertyValue("environment", "browser"));
        LogUtilities.info("chrome driver is opened");
        getDriver().get(getPropertyValue("environment", "Base_Url"));
        LogUtilities.info("page is redirected to url");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void finishingOrder() throws IOException {
        try {
            new P01_LoginPage(getDriver())
                    .enterUsername(DataUtilities.getJsonData("validLogin", "username"))
                    .enterPassword(DataUtilities.getJsonData("validLogin", "password"))
                    .clickOnLoginButton()
                    .addRandomProducts(2, 6)
                    .clickOnCartIcon().clickOnCheckOutButton().fillingInformationForm(FirstName, LastName, zipCode)
                    .clickOnContinueButton().clickOnFinishButton();
            LogUtilities.info(FirstName + " " + LastName + " " + zipCode);
            //  Assert.assertTrue(new P06_completePage(getDriver().checkThanksMessage()));
        } catch (Exception e) {
            e.printStackTrace(); // طباعة الخطأ في الـ Console
            Assert.fail("❌ حدث خطأ أثناء تنفيذ الاختبار: " + e.getMessage());
        }
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
