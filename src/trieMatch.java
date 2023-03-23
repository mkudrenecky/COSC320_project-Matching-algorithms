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
public class Trie {
	
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
	
	// Returns true if key presents in trie, else false
	 static boolean search(String key)
	{
		int level;
		int length = key.length();
		int index;
		TrieNode pCrawl = root;
	
		for (level = 0; level < length; level++)
		{
			index = key.charAt(level);
	
			if (pCrawl.children[index] == null)
				return false;
	
			pCrawl = pCrawl.children[index];
		}
	
		return (pCrawl.isEndOfWord);
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

	// public static StringBuilder swap(String inputString) {
    //     StringBuilder newStr = new StringBuilder();
    //     String[] wordArr = inputString.split(" |,|\\.|!|\\?|\\(|\\)|\\{|\\}|\\[|\\]");

    //     for (String word : wordArr) {
    //         if (search(word)) {
    //             word = value;
    //         }
    //         newStr.append(word + " ");
    //     }
    //     System.out.println(newStr);
    //     return newStr;
    // }
	
	// Driver
	public static void main(String args[])
	{

		String output[] = {"Not present in trie", "Present in trie"};
	
	
		root = new TrieNode();

		 buildTrie("https://raw.githubusercontent.com/krishnakt031990/Crawl-Wiki-For-Acronyms/master/AcronymsFile.csv");
		 if(search("AAP") == true)
		 	System.out.println("AAP --- " + output[1]);
		else System.out.println("AAP --- " + output[0]);

		if(search("hello") == true)
		 	System.out.println("hello --- " + output[1]);
		else System.out.println("hello --- " + output[0]);
	
 }
}
// This code is contributed by Sumit Ghosh

}