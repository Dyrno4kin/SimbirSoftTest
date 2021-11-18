import Service.ParserService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ParserService htmlParserService = new ParserService();
        htmlParserService.getPageData("http://www.vk.com");
    }
}
