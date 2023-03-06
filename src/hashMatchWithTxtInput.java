import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class hashMatchWithTxtInput {

    Hashtable<Integer, String> abbrTable = new Hashtable<>();

    public void buildHashTable(String[] abbrList) {
        for (String word : abbrList) {
            int key = word.hashCode();
            // we can use hashCode or not, can also just use the string as the key (as is
            // done in the sample below)
            abbrTable.put(key, word);
        }
    }

    public StringBuilder swap(StringBuilder inputString, Hashtable<String, String> table) {
        StringBuilder newStr = new StringBuilder();
        String[] wordArr = inputString.toString().split(" |,|\\.|!|\\?|\\(|\\)|\\{|\\}|\\[|\\]");
        for (String word : wordArr) {
            if (table.containsKey(word.trim())) {
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
        }return longTweets;
    }

    public static Hashtable<String, String> inputAbbrFromTxt(Hashtable<String, String> abbreviationMap, String abbrFilePath) {
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

        // Call input from Txt function to receive input in list
        StringBuilder tweetsList = inputTweetFromTxt("./Sample_Txt_Input.txt");

        // Call input from Txt abbreviation list
        abbreviationMap = inputAbbrFromTxt(abbreviationMap,"./Sample_Txt_Abbr.txt");

        // for (String tweet : tweetsList) {
            System.out.println("\nOriginal String: " + tweetsList);
            System.out.print("\nNew String: ");
            test.swap(tweetsList, abbreviationMap);
        // }

        // Finish time
        var finishTime = System.currentTimeMillis();
        System.out.println("Total runtime for algorithm: " + (finishTime - startTime) + "ms");
    }
}