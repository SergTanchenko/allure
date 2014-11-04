package my.company.steps;


import my.company.lib.AbstractSteps;
import org.jbehave.core.annotations.Given;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Serg on 23.10.2014.
 */
public class SimpleSteps extends AbstractSteps {

    public SimpleSteps(WebDriver driver) {
        super(driver);
    }

    @Given("user open google.com")
    @Step
    public void userOpensUrl() {
        webDriver.get("http://google.com");
    }
}
