package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {

    private final WebDriver driver;
    //variables- locators
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");

    public Utility loginErrorMessage;

    // constructor
    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Actions > wait( element to be clickable or present or visible)- scroll-find element-sendData

    public P01_LoginPage enterUsername(String usernameText) {
        Utility.sendData(driver, username, usernameText);
        return this;
    }

    public P01_LoginPage enterPassword(String passwordText) {
        Utility.sendData(driver, password, passwordText);
        return this;
    }

    public P02_LandingPage clickOnLoginButton() {
        Utility.clickOnElement(driver, loginButton);
        return new P02_LandingPage(driver);
    }
    //Validations - Assertions

    public boolean verifyErrorMessage(String expectedMessage, By locator) {
        String actualMessage = driver.findElement(locator).getText();
        return actualMessage.equals(expectedMessage);
    }

    public boolean assertLoginTc(String expectedUrl) {
        return driver.getCurrentUrl().equals(expectedUrl);

    }

}


