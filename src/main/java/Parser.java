import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Parser {
    WordController wordController = new WordController();
    private static final Logger log = Logger.getLogger( Parser.class.getName() );

    public Parser() throws IOException {
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

    public void getPageData(String URL) throws IOException {
        long start = System.currentTimeMillis();
        String textPage = getTextPage(URL);
        String[] splitText = wordController.getSplitText(textPage);

        List<Word> uniqueWord = wordController.getUniqueWordCount(splitText);
        uniqueWord.forEach(System.out::println);

        int countWords = wordController.getTotalWordCount(uniqueWord); //Впринципе splitText.length даст тоже самое
        Word frequentWord = wordController.getMostFrequentWord(uniqueWord);
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
