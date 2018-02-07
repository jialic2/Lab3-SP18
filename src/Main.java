import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        // write your code here
        while (true) {
            System.out.println("Enter 1 - word count\n" +
                    "Enter 2 - unique word count ignore case\n" +
                    "Enter 3 - unique word count\n" +
                    "Enter 4 - exit");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            switch (input) {
                case "1":
                    while (true) {
                        System.out.print("Enter a word: ");
                        final String WORD = scanner.next();
                        if (WORD.equals("quit")) {
                            break;
                        }
                        System.out.println(countWord(WORD));
                    }
                    break;
                case "2":
                    System.out.println(countUniqueWordIgnoreCase());
                    break;
                case "3":
                    System.out.println(countUniqueWord());
                    break;
                case "4":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Enter again.");
            }
        }
    }

    public static int countWord(final String WORD) {
        String websiteAsString = urlToString("http://erdani.com/tdpl/hamlet.txt");
        websiteAsString = websiteAsString.replace(",", "");
        String[] lines = websiteAsString.split("\n");
        int count = 0;
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String word : words) {
                if (word.equalsIgnoreCase(WORD)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int countUniqueWordIgnoreCase() {
        String websiteAsString = urlToString("http://erdani.com/tdpl/hamlet.txt");
        websiteAsString = websiteAsString.replace(",", "");
        ArrayList<String> uniqueWords = new ArrayList<>();
        String[] lines = websiteAsString.split("\n");
        uniqueWords.add(lines[0].split(" ")[0]);
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String word : words) {
                boolean hasWord = false;
                for (String uniqueWord : uniqueWords) {
                    if (uniqueWord.equalsIgnoreCase(word)) {
                        hasWord = true;
                        break;
                    }
                }
                if (!hasWord) {
                    uniqueWords.add(word);
                }
            }
        }
        return uniqueWords.size();
    }

    public static int countUniqueWord() {
        String websiteAsString = urlToString("http://erdani.com/tdpl/hamlet.txt");
        websiteAsString = websiteAsString.replace(",", "");
        ArrayList<String> uniqueWords = new ArrayList<>();
        String[] lines = websiteAsString.split("\n");
        uniqueWords.add(lines[0].split(" ")[0]);
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String word : words) {
                if (!uniqueWords.contains(word)) {
                    uniqueWords.add(word);
                }
            }
        }
        return uniqueWords.size();
    }

    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }
}
