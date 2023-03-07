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

    // method to compare the data and the hashtable - swaps abbreviations with corresponding phrases
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

    // method to read in data from txt file - provides workable data
    public static String inputTweetFromTxt(String txtFilePath) {
        String longTweets = "";
        int count=0;
        try (Scanner tweets = new Scanner(new FileInputStream(txtFilePath));) {
            tweets.useDelimiter("");
			while (tweets.hasNext() && count<50000) {
				String line = tweets.next();
				longTweets += line;
                count++;
			}
            //System.out.println(longTweets);
        } catch (IOException e) {
            System.out.println("Error");
        }return longTweets;
    }

    // method to build hash table using data from the abbreviations URL
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
                //System.out.println(abbrArr[0] + "-" + abbrArr[1]);
                }
                count++;        //counter implemented to offset code reaching an abbreviation with no phrase which crashes
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return abbrTable;
	}

    // method to build hash table from input file
    public Hashtable<String, String> buildTableFromFile(String filePath) {
		Hashtable<String, String> abbrTable = new Hashtable<>();
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				String[] abbrArr = line.split("-");     
                abbrTable.put(abbrArr[0], abbrArr[1]);
                //System.out.println(abbrArr[0] + "-" + abbrArr[1]);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return abbrTable;
	}

    public static void main(String[] args) {

        // instantiate class and two hashtables 
        hashMatch test2 = new hashMatch();
        Hashtable<String, String> table = new Hashtable<>();
        Hashtable<String, String> table2 = new Hashtable<>();

        // Call input from Excel function to receive input in list
        String tweetsList = inputTweetFromTxt("./Sample_Txt_Input.txt");
        String partialData = inputTweetFromTxt("./dataset.csv");

            System.out.println("\nOriginal String: " + tweetsList);

            table = test2.buildTable("https://raw.githubusercontent.com/krishnakt031990/Crawl-Wiki-For-Acronyms/master/AcronymsFile.csv");
        
            // comparison done with hashtable built from text file.
            System.out.println("New string using txt file table:");
            table2 = test2.buildTableFromFile("./Sample_txt_Abbr2.txt");
            
            // this call uses table built from URL - only 'AAC' is swapped, as the count reaches 50 and the build ceases - this is to avoid the null value which crashes
            // the swap function and hashtable are working, but the null value needs to fixed
            System.out.println("New string using URL build table:");
            test2.swap(tweetsList,table);

            // call to view out of partial data set with abbr table from text file (50000 chars)
            System.out.println("Test with partial data and txt table:");
            test2.swap(partialData, table2);

            // call to view output of partial data set with abbr table from URL file (50000 chars)
            System.out.println("Test with partial data and URL table:");
            test2.swap(partialData, table);


    }
}