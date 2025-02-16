package Pages;

import Utilities.LogUtilities;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

import static Utilities.Utility.generalWait;

public class P02_LandingPage {

    private final WebDriver driver;
    private final By addToCartButtonForAllProducts = By.xpath("//button[contains(@class,'btn_inventory')]");
    private final By numberOfProductsOnCartIcon = By.className("shopping_cart_badge");
    private final By numberOfSelectedProducts = By.xpath("//button[.='Remove']");
    private final By CartIcon = By.className("shopping_cart_link");
    private final By pricesOfSelectedProducts = By.xpath("//button[text()='Remove']//preceding-sibling::div[@class='inventory_item_price']");

    //constructor
    public P02_LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    //Actions
    public P02_LandingPage addAllProductsToCart() {
        List<WebElement> allProducts = driver.findElements(addToCartButtonForAllProducts);//هنا بيرجعلى ال 6 عناصر بتوعى
        //علشان اقدر اعمل click على كل العناصر دى محتاج اعمل loop تلف على كل العناصر بتاعتى وابدا ان انا اضغط عليهم
        LogUtilities.info("number of all products:" + allProducts.size());
        for (int i = 1; i <= allProducts.size(); i++) {
            By addToCartButtonForAllProducts = By.xpath("(//button[contains(@class,'btn_inventory')])[" + i + "]"); //dynamic locator
            Utility.clickOnElement(driver, addToCartButtonForAllProducts);
        }
        return this;
    }

    public String getnumberOfProductsOnCartIcon() {
        try {
            LogUtilities.info("number of products on cart :" + Utility.getText(driver, numberOfProductsOnCartIcon));
            return Utility.getText(driver, numberOfProductsOnCartIcon); //exception >> no such expection
        } catch (Exception e) {
            LogUtilities.error(e.getMessage());
            return "0";
        }
    }

    public String getnumberOfSelectedProducts() {
        try {
            List<WebElement> selectedProducts = driver.findElements(numberOfSelectedProducts);
            LogUtilities.info("numberOfSelectedProducts:" + String.valueOf(selectedProducts.size()));
            return String.valueOf(selectedProducts.size());  //exception >> no such expection
        } catch (Exception e) {
            LogUtilities.error(e.getMessage());
            return "0";
        }
    }

    public P02_LandingPage addRandomProducts(int numberOfProductNeeded, int totalNumberOfProducts) {
        Set<Integer> randomNumbers = Utility.generateUniqueNumber(numberOfProductNeeded, totalNumberOfProducts);
        for (int random : randomNumbers) {
            LogUtilities.info("randomNumber" + random);
            By addToCartButtonForAllProducts = By.xpath("(//button[contains(@class,'btn_inventory')])[" + random + "]"); //dynamic locator
            Utility.clickOnElement(driver, addToCartButtonForAllProducts);

        }
        return this;
    }

    public String getTotalPriceOfSelectedProducts() {
        try {
            List<WebElement> prices = driver.findElements(pricesOfSelectedProducts);
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

    public boolean comparingNumberOfSelectedProductsWithCart() {
        return getnumberOfProductsOnCartIcon().equals(getnumberOfSelectedProducts());
    }

    public P03_CartPage clickOnCartIcon() {
        Utility.clickOnElement(driver, CartIcon);
        return new P03_CartPage(driver);
    }

    public boolean verifyCartPageUrl(String expectedUrl) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (Exception e) {
            return false;
        }
        return true;

    }


}
