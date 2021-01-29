package epam.task.first.processor;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringWordProcessor implements WordProcessor {


    private static final Logger LOGGER = Logger.getLogger(StringWordProcessor.class);
    private static final String DELIMITERS = " .;!,?:";

    private ArrayList<String> getChangedWords(final String stringOfText, final int index, final char charToReplace) {
        ArrayList<String> words = new ArrayList<String>();
        StringTokenizer stringTokenizer = new StringTokenizer(stringOfText, DELIMITERS);
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            StringBuilder word = new StringBuilder(token);
            if (index < word.length()) {
                word.setCharAt(index, charToReplace);
            }
            words.add(new String(word));
        }
        return words;
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

    public List<String> process(final List<String> text, final int index, final char charToReplace) throws WordProcessorException {
        LOGGER.info("Text process started");
        List<String> resultText = new ArrayList<String>();
        try {
            for (String stringOfText : text) {
                String resultString = getChangedString(stringOfText, index, charToReplace);
                resultText.add(resultString);
            }
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WordProcessorException(e.getMessage(), e);
        }
        LOGGER.info("Text process finished");
        return resultText;
    }

}
