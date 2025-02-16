package Pages;

import Utilities.LogUtilities;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_CartPage {
    private final WebDriver driver;
    private final By pricesOfProducts = By.xpath("//button[text()='Remove']//preceding-sibling::div[@class='inventory_item_price']");
    private final By checkoutButton = By.id("checkout");

    public P03_CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTotalPriceOfProducts() {
        try {
            List<WebElement> prices = driver.findElements(pricesOfProducts);
            double totalPrice = 0.0;
            for (WebElement price : prices) {
                String priceText = price.getText().replace("$", "");
                totalPrice += Double.parseDouble(priceText);
            }
            LogUtilities.info(("totalPrice" + totalPrice));
            return String.format("%.2f", totalPrice);
        } catch (Exception e) {
            return "0";
        }
    }

    public boolean comparePrices(String expectedPrice) {
        return getTotalPriceOfProducts().equals(expectedPrice);
    }

    public P04_CheckoutPage clickOnCheckOutButton() {

        Utility.clickOnElement(driver, checkoutButton);
        return new P04_CheckoutPage(driver);
    }
}
