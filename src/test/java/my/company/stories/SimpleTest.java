package my.company.stories;

import my.company.lib.AbstractStory;
import my.company.steps.SimpleSteps;

/**
 * Created by Serg on 22.10.2014.
 */
public class SimpleTest extends AbstractStory {

    public SimpleTest() {
        super();
    }

    @Override
    public Object[] requiredSteps() {
        return new Object[]{
                new SimpleSteps(webDriver)
        };
    }


}
