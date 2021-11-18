package Interface;
import Model.Word;

import java.util.List;

public interface WordInterface {
    void addWord(Word word, List<Word> words);

    void updateWord (Word word, List<Word> words);

    List<Word> getUniqueWordCount(String[] splitString);

    Word getMostFrequentWord(List<Word> words);

    Word getElemById(int id, List<Word> words);

    int getTotalWordCount(List<Word> words);
}
