import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class hashMatchWithTxtInput {

    Hashtable<Integer, String> abbrTable = new Hashtable<>();

    public StringBuilder swap(StringBuilder inputString, Hashtable<String, String> table) {
        StringBuilder newStr = new StringBuilder();
        String[] wordArr = inputString.toString().split(" |,|\\.|!|\\?|\\(|\\)|\\{|\\}|\\[|\\]");
        for (String word : wordArr) {
            if (table.containsKey(word)) {
                word = table.get(word);
            }
            newStr.append(word + " ");
        }
        System.out.println(newStr);
        return newStr;
    }

    public static StringBuilder inputTweetFromTxt(String txtFilePath) {
        StringBuilder longTweets = new StringBuilder();
        try (Scanner tweets = new Scanner(new FileInputStream(txtFilePath));) {
            tweets.useDelimiter("");
            while (tweets.hasNext()) {
                String word = tweets.next();
                longTweets.append(word);
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
        return longTweets;
    }

    public static Hashtable<String, String> inputAbbrFromTxt(Hashtable<String, String> abbreviationMap,
            String abbrFilePath) {
        try (Scanner abbr = new Scanner(new FileInputStream(abbrFilePath));) {
            abbr.useDelimiter(",|-");
            for (int i = 0; i < 205; i++) {
                String abbrKey = abbr.next().trim();
                String abbrVal = abbr.next().trim();
                abbreviationMap.put(abbrKey, abbrVal);
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
        return abbreviationMap;
    }

    public static void main(String[] args) {
        // Start time
        var startTime = System.currentTimeMillis();

        hashMatchWithTxtInput test = new hashMatchWithTxtInput();
        Hashtable<String, String> abbreviationMap = new Hashtable<>();
        abbreviationMap = inputAbbrFromTxt(abbreviationMap, "./Sample_Txt_Abbr.txt");

        // running through ALL tweets
        for (int i = 1; i < 50; i++) {
            String filePath = "strings/string_" + i + ".txt";
            StringBuilder tweetsList = inputTweetFromTxt(filePath);
            test.swap(tweetsList, abbreviationMap);
        }

        // running through last segment of tweets
        // String filePath = "strings/string_49.txt";
        // StringBuilder tweetsList = inputTweetFromTxt(filePath);
        // test.swap(tweetsList, abbreviationMap);

        // Finish time
        var finishTime = System.currentTimeMillis();
        System.out.println("Total runtime for algorithm: " + (finishTime - startTime) + "ms");
    }
}