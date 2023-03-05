import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class hashMatch {

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

    public Hashtable<String, String> buildTable(String theUrl) {
		Hashtable<String, String> abbrTable = new Hashtable<>();
        int count = 0;
		
		try {
			URL url = new URL(theUrl); 
			URLConnection urlConnection = url.openConnection(); 

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			
			while ((line = bufferedReader.readLine()) != null && count<50) {
				String[] abbrArr = line.split("-");
                if(abbrArr[0]!=null && abbrArr[1]!=null){
                abbrTable.put(abbrArr[0], abbrArr[1]);
                System.out.println(abbrArr[0] + "-" + abbrArr[1]);
                }
                count++;
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return abbrTable;
	}

    public Hashtable<String, String> buildTableFromFile(String filePath) {
		Hashtable<String, String> abbrTable = new Hashtable<>();
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				String[] abbrArr = line.split("-");
                if(abbrArr[0]!=null && abbrArr[1]!=null){
                abbrTable.put(abbrArr[0], abbrArr[1]);
                System.out.println(abbrArr[0] + "-" + abbrArr[1]);
                }
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return abbrTable;
	}

    public static void main(String[] args) {

        //hashMatch test = new hashMatch();
        hashMatch test2 = new hashMatch();
       // Hashtable<String, String> abbreviationMap = new Hashtable<>();
        Hashtable<String, String> table = new Hashtable<>();
        Hashtable<String, String> table2 = new Hashtable<>();

        // Call input from Excel function to receive input in list
        String tweetsList = inputTweetFromTxt("./Sample_Txt_Input.txt");

        // add some abbreviations and their corresponding phrases to the hash table
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
           // test2.swap(tweetsList, abbreviationMap);
            table = test2.buildTable("https://raw.githubusercontent.com/krishnakt031990/Crawl-Wiki-For-Acronyms/master/AcronymsFile.csv");
            test2.swap(tweetsList, table);

            table2 = test2.buildTableFromFile("./Sample_txt_Abbr2.txt");
            test2.swap(tweetsList, table2);
            System.out.println("we got here Mac");
        // }
    }
}