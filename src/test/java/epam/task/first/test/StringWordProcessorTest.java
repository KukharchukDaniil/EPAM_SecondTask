package epam.task.first.test;

import epam.task.first.processor.StringWordProcessor;
import epam.task.first.processor.WordProcessorException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StringWordProcessorTest {
    private static final List<String> TEST_DATA_ONE = Arrays.asList("....In this tutorial, we will show you how to use",
            " the classic log4j to log a debug or error message in a Java application.");

    @Test
    public void testStringWordProcessorWhenTextIsValid() throws WordProcessorException {
        //given
        StringWordProcessor wordProcessor = new StringWordProcessor();
        List<String> expected = Arrays.asList("....IZ tZis tZtorial, wZ wZll sZow yZu hZw tZ uZe",
                " tZe cZassic lZg4j tZ lZg a dZbug oZ eZror mZssage iZ a JZva aZplication.");
        //when
        List<String> actual = wordProcessor.process(TEST_DATA_ONE,1,'Z');

        //than
        Assert.assertEquals(expected,actual);

    }
    @Test(expected = WordProcessorException.class)
    public void testStringWordProcessorWhenIndexIsInvalid() throws WordProcessorException {
        //given
        StringWordProcessor wordProcessor = new StringWordProcessor();
        //when
        List<String> actual = wordProcessor.process(TEST_DATA_ONE,-3,'Z');


    }
}
