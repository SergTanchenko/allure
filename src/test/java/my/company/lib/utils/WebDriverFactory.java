package my.company.lib.utils;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author sergiitanchenko
 */
public class WebDriverFactory {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebDriverFactory.class);

    private WebDriverFactory() {
    }

    public static WebDriver createInstance() {
        WebDriver driver = null;
        Browsers browser = Browsers.getCurrentOr(Browsers.CHROME);
        switch (browser) {
            case CHROME:
                driver = getChromeInstance();
                break;
        }

        if (driver != null) {
            driver.manage().window().setPosition(new Point(0, 0));
        }

        return driver;
    }

    private static WebDriver getChromeInstance() {

        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(System.getProperty("user.home") + "/" + getChromeDriverName()))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            LOGGER.error("Chrome Service wasn't started! \n" + e.getMessage());
        }

        return new ChromeDriver(service);
    }

    private static String getChromeDriverName() {
        if (OsUtils.isWindows()) {
            return "chromedriver.exe";
        } else if (OsUtils.isUnix()) {
            return "chromedriver";
        } else {
            throw new IllegalArgumentException("Operating system wasn't identified");
        }
    }
}
