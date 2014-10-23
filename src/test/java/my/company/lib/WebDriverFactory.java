package my.company.lib;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			driver.manage().window().setSize(new Dimension(1024, 768));
		}

		return driver;
	}

	private static WebDriver getChromeInstance() {
		ChromeDriverService service = new ChromeDriverService.Builder()
		  .usingDriverExecutable(new File(System.getProperty("user.home") + "/chromedriver"))
		  .usingAnyFreePort()
		  .build();
		try {
			service.start();
		}
		catch (IOException e) {
			LOGGER.error("Chrome Service wasn't started! \n" + e.getMessage());
		}

		return new ChromeDriver(service);
	}
}
