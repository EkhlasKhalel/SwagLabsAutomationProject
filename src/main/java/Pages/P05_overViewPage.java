package Pages;

import Utilities.LogUtilities;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.DecimalFormat;

public class P05_overViewPage {

    private final WebDriver driver;

    private final By subTotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By total = By.className("summary_total_label");
    private final By finishButton = By.id("finish");


    public P05_overViewPage(WebDriver driver) {
        this.driver = driver;
    }

    public float getSubTotal() {
        return Float.parseFloat(driver.findElement(subTotal).getText().replace("Item total: $", ""));
    }

    public float getTax() {
        return Float.parseFloat(driver.findElement(tax).getText().replace("Tax: $", ""));
    }

    public float getTotal() {
        LogUtilities.info("Actual total price: " + driver.findElement(total).getText().replace("Total: $", ""));
        return Float.parseFloat(driver.findElement(total).getText().replace("Total: $", ""));
    }

    public String calculateTotalPrice() {
        float subTotal = getSubTotal();
        float tax = getTax();
        float total = (subTotal + tax);
        LogUtilities.info(" Calculated total price: " + new DecimalFormat("##.00").format(total));
        return String.valueOf(new DecimalFormat("##.00").format(total));
    }

    public boolean compareTotalPrice() {
        //return getSubTotal() == subTotal && getTax() == tax && getTotal() == total;
        return calculateTotalPrice().equals(String.valueOf(getTotal()));
    }

    public P06_completePage clickOnFinishButton() {
        Utility.clickOnElement(driver, finishButton);
        // driver.findElement(finishButton).click();
        return new P06_completePage(driver);
    }

}
