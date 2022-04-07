package main;

import config.Config;

import javax.naming.ldap.Control;
import javax.swing.*;

import static java.awt.BorderLayout.*;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    public MainFrame() {
        super("My game");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        ConfigPanel configPanel = new ConfigPanel(this);
        add(configPanel, NORTH);
        pack();
    }

    public void addCanvasPanel(Config config) {
        DrawingPanel drawingPanel = new DrawingPanel(this, config);
        add(drawingPanel, CENTER);
        pack();
    }

    public void addControlPanel() {
        controlPanel = new ControlPanel(this);
        add(controlPanel, SOUTH);
        pack();
    }

    public void addEndPanel(int player) {
        EndPanel end = new EndPanel(this, player);
        remove(controlPanel);
        add(end, SOUTH);
        pack();
    }
}
