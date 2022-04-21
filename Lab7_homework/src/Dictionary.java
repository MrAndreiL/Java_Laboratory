import java.util.Set;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Dictionary {
    private final Set<String> dict;

    public Dictionary() {
        dict = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("files/words.txt"))) {
            String word = reader.readLine();
            while (word != null) {
                if (isValidWord(word.toUpperCase())) {
                    dict.add(word.toUpperCase());
                }
                word = reader.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean isValidWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (!('A' <= letter && letter <= 'Z')) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return dict.toString();
    }

    public Set<String> getDict() {
        return dict;
    }
}
