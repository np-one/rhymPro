import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class TestTrie {
    public static void main(String args[]) {
       // Soundex soundex = new Soundex();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        Random randomGenerator = new Random();

        HashMap<String, ArrayList<String>> soundexMap = new HashMap<>();

        TrieUtils trie = new TrieUtils();
        String datapath = "/Users/prajpoot/Desktop/apple_assignment/google-10000-english-no-swears.txt";
        try {
            Scanner stdin = new Scanner(new File(datapath));
            while (stdin.hasNextLine()) {
                String word = stdin.nextLine().trim().toLowerCase();
                trie.insert(word);
         /*       String soundexCode = soundex.getShortcut(word).toLowerCase();
                if(soundexMap.containsKey(soundexCode)){
                    soundexMap.get(soundexCode).add(word);
                }
                else {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(word);
                    soundexMap.put(soundexCode,list);
                }*/
            }
            System.out.println("Done Indexing");
            while (true) {
                System.out.println("Enter Word: ");
                String word = reader.readLine();
//                word = soundex.getShortcut(word);
                String wordrev = new StringBuilder(word).reverse().toString().toLowerCase();

                int max = 0;
                for (int i = 0; i <= wordrev.length(); i++) {
                    if (trie.startsWith(wordrev.substring(0, i))) {
                        if (i > max) {
                            max = i;
                        }
                    }
                }
                if (max == 0) {
                    System.out.println("NONE");
                    continue;
                }
                boolean condition = true;
                ArrayList<String> rhymeWords = trie.getAllWords(wordrev.substring(0, max));
                while (condition) {
                    if ((rhymeWords.size() == 1 && rhymeWords.get(0).equals(word)) || rhymeWords.size() == 0) {
                        max = max - 1;
                        rhymeWords = trie.getAllWords(wordrev.substring(0, max));
                    } else {
                        condition = false;
                    }
                }
                if (rhymeWords.contains(word))
                    rhymeWords.remove(word);
                if (rhymeWords.size() == 0) {
                    System.out.println("NONE");
                } else {
                    int index = randomGenerator.nextInt(rhymeWords.size());
                    System.out.println((rhymeWords.get(index)));
                    /*System.out.println(soundexMap.get(rhymeWords.get(index)));*/
                }


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
