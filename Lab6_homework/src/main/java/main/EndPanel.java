package main;

import javax.swing.*;

public class EndPanel extends JPanel {
    final MainFrame frame;
    private int player;
    public EndPanel(MainFrame frame, int player) {
        this.frame = frame;
        this.player = player;
        if (this.player % 2 == 0) {
            redWon();
        } else {
            blueWon();
        }
    }

    private void blueWon() {
        JLabel label = new JLabel("Blue player won!");
        add(label);
    }

    private void redWon() {
        JLabel label = new JLabel("Red player won!");
        add(label);
    }
}
