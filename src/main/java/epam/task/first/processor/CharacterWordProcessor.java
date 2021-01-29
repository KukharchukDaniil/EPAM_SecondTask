package epam.task.first.processor;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class CharacterWordProcessor implements WordProcessor {
    private static final Logger LOGGER = Logger.getLogger(CharacterWordProcessor.class);
    private String getChangedString(final char[] string, final int index, final  char charToReplace) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Integer wordPositionCounter = 0;
        char[] resultString = string.clone();
        for (int i = 0; i < resultString.length; i++) {
            boolean reachedTheWord = false;
            while (i < string.length && Character.isLetterOrDigit(string[i])) {
                if (wordPositionCounter == index) {
                    resultString[i] = charToReplace;
                }
                wordPositionCounter++;
                i++;
                reachedTheWord = true;
            }
            if (reachedTheWord) {
                wordPositionCounter = 0;
                i--;
            }
        }
        return new String(resultString);
    }
    public List<String> process(final List<String> text, final int index, final char charToReplace) throws WordProcessorException {
        LOGGER.info("Character word processor started");
        List<char[]> strings = new ArrayList<char[]>();
        for (String string : text) {
            strings.add(string.toCharArray());
        }
        List<String> resultText = new ArrayList<String>();
        try {
            for (char[] string : strings) {
                resultText.add(getChangedString(string, index, charToReplace));
            }
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WordProcessorException(e.getMessage(), e);
        }

        LOGGER.info("Character word processor finished");
        return resultText;
    }
}

