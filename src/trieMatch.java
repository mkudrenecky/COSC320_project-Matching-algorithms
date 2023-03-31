import java.net.URL;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.*;

public class trieMatch{

// Java implementation of search and insert operations
// on Trie
static class Trie {
	
	// Alphabet size (# of symbols)
	static final int ALPHABET_SIZE = 256;
	
	// trie node
	static class TrieNode
	{
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];
	
		// isEndOfWord is true if the node represents
		// end of a word
		boolean isEndOfWord;
		String value; // corresponding phrase at root node
		
		TrieNode(){
			isEndOfWord = false;
			for (int i = 0; i < ALPHABET_SIZE; i++)
				children[i] = null;
			value="";
		}
	};
	
	static TrieNode root;
	
	// If not present, inserts key into trie
	// If the key is prefix of trie node,
	// just marks leaf node
	static void insert(String key, String value)
	{
		int level;
		int length = key.length();
		int index;
	
		TrieNode pCrawl = root;
	
		for (level = 0; level < length; level++)
		{
			index = key.charAt(level);
			if (pCrawl.children[index] == null)
				pCrawl.children[index] = new TrieNode();
	
			pCrawl = pCrawl.children[index];
		}
	
		// mark last node as leaf
		pCrawl.isEndOfWord = true;
		pCrawl.value = value; // set value at leaf node to corresponding phrase!
	}
	
	// Returns value if key presents in trie, else null
	 static String search(String key)
	{
		int level;
		int length = key.length();
		int index;
		TrieNode pCrawl = root;
	
		for (level = 0; level < length; level++)
		{
			index = key.charAt(level);
	
			if (pCrawl.children[index] == null)
				return null;
	
			pCrawl = pCrawl.children[index];
		}
	
		return (pCrawl.value);
	}

	public static void buildTrie(String theUrl) {
		
        int count = 0;
		
		try {
			URL url = new URL(theUrl); 
			URLConnection urlConnection = url.openConnection(); 

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;

			while ((line = bufferedReader.readLine()) != null && count<50) {
				String[] abbrArr = line.split("-");
                if(abbrArr[0]!=null && abbrArr[1]!=null){
                insert(abbrArr[0], abbrArr[1]);
                System.out.println(abbrArr[0] + "-" + abbrArr[1]);
                }
                count++;        //counter implemented to offset code reaching an abbreviation with no phrase which crashes
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static StringBuilder swap(String inputString) {
        StringBuilder newStr = new StringBuilder();
        String[] wordArr = inputString.split(" |,|\\.|!|\\?|\\(|\\)|\\{|\\}|\\[|\\]");

        for (String word : wordArr) {
            if (search(word)!=null) {
                word = search(word);
            }
            newStr.append(word + " ");
        }
        System.out.println(newStr);
        return newStr;
    }

	// method to read in data from txt file - provides workable data
    public static String inputTweetFromTxt(String filePath) {
        String longTweets = "";

        // this try/catch code block reads by line instead of word but loses structure of text - alternative to code block below
        try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
                longTweets += line;     
                //System.out.println(abbrArr[0] + "-" + abbrArr[1]);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

        // try (Scanner tweets = new Scanner(new FileInputStream(filePath));) {
        //     tweets.useDelimiter("");
		// 	while (tweets.hasNext()) {
		// 		String line = tweets.next();
		// 		longTweets += line;
		// 	}
        //     //System.out.println(longTweets);
        // } catch (IOException e) {
        //     System.out.println("Error");
        // }
        return longTweets;
    }
	
	// Driver
	
	public static void main(String args[])
	{

		String output[] = {"Not present in trie", "Present in trie"};
	
	
		root = new TrieNode();

		 buildTrie("https://raw.githubusercontent.com/krishnakt031990/Crawl-Wiki-For-Acronyms/master/AcronymsFile.csv");

		 // Testing functionality:
		 String result = search("AAP");
		 System.out.println("HERE IS THE RESULT: "+ result);
		 
		 String result2 = search("Hello");
		 System.out.println("HERE IS THE 2nd RESULT: "+ result2);

		 String tweetsList = inputTweetFromTxt("Sample_Txt_Input.txt");	
		 System.out.println("Here is the result using swap: ");
		 swap(tweetsList);
 }
}
}