package Service;
import Model.Word;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class ParserService {
    WordService wordService = new WordService();
    private static final Logger log = Logger.getLogger( ParserService.class.getName() );

    public ParserService() throws IOException {
        FileHandler fh = new FileHandler("log.txt", true);
        SimpleFormatter sf = new SimpleFormatter();
        fh.setFormatter(sf);
        log.addHandler(fh);
    }

    public String getTextPage(String URL){
        try {
            Document doc = Jsoup.connect(URL).get();
            log.log( Level.INFO, "Получаем данные страницы: " + URL);
            return doc.text();
        }catch (Exception e)
        {
            log.log( Level.WARNING, "Не удалось получить данные станицы:" + URL + e.getMessage());
        }
        return null;
    }

    public void getPageData(String URL){
        long start = System.currentTimeMillis();
        String textPage = getTextPage(URL);
        textPage = textPage.toLowerCase(Locale.ROOT);
        String[] splitText = wordService.getSplitText(textPage);

        List<Word> uniqueWord = wordService.getUniqueWordCount(splitText);
        uniqueWord = uniqueWord.stream().sorted(Comparator.comparingInt(Word::getCount).reversed()).collect(Collectors.toList());
        uniqueWord.forEach(System.out::println);

        int countWords = wordService.getTotalWordCount(uniqueWord); //Впринципе splitText.length даст тоже самое
        Word frequentWord = wordService.getMostFrequentWord(uniqueWord);
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;

        System.out.println("Прошло времени, мс: " + elapsed);
        System.out.printf("Сайт: %s Всего слов: %d, из них уникальных: %d, самое часто встречающееся слово: %s количество: %d \n ",
                URL ,countWords, uniqueWord.size()+1, frequentWord.getName(), frequentWord.getCount());
        log.log( Level.INFO, "Сайт: " + URL
                            +" Всего слов: " + countWords + ", из них уникальных: " + (uniqueWord.size()+1)
                            + ", самое часто встречающееся слово: " + frequentWord.getName() + " количество: " + frequentWord.getCount() + "\n ");
    }
}
