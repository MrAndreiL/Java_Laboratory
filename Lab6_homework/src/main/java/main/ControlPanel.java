package main;

import javax.swing.*;

public class ControlPanel extends JPanel {
    final MainFrame frame;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        JButton load = new JButton("Load");
        JButton save = new JButton("Save");
        JButton exit = new JButton("Exit");

        add(load);
        add(save);
        add(exit);
    }
}
