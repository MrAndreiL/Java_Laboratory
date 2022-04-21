
public class App {
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        Timekeeper timekeeper = new Timekeeper();
        game.addPlayer(new Player("Player 1", game));
        game.addPlayer(new Player("Player 2", game));
        game.addPlayer(new Player("Player 3", game));
        new Thread(timekeeper).start();
        game.play();
        System.out.println("Start!");
        while (!timekeeper.getState()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        game.stop();
        System.out.println("Stopped!");
        game.stats();
    }
}
