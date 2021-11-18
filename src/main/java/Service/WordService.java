package Service;
import Interface.WordInterface;
import Model.Word;
import javax.naming.Name;
import java.io.IOException;
import java.util.*;

public class WordService implements WordInterface {

    public String[] getSplitText(String textPage){
        return textPage.split("[\\s.,!?;:{}()\t\n\r\f]+");
    }

    @Override
    public void addWord(Word word, List<Word> words) {
        int maxId = 0;
        if (words.size() > 0){
            maxId = words.stream().max(Comparator.comparing(Word::getId)).orElse(null).getId();
        }
        words.add(new Word(maxId+1, word.getName(), word.getCount()));
    }

    @Override
    public void updateWord(Word word, List<Word> words) {
        Word findWord = getElemById(word.getId(), words);
        if (findWord != null)
            words.set(words.indexOf(findWord), word);
    }

    @Override
    public List<Word> getUniqueWordCount(String[] splitString){
        List<Word> words = new ArrayList<>();
        for(String t : splitString){
            Optional<Word> findWord = words.stream().filter(word-> word.getName().equals(t)).findFirst();
            if (findWord.isPresent()){
                updateWord(new Word(findWord.get().getId(), findWord.get().getName(), findWord.get().getCount()+1), words);
            } else {
                addWord(new Word(0, t,1), words);
            }
        }
        return words;
    }

    @Override
    public Word getMostFrequentWord(List<Word> uniqueWord){
        return uniqueWord.stream().max(Comparator.comparing(Word::getCount)).orElse(null);
    }

    @Override
    public Word getElemById(int id, List<Word> words) {
        return words.stream().filter(w -> w.getId() == id).findFirst().orElse(null);
    }

    @Override
    public int getTotalWordCount(List<Word> uniqueWord){
        return uniqueWord.stream().mapToInt(Word::getCount).sum();
    }

    //Реализация через hash map
   /* public HashMap<String, Integer> getUniqueWordCount(String[] splitString){
        HashMap<String, Integer> uniqueWord = new HashMap<>();
        for(String t : splitString){
            if (uniqueWord.containsKey(t)){
                uniqueWord.put(t, uniqueWord.get(t)+1);
            } else {
                uniqueWord.put(t,1);
            }
        }
        return uniqueWord;
    } */

    /* public void printUniqueWord (HashMap<String, Integer> uniqueWord) {
      Set<String> keys = uniqueWord.keySet();
      for (String key : keys){
          System.out.printf("Слово: %s Количество %d \n",key, uniqueWord.get(key));
      }
    } */
}
