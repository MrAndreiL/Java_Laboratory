package main;

import javax.swing.*;
import config.Config;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends JPanel {
    private final MainFrame frame;
    private final Config config;
    JLabel label;
    JSpinner spinner1;
    JSpinner spinner2;
    JButton button;

    public ConfigPanel(MainFrame mainFrame) {
        this.frame  = mainFrame;
        this.config = new Config();
        init();
    }

    private void init() {
        label    = new JLabel("Grid size: ");
        spinner1 = new JSpinner(new SpinnerNumberModel(10, 4, 20, 1));
        spinner2 = new JSpinner(new SpinnerNumberModel(10, 4, 20, 1));

        // Add an action listener and bind it to button.
        button   = new JButton("Create");
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JButton button = (JButton) actionEvent.getSource();

                // Make button unclickable.
                button.setEnabled(false);

                // Update rows and columns in configuration and proceed with drawing the canvas.
                config.setColumns((Integer) spinner2.getValue());
                config.setRows((Integer) spinner1.getValue());
                frame.addCanvasPanel(config);
                frame.addControlPanel();
            }
        };
        button.addActionListener(al);

        add(label);
        add(spinner1);
        add(spinner2);
        add(button);
    }
}
