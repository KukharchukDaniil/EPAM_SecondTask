package epam.task.first.test;

import epam.task.first.processor.RegexWordProcessor;
import epam.task.first.processor.WordProcessorException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RegexWordProcessorTest {
    private static final List<String> TEST_DATA_ONE = Arrays.asList("....In this tutorial, we will show you how to use",
            " the classic log4j to log a debug or error message in a Java application.");

    @Test
    public void testRegexWordProcessorWhenIndexIsValid() throws WordProcessorException {
        //given
        RegexWordProcessor wordProcessor = new RegexWordProcessor();
        List<String> expected = Arrays.asList("....IZ tZis tZtorial, wZ wZll sZow yZu hZw tZ uZe",
                " tZe cZassic lZg4j tZ lZg a dZbug oZ eZror mZssage iZ a JZva aZplication.");
        //when
        List<String> actual = wordProcessor.process(TEST_DATA_ONE,1,'Z');

        //than
        Assert.assertEquals(expected,actual);

    }
    @Test(expected = WordProcessorException.class)
    public void testRegexWordProcessorWhenIndexIsInvalid() throws WordProcessorException {
        //given
        RegexWordProcessor wordProcessor = new RegexWordProcessor();
        //when
        List<String> actual = wordProcessor.process(TEST_DATA_ONE,-3,'Z');


    }
}
