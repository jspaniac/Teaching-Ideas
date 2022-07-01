import java.util.*;
import static java.util.Map.entry;
import java.io.*;

public class MorseDecoderCharacter {
    private Map<Character, String> charToRep;
    private Set<String> dict;
    private Set<String> prefixes;

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

    public MorseDecoderCharacter(Map<Character, String> charToRep, Set<String> dict) {
        this.charToRep = charToRep;
        this.dict = dict;

        this.prefixes = new HashSet<>();
        for (String word : dict) {
            for (int i = 1; i <= word.length(); i++) {
                prefixes.add(word.substring(0, i));
            }
        }
    }

    
    public Collection<String> decode(String input) {
        Stack<String> options = new Stack<>();
        decode(input, options, "", "");
        return options;
    }

    private void decode(String input, Stack<String> options, String sentence, String word) {
        if (input.isEmpty() && word.isEmpty()) {
            options.push(sentence);
        } else {
            if (dict.contains(word)) {
                decode(input, options, sentence + " " + word, "");
            }

            for (Character c : charToRep.keySet()) {
                String rep = charToRep.get(c);
                if (input.startsWith(rep) && prefixes.contains(word + c)) {
                    decode(input.substring(rep.length()), options, sentence, word + c);
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