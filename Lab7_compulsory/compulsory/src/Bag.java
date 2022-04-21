import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.Integer;
import java.lang.InterruptedException;

public class Bag {
    private Map<Tile, Integer> list;

    private boolean tileAvailable = true;

    private boolean extractAvailable = true;

    public Bag() {
        list = new HashMap<>();
        readTiles();
    }

    public synchronized void addTile(Tile tile, int number) {
        while (!tileAvailable) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Thread Stopped!");
            }
        }
        tileAvailable = false;
        if (!list.containsKey(tile)) {
            list.put(tile, number);
        } else { // the tile already exists => append to it
            list.put(tile, list.get(tile) + number);
        }
        tileAvailable = true;
        notifyAll();
    }

    private void readTiles() {
        // Open files.txt and add tiles and frequency.
        try (BufferedReader read = new BufferedReader(new FileReader("files/tiles.txt"))) {
            String line = read.readLine();
            while (line != null) {
                // Read file and split items: --leter --apparitions --points
                String[] splitLine = line.split(" ");
                Tile tile = new Tile(splitLine[0].charAt(0), Integer.parseInt(splitLine[2]));

                addTile(tile, Integer.parseInt(splitLine[1]));
                line = read.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void printTiles() {
        for (Tile tile : list.keySet()) {
            System.out.println(tile.toString());
        }
    }

    public synchronized List<Tile> extractTile(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        while (!extractAvailable) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Thread stopped!");
            }
        }
        extractAvailable = false;
        Random random = new Random();
        for (int i = 0; i < howMany; i++) {
            if (list.isEmpty()) {
                break;
            }

            // Randomly choose a tile.
            int randomTile = random.nextInt(list.size());
            Tile tile = (Tile) list.keySet().toArray()[randomTile];

            // Update the number and check if it is zero.
            list.put(tile, list.get(tile) - 1);
            if (list.get(tile) == 0) {
                list.remove(tile);
            }

            // Append to the returning list.
            extracted.add(tile);
        }
        extractAvailable = true;
        notifyAll();
        return extracted;
    }
}
