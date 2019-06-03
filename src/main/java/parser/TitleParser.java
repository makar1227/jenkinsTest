package parser;

public class TitleParser {
    public static String getShowNameFromTitle(String title) {
        int index = title.indexOf(" - Season");
        return title.substring(0, index).trim();
    }
}
