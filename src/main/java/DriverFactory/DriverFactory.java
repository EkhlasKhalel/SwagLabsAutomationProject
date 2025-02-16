package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(String browser) { //EDGE edge Edge
        switch (browser.toLowerCase()) { ///احول الكلمه لما تجيلى ل lowercase قبل ما اعمل check عليها
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driverThreadLocal.set(new ChromeDriver(options));
                break;
            case "firefox":
                FirefoxOptions options1 = new FirefoxOptions();
                options1.addArguments("--start-maximized");
                driverThreadLocal.set(new FirefoxDriver(options1));
                break;
            default:
                EdgeOptions options2 = new EdgeOptions();
                options2.addArguments("--start-maximized");
                driverThreadLocal.set(new EdgeDriver(options2));

        }
    }

    public static void quitDriver() {
        getDriver().quit();
        driverThreadLocal.remove();
    }
}
