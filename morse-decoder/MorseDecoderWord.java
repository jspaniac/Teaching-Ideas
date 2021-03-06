import java.util.*;
import static java.util.Map.entry;
import java.io.*;

public class MorseDecoderWord {
    private Map<Character, String> charToRep;
    private Set<String> dict;
    private Map<String, String> wordToRep;

    public static final Map<Character, String> MORSE_CODE = Map.ofEntries(
            entry('a', ".-"), entry('b', "-..."), entry('c', "-.-."), entry('d', "-.."),
            entry('e', "."), entry('f', "..-."), entry('g', "--."), entry('h', "...."),
            entry('i', ".."), entry('j', ".---"), entry('k', "-.-"), entry('l', ".-.."),
            entry('m', "--"), entry('n', "-."), entry('o', "---"), entry('p', ".--."),
            entry('q', "--.-"), entry('r', ".-."), entry('s', "..."), entry('t', "-"),
            entry('u', "..-"), entry('v', "...-"), entry('w', ".--"), entry('x', "-..-"),
            entry('y', "-.--"), entry('z', "--.."),
            entry('1', ".----"), entry('2', "..---"), entry('3', "...--"), entry('4', "....-"),
            entry('5', "....."), entry('6', "-...."), entry('7', "--..."), entry('8', "---.."),
            entry('9', "----."), entry('0', "-----"), entry(' ', "/")
    );

    public static final Set<String> DICTIONARY = getDict();
    private static Set<String> getDict() {
        Set<String> dict = new HashSet<>();
        try {
            Scanner sc = new Scanner(new File("dict2.txt"));
            while (sc.hasNextLine()) {
                dict.add(sc.nextLine().toLowerCase().trim());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return dict;
    }

    public MorseDecoderWord(Map<Character, String> charToRep, Set<String> dict) {
        this.charToRep = charToRep;
        this.dict = dict;

        this.wordToRep = new TreeMap<>();
        for (String word : dict) {
            String rep  = "";
            for (int i = 0; i < word.length(); i++) {
                rep += charToRep.get(word.charAt(i));
            }
            wordToRep.put(word, rep);
        }
    }

    
    public Collection<String> decode(String input) {
        Stack<String> options = new Stack<>();
        decode(input, options, "");
        return options;
    }

    private void decode(String input, Stack<String> options, String sentence) {
        if (input.isEmpty()) {
            options.push(sentence);
        } else {
            for (String word : wordToRep.keySet()) {
                String rep = wordToRep.get(word);
                if (input.startsWith(wordToRep.get(word))) {
                    decode(input.substring(rep.length()), options, sentence + " " + word);
                }
            }
        }
    }
    

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        String input = sc.nextLine();

        MorseDecoder decoder = new MorseDecoder(MorseDecoder.MORSE_CODE, MorseDecoder.DICTIONARY);
        Collection<String> results = decoder.decode(input);
        for (String s : results) {
            System.out.println(s);
        }
        System.out.println();
    }
}