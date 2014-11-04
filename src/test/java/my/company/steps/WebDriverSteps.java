package my.company.steps;

import my.company.lib.AbstractSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 28.10.13
 */
public class WebDriverSteps extends AbstractSteps {
    public WebDriverSteps(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public void openMainPage() {
        webDriver.get("http://ya.ru");
    }

    @Step
    public void search(String text) {
        webDriver.findElement(By.id("text")).sendKeys(text);
        webDriver.findElement(By.className("b-form-button__input")).submit();
    }

    @Attachment(type = "image/png")
    public File makeScreenshot() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
    }

    public void quit() {
        webDriver.quit();
    }
}
