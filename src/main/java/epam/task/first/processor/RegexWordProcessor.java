package epam.task.first.processor;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RegexWordProcessor implements WordProcessor {

    private static final String REGEX_PATTERN = "\\s+|,\\s*|\\.\\s*";
    private static final Logger LOGGER = Logger.getLogger(RegexWordProcessor.class);

    private List<String> getChangedWords(final String stringOfText, final int index, final char charToReplace) {
        List<StringBuilder> bufferList = new ArrayList<StringBuilder>();
        String[] words = stringOfText.split(REGEX_PATTERN);

        for (String word : words) {
            bufferList.add(new StringBuilder(word));
        }
        for (StringBuilder builder : bufferList) {
            if (index < builder.length()) {
                builder.setCharAt(index, charToReplace);
            }
        }
        List<String> resultList = new ArrayList<String>();
        for (StringBuilder stringBuilder : bufferList) {
            resultList.add(new String(stringBuilder));
        }
        return resultList;
    }

    private String getChangedString(final String stringOfText, final int index, final char charToReplace) {

        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        //Getting transformed words list
        List<String> words = getChangedWords(stringOfText, index, charToReplace);

        //Getting changed string with delimiters' sequence being saved
        Integer wordsListCounter = 0;
        StringBuilder resultString = new StringBuilder();
        Integer stringLength = stringOfText.length();
        for (int i = 0; i < stringLength; i++) {
            char currentChar = stringOfText.charAt(i);
            if (Character.isLetterOrDigit(currentChar)) {
                String currentWord = words.get(wordsListCounter++);
                resultString.append(currentWord);
                i += currentWord.length() - 1;
            } else {
                resultString.append(currentChar);
            }
        }
        return new String(resultString);
    }

    public List<String> process(final List<String> text, final int index, final  char charToReplace) throws WordProcessorException {
        LOGGER.info("Regex word processor started");
        List<String> resultList = new ArrayList<String>();
        try {
            for (String string : text) {
                String changedString = getChangedString(string, index, charToReplace);
                resultList.add(changedString);
            }
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WordProcessorException(e.getMessage(), e);
        }
        LOGGER.info("Regex word processor finished");
        return resultList;
    }
}
