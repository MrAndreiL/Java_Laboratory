import java.time.Instant;
import java.time.Duration;
import java.lang.Runnable;
import java.lang.Thread;
import java.lang.InterruptedException;

public class Timekeeper implements Runnable {
    private boolean isUp = false;

    @Override
    public void run() {
        int timeLimit = 30;
        while (timeLimit > 0) {
            Instant start = Instant.now();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            if (timeLimit % 5 == 0) {
                System.out.println("5 seconds have passed!");
            }
            timeLimit -= timeElapsed.toSeconds();
        }
        isUp = true;
    }

    public boolean getState() {
        return isUp;
    }
}
