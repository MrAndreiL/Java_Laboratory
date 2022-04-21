import java.lang.Thread;
import java.util.Map;
import java.util.HashMap;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new Dictionary();
    private final Map<Player, Thread> players = new HashMap<>();

    public void addPlayer(Player player) {
        players.put(player, null);
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void play() {
        for (Player player : players.keySet()) {
            Thread t = new Thread(player);
            players.put(player, t);
            t.start();
        }
    }

    public void stop() {
        for (Thread t : players.values()) {
            t.interrupt();
        }
    }

    public void stats() {

    }
}
