import java.lang.Runnable;
import java.util.List;
import java.lang.Thread;
import java.lang.InterruptedException;

public class Player implements Runnable {
    private final String name;
    private Game game;
    private int extNumber;
    private List<Tile> extractedAux = null;

    public Player(String name, Game game) {
        this.name = name;
        this.extNumber = 7;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private String isValidWord(List<Tile> wordList) {
        // 1. Create frequency array based on the wordList.
        int[] wordFreq = new int[27];
        for (Tile tile : wordList) {
            wordFreq[tile.getLetter() - 'A']++;
        }

        // 2. Iterate through dictionary, create frequency array for each and compare.
        int[] freq = new int[27];
        for (String word : game.getDictionary().getDict()) {
            // Iterate through the word.
            for (int i = 0; i < word.length(); i++) {
                freq[word.charAt(i) - 'A']++;
            }

            // Check if enough letters exist.
            boolean isEqual = true;
            for (int i = 0; i < 26; i++) {
                if (freq[i] != wordFreq[i]) {
                    isEqual = false;
                    break;
                }
            }
            if (isEqual) {
                return word;
            }

            // Re-initialize frequency array.
            for (int i = 0; i < 26; i++) {
                freq[i] = 0;
            }
        }
        return null;
    }

    private boolean areSameList(List<Tile> list1, List<Tile> list2) {
        // Frequency lists for both.
        int freq1[] = new int[27];
        for (Tile tile : list1) {
            freq1[tile.getLetter() - 'A']++;
        }
        int freq2[] = new int[27];
        for (Tile tile : list2) {
            freq2[tile.getLetter() - 'A']++;
        }

        for (int i = 0; i < 26; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean submitWord() {
        List<Tile> extracted = game.getBag().extractTile(extNumber);
        if (extracted.isEmpty()) {
            return false;
        }

        if (extractedAux == null) {
            extractedAux = extracted;
        } else {
            if (areSameList(extracted, extractedAux)) {
                return false;
            } else {
                extractedAux = extracted;
            }
        }

        // Create a word with all the extracted tiles;
        String word = isValidWord(extracted);
        if (word != null) {
            game.getBoard().addWord(this, word);
            extNumber = word.length();
        } else if (game.getDictionary().getDict().size() > 7) {
            // put the tiles back in the bag.
            for (Tile tile : extracted) {
                game.getBag().addTile(tile, 1);
            }
        }

        // Make the player sleep for 50ms
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            System.out.println("Thread stopped!");
        }
        return true;
    }

    @Override
    public void run() {
        while (submitWord()) {

        }
    }
}
