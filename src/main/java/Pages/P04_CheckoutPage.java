package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Utilities.Utility.generalWait;

public class P04_CheckoutPage {

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private WebDriver driver;

    public P04_CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public P04_CheckoutPage fillingInformationForm(String fName, String lName, String zip) {
        Utility.sendData(driver, firstName, fName);
        Utility.sendData(driver, lastName, lName);
        Utility.sendData(driver, postalCode, zip);
        return this;
    }

    public P05_overViewPage clickOnContinueButton() {
        Utility.clickOnElement(driver, continueButton);
        return new P05_overViewPage(driver);

    }

    public boolean verifyUrl(String expectedUrl) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (Exception e) {
            return false;
        }
        return true;

    }
}
