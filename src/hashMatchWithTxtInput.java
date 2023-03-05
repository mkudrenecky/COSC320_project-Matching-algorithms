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
        System.out.println("In swap function");
        for (String word : wordArr) {
            System.out.println("Entered for swap");
            if (table.containsKey(word)) {
                System.out.println("Match");
                word = table.get(word);
            }
            newStr.append(word + " ");
        }
        System.out.println(newStr + "???");
        System.out.println("!!!");
        return newStr;
    }

    public static String inputTweetFromTxt(String txtFilePath) {
        String longTweets = "";
        try (Scanner tweets = new Scanner(new FileInputStream(txtFilePath));) {
            tweets.useDelimiter("");
			while (tweets.hasNext()) {
				String word = tweets.next();
				longTweets += word;
			}
            System.out.println(longTweets);
        } catch (IOException e) {
            System.out.println("Error");
        }return longTweets;
    }

    public static Hashtable<String, String> inputAbbrFromTxt(Hashtable<String, String> abbreviationMap, String abbrFilePath) {
        try (Scanner abbr = new Scanner(new FileInputStream(abbrFilePath));) {
            abbr.useDelimiter(",|-");
			for (int i = 0; i < 10; i++) {
				String abbrKey = abbr.next();
                String abbrVal = abbr.next();
                abbreviationMap.put(abbrKey, abbrVal);
			}
            System.out.println("Abbr map: " + abbreviationMap);
        } catch (IOException e) {
            System.out.println("Error");
        }
        return abbreviationMap;
    }

    public static void main(String[] args) {

        hashMatch test = new hashMatch();
        Hashtable<String, String> abbreviationMap = new Hashtable<>();

        // Call input from Txt function to receive input in list
        String tweetsList = inputTweetFromTxt("./Sample_Txt_Input.txt");

        // Call input from Txt abbreviation list
        abbreviationMap = inputAbbrFromTxt(abbreviationMap,"./Sample_Txt_Abbr.txt");

        // // add some abbreviations and their corresponding phrases to the hash table
        // abbreviationMap.put("ASAP", "As Soon As Possible");
        // abbreviationMap.put("FYI", "For Your Information");
        // abbreviationMap.put("ETA", "Estimated Time of Arrival");
        // abbreviationMap.put("BTW", "By The Way");
        // abbreviationMap.put("LOL", "Laugh out loud");
        // abbreviationMap.put("IMO", "In my opinion");
        // abbreviationMap.put("RN", "Right now");
        // abbreviationMap.put("SUS", "Suspicious");
        // abbreviationMap.put("FML", "F*** my life");
        // abbreviationMap.put("GOAT", "Greatest of all time");

        // for (String tweet : tweetsList) {
            System.out.println("\nOriginal String: " + tweetsList);
            System.out.print("\nNew String: ");
            test.swap(tweetsList, abbreviationMap);
        // }
    }
}