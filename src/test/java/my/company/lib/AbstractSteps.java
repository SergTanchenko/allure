package my.company.lib;

import org.openqa.selenium.WebDriver;

/**
 * Created by Serg on 23.10.2014.
 */
public abstract class AbstractSteps {
    protected WebDriver webDriver;

    public AbstractSteps(final WebDriver driver) {
        webDriver = driver;
    }
}
