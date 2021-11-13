import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser htmlParser = new Parser();
        htmlParser.getPageData("http://www.vk.com");
    }
}
