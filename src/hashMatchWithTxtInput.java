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

    public StringBuilder swap(String inputString, Hashtable<String, String> table) {
        StringBuilder newStr = new StringBuilder();
        String[] wordArr = inputString.split(" |,|\\.|!|\\?|\\(|\\)|\\{|\\}|\\[|\\]");

        for (String word : wordArr) {
            if (table.containsKey(word)) {
                word = table.get(word);
            }
            newStr.append(word + " ");
        }
        System.out.println(newStr);
        return newStr;
    }

    public static String inputTweetFromTxt(String txtFilePath) {
        String longTweets = "";
        try (Scanner tweets = new Scanner(new FileInputStream(txtFilePath));) {
            tweets.useDelimiter("");
			while (tweets.hasNext()) {
				String line = tweets.next();
				longTweets += line;
			}
            System.out.println(longTweets);
        } catch (IOException e) {
            System.out.println("Error");
        }return longTweets;
    }

    public static void main(String[] args) {

        hashMatch test = new hashMatch();
        Hashtable<String, String> abbreviationMap = new Hashtable<>();

        // Call input from Excel function to receive input in list
        String tweetsList = inputTweetFromTxt("./Sample_Txt_Input.txt");

        // add some abbreviations and their corresponding phrases to the hash table
        abbreviationMap.put("ASAP", "As Soon As Possible");
        abbreviationMap.put("FYI", "For Your Information");
        abbreviationMap.put("ETA", "Estimated Time of Arrival");
        abbreviationMap.put("BTW", "By The Way");
        abbreviationMap.put("LOL", "Laugh out loud");
        abbreviationMap.put("IMO", "In my opinion");
        abbreviationMap.put("RN", "Right now");
        abbreviationMap.put("SUS", "Suspicious");
        abbreviationMap.put("FML", "F*** my life");
        abbreviationMap.put("GOAT", "Greatest of all time");

        // for (String tweet : tweetsList) {
            System.out.println("\nOriginal String: " + tweetsList);
            System.out.print("\nNew String: ");
            test.swap(tweetsList, abbreviationMap);
        // }
    }
}