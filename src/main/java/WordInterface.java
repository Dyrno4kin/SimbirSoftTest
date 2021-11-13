import java.util.List;
import java.util.Optional;

public interface WordInterface {
    public void addWord (Word word, List<Word> words);

    public void updateWord (Word word, List<Word> words);

    public List<Word> getUniqueWordCount(String[] splitString);

    public Word getMostFrequentWord(List<Word> words);

    public Word getElemById(int id, List<Word> words);

    public int getTotalWordCount(List<Word> words);
}
