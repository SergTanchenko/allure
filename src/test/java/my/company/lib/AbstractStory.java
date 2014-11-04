package my.company.lib;


import my.company.lib.utils.WebDriverFactory;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

/**
 * Created by Serg on 23.10.2014.
 */
public abstract class AbstractStory extends JUnitStory {

    protected WebDriver webDriver;

    public AbstractStory() {
        super();
        webDriver = WebDriverFactory.createInstance();
    }

    @Override
    public Configuration configuration() {
        Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "true");

        return new MostUsefulConfiguration()
                .usePendingStepStrategy(new FailingUponPendingStep())
                .useStoryReporterBuilder(
                        new StoryReporterBuilder().withFailureTrace(true).withDefaultFormats().withViewResources(viewResources)
                                .withFormats(Format.CONSOLE, Format.HTML, Format.XML));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), requiredSteps());
    }

    @Override
    public Embedder configuredEmbedder() {
        final EmbedderControls controls = new EmbedderControls()
                .doIgnoreFailureInStories(false)
                .doIgnoreFailureInView(true)
                .doGenerateViewAfterStories(true);

        final Embedder embedder = super.configuredEmbedder();
        embedder.useEmbedderControls(controls);

        return embedder;
    }

    public abstract Object[] requiredSteps();


    @Before
    public void setUp() {
    }

    @After
    public void after() {
        webDriver.quit();
    }

}
