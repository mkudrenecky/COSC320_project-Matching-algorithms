import java.util.*;

public class hashMatch{

    Hashtable<Integer, String> abbrTable = new Hashtable<>();
    
    public void buildHashTable(String[] abbrList){
        for (String word : abbrList){
            int key = word.hashCode(); // we can use hashCode or not, can also just use the string as the key (as is done in the sample below)
            abbrTable.put(key, word);
        }
    }
    
    public StringBuilder swap(String inputString, Hashtable<String,String> table){
        StringBuilder newStr = new StringBuilder();
        String[] wordArr = inputString.split(" ");

        for (String word: wordArr){
            if (table.containsKey(word)){
                word = table.get(word);  
            }
            newStr.append(word + " ");
        }
        System.out.println(newStr);
        return newStr;
    }
    
    public static void main(String[] args){

        hashMatch test = new hashMatch();
        Hashtable<String, String> abbreviationMap = new Hashtable<>();

        // ASAP, is skipped as it is split on space and contains the "," 
        String sample = "Hello, BTW I need to get a new toothbrush ASAP, so I can clean my dirty teeth FYI";

        // add some abbreviations and their corresponding phrases to the hash table
        abbreviationMap.put("ASAP", "As Soon As Possible");
        abbreviationMap.put("FYI", "For Your Information");
        abbreviationMap.put("ETA", "Estimated Time of Arrival");
        abbreviationMap.put("BTW", "By The Way");

        System.out.println("Original String: " + sample);
        System.out.print("New String with abbrs swapped: ");
        test.swap(sample, abbreviationMap);

        // this code kind of works, the output appends all the words without spacing or punctuation so it needs
        // to be refined. However, the basics are more or less working. Feel free to alter

    }
}