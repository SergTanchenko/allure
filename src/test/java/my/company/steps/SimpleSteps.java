package my.company.steps;


import my.company.lib.AbstractSteps;
import org.jbehave.core.annotations.Given;
import org.openqa.selenium.WebDriver;

/**
 * Created by Serg on 23.10.2014.
 */
public class SimpleSteps extends AbstractSteps {

    public SimpleSteps(WebDriver driver) {
        super(driver);
    }

    @Given("user open google.com")
    public void userOpensUrl() {
        webDriver.navigate().to("google.com");
    }
}
