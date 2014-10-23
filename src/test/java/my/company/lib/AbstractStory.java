package my.company.lib;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
import org.jbehave.core.steps.ParameterConverters;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

/**
 * Created by Serg on 23.10.2014.
 */
public abstract class AbstractStory extends JUnitStory {

    protected final WebDriver webDriver;

    public AbstractStory() {
		webDriver = WebDriverFactory.createInstance();
    }

	@Override
	public Configuration configuration() {
		Properties viewResources = new Properties();
		viewResources.put("decorateNonHtml", "true");

		return new MostUsefulConfiguration()
		  .usePendingStepStrategy(new FailingUponPendingStep())
		  .useParameterConverters(new ParameterConverters().addConverters(customConverters()))
		  .useStoryReporterBuilder(
			new StoryReporterBuilder().withFailureTrace(true).withDefaultFormats().withViewResources(viewResources)
			  .withFormats(Format.CONSOLE, Format.HTML, Format.XML));
	}

	private ParameterConverters.ParameterConverter[] customConverters() {
		final List<ParameterConverters.ParameterConverter> converters = new ArrayList<>();
		// Custom date pattern
		converters.add(new ParameterConverters.DateConverter(new SimpleDateFormat("dd-MM-yyyy")));
		// Custom boolean converter
		converters.add(new ParameterConverters.BooleanConverter("tradable", "nontradable"));
		// Custom table without header converter
		converters.add(new ParameterConverters.StringListConverter());
		converters.add(new ParameterConverters.EnumConverter());
		return converters.toArray(new ParameterConverters.ParameterConverter[converters.size()]);
	}

	public abstract Object[] requiredSteps();

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

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), requiredSteps());
	}


	@Before
	public void beforeStories() {
	}

	@After
	public void tearDown() {
		webDriver.quit();
	}

}
