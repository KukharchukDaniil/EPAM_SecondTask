package epam.task.first.processor;

import java.util.List;

public interface WordProcessor {
    public List<String> process(final List<String> text, final int index, final char charToReplace) throws WordProcessorException;
}
