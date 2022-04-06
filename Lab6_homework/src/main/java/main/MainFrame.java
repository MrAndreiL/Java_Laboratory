package main;

import config.Config;

import javax.swing.*;

import static java.awt.BorderLayout.*;

public class MainFrame extends JFrame {
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
        ControlPanel controlPanel = new ControlPanel(this);
        add(controlPanel, SOUTH);
        pack();
    }
}
