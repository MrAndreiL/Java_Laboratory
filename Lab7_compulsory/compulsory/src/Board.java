import java.util.List;
import java.util.ArrayList;

public class Board {
    private List<String> words;

    private boolean addAvailable = true;

    public Board() {
        words = new ArrayList<>();
    }

    public synchronized void addWord(Player player, String word) {
        while (!addAvailable) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Thread stopped!");
                player.deactivate();
            }
        }
        addAvailable = false;
        words.add(word);
        System.out.println(player.getName() + ":" + word);
        addAvailable = true;
        notifyAll();
    }

    @Override
    public String toString() {
        return words.toString();
    }
}
